package com.sr.superhelperx.util;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;

import com.sr.superhelperx.app.AppApplication;
import com.sr.superhelperx.permission.PermissionsActivity;

import java.util.List;

/**
 * Created by Hang.Yang on 2018/8/17 14:14.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class UtilApp {
    private UtilApp() {
    }

    public static PackageInfo getPackageInfo() {
        try {
            return AppApplication.AINSTANCE.getPackageManager().getPackageInfo(AppApplication.AINSTANCE.getPackageName(), 0);
        } catch (Exception var1) {
            return null;
        }
    }

    public static String packageName() {
        try {
            return getPackageInfo().packageName;
        } catch (Exception var1) {
            return "";
        }
    }

    public static String appName() {
        try {
            return AppApplication.AINSTANCE.getResources().getString(getPackageInfo().applicationInfo.labelRes);
        } catch (Exception var1) {
            return "";
        }
    }

    public static int versionCode() {
        try {
            return getPackageInfo().versionCode;
        } catch (Exception var1) {
            return 0;
        }
    }

    public static String versionName() {
        try {
            return getPackageInfo().versionName;
        } catch (Exception var1) {
            return null;
        }
    }

    public static boolean isAppInstalled(String packageName) {
        List p = AppApplication.AINSTANCE.getPackageManager().getInstalledPackages(0);
        if(p != null) {
            for(int i = 0; i < p.size(); ++i) {
                if(((PackageInfo)p.get(i)).packageName.equals(packageName)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isServiceWork(Class<? extends Service> clazz) {
        boolean w = false;
        List l = ((ActivityManager) AppApplication.AINSTANCE.getSystemService("activity")).getRunningServices(40);
        if(l.size() <= 0) {
            return false;
        } else {
            for(int i = 0; i < l.size(); ++i) {
                if(((ActivityManager.RunningServiceInfo)l.get(i)).service.getClassName().equals(clazz.toString())) {
                    w = true;
                    break;
                }
            }

            return w;
        }
    }

    public static void sms(final String phoneNumber, final String message) {
        PermissionsActivity.StartActivity(new String[]{"android.permission.SEND_SMS"}, new PermissionsActivity.PermissionsCallBack() {
            public void onSuccess() {
                AppApplication.INSTANCE.currentActivity().startActivity((new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + phoneNumber))).putExtra("sms_body", message).setFlags(268435456));
            }

            public void onFail() {
                (new AlertDialog.Builder(AppApplication.INSTANCE.currentActivity())).setCancelable(false).setTitle(UtilApp.appName()).setMessage("请手动开启拨号权限").setPositiveButton("否", (DialogInterface.OnClickListener)null).setNegativeButton("是", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UtilPermissionManager.show();
                    }
                }).show();
            }
        });
    }

    public static void call(final String number) {
        PermissionsActivity.StartActivity(new String[]{"android.permission.CALL_PHONE"}, new PermissionsActivity.PermissionsCallBack() {
            public void onSuccess() {
                AppApplication.INSTANCE.currentActivity().startActivity((new Intent("android.intent.action.CALL", Uri.parse("tel:" + number))).setFlags(268435456));
            }

            public void onFail() {
                (new AlertDialog.Builder(AppApplication.INSTANCE.currentActivity())).setCancelable(false).setTitle(UtilApp.appName()).setMessage("请手动开启短信权限").setPositiveButton("否", (DialogInterface.OnClickListener)null).setNegativeButton("是", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UtilPermissionManager.show();
                    }
                }).show();
            }
        });
    }

    public static void callQQ(String qqNumber) {
        AppApplication.INSTANCE.currentActivity().startActivity((new Intent("android.intent.action.VIEW", Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + qqNumber + "&version=1"))).setFlags(268435456));
    }
}
