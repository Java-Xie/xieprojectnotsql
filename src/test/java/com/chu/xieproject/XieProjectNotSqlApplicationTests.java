package com.chu.xieproject;

import com.chu.xieproject.decorete.BasicComponentGateway;
import com.chu.xieproject.decorete.GatewayComponent;
import com.chu.xieproject.decorete.impl.LimitDecorator;
import com.chu.xieproject.decorete.impl.LogDecorator;
import com.chu.xieproject.istrategypattern.*;
import com.chu.xieproject.service.testservices.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XieProjectNotSqlApplicationTests {

    private TestService testService;

    @Value("${chu.test.value}")
    private String chuValue;

    @Autowired
    XieProjectNotSqlApplicationTests(TestService testService) {
        this.testService = testService;
    }

    @Test
    void contextLoads() {
        System.out.println("测试" + testService.Login("admin", "123"));
    }

    /**
     * 测试策略模式
     */
    @Test
    void testStrategyPattern() {
        System.out.println("测试策略模式");
        System.out.println(chuValue);
        Strategy strategy = IFactory.newStrategy("com.chu.xieproject.istrategypattern.ConcreteStrategyA");
        strategy.algorithmInterface();
    }

    /**
     * 测试装饰者模式
     */
    @Test
    void testDecorator() {
        System.out.println("测试装饰者模式");
        GatewayComponent gatewayComponent = new LimitDecorator(new LogDecorator(new BasicComponentGateway()));
        gatewayComponent.service();
    }

}
