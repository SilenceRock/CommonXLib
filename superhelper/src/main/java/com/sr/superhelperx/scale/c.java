package com.sr.superhelperx.scale;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.sr.superhelperx.rebound.h;

/**
 * Created by Hang.Yang on 2018/8/17 13:47.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

/*@z
@f*/
final class c extends b implements d {
    private a k = new a();
    //放大倍数
    private float c;
    //基础宽
    private int d;

    c(int s, float a) {
        this.d = s;
        this.c = a;
    }

    public View loadViewPadding(View v, int l, int t, int r, int b) {
        try {
            v.setPadding(this.getWidthHeight(l), this.getWidthHeight(t), this.getWidthHeight(r), this.getWidthHeight(b));
        } catch (Exception var7) {
            ;
        }

        return v;
    }

    public View loadViewMargin(View v, int l, int t, int r, int b) {
        try {
            ViewGroup.MarginLayoutParams m = (ViewGroup.MarginLayoutParams)v.getLayoutParams();
            m.leftMargin = this.getWidthHeight(l);
            m.topMargin = this.getWidthHeight(t);
            m.rightMargin = this.getWidthHeight(r);
            m.bottomMargin = this.getWidthHeight(b);
            v.setLayoutParams(m);
        } catch (Exception var7) {
            ;
        }

        return v;
    }

    public View loadViewWidthHeightSize(View v, int w, int h, int s) {
        return this.loadViewSize(this.loadViewWidthHeight(v, w, h), s);
    }

    public View loadViewWidthHeight(View v, int w, int h) {
        try {
            ViewGroup.MarginLayoutParams m = (ViewGroup.MarginLayoutParams)v.getLayoutParams();
            int pw = this.getWidthHeight(w);
            int ph = this.getWidthHeight(h);
            if(pw > 0) {
                m.width = pw;
            }else if (pw == 0){
                m.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            }else {
                m.width = ViewGroup.LayoutParams.MATCH_PARENT;
            }
            if(ph > 0) {
                m.height = ph;
            }else if (ph == 0){
                m.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            }else {
                m.height = ViewGroup.LayoutParams.MATCH_PARENT;
            }
            v.setLayoutParams(m);
        } catch (Exception var7) {
            ;
        }

        return v;
    }

    public View loadViewMinWidth(View v, int w) {
        try {
            v.getClass().getMethod(this.k(v)?"setMinWidth":"setMinimumWidth", new Class[]{Integer.TYPE}).invoke(v, new Object[]{Integer.valueOf(this.getWidthHeight(w))});
        } catch (Exception var4) {
            ;
        }

        return v;
    }

    public View loadViewMaxWidth(View v, int w) {
        if(this.k(v)) {
            try {
                v.getClass().getMethod("setMaxWidth", new Class[]{Integer.TYPE}).invoke(v, new Object[]{Integer.valueOf(this.getWidthHeight(w))});
            } catch (Exception var4) {
                ;
            }
        }

        return v;
    }

    public View loadViewMinHeight(View v, int h) {
        try {
            v.getClass().getMethod(this.k(v)?"setMinHeight":"setMinimumHeight", new Class[]{Integer.TYPE}).invoke(v, new Object[]{Integer.valueOf(this.getWidthHeight(h))});
        } catch (Exception var4) {
            ;
        }

        return v;
    }

    public View loadViewMaxHeight(View v, int h) {
        if(this.k(v)) {
            try {
                v.getClass().getMethod("setMaxHeight", new Class[]{Integer.TYPE}).invoke(v, new Object[]{Integer.valueOf(this.getWidthHeight(h))});
            } catch (Exception var4) {
                ;
            }
        }

        return v;
    }

    public View loadViewSize(View v, int s) {
        if(this.k(v)) {
            try {
                v.getClass().getMethod("setTextSize", new Class[]{Integer.TYPE, Float.TYPE}).invoke(v, new Object[]{Integer.valueOf(0), Float.valueOf(this.getSize(s))});
            } catch (Exception var4) {
                ;
            }
        }

        return v;
    }

    public View loadViewGroup(ViewGroup v) {
        h h = new h();
        View i = this.l(v, this.k, h);
        h.c();
        return i;
    }

    public int getWidthHeight(int v) {
        return Math.round(this.e((float)this.b, (float)this.d, (float)v));
    }

    public float getSize(int v) {
        return Math.round(this.e((float)this.b, (float)this.d, (float)v) * this.c);
    }

    private boolean k(View v) {
        return v instanceof TextView || v instanceof EditText || v instanceof Button || v instanceof CheckBox || v instanceof RadioButton;
    }

    private View l(ViewGroup vg, com.sr.superhelperx.scale.a a, h h) {
        a.d(this.f(vg.getTag()), vg, this, h);

        for(int i = 0; i < vg.getChildCount(); ++i) {
            View v = vg.getChildAt(i);
            if(v instanceof ViewGroup) {
                this.l((ViewGroup)v, a, h);
            } else {
                a.d(this.f(v.getTag()), v, this, h);
            }
        }

        return vg;
    }

    private float e(float v1, float v2, float v3) {
        float v4 = v2 > v3 ? v1 / (v2 / v3) : v1 * (v3 / v2);
        return v4 > 0.0F && v4 < 1.0F ? 1.0F : v4 > -1.0F && v4 < 0F ? -1.0F : v4;
    }

    private String f(Object o) {
        try {
            return o.toString();
        } catch (Exception var3) {
            return "";
        }
    }
}