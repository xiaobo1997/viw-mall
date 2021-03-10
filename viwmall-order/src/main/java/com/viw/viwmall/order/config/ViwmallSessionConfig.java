package com.viw.viwmall.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/11 1:27
 * @description:
 */
@Configuration
public class ViwmallSessionConfig {

        @Bean
        public CookieSerializer cookieSerializer(){
            DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();

            cookieSerializer.setDomainName("viwmall.com");
            cookieSerializer.setCookieName("GULISESSION");

            return cookieSerializer;
        }

        @Bean
        public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
            return new GenericJackson2JsonRedisSerializer();
        }
}
