package com.viw.viwmall.product.config;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/6 1:58
 * @description: spring cache 自定义的配置
 */
@EnableConfigurationProperties(CacheProperties.class)
@Configuration
@EnableCaching
public class MyCacheConfig {


//    @Autowired
//    CacheProperties cacheProperties;

    /**
     * 配置文件中的东西没有用上；
     *
     * 1、原来和配置文件绑定的配置类是这样子的
     *      @ConfigurationProperties(prefix = "spring.cache")
     *      public class CacheProperties
     *
     * 2、要让他生效
     *      @EnableConfigurationProperties(CacheProperties.class)
     *
     * @return
     */
    @Bean
    RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties){


        // config.serializeKeysWith() 会new一个新的 RedisCacheConfiguration，需要让他引用config
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
//        config = config.entryTtl();
        // 定义用于对缓存键进行反序列化的SerializationPair 。
        config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        // 定义用于对缓存value进行反序列化的SerializationPair 。
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));


        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        //将配置文件中的所有配置都生效
        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            config = config.prefixKeysWith(redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }


        return config;
    }
}
