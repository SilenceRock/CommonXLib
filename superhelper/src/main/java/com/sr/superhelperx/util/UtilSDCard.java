package com.sr.superhelperx.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by Hang.Yang on 2018/8/17 16:26.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class UtilSDCard {
    private UtilSDCard() {
    }

    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath() + File.separator;
    }
}
