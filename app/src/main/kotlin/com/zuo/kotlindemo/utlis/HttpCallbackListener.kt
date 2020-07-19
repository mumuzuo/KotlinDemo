package com.zuo.kotlindemo.utlis

/**
 * @author zuo
 * @date 2020/7/19 16:13
 */
interface HttpCallbackListener {

    fun onFinish(response: String)

    fun onError(e: Exception)
}