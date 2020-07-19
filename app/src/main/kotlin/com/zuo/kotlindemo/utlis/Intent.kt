package com.zuo.kotlindemo.utlis

import android.content.Context
import android.content.Intent

/**
 * Kotlin 组件跳转帮助文件
 * @author zuozhijie
 * @date 2020/7/17 9:24
 */

/**
 * Activity 跳转帮助方法
 * @author zuozhijie
 * @date 2020/7/17 9:27
 */
inline fun <reified T> toActivity(context: Context, block: Intent.() -> Unit) {
    val intent = Intent(context, T::class.java)
    block(intent)
    context.startActivity(intent)
}