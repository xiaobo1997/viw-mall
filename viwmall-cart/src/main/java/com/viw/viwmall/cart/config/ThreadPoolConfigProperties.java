package com.viw.viwmall.cart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/10 1:24
 * @description:
 */
@ConfigurationProperties(prefix = "viwmall.thread")
@Component
@Data
public class ThreadPoolConfigProperties {
    private Integer coreSize;
    private Integer maxSize;
    private Integer keepAliveTime;
}
