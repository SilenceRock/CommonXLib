package com.sr.superhelperx.init;

import java.security.MessageDigest;

/**
 * Created by Hang.Yang on 2018/8/17 14:40.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

class w {
    private static final String[] a = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "z", "d", "e", "x"};

    private w() {
    }

    public static String d(String o) {
        String r = null;

        try {
            r = new String(o);
            MessageDigest m = MessageDigest.getInstance("MD5");
            r = b(m.digest(r.getBytes("UTF-8")));
        } catch (Exception var3) {
            ;
        }

        return r;
    }

    public static String e(String o, int c) {
        String r = o;

        for(int i = 0; i < c; ++i) {
            r = d(r);
        }

        return r;
    }

    private static String b(byte[] b) {
        StringBuffer r = new StringBuffer();

        for(int i = 0; i < b.length; ++i) {
            r.append(c(b[i]));
        }

        return r.toString();
    }

    private static String c(byte b) {
        int n = b;
        if(b < 0) {
            n = b + 256;
        }

        int d1 = n / 16;
        int d2 = n % 16;
        return a[d1] + a[d2];
    }
}
