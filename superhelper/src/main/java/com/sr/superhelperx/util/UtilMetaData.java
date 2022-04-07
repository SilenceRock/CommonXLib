package com.sr.superhelperx.util;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;

import com.sr.superhelperx.app.AppApplication;

/**
 * Created by Hang.Yang on 2018/8/17 16:24.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@SuppressLint({"NewApi"})
public final class UtilMetaData {
    private UtilMetaData() {
    }

    public static String applicationMetaData(String name, String defaultValue) {
        String v = defaultValue;

        try {
            v = AppApplication.AINSTANCE.getPackageManager().getApplicationInfo(AppApplication.AINSTANCE.getPackageName(), 128).metaData.getString(name, defaultValue);
        } catch (PackageManager.NameNotFoundException var4) {
            ;
        }

        return v;
    }
}