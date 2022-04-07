package com.sr.superhelperx.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Hang.Yang on 2018/8/17 16:29.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class AppAdaptList extends ListView {
    public AppAdaptList(Context context) {
        super(context);
    }

    public AppAdaptList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AppAdaptList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void onMeasure(int w, int h) {
        super.onMeasure(w, MeasureSpec.makeMeasureSpec(536870911, -2147483648));
    }
}
