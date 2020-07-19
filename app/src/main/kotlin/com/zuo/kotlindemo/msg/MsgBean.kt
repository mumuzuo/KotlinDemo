package com.zuo.kotlindemo.msg

/**
 *
 * @author zuozhijie
 * @date 2020/7/6 16:26
 */
data class MsgBean(var msg: String, var type: Int) {
    /**
     * 伴生类
     */
    companion object {
        //定义常量
        const val TYPE_RECEIVED = 0
        const val TYPE_SEND = 1
    }

}