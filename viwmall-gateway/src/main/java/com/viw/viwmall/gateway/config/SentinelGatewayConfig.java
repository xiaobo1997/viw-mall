package com.viw.viwmall.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.fastjson.JSON;
import com.viw.common.exception.BizCodeEnume;
import com.viw.common.utils.R;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/16 0:13
 * @description: 自定义网关流控返回数据
 */

@Configuration
public class SentinelGatewayConfig {

    //TODO 响应式编程
    //GatewayCallbackManager
    public SentinelGatewayConfig(){
        GatewayCallbackManager.setBlockHandler(new BlockRequestHandler(){
            //网关限流了请求，就会调用此回调  Mono Flux
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable t) {

                R error = R.error(BizCodeEnume.TOO_MANY_REQUEST.getCode(), BizCodeEnume.TOO_MANY_REQUEST.getMsg());
                String errJson = JSON.toJSONString(error);

//                Mono<String> aaa = Mono.just("aaa");
                Mono<ServerResponse> body = ServerResponse.ok().body(Mono.just(errJson), String.class);
                return body;
            }
        });

//        FlowRule flowRule = new FlowRule();
//        flowRule.setRefResource("gulimall_seckill_route");
////        flowRule.set
//        FlowRuleManager.loadRules(Arrays.asList(flowRule));
    }

}
