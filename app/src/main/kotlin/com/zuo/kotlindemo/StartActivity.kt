package com.zuo.kotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zuo.kotlindemo.utlis.HttpCallbackListener
import com.zuo.kotlindemo.utlis.HttpUtil
import com.zuo.kotlindemo.utlis.toActivity
import kotlinx.android.synthetic.main.activity_start.*

/**
 * 启动界面
 * @author zuozhijie
 * @date 2020/7/17 9:05
 */
class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        initView()
    }

    private fun initView() {
        hint_text.setOnClickListener {
            //1、原生跳转
            /* val intent = Intent(this, MainActivity::class.java)
             intent.putExtra("name", "admin")
             intent.putExtra("psw", "123456")
             startActivity(intent)*/
            //2、简化后的跳转
            toActivity<MainActivity>(this) {
                putExtra("name", "admin")
                putExtra("psw", "123456")
            }
        }

    }

}