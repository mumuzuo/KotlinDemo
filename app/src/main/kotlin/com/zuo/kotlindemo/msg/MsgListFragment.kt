package com.zuo.kotlindemo.msg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.zuo.kotlindemo.R
import kotlinx.android.synthetic.main.fragment_list_msg.*
import kotlinx.android.synthetic.main.header_view.*


class MsgListFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_msg, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        title.setText("消息列表")
        back.setOnClickListener(this)
        connect.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            back -> {
                findNavController(this).popBackStack()
            }
            connect -> {
                findNavController(this)
                    .navigate(R.id.action_msgListFragment_to_msgFragment)
            }
        }
    }


}