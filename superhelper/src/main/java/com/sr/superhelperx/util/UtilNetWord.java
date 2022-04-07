package com.sr.superhelperx.util;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import com.sr.superhelperx.app.AppApplication;

/**
 * Created by Hang.Yang on 2018/8/17 16:24.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class UtilNetWord {
    private UtilNetWord() {
    }

    public static boolean isNetworkConnected() {
        NetworkInfo n = ((ConnectivityManager) AppApplication.AINSTANCE.getSystemService("connectivity")).getActiveNetworkInfo();
        return n != null?n.isAvailable():false;
    }

    public static boolean isWifiConnected() {
        NetworkInfo n = ((ConnectivityManager) AppApplication.AINSTANCE.getSystemService("connectivity")).getNetworkInfo(1);
        return n != null?n.isAvailable():false;
    }

    public static boolean isMobileConnected() {
        NetworkInfo n = ((ConnectivityManager) AppApplication.AINSTANCE.getSystemService("connectivity")).getNetworkInfo(0);
        return n != null?n.isAvailable():false;
    }

    public static String getWifiIP() {
        return int2string(((WifiManager) AppApplication.AINSTANCE.getSystemService("wifi")).getConnectionInfo().getIpAddress());
    }

    private static String int2string(int i) {
        return (i & 255) + "." + (i >> 8 & 255) + "." + (i >> 16 & 255) + "." + (i >> 24 & 255);
    }
}