package com.sr.superhelperx.util;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import com.sr.superhelperx.app.AppApplication;

/**
 * Created by Hang.Yang on 2018/8/17 14:15.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class UtilPermissionManager {
    private UtilPermissionManager() {
    }

    public static void show() {
        a();
    }

    private static void a() {
        try {
            AppApplication.INSTANCE.currentActivity().startActivity((new Intent("miui.intent.action.APP_PERM_EDITOR")).setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity")).putExtra("extra_pkgname", UtilApp.packageName()));
        } catch (Exception var1) {
            b();
        }

    }

    private static void b() {
        try {
            AppApplication.INSTANCE.currentActivity().startActivity((new Intent("com.meizu.safe.security.SHOW_APPSEC")).addCategory("android.intent.category.DEFAULT").putExtra("packageName", "com.yh.helperx"));
        } catch (Exception var1) {
            c();
        }

    }

    private static void c() {
        try {
            AppApplication.INSTANCE.currentActivity().startActivity((new Intent()).setFlags(268435456).setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity")));
        } catch (Exception var1) {
            e();
        }

    }

    private static void e(){
        try {
            AppApplication.INSTANCE.currentActivity().startActivity(AppApplication.INSTANCE.currentActivity().getPackageManager().getLaunchIntentForPackage("com.iqoo.secure"));
        } catch (Exception var1){
            f();
        }
    }

    private static void f(){
        try {
            AppApplication.INSTANCE.currentActivity().startActivity(AppApplication.INSTANCE.currentActivity().getPackageManager().getLaunchIntentForPackage("com.oppo.safe"));
        } catch (Exception var1){
            d();
        }
    }

    private static void d() {
        Intent i = (new Intent()).addFlags(268435456);
        if(Build.VERSION.SDK_INT >= 9) {
            i.setAction("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", UtilApp.packageName(), (String)null));
        } else if(Build.VERSION.SDK_INT <= 8) {
            i.setAction("android.intent.action.VIEW").setClassName("com.android.settings", "com.android.settings.InstalledAppDetails").putExtra("com.android.settings.ApplicationPkgName", UtilApp.packageName());
        }

        AppApplication.INSTANCE.currentActivity().startActivity(i);
    }
}
