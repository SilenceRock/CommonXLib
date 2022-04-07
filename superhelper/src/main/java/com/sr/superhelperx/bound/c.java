package com.sr.superhelperx.bound;

import android.view.View;
import android.widget.AdapterView;

import java.lang.reflect.Method;

/**
 * Created by Hang.Yang on 2018/8/17 14:26.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class c implements View.OnClickListener, View.OnLongClickListener, AdapterView.OnItemClickListener {
    private Object o;
    private Method m;

    public c(Object j, Method l) {
        this.o = j;
        this.m = l;
    }

    public void onClick(View v) {
        try {
            this.m.invoke(this.o, new Object[]{v});
        } catch (Exception var5) {
            try {
                this.m.invoke(this.o, new Object[0]);
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

    }

    public boolean onLongClick(View v) {
        boolean f = false;

        try {
            f = ((Boolean)this.m.invoke(this.o, new Object[]{v})).booleanValue();
        } catch (Exception var6) {
            try {
                f = ((Boolean)this.m.invoke(this.o, new Object[0])).booleanValue();
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

        return f;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        try {
            this.m.invoke(this.o, new Object[]{adapterView, view, Integer.valueOf(i), Long.valueOf(l)});
        } catch (Exception var9) {
            try {
                this.m.invoke(this.o, new Object[0]);
            } catch (Exception var8) {
                var8.printStackTrace();
            }
        }

    }
}
