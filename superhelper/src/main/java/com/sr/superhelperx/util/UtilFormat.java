package com.sr.superhelperx.util;

import java.text.DecimalFormat;

/**
 * Created by Hang.Yang on 2018/8/17 16:22.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class UtilFormat {
    private DecimalFormat d;

    private UtilFormat() {
        this.d = new DecimalFormat("0.00");
    }

    public String formatPrice(Object price) {
        return this.d.format(price);
    }

    public static UtilFormat getInstance() {
        return UtilFormat.UtilFormatHolder.INSTANCE;
    }

    private static class UtilFormatHolder {
        static final UtilFormat INSTANCE = new UtilFormat();

        private UtilFormatHolder() {
        }
    }
}
