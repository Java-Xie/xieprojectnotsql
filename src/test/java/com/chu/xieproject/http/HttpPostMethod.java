package com.chu.xieproject.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.File;
import java.io.IOException;

/**
 * @title: HttpUpload
 * @author: xiezhiqiang
 * @date 2020/5/20 10:35
 */
public class HttpPostMethod {

    // httpclient  HttpPostMethod  发送post请求
    public static void main(String[] args) {
        File file = new File("C:\\Users\\xie\\Desktop\\outputFile.PDF");
        PostMethod postMethod = new PostMethod("http://localhost:8080/register");
        HttpClient httpClient = new HttpClient();
        // 通过以下方法可以模拟页面参数提交
        postMethod.addParameter("name","客户端名称");
        postMethod.addParameter("pwd","客户端密码");
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
