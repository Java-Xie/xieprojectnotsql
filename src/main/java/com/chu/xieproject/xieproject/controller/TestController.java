package com.chu.xieproject.xieproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    /**
     * 测试
     * @return String
     */
    @RequestMapping("test")
    @ResponseBody
    public String test(){
        System.out.println("test");
        return "test";
    }

}
