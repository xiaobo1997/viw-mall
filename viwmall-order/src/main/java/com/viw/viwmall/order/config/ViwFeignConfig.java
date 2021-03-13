package com.viw.viwmall.order.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/13 1:58
 * @description: feign增强 拦截器  防止丢失请求头信息
 * 给容器中添加一个拦截器
 */
@Configuration
public class ViwFeignConfig {

    @Bean("requestInterceptor")
    public RequestInterceptor requestInterceptor(){
        return new RequestInterceptor(){
            /**
             *
             * @param template 真正发送的请求
             */
            @Override
            public void apply(RequestTemplate template) {
                System.out.println("feign远程调用之前先执行apply方法");
                /**
                 * 1、RequestContextHolder拿到刚进来的这个请求   当前toTrade中
                 * 的HttpServletRequest请求的所有属性 {@link com.viw.viwmall.order.web.OrderWebController#toTrade(Model, HttpServletRequest)}
                 */
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if(attributes!=null){
                    System.out.println("RequestInterceptor线程...."+Thread.currentThread().getId());
                    HttpServletRequest request = attributes.getRequest(); //老请求
                    if(request != null){
                        //同步请求头数据，Cookie 因为远程服务丢失了cookie无法得到其实用户已经登录的cookie
                        String cookie = request.getHeader("Cookie");
                        //给新请求同步了老请求的cookie
                        template.header("Cookie",cookie);
                    }
                }
            }
        };
    }

}
