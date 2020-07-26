package com.zuo.kotlindemo.uav

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zuo.kotlindemo.R
import com.zuo.kotlindemo.utils.FileUtils
import com.zuo.kotlindemo.utils.ICallBack
import kotlinx.android.synthetic.main.fragment_uav_result.*
import kotlinx.android.synthetic.main.header_view.*
import java.io.File
import java.io.FileFilter

class UavResultFragment : Fragment(), ICallBack<File> {
    var selectedFile: File? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_uav_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title.setText("无人机成果列表")
        initData()
        //匿名内部类的写法
        btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (selectedFile == null) {
                    Toast.makeText(context, "请选择无人机飞行成果！", Toast.LENGTH_LONG).show()
                } else {
                    //无人机成果界面
                    /*val bundle = Bundle()
                    bundle.putString("path", selectedFile!!.absolutePath)
                    NavHostFragment.findNavController(this@UavResultFragment)
                        .navigate(R.id.action_uavResultFragment_to_uavFragment, bundle)*/

                    //使用 safe args 跳转
                    val nav = UavResultFragmentDirections.actionUavResultFragmentToUavFragment(
                        selectedFile!!.absolutePath
                    )
                    findNavController(this@UavResultFragment).navigate(nav)
                }
            }
        })
        //使用 lambda 表达式简化后的写法
        back.setOnClickListener {
            findNavController(this).popBackStack()
        }
    }

    private fun initData() {
        val td_path =
            Environment.getExternalStorageDirectory().toString() + File.separator + "KingoFile"
        val file = File(td_path)
        if (!file.exists()) {
            file.mkdirs()
        }
        val list = FileUtils.listFilesInDirWithFilter(file, object : FileFilter {
            override fun accept(p0: File?): Boolean {
                if (p0 == null || !p0.name.endsWith(".td")) {
                    return false
                }
                return true
            }
        })
        list?.let {
            rv.adapter = UavResultAdapter(it, this)
            rv.layoutManager = LinearLayoutManager(activity)
            rv.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }
    }

    /**
     * 列表点击回调
     */
    override fun onBack(t: File) {
        tv.setText("已选择：${t.name}")
        selectedFile = t
    }

}