package com.zuo.kotlindemo.msg

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zuo.kotlindemo.R

/**
 * 密封类，及其子类
 *
 * @author zuozhijie
 * @date 2020/7/6 19:12
 */
sealed class MsgViewHolder(view: View) : RecyclerView.ViewHolder(view)

class LeftViewHolder(view: View) : MsgViewHolder(view) {
    val textView: TextView = view.findViewById(R.id.msg_left_tv)
}

class RightViewHolder(view: View) : MsgViewHolder(view) {
    val textView: TextView = view.findViewById(R.id.msg_right_tv)
}
