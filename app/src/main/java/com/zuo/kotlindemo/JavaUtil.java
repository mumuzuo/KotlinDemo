package com.zuo.kotlindemo;


import com.zuo.kotlindemo.learn.Singleton;
import com.zuo.kotlindemo.learn.Student;

/**
 * java 类
 *
 * @author zuozhijie
 * @date 2020/7/6 14:51
 */
public class JavaUtil<T> {

    public void doSomething() {
        //调用伴生类中添加 @JvmStatic 注解的方法
        Student.defaultInfo();
        //调用单例类中添加 @JvmStatic 注解的方法
        Singleton.singletonTest();

    }

}
