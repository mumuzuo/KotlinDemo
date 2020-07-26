package com.zuo.kotlindemo

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
 * @author zuozhijie
 * @date 2020/7/6 15:55
 */
class MainActivity : AppCompatActivity(), ViewModelStoreOwner {
    //获取导航控制器
    private val navController: NavController
        get() {
            return findNavController(nav_host_fragment)
        }

    //抽屉布局导航配置（关联导航图和抽屉布局）
    private lateinit var appBarConfiguration: AppBarConfiguration

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //将抽屉布局关联到导航图
        appBarConfiguration = AppBarConfiguration(navController.graph, drawer_layout)
        //将抽屉布局中的左侧菜单关联到导航控制器
        NavigationUI.setupWithNavController(nav_view, navController)
//        findViewById<NavigationView>(R.id.nav_view).setupWithNavController(navController)
        val mainViewModel =
            ViewModelProvider(navController.getViewModelStoreOwner(navController.graph.id)).get(
                MainViewModel::class.java
            )
        mainViewModel.commentData = "这是共用函数"
        initView()
    }

    @SuppressLint("RestrictedApi")
    private fun initView() {
        sendMsg()
        showBackStack()
        navController.addOnDestinationChangedListener(object :
            NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                Toast.makeText(this@MainActivity, "切换到了${destination.label}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        /**
         * 跳转到 MainFragment
         * TODO 不写，下面的导航逻辑，默认时导航到 activity_main 中设置的导航图的起始目的地
         */
//        NavHostFragment.findNavController(nav_host_fragment).navigate(R.id.mainFragment)
    }

    @SuppressLint("RestrictedApi")
    private fun showBackStack() {
        findViewById<View>(R.id.stack).setOnClickListener {
            if (back_stack.visibility == View.VISIBLE) {
                back_stack.visibility = View.GONE
                back_stack.setText("")
                return@setOnClickListener
            }
            val sb = StringBuilder()
            val backStack = navController.backStack
            val descendingIterator = backStack.descendingIterator()
            while (descendingIterator.hasNext()) {
                val backStackItem = descendingIterator.next()
                backStackItem?.let {
                    backStackItem.destination.label?.let {
                        sb.append(it.toString() + "\n")
                    }
                }
            }
            back_stack.visibility = View.VISIBLE
            back_stack.setText(sb.toString())
        }
        findViewById<View>(R.id.back_stack).setOnClickListener {
            back_stack.visibility = View.GONE
            back_stack.setText("")
        }
    }

    private fun sendMsg() {
        val job = Job()
        val coroutineScope = CoroutineScope(job);
        coroutineScope.launch {
            delay(1000)
            sendNotification()
        }
    }

    /**
     * 来一条通知
     */
    private fun sendNotification() {
        val mNotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(this, "channelId")
            .setCategory(Notification.CATEGORY_RECOMMENDATION)
            .setContentTitle("王五")
            .setDefaults(Notification.DEFAULT_SOUND)
            .setContentText("在吗？ 组队，组队")
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .setContentIntent(getPendingIntent()) //设置 PendingIntent
        val notification: Notification = builder.build()
        mNotificationManager.notify(1, notification)
    }

    /**
     * 通过 PendingIntent 设置当通知被单击的时候需要跳转到的 目的地（消息界面）
     * 以及传递参数
     */
    fun getPendingIntent(): PendingIntent {
        val bundle = Bundle()
        bundle.putString("msg", "在吗？ 组队，组队")

        return NavDeepLinkBuilder(this)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.msgFragment)
            .setArguments(bundle)
            .createPendingIntent()
    }
}

