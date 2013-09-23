package com.letonlife.lessons.service;

import com.caucho.hessian.server.HessianServlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * 服务实现类
 *
 * @author leton li
 * @version 0.1
 */
public class BasicService extends HessianServlet implements Basic {

    public String sayHello(String name) {
        return "Hello! " + name;
    }

    @Override
    public void upload(String filename, InputStream in) {
        System.out.println(System.currentTimeMillis());
        int count = 0;

        try {
            byte[] buf = new byte[1024];


            FileOutputStream test = new FileOutputStream(new File("C:/apache-tomcat-6.0.35.zip1"));
            while ((count = in.read(buf)) != -1) {
                test.write(buf,0,count);
//                System.out.println("reading view in console=" + count);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("total buffers filled=" + count);
        System.out.println(System.currentTimeMillis());
    }

}