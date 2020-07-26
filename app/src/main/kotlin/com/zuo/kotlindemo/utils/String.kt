package com.zuo.kotlindemo.utils


/**
 * String 乘法运算：重复n次
 * @author zuozhijie
 * @date 2020/7/8 14:21
 */
operator fun String.times(n: Int): String = repeat(n)

infix fun String.start(str: String) = startsWith(str)