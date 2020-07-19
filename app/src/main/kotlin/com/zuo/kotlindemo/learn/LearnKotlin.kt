package com.zuo.kotlindemo.learn

import com.zuo.kotlindemo.utlis.HttpCallbackListener
import com.zuo.kotlindemo.utlis.HttpUtil
import kotlinx.coroutines.*

/**
 * kotlin 学习，main 函数
 *
 * @author zuozhijie
 * @date 2020/7/1 11:47
 */
fun main() {
    runBlocking {
        val address = "http://www.baidu.com/"
        try {
            val response = HttpUtil.request(address)
            println(response)
        } catch (e: Exception) {
            println(e.message)
        }
    }
}

suspend fun repeatOut(times: Int) = coroutineScope {
    repeat(times) {
        launch {
            println(it)
        }
    }
}

inline fun <reified T> getObjectType() = T::class.java
