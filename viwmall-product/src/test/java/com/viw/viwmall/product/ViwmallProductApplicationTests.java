package com.viw.viwmall.product;

//import com.aliyun.oss.OSSClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.UUID;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ViwmallProductApplicationTests {

   // @Autowired
  //  private BrandService brandService;
   @Autowired
   StringRedisTemplate stringRedisTemplate;

//    @Autowired
//    private OSSClient ossClient;

    @Autowired
    RedissonClient redissonClient;

    @Test
    public void testRedissionClient(){
        System.out.println(redissonClient);
        //org.redisson.Redisson@3ba46845
    }

    @Test
    public void uploadImg() throws FileNotFoundException {
//        FileInputStream fileInputStream = new FileInputStream("D:\\doc\\1.png");
//        ossClient.putObject("xiaobo-project","001.png",fileInputStream);
//        ossClient.shutdown();
//        System.out.println("OK");
    }

    @Test
    public void contextLoads() {
//        BrandEntity brandEntity = new BrandEntity();
//        brandEntity.setDescript("测试品牌1");
//        brandEntity.setName("小米");
//        brandService.save(brandEntity);
//        System.out.println("保存成功");
    }


    @Test
    public void testStringRedis(){
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        stringValueOperations.set("testtime",new Date().toString());
        System.out.println(stringRedisTemplate.opsForValue().get("testtime"));
        //Fri Mar 05 00:13:51 CST 2021
    }


    @Test
    public void teststringRedisTemplate(){
        /**
         *
         */
        //hello   world
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        //保存
        ops.set("hello","world_"+ UUID.randomUUID().toString());
        //查询
        String hello = ops.get("hello");
        System.out.println("之前保存的数据是："+hello);

    }

}
