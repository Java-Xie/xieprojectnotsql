package com.chu.xieproject.service.testservices.imp;

import com.chu.xieproject.service.testservices.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImp1 implements TestService {

    @Override
    public Boolean Login(String loginname,String password) {
        if ("admin".equals(loginname)&&"123".equals(password)){
            System.out.println(loginname+"---"+password);
            return true;
        }
        System.out.println(loginname+"---"+password);
        return false;
    }
}
