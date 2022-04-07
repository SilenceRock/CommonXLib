package com.sr.superhelperx.paper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Hang.Yang on 2018/8/17 16:06.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@SuppressLint({"NewApi"})
public abstract class GuideLayer extends Guide {
    public GuideLayer(Context c, AttributeSet a) {
        super(c, a);
    }

    protected void transformPage(View v, float p) {
        ViewGroup g = (ViewGroup)v;
        int w = v.getWidth();

        for(int i = 0; i < g.getChildCount(); ++i) {
            g.getChildAt(i).setTranslationX((float)(w / (i + 1)) * p);
        }

    }
}