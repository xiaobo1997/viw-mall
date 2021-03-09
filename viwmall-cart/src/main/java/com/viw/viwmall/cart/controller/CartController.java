package com.viw.viwmall.cart.controller;

import com.viw.viwmall.cart.service.CartService;
import com.viw.viwmall.cart.vo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.ExecutionException;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/10 0:52
 * @description:
 */
@Controller
public class CartController {

    @Autowired
    CartService cartService;

    /**
     * 添加商品到购物车
     * RedirectAttributes ra
     *      ra.addFlashAttribute();将数据放在session里面可以在页面取出，但是只能取一次
     *      ra.addAttribute("skuId",skuId);将数据放在url后面
     * @return
     */
    @GetMapping("/addToCart")
    public String addToCart(@RequestParam("skuId") Long skuId,
                            @RequestParam("num") Integer num,
                            RedirectAttributes ra) throws ExecutionException, InterruptedException {


        cartService.addToCart(skuId,num);
//        model.addAttribute("skuId",skuId);
        ra.addAttribute("skuId",skuId);
        return "redirect:http://cart.viwmall.com/addToCartSuccess.html";
    }

    /**
     *
     * 浏览器有一个cookie；user-key；标识用户身份，一个月后过期；
     * 如果第一次使用jd的购物车功能，都会给一个临时的用户身份；
     * 浏览器以后保存，每次访问都会带上这个cookie；
     *
     * 登录：session有
     * 没登录：按照cookie里面带来user-key来做。
     * 第一次：如果没有临时用户，帮忙创建一个临时用户。
     * @return
     */
    @GetMapping("/cart.html")
    public String cartListPage(Model model) throws ExecutionException, InterruptedException {
        //1、快速得到用户信息，id，user-key
//        System.out.println(userInfoTo);

//        Cart cart = cartService.getCart();
//        model.addAttribute("cart",cart);
        return "cartList";
    }

}
