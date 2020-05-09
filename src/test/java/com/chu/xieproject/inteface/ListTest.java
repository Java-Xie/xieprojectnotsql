package com.chu.xieproject.inteface;

import com.spire.ms.System.Collections.ArrayList;

import java.util.Arrays;
import java.util.List;

/**
 * @title: ListTest
 * @author: xiezhiqiang
 * @date 2020/5/8 15:34
 */
public class ListTest {
    public static void main(String[] args) {
        String[] arr = {"测试1","测试2","测试3"};
        List list4 = Arrays.asList(arr);
        list4.set(1,"新测试11");
        arr[2] = "数组新测试";
        Object[] arrList = list4.toArray();
        arrList[1] = "集合转化数组";
        System.out.println(list4.getClass().getName() + "@" + Integer.toHexString(list4.hashCode()));
        System.out.println(arr.getClass().getName() + "@" + Integer.toHexString(arr.hashCode()));
        list4.forEach(System.out::println);
        for (int i = 0; i < arrList.length; i++) {
            System.out.println(arrList[i]);
        }
    }
}
