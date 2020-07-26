package com.zuo.kotlindemo.learn

import com.zuo.kotlindemo.utils.start

/**
 * kotlin 学习，main 函数
 *
 * @author zuozhijie
 * @date 2020/7/1 11:47
 */
fun main() {

    /*if ("hello world".startsWith("hello")) {
        println("is true")
    }*/


    if ("hello world" start "hello") {
        println("is true")
    }
    println(getObjectType<String>())
    println(getObjectType<Int>())
}


inline fun <reified T> getObjectType() = T::class.java
