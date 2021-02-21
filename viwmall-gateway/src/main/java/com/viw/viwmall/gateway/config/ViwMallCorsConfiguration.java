package com.viw.viwmall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @Author: xhb
 * @Date: 2021/2/9 9:14
 * gateway跨域配置
 */
@Configuration
public class ViwMallCorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();

        //1、配置跨域
        corsConfiguration.addAllowedHeader("*");// 添加允许请求头
        corsConfiguration.addAllowedMethod("*");//添加允许请求方法，“*” 允许所有请求方式跨域
        corsConfiguration.addAllowedOrigin("*"); // 添加允许请求源
        corsConfiguration.setAllowCredentials(true);// 是否支持用户凭证 cookie，是否允许带cookie 跨域

        source.registerCorsConfiguration("/**",corsConfiguration);//注册跨域配置信息
        return new CorsWebFilter(source);
    }
}
