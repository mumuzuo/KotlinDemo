package com.zuo.kotlindemo.learn

import com.zuo.kotlindemo.learn.IStudy
import com.zuo.kotlindemo.learn.Person

/**
 * 定义一个学生类，包含学号、属性 ，另外学生同样具有姓名及年龄
 *
 * @author zuozhijie
 * @date 2020/7/1 16:19
 */
class Student(
    var sno: String = "0000",
    var grade: Int = 0,
    age: Int = 0,
    name: String = "unKnow"
) :
    Person(age, name), IStudy {

    companion object {
        @JvmStatic
        fun defaultInfo(): String {
            return "sno: 0000"
        }
    }

    override fun getInfo(): String {
        return super.getInfo() + " \n sno is ${sno} and grade is ${grade}"
    }

    override fun readBooks() {
        println(" ${name} is read book now ")
    }

    override fun doHomework() {
        println(" ${name} is do homework of ${grade}  grade")
    }
}