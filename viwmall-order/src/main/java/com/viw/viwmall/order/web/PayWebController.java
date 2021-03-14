package com.viw.viwmall.order.web;

import com.alipay.api.AlipayApiException;
import com.viw.viwmall.order.config.AlipayTemplate;
import com.viw.viwmall.order.service.OrderService;
import com.viw.viwmall.order.vo.PayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/14 18:52
 * @description: 阿里支付 controller  订单确认页面点击支付
 */

@Controller
public class PayWebController {

    @Autowired
    AlipayTemplate alipayTemplate;

    @Autowired
    OrderService orderService;


    /**
     * 去支付
     * 1、将支付页让浏览器展示。
     * 2、支付成功以后，我们要跳到用户的订单列表页
     *
     * @param orderSn
     * @return
     * @throws AlipayApiException
     */
    @ResponseBody
    @GetMapping(value = "/payOrder", produces = "text/html")//  produces 表面返回的是  text/html 格式
    public String payOrder(@RequestParam("orderSn") String orderSn) throws AlipayApiException {

//        PayVo payVo = new PayVo();
//        payVo.setBody();//订单的备注
//        payVo.setOut_trade_no();//订单号
//        payVo.setSubject();//订单的主题
//        payVo.setTotal_amount();
        PayVo payVo = orderService.getOrderPay(orderSn);//构建要支付的VO
        //返回的是一个页面。将此页面直接交给浏览器就行
        String pay = alipayTemplate.pay(payVo); // pay是一段html
        System.out.println(pay);
        return pay;
    }
}