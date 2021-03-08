package com.viw.viwmall.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/7 23:15
 * @description: 自定义webMvc的功能
 */

@Configuration
public class ViwmallWebConfig implements WebMvcConfigurer {

    /**
     * 视图映射
     * 直接映射-无需controller
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        /**
         *     @GetMapping("/login.html")
         *     public String loginPage(){
         *
         *         return "login";
         *     }
         */
//        registry.addViewController("/login.html").setViewName("login");
        //只是get请求能映射
        registry.addViewController("/reg.html").setViewName("reg");
    }
}
