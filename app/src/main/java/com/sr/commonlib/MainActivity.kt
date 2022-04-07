package com.sr.commonlib

import android.os.Bundle
import com.sr.superhelperx.activity.AppKtV4Activity
import com.sr.superhelperx.glide.GlideLoader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppKtV4Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        GlideLoader.getInstance()[context,"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2Ftp03%2F1Z921104Z92S8-0-lp.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1651802443&t=1c07622a3b11f0632d570cd633a0733d",img_test]
    }
}
