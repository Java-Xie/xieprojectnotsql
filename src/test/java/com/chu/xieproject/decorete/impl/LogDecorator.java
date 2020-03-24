package com.chu.xieproject.decorete.impl;

import com.chu.xieproject.decorete.AbstractDecorator;
import com.chu.xieproject.decorete.GatewayComponent;

/**
 * @title: LogDecorator
 * @author: xiezhiqiang
 * @date 2020/3/24 10:10
 */
public class LogDecorator extends AbstractDecorator {

    public LogDecorator(GatewayComponent gateway) {
        super(gateway);
    }

    @Override
    public void service() {
        super.service();
        System.out.println("第三关：测试Log");
    }
}
