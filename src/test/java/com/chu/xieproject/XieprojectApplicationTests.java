package com.chu.xieproject;

import com.chu.xieproject.istrategypattern.ConcreteStrategyA;
import com.chu.xieproject.istrategypattern.ConcreteStrategyB;
import com.chu.xieproject.istrategypattern.Context;
import com.chu.xieproject.service.testservices.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XieprojectApplicationTests {

    private TestService testService;

    @Value("${chu.test.value}")
    private String chuValue;

    @Autowired
    XieprojectApplicationTests(TestService testService) {
        this.testService = testService;
    }

    @Test
    void contextLoads() {
        System.out.println("测试"+testService.Login("admin","123"));
    }

    @Test
    void testStrategyPattern() {
        System.out.println("测试策略模式");
        System.out.println(chuValue);
        Context contexta = new Context(new ConcreteStrategyA());
        contexta.contextInterface();
        Context contextb = new Context(new ConcreteStrategyB());
        contextb.contextInterface();
    }

}
