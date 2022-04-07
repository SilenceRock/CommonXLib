package com.sr.superhelperx.util;

import android.annotation.TargetApi;
import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Hang.Yang on 2018/8/17 16:17.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */


public final class UtilBase64 {
    private UtilBase64() {
    }

    @TargetApi(8)
    public static String encodeBase64File(String path) {
        try {
            File e = new File(path);
            FileInputStream i = new FileInputStream(e);
            byte[] b = new byte[(int)e.length()];
            i.read(b);
            i.close();
            return Base64.encodeToString(b, 0);
        } catch (Exception var4) {
            return "";
        }
    }
}
