package com.chu.xieproject.inteface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @title: Test
 * @author: xiezhiqiang
 * @date 2020/5/8 13:48
 */
public class Test implements AInterface{
    public static void main(String[] args) {
        Test test = new Test();
        test.A();
        BInterface addition = str -> "测试"+ str;
        addition.B("str");
        String[] arr = {"测试1","测试2","测试3"};
        List<String> list = new ArrayList();
        List<String> listNew = new ArrayList();
        Collections.addAll(list, arr);
        list.forEach(addition::B);
        list.forEach(s -> listNew.add("新值"+ s));
        listNew.forEach(System.out::println);
    }
}
