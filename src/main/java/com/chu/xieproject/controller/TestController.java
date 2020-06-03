package com.chu.xieproject.controller;

import com.chu.xieproject.service.testservices.TestService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    private final TestService testService;

    private static byte[] bufferdown;

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
     * 文件上传页面
     * @return fileupload.html
     */
    @RequestMapping("fileupload")
    public String fileupload(){
//        System.out.println("登录成功");
        return "fileupload";
    }

    /**
     * 文件上传
     * @return fileuploaded.html
     */
    @RequestMapping("fileuploaded")
    @ResponseBody
    public String fileuploaded(@RequestParam("file") MultipartFile file){
        System.out.println(file);
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
        byte[] buffer = new byte[0];
        try {
            buffer = file.getBytes();
            bufferdown = buffer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(buffer);
        System.out.println("文件上传成功");
        return "true";
    }

    /**
     * 文件下载
     * @return filedown.html
     */
    @RequestMapping("filedown")
    public void filedown(HttpServletResponse resp){
        System.out.println(bufferdown);
        InputStream inputStream = null;
        OutputStream os = null;
        resp.reset();
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition","attachment;filename=test.jpg");
        inputStream = new ByteArrayInputStream(bufferdown);
        byte[] buffer = new byte[1024];
        try {
            os = resp.getOutputStream();
            int len = 0;
            while ((len = inputStream.read(buffer)) > 0){
                os.write(buffer, 0, len);
            }
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null){
                    inputStream.close();
                }
                if (os != null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("文件下载");
    }

    //注册
    @RequestMapping("/register")
    @ResponseBody
    //配置跨域
    @CrossOrigin
    public Map register(HttpServletRequest request, String name, String pwd){
        Map map = new HashMap();
        map.put("name","admin");
        map.put("pwd","123");
        map.put("code", 200);
        System.out.println("访问到了controller,name为"+name+"密码为："+pwd);
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
