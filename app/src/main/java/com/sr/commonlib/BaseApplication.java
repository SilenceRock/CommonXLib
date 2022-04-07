package com.sr.commonlib;

import android.app.Application;


/**
 * FileName: BaseApplication
 * Author: hangY
 * Date: 2022/4/2 17:32
 * Description:
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        BaseCommonLib.getInstance().init(this);
    }
}
