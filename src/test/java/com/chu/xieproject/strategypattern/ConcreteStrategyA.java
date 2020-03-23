package com.chu.xieproject.strategypattern;

/**
 * @title: ConcreteStrategyA
 * @author: xiezhiqiang
 * @date 2020/3/19 10:52
 */
public class ConcreteStrategyA extends Strategy {

    @Override
    public void algorithmInterface() {
        System.out.println("算法A实现");
    }

}
