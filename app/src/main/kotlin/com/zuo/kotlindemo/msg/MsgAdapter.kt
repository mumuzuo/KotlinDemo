package com.zuo.kotlindemo.msg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zuo.kotlindemo.R

/**
 * 消息列表适配器
 *
 * @author zuozhijie
 * @date 2020/7/6 15:54
 */
class MsgAdapter(val data: List<MsgBean>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == MsgBean.TYPE_RECEIVED) {
            LeftViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.msg_left_item,
                    parent,
                    false
                )
            )
        } else {
            RightViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.msg_right_item,
                    parent,
                    false
                )
            )
        }

    override fun getItemViewType(position: Int): Int = data[position].type

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msgBean = data[position]
        when (holder) {
            is LeftViewHolder -> holder.textView.text = msgBean.msg
            is RightViewHolder -> holder.textView.text = msgBean.msg
        }
    }

}