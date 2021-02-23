package com.viw.viwmall.product;

//import com.aliyun.oss.OSSClient;
import com.viw.viwmall.product.entity.BrandEntity;
import com.viw.viwmall.product.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ViwmallProductApplicationTests {

    @Autowired
    private BrandService brandService;


//    @Autowired
//    private OSSClient ossClient;

    @Test
    public void uploadImg() throws FileNotFoundException {
//        FileInputStream fileInputStream = new FileInputStream("D:\\doc\\1.png");
//        ossClient.putObject("xiaobo-project","001.png",fileInputStream);
//        ossClient.shutdown();
//        System.out.println("OK");
    }

    @Test
    public void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setDescript("测试品牌1");
        brandEntity.setName("小米");
        brandService.save(brandEntity);
        System.out.println("保存成功");
    }

}
