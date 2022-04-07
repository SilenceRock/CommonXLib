package com.sr.superhelperx.util;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Hang.Yang on 2018/8/17 14:32.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class UtilClear {
    private UtilClear() {
    }

    public static void clear(Object o) {
        try {
            co(o);
        } catch (Exception var2) {
            ;
        }

        System.gc();
    }

    private static void co(Object o) throws Exception {
        Field[] fs = o.getClass().getDeclaredFields();
        if(fs != null && fs.length > 0) {
            Field[] var2 = fs;
            int var3 = fs.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Field f = var2[var4];

                try {
                    f.setAccessible(true);
                    Object oo = f.get(o);
                    if(oo != null) {
                        cf(oo);
                        f.set(o, (Object)null);
                    }

                    f.setAccessible(false);
                } catch (Exception var7) {
                    ;
                }
            }
        }

    }

    private static void cf(Object o) {
        if(o instanceof Collection) {
            Collection m = (Collection)o;
            Iterator c = m.iterator();

            while(c.hasNext()) {
                Object oo = c.next();
                cf(oo);
            }

            m.clear();
        } else if(o instanceof Map) {
            Map m1 = (Map)o;
            Collection c1 = m1.values();
            Iterator oo2 = c1.iterator();

            while(oo2.hasNext()) {
                Object oo1 = oo2.next();
                cf(oo1);
            }

            m1.clear();
        } else if(o instanceof ViewGroup) {
            cvg((ViewGroup)o);
        } else if(o instanceof Bitmap) {
            ((Bitmap)o).recycle();
        }

    }

    private static void cvg(ViewGroup vg) {
        try {
            for(int i = 0; i < vg.getChildCount(); ++i) {
                View v = vg.getChildAt(i);
                if(v instanceof ViewGroup) {
                    cvg((ViewGroup)v);
                }
            }

            vg.removeAllViews();
        } catch (Exception var3) {
            ;
        }

    }
}