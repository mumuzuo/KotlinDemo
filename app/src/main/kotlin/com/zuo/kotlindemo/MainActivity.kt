package com.zuo.kotlindemo

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import androidx.core.content.edit
import androidx.recyclerview.widget.LinearLayoutManager

import com.zuo.kotlindemo.msg.MsgAdapter
import com.zuo.kotlindemo.msg.MsgBean
import kotlinx.android.synthetic.main.activity_main.*

/**
 *
 * @author zuozhijie
 * @date 2020/7/6 15:55
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val msgList = ArrayList<MsgBean>()
    lateinit var adapter: MsgAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        initMsg()
        adapter = MsgAdapter(msgList)
        val linearLayoutManager = LinearLayoutManager(this)
        rv.layoutManager = linearLayoutManager
        rv.adapter = adapter
        btn.setOnClickListener(this)
        uav_data_back.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            btn -> {
                val content = et.text.toString().trim()
                if (content.isNotEmpty()) {
                    msgList.add(MsgBean(content, MsgBean.TYPE_SEND))
                    adapter.notifyItemInserted(msgList.size - 1)
                    rv.scrollToPosition(msgList.size - 1)
                    et.setText("")
                }
            }
            uav_data_back -> {
                val intent = Intent("com.landcloud.zxjg.uav.receive")
                val td_path = "/storage/emulated/0/KingoFile/1594121077681_.td"
                val bundle = Bundle()
                bundle.putString("TD_PATH", td_path)
                intent.putExtra("LAND_BUNDLE", bundle)
                intent.putExtra("appName", "_在线监管")
                intent.putExtra("appPackageName", "com.kingoit.onemap.investigation_sd_gdhctest")
                intent.putExtra("version", "1.1")
                startActivity(intent)
            }
        }
    }

    private fun initMsg() {
        msgList.add(MsgBean("Hello guy!", MsgBean.TYPE_RECEIVED))
        msgList.add(MsgBean("Hello who is what?", MsgBean.TYPE_SEND))
        msgList.add(MsgBean("My name is Tony,Nice talking to you ", MsgBean.TYPE_RECEIVED))
    }
}

