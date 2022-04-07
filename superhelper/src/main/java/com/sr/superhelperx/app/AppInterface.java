package com.sr.superhelperx.app;

import android.content.Intent;

import com.sr.superhelperx.permission.PermissionsResultAction;
import com.sr.superhelperx.receiver.AppReceiver;

/**
 * Created by Hang.Yang on 2018/8/17 14:18.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public interface AppInterface {
    void setAppCycle(AppCycle var1);

    void startVerifyActivity(Class<?> var1);

    void addReceiver(AppReceiver var1);

    void setAppCallBack(AppCallBack var1);

    AppApplication getAppApplication() throws Exception;

    void startVerifyActivity(Class<?> var1, Intent var2);

    void removeReceiver(Class<? extends AppReceiver> var1);

    AppCallBack getAppCallBack(Class<?> var1) throws Exception;

    void delayed(long var1, AppInterface.OnDelayedEnd var3);

    void permission(String[] var1, PermissionsResultAction var2);

    public interface OnDelayedEnd {
        void onEnd();
    }
}
