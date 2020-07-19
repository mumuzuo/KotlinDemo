package com.zuo.kotlindemo.learn

import com.zuo.kotlindemo.learn.Person

/**
 * 当前类只有次构造函数，没有主构造函数
 *
 * @author zuozhijie
 * @date 2020/7/2 19:19
 */
class Student2 : Person {
    constructor(age: Int, name: String) : super(age, name)
}