package com.chu.xieproject.controller;

import com.chu.xieproject.service.testservices.TestService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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

    //注册
    @RequestMapping("/register")
    @ResponseBody
    public Map register(String name, String pwd){
        Map map = new HashMap();
        map.put("name","admin");
        map.put("pwd","123");
        return map;
    }

    /**
     * add
     * @return add
     */
    @RequestMapping("add")
    @ResponseBody
    public String add(){
        return "add";
    }

    /**
     * update
     * @return update
     */
    @RequestMapping("update")
    @ResponseBody
    public String update(){
        return "update";
    }

    //跳转未授权页面
    @RequestMapping("/unauthorizedPage")
    public String unauthorizedPage(){
        System.out.println("未授权");
        return "unauthorizedPage";
    }

    /**
     * 首页
     * @return index.html
     */
    @RequestMapping("index")
    public String index(String loginname,String password){
        System.out.println("登录成功");
        return "index";
    }

    @RequestMapping("/doLogin")
    public String doLogin(String name,String pwd){
        System.out.println("开始登录:"+name+"/"+pwd);
        //获取Subject对象[一个容器，存东西的]
        Subject subject= SecurityUtils.getSubject();
        //封装成对象
        UsernamePasswordToken token=new UsernamePasswordToken(name,pwd);
        //判断登录成功的依据就是此处有异常没
        try {
            subject.login(token);
            System.out.println("getPrincipal："+subject.getPrincipal());
        }catch (Exception e){
            System.out.println("账号密码错误");
            return "test";//登录页面
        }
        //成功去的页面
        return "index";
    }

}
