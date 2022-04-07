package com.sr.superhelperx.view;

import android.annotation.TargetApi;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Hang.Yang on 2018/8/17 16:29.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@TargetApi(9)
public class AppAdaptRecycler extends RecyclerView {
    public AppAdaptRecycler(Context context) {
        super(context);
    }

    public AppAdaptRecycler(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AppAdaptRecycler(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void onMeasure(int w, int h) {
        super.onMeasure(w, MeasureSpec.makeMeasureSpec(536870911, -2147483648));
    }
}