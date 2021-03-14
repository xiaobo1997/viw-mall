package com.viw.viwmall.seckill.interceptor;

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
 * @Date: 2021/3/14 23:52
 * @description:
 */


@Component
public class LoginUserInterceptor implements HandlerInterceptor {


    public static ThreadLocal<MemberRespVo> loginUser = new ThreadLocal<>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //  /order/order/status/2948294820984028420
        String uri = request.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean match = antPathMatcher.match("/kill", uri);

        if(match){
            MemberRespVo attribute = (MemberRespVo) request.getSession().getAttribute(AuthServerConstant.LOGIN_USER);
            if(attribute!=null){
                loginUser.set(attribute);
                return true;
            }else {
                //没登录就去登录
                request.getSession().setAttribute("msg","请先进行登录");
                response.sendRedirect("http://auth.viwmall.com/login.html");
                return false;
            }
        }

        return true;



    }
}

