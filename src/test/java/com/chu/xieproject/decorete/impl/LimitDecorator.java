package com.chu.xieproject.decorete.impl;

import com.chu.xieproject.decorete.AbstractDecorator;
import com.chu.xieproject.decorete.GatewayComponent;

/**
 * @title: LimitDecorator
 * @author: xiezhiqiang
 * @date 2020/3/24 10:08
 */
public class LimitDecorator extends AbstractDecorator {

    public LimitDecorator(GatewayComponent gateway) {
        super(gateway);
    }

    @Override
    public void service() {
        super.service();
        System.out.println("第二步：网关Limit");
    }
}
