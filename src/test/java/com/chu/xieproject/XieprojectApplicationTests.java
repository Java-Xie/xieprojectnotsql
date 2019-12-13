package com.chu.xieproject;

import com.chu.xieproject.service.testservices.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XieprojectApplicationTests {

    private TestService testService;

    @Autowired
    XieprojectApplicationTests(TestService testService) {
        this.testService = testService;
    }

    @Test
    void contextLoads() {
        System.out.println("测试"+testService.Login("admin","12"));
    }

}
