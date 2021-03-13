package com.viw.viwmall.order.web;

import com.viw.common.exception.NoStockException;
import com.viw.viwmall.order.service.OrderService;
import com.viw.viwmall.order.vo.OrderConfirmVo;
import com.viw.viwmall.order.vo.OrderSubmitVo;
import com.viw.viwmall.order.vo.SubmitOrderResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/12 22:50
 * @description: 客户端订单controller
 */
@Controller
public class OrderWebController {



    @Autowired
    OrderService orderService;



    /**
     * 购物车去结算确认页
     *
     * @param model
     * @param request
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/toTrade")
    public String toTrade(Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {
        OrderConfirmVo confirmVo = orderService.confirmOrder();
        model.addAttribute("orderConfirmData", confirmVo);
        //展示订单确认的数据
        return "confirm";
    }

    /**
     * 下单功能：
     *      创建订单，验令牌，验价格，锁库存
     *      下单成功： 支付页
     *      下单失败： 回订单确认页面
     * @param vo
     * @return
     */
    @PostMapping("/submitOrder")
    public String submitOrder(OrderSubmitVo vo, Model model, RedirectAttributes redirectAttributes){


        try {
            SubmitOrderResponseVo responseVo = orderService.submitOrder(vo);
            //下单失败回到订单确认页重新确认订单信息
            System.out.println("订单提交的数据..."+vo);
            if(responseVo.getCode() == 0){
                //下单成功来到支付选择页
                model.addAttribute("submitOrderResp",responseVo);
                return  "pay";
            }else{
                String msg = "下单失败；";
                switch (responseVo.getCode()){
                    case 1:  msg += "订单信息过期，请刷新再次提交"; break;
                    case 2: msg+= "订单商品价格发生变化，请确认后再次提交"; break;
                    case 3: msg+="库存锁定失败，商品库存不足"; break;
                }
                redirectAttributes.addFlashAttribute("msg",msg);
                return "redirect:http://order.viwmall.com/toTrade";
            }
        }catch (Exception e){
            if(e instanceof NoStockException){
                String message = ((NoStockException) e).getMessage();
                redirectAttributes.addFlashAttribute("msg",message);
            }

            return "redirect:http://order.viwmall.com/toTrade";
        }


    }

}
