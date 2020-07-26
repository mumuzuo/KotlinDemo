package com.zuo.kotlindemo

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.zuo.kotlindemo.utils.toActivity
import kotlinx.android.synthetic.main.activity_login.*


/**
 * 启动界面
 * @author zuozhijie
 * @date 2020/7/17 9:05
 */
class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<Button>(R.id.login).setOnClickListener(this)
        initView()
    }

    private fun initView() {
        val intent = intent
        intent?.let {
            val nameStr = intent.getStringExtra("name")
            val pswStr = intent.getStringExtra("psw")
            nameStr?.let {
                name.setText(nameStr)
            }
            pswStr?.let {
                psw.setText(pswStr)
            }
        }

    }

    override fun onClick(p0: View?) {
        when (p0) {
            login -> {
                toActivity<MainActivity>(this) {}
            }
        }
    }


}