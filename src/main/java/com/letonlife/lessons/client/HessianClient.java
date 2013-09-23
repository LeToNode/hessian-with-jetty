package com.letonlife.lessons.client;

import com.caucho.hessian.client.HessianProxyFactory;
import com.letonlife.lessons.service.Basic;

import java.io.*;


/**
 *
 * 客户端访问示例
 *
 * @author lt
 * @version 0.1
 */
public class HessianClient {
    public static void main(String[] args) throws Exception {
        HessianProxyFactory factory = new HessianProxyFactory();
        Basic basic = (Basic) factory.create(Basic.class,
                "http://localhost:8080/test");

        System.out.println("sayHello: " + basic.sayHello("leton"));

        InputStream in=null;

        try {
            in = new FileInputStream("C:/ideaIU-12.1.4.exe");  //大文件不支持
            basic.upload("some file", in);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            in.close();
        }

    }
}