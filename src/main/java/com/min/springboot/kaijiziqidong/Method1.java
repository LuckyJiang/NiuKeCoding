package com.min.springboot.kaijiziqidong;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**https://www.cnblogs.com/lsgspace/p/10508180.html
 * 开机自启动方法1：
 *          将要执行的方法所在的类交个spring容器扫描(@Component)
 *          并且在要执行的方法上添加@PostConstruct注解或者静态代码块执行
 * @author jxm
 * @version 1.0
 * @date 2020/6/19 11:23
 */

@Component
public class Method1 {

    //静态代码块会在依赖注入后自动执行,并优先执行
    static{
        System.out.println("---static--");
    }
    /**
     *  @PostConstruct和@PreDestroy，这两个注解被用来修饰一个非静态的void（）方法
     * @PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行，init（）方法之前执行
     */
    @PostConstruct  //修改static
    public static void haha(){
        System.out.println("@Postcontruct’在依赖注入完成后自动调用");
    }

    @PostConstruct   //修饰非static
    public void haha1(){
        System.out.println("@Postcontruct’在依赖注入完成后自动调用");
    }
}
