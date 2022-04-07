package com.sr.commonlib;

import android.app.Application;

import com.sr.superhelperx.app.AppApplication;
import com.sr.superhelperx.app.AppInit;

/**
 * FileName: BaseCommonLib
 * Author: hangY
 * Date: 2022/4/2 17:24
 * Description:
 * name 项目名称
 * log 是否打印
 * crash 拦截全局异常
 * initialize 是否网络初始化
 * width 适配宽度
 * scale 适配字体倍数
 *
 * 这个类尽量别转成Kotlin文件，因为Kotlin不继承父类的静变量/方法
 */
@AppInit(name = "CommonXLib", log = true, crash = false, initialize = false, width = 720, scale = 1f)
public class BaseCommonLib extends AppApplication {

    private static BaseCommonLib baseCommonLib;

    public BaseCommonLib(){
        super();
    }

    public static BaseCommonLib getInstance(){
        if (baseCommonLib == null){
            baseCommonLib = new BaseCommonLib();
        }
        return baseCommonLib;
    }

    public void init(Application application){
        setApplication(application);
    }
}
