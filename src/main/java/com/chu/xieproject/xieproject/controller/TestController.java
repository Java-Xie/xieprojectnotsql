package com.chu.xieproject.xieproject.controller;

import com.chu.xieproject.xieproject.service.testservices.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    /**
     * 测试
     * @return test
     */
    @RequestMapping("test")
    @ResponseBody
    public String test(){
        return "test";
    }

    /**
     * 首页
     * @return index.html
     */
    @RequestMapping("index")
    public String index(String loginname,String password){
        if (testService.Login(loginname,password)){
            System.out.println("登录成功");
            return "index";
        }
        System.out.println("登录失败");
        return "loginerror";
    }

}
