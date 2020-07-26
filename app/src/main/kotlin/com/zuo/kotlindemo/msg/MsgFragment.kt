package com.zuo.kotlindemo.msg

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zuo.kotlindemo.MainViewModel
import com.zuo.kotlindemo.R
import kotlinx.android.synthetic.main.fragment_msg.*
import kotlinx.android.synthetic.main.header_view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MsgFragment : Fragment(), View.OnClickListener {
    private val msgList = ArrayList<MsgBean>()
    lateinit var adapter: MsgAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_msg, container, false)
    }


    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        //获取 View Model 的数据
        val navController = findNavController(this)
        val mainViewModel =
            ViewModelProvider(navController.getViewModelStoreOwner(navController.graph.id)).get(
                MainViewModel::class.java
            )
        val toast = Toast.makeText(
            this.context,
            this::class.java.name + " 取到 " + mainViewModel.commentData,
            Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    private fun initView() {
        title.setText("消息界面")
        back.setOnClickListener(this)
        initMsg()
        //bundle 获取
        val bundle = arguments
        bundle?.let {
            val msg = bundle.getString("msg")
            msg?.let {
                msgList.add(MsgBean(msg, MsgBean.TYPE_RECEIVED))
            }
        }
        adapter = MsgAdapter(msgList)
        val linearLayoutManager = LinearLayoutManager(activity)
        rv.layoutManager = linearLayoutManager
        rv.adapter = adapter
        btn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            back -> {
                findNavController(this).popBackStack()
            }
            btn -> {
                val content = et.text.toString().trim()
                if (content.isNotEmpty()) {
                    msgList.add(MsgBean(content, MsgBean.TYPE_SEND))
                    adapter.notifyItemInserted(msgList.size - 1)
                    rv.scrollToPosition(msgList.size - 1)
                    et.setText("")
                    if (content.contains("hi")) {
                        backMsgDelay()
                    }
                }
            }
        }
    }


    private fun backMsgDelay() {
        val job = Job()
        val coroutineScope = CoroutineScope(job);
        coroutineScope.launch {
            delay(1000)
            msgList.add(MsgBean("You're handsome", MsgBean.TYPE_RECEIVED))
            Handler(Looper.getMainLooper()).post(Runnable {
                adapter.notifyItemInserted(msgList.size - 1)
                rv.scrollToPosition(msgList.size - 1)
            })
        }
    }

    private fun initMsg() {
        msgList.add(MsgBean("Hello guy!", MsgBean.TYPE_RECEIVED))
        msgList.add(MsgBean("Hello who is what?", MsgBean.TYPE_SEND))
        msgList.add(MsgBean("My name is Tony,Nice talking to you ", MsgBean.TYPE_RECEIVED))
    }
}