package com.viw.viwmall.member.web;

import com.alibaba.fastjson.JSON;
import com.viw.common.utils.R;
import com.viw.viwmall.member.feign.OrderFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/14 19:07
 * @description: 订单支付成功后 跳支 会员订单列表页面
 */

@Controller
public class MemberWebController {

    @Autowired
    OrderFeignService orderFeignService;

    /**
     * 订单分页查询
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("/memberOrder.html")
    public String memberOrderPage(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                  Model model, HttpServletRequest request){
        //获取到支付宝给我们传来的所有请求数据；
//        request。验证签名，如果正确可以去修改。


        //查出当前登录的用户的所有订单列表数据
        Map<String,Object> page =new HashMap<>();
        page.put("page",pageNum.toString());
        //
        R r = orderFeignService.listWithItem(page);
        System.out.println(JSON.toJSONString(r));
        model.addAttribute("orders",r);
        return "orderList";
    }
}
