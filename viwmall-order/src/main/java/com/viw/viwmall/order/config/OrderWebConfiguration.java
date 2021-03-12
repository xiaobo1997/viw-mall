package com.viw.viwmall.order.config;

import com.viw.viwmall.order.interceptor.LoginUserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/13 0:27
 * @description: spring mvc配置
 * 1.配置拦截器
 */
@Configuration
public class OrderWebConfiguration implements WebMvcConfigurer {


    @Autowired
    LoginUserInterceptor interceptor;

    /**
     * 当前项目中添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }
}
