package com.chu.xieproject.decorete;

/**
 * @title: AbstractDecorator
 * @author: xiezhiqiang
 * @date 2020/3/24 10:03
 */
public class AbstractDecorator extends GatewayComponent {

    private GatewayComponent gatewayComponent;

    public AbstractDecorator(GatewayComponent gateway) {
        this.gatewayComponent = gateway;
    }

    @Override
    public void service() {
        if (gatewayComponent != null) {
            gatewayComponent.service();
        }
    }

}
