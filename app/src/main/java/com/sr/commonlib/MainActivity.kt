package com.sr.commonlib

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.os.CountDownTimer
import com.sr.superhelperx.activity.AppKtV4Activity
import com.sr.superhelperx.util.UtilLog
import com.sr.superhelperx.util.UtilToast
import kotlinx.android.synthetic.main.activity_main.*
import java.net.InetAddress
import kotlin.concurrent.thread
import kotlin.system.exitProcess

class MainActivity : AppKtV4Activity() {

    private val TAG = javaClass.simpleName

    private val WHAT_EXIT_APP = -10000

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initHandlerCallBack()
//        GlideLoader.getInstance()[context,"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2Ftp03%2F1Z921104Z92S8-0-lp.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651802443&t=1c07622a3b11f0632d570cd633a0733d",img_test]

//        parsingDomain("skmobiletv.96396.cn")

        tv_hello.setOnClickListener {
//            ll_item.removeView(img_test)
        }

        tv_hello_world.setOnClickListener {
//            ll_item.addView(img_test,2)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        UtilLog.e(TAG, "onConfigurationChanged fontScale = " + newConfig.fontScale)
        super.onConfigurationChanged(newConfig)
    }

    private fun parsingDomain(hostUrl: String){
        var ipAddress = ""
        thread {
            try {
                val mInetAddress = InetAddress.getByName(hostUrl)
                if (mInetAddress != null){
                    ipAddress = mInetAddress.hostAddress
                }
                UtilLog.e(TAG,"ipAddress = $ipAddress")
                InetAddress.getAllByName(hostUrl).forEach {
                    UtilLog.e(TAG,"hostAddress = ${it.hostAddress}")
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private fun initHandlerCallBack(){
        setHandlerListener {
            UtilLog.e(TAG,"Handler-What ----> $it")
            when(it){
                WHAT_EXIT_APP -> exitProcess(0)
            }
        }
    }

    /**
     * 退出App
     */
    private var firstTime: Long = 0

    override fun onBackPressed() {
        val exitTime = System.currentTimeMillis()
        if (exitTime - firstTime < 2000) {
//            moveTaskToBack(true)
//            super.onBackPressed()
            BaseCommonLib.INSTANCE.beforeExit()
            delayedHandler(WHAT_EXIT_APP,100L)
        } else {
            UtilToast.show("再按一次退出App")
            firstTime = exitTime
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        UtilLog.e(TAG,"onDestroy")
        exitProcess(0)
    }
}
