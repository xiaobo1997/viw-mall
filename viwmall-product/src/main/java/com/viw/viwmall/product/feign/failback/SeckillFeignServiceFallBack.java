package com.viw.viwmall.product.feign.failback;

import com.viw.common.exception.BizCodeEnume;
import com.viw.common.utils.R;
import com.viw.viwmall.product.feign.SeckillFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/7 17:53
 * @description:
 */
@Slf4j
@Component
public class SeckillFeignServiceFallBack implements SeckillFeignService {
    @Override
    public R getSkuSeckillInfo(Long skuId) {
        log.info("熔断方法调用...getSkuSeckillInfo");
        return R.error(BizCodeEnume.TOO_MANY_REQUEST.getCode(),BizCodeEnume.TOO_MANY_REQUEST.getMsg());
    }
}
