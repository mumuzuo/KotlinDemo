package com.zuo.kotlindemo.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.get
import com.zuo.kotlindemo.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), View.OnClickListener {
    //获取导航控制器
    private val navController: NavController
        get() {
            return findNavController(nav_host_fragment)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_view.setOnClickListener(this)
        uav_view.setOnClickListener(this)
        msg_view.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            uav_view -> {
                //跳转到无人机成果列表
                navController.navigate(R.id.action_mainFragment_to_uavResultFragment)
            }
            msg_view -> {
                //跳转到聊天界面
                navController.navigate(R.id.action_mainFragment_to_msgListFragment)
            }
            login_view -> {
                //跳转登录界面
                navController.navigate(R.id.action_mainFragment_to_loginActivity)
                /*activity?.let {
                    *//*val intent = Intent(it, LoginActivity::class.java)
                    intent.putExtra("name", "admin")
                    intent.putExtra("psw", "123456")
                    it.startActivity(intent)*//*
                    toActivity<LoginActivity>(it) {
                        putExtra("name", "admin")
                        putExtra("psw", "123456")
                    }
                }*/
            }
        }
    }


}