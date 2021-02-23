package com.viw.viwmall.thirdparty;

import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class ViwmallThirdPartyApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private OSSClient ossClient;

    @Test
    public void uploadImg() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("D:\\doc\\1.png");
        ossClient.putObject("xiaobo-project","001.png",fileInputStream);
        ossClient.shutdown();
        System.out.println("OK");
    }

}
