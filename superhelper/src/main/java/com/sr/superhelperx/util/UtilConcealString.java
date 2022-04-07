package com.sr.superhelperx.util;

/**
 * Created by Hang.Yang on 2018/8/17 16:18.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class UtilConcealString {
    private UtilConcealString() {
    }

    public static String conceal(int before, int later, String string) {
        char[] t = new char[string.length()];

        for(int i = 0; i < string.length(); ++i) {
            t[i] = before + later <= string.length() && i > before - 1 && i < string.length() - later?42:string.charAt(i);
        }

        return new String(t);
    }
}