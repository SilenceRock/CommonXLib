package com.sr.superhelperx.view.web;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.sr.superhelperx.scale.ScaleScreenHelperFactory;

/**
 * Created by Hang.Yang on 2018/8/17 16:47.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

class AppProgressBar extends ProgressBar {
    public AppProgressBar(Context context) {
        super(context, (AttributeSet)null, 16842872);
        this.setLayoutParams(new LinearLayout.LayoutParams(-1, ScaleScreenHelperFactory.getInstance().getWidthHeight(5)));
    }
}
