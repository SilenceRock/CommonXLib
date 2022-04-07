package com.sr.superhelperx.util;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.sr.superhelperx.app.AppApplication;

/**
 * Created by Hang.Yang on 2018/8/17 16:25.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class UtilPhone {
    private UtilPhone() {
    }

    private static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) AppApplication.AINSTANCE.getSystemService("phone");
    }

    public static String getPhoneNumber() {
        return getTelephonyManager().getLine1Number();
    }

    public static String getFacilitator() {
        String facilitator = "N/A";

        try {
            String imsi = getTelephonyManager().getSubscriberId();
            if(!imsi.startsWith("46000") && !imsi.startsWith("46002")) {
                if(imsi.startsWith("46001")) {
                    facilitator = "�й���ͨ";
                } else if(imsi.startsWith("46003")) {
                    facilitator = "�й�����";
                }
            } else {
                facilitator = "�й��ƶ�";
            }
        } catch (Exception var2) {
            ;
        }

        return facilitator;
    }

    public static String getPhoneInfo(Context context) {
        TelephonyManager telephonyManager = getTelephonyManager();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nDeviceId(IMEI) = " + telephonyManager.getDeviceId());
        stringBuilder.append("\nDeviceSoftwareVersion = " + telephonyManager.getDeviceSoftwareVersion());
        stringBuilder.append("\nLine1Number = " + telephonyManager.getLine1Number());
        stringBuilder.append("\nNetworkCountryIso = " + telephonyManager.getNetworkCountryIso());
        stringBuilder.append("\nNetworkOperator = " + telephonyManager.getNetworkOperator());
        stringBuilder.append("\nNetworkOperatorName = " + telephonyManager.getNetworkOperatorName());
        stringBuilder.append("\nNetworkType = " + telephonyManager.getNetworkType());
        stringBuilder.append("\nPhoneType = " + telephonyManager.getPhoneType());
        stringBuilder.append("\nSimCountryIso = " + telephonyManager.getSimCountryIso());
        stringBuilder.append("\nSimOperator = " + telephonyManager.getSimOperator());
        stringBuilder.append("\nSimOperatorName = " + telephonyManager.getSimOperatorName());
        stringBuilder.append("\nSimSerialNumber = " + telephonyManager.getSimSerialNumber());
        stringBuilder.append("\nSimState = " + telephonyManager.getSimState());
        stringBuilder.append("\nSubscriberId(IMSI) = " + telephonyManager.getSubscriberId());
        stringBuilder.append("\nVoiceMailNumber = " + telephonyManager.getVoiceMailNumber());
        return stringBuilder.toString();
    }
}
