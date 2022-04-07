package com.sr.superhelperx.entity;

import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by Hang.Yang on 2018/8/17 15:35.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class AppEntity {
    public int _ID;

    public AppEntity() {
    }

    public String string() {
        String l = "";
        String t = ", ";
        Field[] s = this.getClass().getDeclaredFields();
        Field[] var4 = s;
        int var5 = s.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Field f = var4[var6];

            try {
                l = l + f.getName() + "=" + f.get(this) + t;
            } catch (Exception var9) {
                ;
            }
        }

        return "[" + (l.length() == 0?"":l.substring(0, l.length() - t.length())) + "]";
    }

    public void log() {
        Log.e(this.getClass().toString() + " log->", this.string());
    }
}