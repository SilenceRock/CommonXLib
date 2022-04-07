package com.sr.superhelperx.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Hang.Yang on 2018/8/17 16:28.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class AppAdaptGrid extends GridView {
    public AppAdaptGrid(Context context) {
        super(context);
    }

    public AppAdaptGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AppAdaptGrid(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onMeasure(int w, int h) {
        super.onMeasure(w, MeasureSpec.makeMeasureSpec(536870911, -2147483648));
    }
}