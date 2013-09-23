package com.letonlife.lessons.client;

import com.caucho.hessian.client.HessianProxyFactory;
import com.letonlife.lessons.service.Basic;




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


    }
}