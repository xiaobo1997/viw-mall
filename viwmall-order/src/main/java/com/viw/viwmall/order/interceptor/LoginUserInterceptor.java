package com.viw.viwmall.order.interceptor;

import com.viw.common.constant.AuthServerConstant;
import com.viw.common.vo.MemberRespVo;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/12 22:53
 * @description: 订单登录拦截
 */
@Component
public class LoginUserInterceptor implements HandlerInterceptor {


    public static ThreadLocal<MemberRespVo> loginUser = new ThreadLocal<>();

    /**
     * 前置拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /**
         * 路径匹配，否则feign 调用会被拦截
         */
        //  /order/order/status/2948294820984028420
        String uri = request.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean match = antPathMatcher.match("/order/order/status/**", uri);
        boolean match1 = antPathMatcher.match("/payed/notify", uri);
        if(match || match1){
            return true;
        }

        // 获取登录用户
        MemberRespVo attribute = (MemberRespVo) request.getSession().getAttribute(AuthServerConstant.LOGIN_USER);
        //说明登录了
        if(attribute!=null){
            loginUser.set(attribute);
            return true;
        }else {
            //没登录就去登录

            //提示消息放入session
            request.getSession().setAttribute("msg","请先进行登录");
            //重定向登录页
            response.sendRedirect("http://auth.viwmall.com/login.html");
            return false;
        }

    }

}
