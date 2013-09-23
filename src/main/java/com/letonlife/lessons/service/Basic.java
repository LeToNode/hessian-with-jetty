package com.letonlife.lessons.service;

import java.io.InputStream;

public interface Basic {

    public String sayHello(String name);

    public void upload(String filename, InputStream data);
}