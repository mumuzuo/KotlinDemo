package com.zuo.kotlindemo.utils

/**
 * 回调接口的使用
 *
 * @author zuozhijie
 * @date 2020/7/20 20:26
 */
interface ICallBack<T> {
    fun onBack(t: T)
}