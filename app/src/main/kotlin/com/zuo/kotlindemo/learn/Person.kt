package com.zuo.kotlindemo.learn

import kotlin.reflect.KProperty

/**
 * 定义一个人的类，包含名字、年龄属性 ，还有一个获取人信息的函数
 *
 * @author zuozhijie
 * @date 2020/7/1 15:57
 */
open class Person(var age: Int, var name: String) {

    open fun getInfo() = " user name is ${name}  and age is ${age}"

    operator fun <String> getValue(myClass: MyClass<String>, prop: KProperty<*>): Any {
        return getInfo()
    }

    operator fun setValue(myClass: MyClass<String>, prop: KProperty<*>, vlue: Any?) {

    }

}