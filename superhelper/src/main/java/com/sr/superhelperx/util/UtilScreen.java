package com.sr.superhelperx.util;

import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.sr.superhelperx.app.AppApplication;

/**
 * Created by Hang.Yang on 2018/8/17 14:07.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class UtilScreen {
    private UtilScreen() {
    }

    public static int[] screenSize() {
        DisplayMetrics d = new DisplayMetrics();
        ((WindowManager) AppApplication.AINSTANCE.getSystemService("window")).getDefaultDisplay().getMetrics(d);
        return new int[]{d.widthPixels, d.heightPixels};
    }
}
