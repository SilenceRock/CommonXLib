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
public class a implements ViewPager.PageTransformer {
    private static final float MIN_ALPHA = 0.5F;
    private static final float MIN_SCALE = 0.85F;

    public a() {
    }

    public void transformPage(View page, float position) {
        int pageWidth = page.getWidth();
        int pageHeight = page.getHeight();
        if(position < -1.0F) {
            page.setAlpha(0.0F);
        } else if(position <= 1.0F) {
            float scaleFactor = Math.max(0.85F, 1.0F - Math.abs(position));
            float vertMargin = (float)pageHeight * (1.0F - scaleFactor) / 2.0F;
            float horzMargin = (float)pageWidth * (1.0F - scaleFactor) / 2.0F;
            if(position < 0.0F) {
                page.setTranslationX(horzMargin - vertMargin / 2.0F);
            } else {
                page.setTranslationX(-horzMargin + vertMargin / 2.0F);
            }

            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setAlpha(0.5F + (scaleFactor - 0.85F) / 0.14999998F * 0.5F);
        } else {
            page.setAlpha(0.0F);
        }

    }
}
