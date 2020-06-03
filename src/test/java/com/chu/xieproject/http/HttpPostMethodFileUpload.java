package com.chu.xieproject.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @title: HttpPostMethodFileUpload
 * @author: xiezhiqiang
 * @date 2020/5/20 10:35
 */
public class HttpPostMethodFileUpload {

    // httpclient  HttpPostMethod  发送post请求
    public static void main(String[] args) {
        File file = new File("C:\\Users\\xie\\Desktop\\测试.txt");
        PostMethod postMethod = new PostMethod("http://localhost:8080/file/fileTest");
        HttpClient httpClient = new HttpClient();
        // 通过以下方法可以模拟页面参数提交
        postMethod.addParameter("name","客户端名称");
        postMethod.addParameter("pwd","客户端密码");
        try {
            Part[] parts = { new FilePart(file.getName(), file) };
            postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 设置参数编码
        postMethod.getParams().setContentCharset("UTF-8");
//        postMethod.addRequestHeader("Content-Type","application/json;charset=utf-8");
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
//        HttpMethodParams params = postMethod.getParams();
//        params.setContentCharset("UTF-8");
        try {
            int status = httpClient.executeMethod(postMethod);
            System.out.println(status);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
    }
}
