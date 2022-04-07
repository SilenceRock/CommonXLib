package com.sr.superhelperx.util;

import android.util.Log;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * Created by Hang.Yang on 2018/8/17 16:23.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class UtilMD5 {

    private static final String[] h_low = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private UtilMD5() {
    }

    public static String MD5Encode(String origin) {
        String r = null;

        try {
            r = origin;
            MessageDigest m = MessageDigest.getInstance("MD5");
            r = byteArrayToHexString(m.digest(r.getBytes()));
        } catch (Exception var4) {
            ;
        }

        return r;
    }

    public static String MD5Encode(String origin, String charsetname) {
        String r = null;

        try {
            r = origin;
            MessageDigest m = MessageDigest.getInstance("MD5");
            if(charsetname != null && !"".equals(charsetname)) {
                r = byteArrayToHexString(m.digest(r.getBytes(charsetname)));
            } else {
                r = byteArrayToHexString(m.digest(r.getBytes()));
            }
        } catch (Exception var4) {
            ;
        }

        return r;
    }

    public static String MD5EncodeCount(String origin, String charsetname, int count) {
        String r = origin;

        for(int i = 0; i < count; ++i) {
            r = MD5Encode(r, charsetname);
        }

        return r;
    }

    public static String MD5FileEncode(String filename) {
        InputStream fis;
        byte[] buffer = new byte[1024];
        int numRead = 0;
        MessageDigest md5;
        try {
            fis = new FileInputStream(filename);
            md5 = MessageDigest.getInstance("MD5");
            while ((numRead = fis.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }
            fis.close();
            return byteArrayToHexString(md5.digest());
        } catch (Exception e) {
            Log.e("UtilMD5","MD5FileEncode ERROR");
            return null;
        }
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer r = new StringBuffer();

        for(int i = 0; i < b.length; ++i) {
            r.append(byteToHexString(b[i]));
        }

        return r.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if(b < 0) {
            n = b + 256;
        }

        int d1 = n / 16;
        int d2 = n % 16;
        return h_low[d1] + h_low[d2];
    }
}

