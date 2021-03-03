package com.viw.viwmall.product.web;

import com.viw.viwmall.product.entity.CategoryEntity;
import com.viw.viwmall.product.vo.Catelog2Vo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


import com.viw.viwmall.product.entity.CategoryEntity;
import com.viw.viwmall.product.service.CategoryService;
//import com.viw.viwmall.product.vo.Catelog2Vo;
//import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: xhb
 * @Email: xiaobo97@163.com
 * @gitee:https://gitee.com/xiaobo97
 * @Date: 2021/3/3 23:04
 * @description: 商品客户端首页
 */
@Controller
public class IndexController {

    @Autowired
    CategoryService categoryService;

//    @Autowired
//    RedissonClient redisson;

//    @Autowired
//    StringRedisTemplate redisTemplate;


    /**
     * 首页
     * @param model
     * @return
     */
    @GetMapping({"/","/index.html"})
    public String indexPage(Model model){

        System.out.println(""+Thread.currentThread().getId());
        //TODO 1、查出所有的1级分类
        List<CategoryEntity> categoryEntities =  categoryService.getLevel1Categorys();

        // 视图解析器进行拼串：
        // classpath:/templates/ +返回值+  .html
        model.addAttribute("categorys",categoryEntities);
        return "index";
    }

    /**
     * 返回分类数据VO给页面 封装成json
     * {
     *     “1”:[
     *          Object:{
     *              xxx
     *          }
     *     ],
     *     "2":[
     *          Object:{
     *              xxx
     *      }
     *     ]
     *     xxx
     * }
     * @return
     */
    //index/catalog.json
    @ResponseBody
    @GetMapping("/index/catalog.json")
    public Map<String, List<Catelog2Vo>> getCatalogJson(){
        Map<String, List<Catelog2Vo>> catalogJson = categoryService.getCatalogJson();
        return catalogJson;
    }


}
