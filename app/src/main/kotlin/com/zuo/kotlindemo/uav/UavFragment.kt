package com.zuo.kotlindemo.uav

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.navArgs
import com.zuo.kotlindemo.R
import kotlinx.android.synthetic.main.fragment_uav.*
import kotlinx.android.synthetic.main.header_view.*
import java.io.File

class UavFragment : Fragment(), View.OnClickListener {
    //    lateinit var tdPath: String
    val args: UavFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_uav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        title.setText("无人机成果详情")
        uav_data_back.setOnClickListener(this)
        back.setOnClickListener(this)
        //接收 一个 Bundle 参数
        /*val bundle = arguments
        if (bundle != null) {
            tdPath = bundle.getString("path").toString()
            val file = File(tdPath)
            if (file.exists()) {
                title.setText("无人机成果(${file.name})")
                uav_content.setText(file.readText())
                uav_content.setMovementMethod(ScrollingMovementMethod.getInstance());
            }
        }*/
        args.toBundle()
        //接收 Safe Args 传递的参数
        val file = File(args.path)
        if (file.exists()) {
            title.setText("无人机成果(${file.name})")
            uav_content.setText(file.readText())
            uav_content.setMovementMethod(ScrollingMovementMethod.getInstance());
        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            back -> {
                findNavController(this).popBackStack()
            }
            uav_data_back -> {
                if (TextUtils.isEmpty(args.path)) {
                    Toast.makeText(context, "没有飞行成果，无法回传！", Toast.LENGTH_LONG).show()
                } else {
                    val intent = Intent("com.landcloud.zxjg.uav.receive")
                    val bundle = Bundle()
                    bundle.putString("TD_PATH", args.path)
                    intent.putExtra("LAND_BUNDLE", bundle)
                    intent.putExtra("appName", "_在线监管")
                    intent.putExtra(
                        "appPackageName",
                        "com.kingoit.onemap.investigation_sd_gdhctest"
                    )
                    intent.putExtra("version", "1.1")
                    startActivity(intent)
                }
            }
        }
    }


}