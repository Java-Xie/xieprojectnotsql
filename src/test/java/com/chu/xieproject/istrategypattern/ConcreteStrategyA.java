package com.chu.xieproject.istrategypattern;

/**
 * @title: ConcreteStrategyA
 * @author: xiezhiqiang
 * @date 2020/3/19 10:52
 */
public class ConcreteStrategyA implements Strategy {

    @Override
    public void algorithmInterface() {
        System.out.println("算法A实现");
    }

}
