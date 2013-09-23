## Hessian与Jetty组合开发指南

### 简介

#### Hessian([官方网站](http://hessian.caucho.com/) - [维基百科](http://en.wikipedia.org/wiki/Hessian_(web_service_protocol) - [百度百科](http://baike.baidu.com/view/2255290.htm))

Hessian是一个二进制的Web服务框架,它使得开发web不再依赖一个大型的框架,也不需要学习新的协议方面知识. 由于它采用了二进制协议,使得它非常适合不需要其它扩展协议支持的二进制数据传输。

Hessian由Caucho技术有限公司开发,该公司已经发布了基于Java、Python、ActionScript和Adobe Flash实现的Hessian实现(基于Apache开源许可协议)。其它语言(C++、.NET C#、JavaScript、Perl、PHP、Ruby、Objective-C、D以及Erlang)的实现也以开源实现的方式进行了支持.

#### Jetty([官方网站](http://www.eclipse.org/jetty/‎) - [维基百科](http://en.wikipedia.org/wiki/Jetty_(web_server) - [百度百科](http://baike.baidu.com/view/1425008.htm))

Jetty 是一个开源的servlet容器，它为基于Java的web内容，例如JSP和servlet提供运行环境。Jetty是使用Java语言编写的，它的API以一组JAR包的形式发布。开发人员可以将Jetty容器实例化成一个对象，可以迅速为一些独立运行（stand-alone）的Java应用提供网络和web连接。

> 选择Hessian和Jetty组合起来开发web服务的主要原因有以下几点:
* 原有的产品是基于Java开发的普通应用,产品后续需要增加开放web服务的要求。考虑到开发进度和技术难度的关系，选择学习成本较低的Hessian和Jetty作为服务框架的开发主体。
* 从性能方面考虑，采用RPC协议的Hessian性能优于SOAP协议，这里的结果并没有经过本人的测试和论证，如果感兴趣，可以查看[此文](http://m.chinabyte.com/285/12221285_m.shtml).


### 开发一个简单的Web服务

1.准备开发环境
 
 这里我们使用Jetty作为应用内置的服务器启动，因此需要准备相应的依赖库:
 
 * jetty-continuation*.jar
 * jetty-http*.jar
 * jetty-io*.jar
 * jetty-security*.jar
 * jetty-server*.jar
 * jetty-servlet*.jar
 * jetty-servlets*.jar
 * jetty-util*.jar
 
可以前往前文的官方网站下载，然后载入classpath中。需要注意的是Jetty现在采用的三个稳定同时开发的过程，不同的版本支持的JVM的版本是不同，请注意选择(v9支持Java 7;v8支持最低Java 6;v7支持最低Java 5)。   
同时还需要在classpath中准备servlet-api和hessian相应的jar文件。   
当然也可以使用Maven pom来直接创建。

2.定义服务接口类和实现类

* Basic(服务接口定义)

```
public interface Basic {
    public String sayHello(String name);
}
```

* BasicService(服务实现)

```
public class BasicService extends HessianServlet implements Basic {
    public String sayHello(String name) {
        return "Hello! " + name;
    }
}
```

3.编写服务端

* ServiceMain(服务启动)

```
public class ServiceMain {
    public static void main(String[] args) {
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        server.setHandler(context);
        ServletHolder servletHolder = new ServletHolder(new BasicService());
        context.addServlet(servletHolder, "/test");
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
```

4.编写客户端

* HessianClient(客户端调用示例)

```
public class HessianClient {
    public static void main(String[] args) throws Exception {
        HessianProxyFactory factory = new HessianProxyFactory();
        Basic basic = (Basic) factory.create(Basic.class,
                "http://localhost:8080/test");
        System.out.println("sayHello: " + basic.sayHello("leton"));
    }
}
```

5.运行服务端和客户端

先后启动服务端和客户端运行后，客户端运行后将打印输出:`sayHello: Hello! leton`

### 结语

通过以上实践,可以发现使用Hessian和Jetty可以很容易的开发出一个基本的Web服务支持的应用，非常适合为普通的Java应用增加web服务和远程访问的支持.
