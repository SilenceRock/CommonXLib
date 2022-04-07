package com.sr.superhelperx.util;

import android.content.IntentFilter;

/**
 * Created by Hang.Yang on 2018/8/17 16:17.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class UtilClassName {
    private UtilClassName() {
    }

    public static IntentFilter getIntentFilter(Class<?> cls) {
        return new IntentFilter(getActionName(cls));
    }

    public static String getActionName(Class<?> cls) {
        return "!" + getName(cls);
    }

    public static String getName(Class<?> cls) {
        String[] n = cls.getName().split("\\.");
        return n[n.length - 1];
    }
}
