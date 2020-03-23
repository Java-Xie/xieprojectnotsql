package com.chu.xieproject.istrategypattern;

/**
 * @title: Context
 * @author: xiezhiqiang
 * @date 2020/3/19 10:48
 */
public class Context {

    Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    //上下文接口
    public void contextInterface() {
        strategy.algorithmInterface();
    }

}
