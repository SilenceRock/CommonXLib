package com.sr.superhelperx.paper.trans;

import android.annotation.SuppressLint;
import androidx.viewpager.widget.ViewPager;
import android.view.View;

/**
 * Created by Hang.Yang on 2018/8/17 16:07.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@SuppressLint({"NewApi"})
public class b implements ViewPager.PageTransformer {
    private static final float M = 0.75F;

    public b() {
    }

    public void transformPage(View p, float i) {
        if(i < -1.0F) {
            p.setAlpha(0.0F);
        } else if(i <= 0.0F) {
            p.setAlpha(1.0F);
            p.setTranslationX(0.0F);
            p.setScaleX(1.0F);
            p.setScaleY(1.0F);
        } else if(i <= 1.0F) {
            p.setAlpha(1.0F - i);
            p.setTranslationX((float)p.getWidth() * -i);
            float s = 0.75F + 0.25F * (1.0F - Math.abs(i));
            p.setScaleX(s);
            p.setScaleY(s);
        } else {
            p.setAlpha(0.0F);
        }

    }
}
