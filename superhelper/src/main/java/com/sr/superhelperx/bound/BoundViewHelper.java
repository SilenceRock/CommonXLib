package com.sr.superhelperx.bound;

import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Hang.Yang on 2018/8/17 14:26.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class BoundViewHelper {
    private BoundViewHelper() {
    }

    public static View boundView(Object o, View cv) {
        Class c = o.getClass();
        Field[] fs = c.getDeclaredFields();
        Field[] ms = fs;
        int var5 = fs.length;

        int var6;
        for(var6 = 0; var6 < var5; ++var6) {
            Field f = ms[var6];
            if(f.isAnnotationPresent(BoundView.class)) {
                try {
                    BoundView m = (BoundView)f.getAnnotation(BoundView.class);
                    View i = cv.findViewById(m.value());
                    if(i != null) {
                        f.setAccessible(true);
                        f.set(o, i);
                        f.setAccessible(false);
                        if(m.isClick()) {
                            i.setClickable(true);
                            i.setOnClickListener((View.OnClickListener)o);
                        }
                    }
                } catch (Exception var16) {
                    ;
                }
            }
        }

        Method[] var17 = c.getDeclaredMethods();
        Method[] var18 = var17;
        var6 = var17.length;

        for(int var19 = 0; var19 < var6; ++var19) {
            Method var20 = var18[var19];
            if(var20.isAnnotationPresent(BoundClick.class)) {
                int[] var21 = ((BoundClick)var20.getAnnotation(BoundClick.class)).value();
                int[] var10 = var21;
                int var11 = var21.length;

                for(int var12 = 0; var12 < var11; ++var12) {
                    int s = var10[var12];

                    try {
                        View v = cv.findViewById(s);
                        v.setClickable(true);
                        v.setOnClickListener(new c(o, var20));
                    } catch (Exception var15) {
                        ;
                    }
                }
            }
        }

        return cv;
    }
}

