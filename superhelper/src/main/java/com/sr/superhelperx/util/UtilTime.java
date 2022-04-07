package com.sr.superhelperx.util;

import java.text.DecimalFormat;

/**
 * Created by Hang.Yang on 2018/8/17 16:26.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public abstract class UtilTime {
    private DecimalFormat decimalFormat = new DecimalFormat("00");
    public static int DATE_HOUR_MINUTE_SECOND = 0;
    public static int HOUR_MINUTE_SECOND = 1;

    public UtilTime() {
    }

    public void loadMillisecond(int type, long millisecond) {
        this.loadSecond(type, (int)(millisecond / 1000L));
    }

    public void loadSecond(int type, int second) {
        byte d = 0;
        byte h = 0;
        boolean m = false;
        boolean s = false;
        int h1;
        int m1;
        int s1;
        if(type == DATE_HOUR_MINUTE_SECOND) {
            s1 = second % 60;
            int lm = second / 60;
            m1 = lm % 60;
            int lh = lm / 60;
            h1 = lh % 24;
            int d1 = lh / 24;
            this.onConversion(this.decimalFormat.format((long)d1), this.decimalFormat.format((long)h1), this.decimalFormat.format((long)m1), this.decimalFormat.format((long)s1));
        } else if(second <= 0) {
            this.onConversion(this.decimalFormat.format((long)d), this.decimalFormat.format(0L), this.decimalFormat.format(0L), this.decimalFormat.format(0L));
        } else {
            m1 = second / 60;
            if(m1 < 60) {
                s1 = second % 60;
                this.onConversion(this.decimalFormat.format((long)d), this.decimalFormat.format((long)h), this.decimalFormat.format((long)m1), this.decimalFormat.format((long)s1));
            } else {
                h1 = m1 / 60;
                m1 %= 60;
                s1 = second - h1 * 3600 - m1 * 60;
                this.onConversion(this.decimalFormat.format((long)d), this.decimalFormat.format((long)h1), this.decimalFormat.format((long)m1), this.decimalFormat.format((long)s1));
//                if(h1 < 99) {
//                    m1 %= 60;
//                    s1 = second - h1 * 3600 - m1 * 60;
//                    this.onConversion(this.decimalFormat.format((long)d), this.decimalFormat.format((long)h1), this.decimalFormat.format((long)m1), this.decimalFormat.format((long)s1));
//                } else {
//                    this.onConversion(this.decimalFormat.format((long)d), this.decimalFormat.format(99L), this.decimalFormat.format(59L), this.decimalFormat.format(59L));
//                }
            }
        }

    }

    public abstract void onConversion(String var1, String var2, String var3, String var4);
}

