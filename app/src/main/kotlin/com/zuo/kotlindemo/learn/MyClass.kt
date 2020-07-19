package com.zuo.kotlindemo.learn

/**
 *
 * @author zuozhijie
 * @date 2020/7/13 19:20
 */
class MyClass<T>(val helperSet: HashSet<T>) : Set<T> by helperSet {

    fun helloWorld() = println("helloWorld")

    val isLocal by Person(19,"dd")
}