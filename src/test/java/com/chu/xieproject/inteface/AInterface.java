package com.chu.xieproject.inteface;

/**
 * @title: AInterface
 * @author: xiezhiqiang
 * @date 2020/5/8 13:42
 */
public interface AInterface {
    default void A(){
        System.out.println("测试A方法");
    }
}
