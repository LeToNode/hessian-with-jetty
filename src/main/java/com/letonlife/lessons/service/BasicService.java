package com.letonlife.lessons.service;

import com.caucho.hessian.server.HessianServlet;



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



}