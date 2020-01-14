package com.chu.xieproject.controller;

import com.chu.xieproject.entity.Users;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("restful")
public class RestfulController {

    /**
     * @PathVariable：取地址上面的值(如：restful/1中的1)
     */
    @GetMapping("{id}")
    public Object getOne(@PathVariable("id") Integer id){
        Users users = new Users();
        users.setUid(id);
        return users;
    }

    /**
     * @RequestParam：把参数注入该对象
     * value：前台传入的参数名称
     * defaultValue：该参数的默认值(为null,或不传该值时启用)
     * required：true为不传该参数则报错
     */
    @GetMapping
    public Object getList(@RequestParam(value = "rowsize",defaultValue = "10") Integer rowSize
            , @RequestParam(value = "page",defaultValue = "1") Integer page){
        List list = new ArrayList();
        list.add(rowSize);
        list.add(page);
        return list;
    }

    @PostMapping
    public Object postOne(@RequestBody String users){
        System.out.println(users);
        return users;
    }

}
