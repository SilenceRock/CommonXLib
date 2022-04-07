package com.sr.superhelperx.scale;

/**
 * Created by Hang.Yang on 2018/8/17 13:57.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class ScaleScreenHelperFactory {
    //倍数
    private static float t;
    //默认宽度
    private static int b;
    //interface
    private static d i;

    private ScaleScreenHelperFactory() {
    }

    public static d getInstance() {
        return i;
    }

    public static void reset() {
        init(b, t);
    }

    public static void init(int s, float c) {
        b = s;
        t = c;
        i = new c(s, c);
    }
}
