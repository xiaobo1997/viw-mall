package com.viw.viwmall.cart.interceptor;

import com.viw.common.constant.AuthServerConstant;
import com.viw.common.constant.CartConstant;
import com.viw.common.vo.MemberRespVo;
import com.viw.viwmall.cart.vo.UserInfoTo;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/10 0:55
 * @description:在执行目标方法之前，判断用户的登录状态。并封装传递(用户信息)给controller
 */

public class CartInterceptor implements HandlerInterceptor {

    /**
     * 同一个请求thread共享数据
     */
    public static ThreadLocal<UserInfoTo> threadLocal = new ThreadLocal<>();

    /**
     * 目标方法执行之前拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        UserInfoTo userInfoTo = new UserInfoTo();

        HttpSession session = request.getSession();
        //获取session中的userKey
        MemberRespVo member = (MemberRespVo) session.getAttribute(AuthServerConstant.LOGIN_USER);
        //如果userKey不为空 说明用户登录了
        if(member != null){
            //用户登录
            userInfoTo.setUserId(member.getId());
        }

        Cookie[] cookies = request.getCookies();
        //遍历cookie获取 userKey 的cookie
        if(cookies!=null && cookies.length>0){
            for (Cookie cookie : cookies) {
                //user-key
                String name = cookie.getName();
                if(name.equals(CartConstant.TEMP_USER_COOKIE_NAME)){
                    userInfoTo.setUserKey(cookie.getValue());
                    userInfoTo.setTempUser(true);
                }
            }
        }

        //如果没有临时用户一定分配一个临时用户
        if(StringUtils.isEmpty(userInfoTo.getUserKey())){
            String uuid = UUID.randomUUID().toString();
            userInfoTo.setUserKey(uuid);
        }
        //目标方法执行之前
        threadLocal.set(userInfoTo);
        return true;
    }

    /**
     * 业务执行之后；分配临时用户，让浏览器保存
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        UserInfoTo userInfoTo = threadLocal.get();

        //如果没有临时用户一定保存一个临时用户
        if(!userInfoTo.isTempUser()){
            //持续的延长临时用户的过期时间
            Cookie cookie = new Cookie(CartConstant.TEMP_USER_COOKIE_NAME, userInfoTo.getUserKey());
            cookie.setDomain("viwmall.com");
            cookie.setMaxAge(CartConstant.TEMP_USER_COOKIE_TIMEOUT);
            // 让浏览器保存cookie
            response.addCookie(cookie);
        }


    }
}
