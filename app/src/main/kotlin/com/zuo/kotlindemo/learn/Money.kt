package com.zuo.kotlindemo.learn

/**
 * Money 实体类 扩展类 extension class
 *
 * @author zuozhijie
 * @date 2020/7/8 10:11
 */
data class Money(val value: Double, val unit: String = "RMB") {
    //重载运算符 + , Money 对象相加
    operator fun plus(obj: Money): Money = Money(value + obj.value)
    //重载运算符 + ，Money 对象 和数值相加
    operator fun plus(money: Int): Money = Money(value + money)

}