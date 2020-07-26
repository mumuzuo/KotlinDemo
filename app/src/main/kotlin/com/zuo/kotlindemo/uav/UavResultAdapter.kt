package com.zuo.kotlindemo.uav

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.zuo.kotlindemo.R
import com.zuo.kotlindemo.utils.ICallBack
import java.io.File
import java.text.DateFormat
import java.util.*

/**
 * 消息列表适配器
 *
 * @author zuozhijie
 * @date 2020/7/6 15:54
 */
class UavResultAdapter(val data: List<File>, val iCallBack: ICallBack<File>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.N)
    var dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_uav_result,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val file = data[position]
        when (holder) {
            is MyViewHolder -> {
                holder.name.setText("无人机成果：${file.name}")
                holder.time.setText("举证时间：${dateFormat.format(file.lastModified())}")
                holder.itemView.setOnClickListener {
                    iCallBack.onBack(file)
                }
            }

        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val time: TextView = view.findViewById(R.id.time)
    }
}