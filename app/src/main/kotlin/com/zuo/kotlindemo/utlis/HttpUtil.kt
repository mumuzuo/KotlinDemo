package com.zuo.kotlindemo.utlis

import android.text.TextUtils
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.Exception
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @author zuo
 * @date 2020/7/19 15:54
 */
object HttpUtil {

    /**
     * 利用挂起函数，同步返回网络请求结果
     */
    suspend fun request(address: String): String {
        return suspendCoroutine {
            sendHttpRequest(address, object : HttpCallbackListener {
                override fun onFinish(response: String) {
                    it.resume(response)
                }

                override fun onError(e: Exception) {
                    it.resumeWithException(e)
                }
            })
        }
    }

    /**
     * 异步回调的方式返回网络请求结果
     */
    fun sendHttpRequest(address: String, iCallBack: HttpCallbackListener) {
        thread {
            var connection: HttpURLConnection? = null
            try {
                var response = StringBuilder()
                val url = URL(address)
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 10000
                connection.readTimeout = 10000
                val inputStream = connection.inputStream
                val reader = BufferedReader(InputStreamReader(inputStream))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                iCallBack.onFinish(response.toString())
            } catch (e: Exception) {
                iCallBack.onError(e)
            } finally {
                connection?.disconnect()
            }

        }
    }

}