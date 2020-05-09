package com.chu.xieproject.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @title: Test
 * @author: xiezhiqiang
 * @date 2020/5/8 10:24
 */
public class TimeTest {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.atStartOfDay());
        LocalDateTime localDateTime = localDate.atStartOfDay();
        System.out.println(localDate.plusDays(30));
        System.out.println(localDate.plusMonths(30));
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        System.out.println(localDate.plusDays(3).getDayOfWeek().getValue());
        System.out.println(localDate.plusDays(-3).isAfter(localDate));
    }
}
