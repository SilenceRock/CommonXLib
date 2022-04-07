package com.sr.superhelperx.scale;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Hang.Yang on 2018/8/17 13:48.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public interface d {
    View loadViewWidthHeightSize(View var1, int var2, int var3, int var4);

    View loadViewPadding(View var1, int var2, int var3, int var4, int var5);

    View loadViewMargin(View var1, int var2, int var3, int var4, int var5);

    View loadViewWidthHeight(View var1, int var2, int var3);

    View loadViewMinWidth(View var1, int var2);

    View loadViewMaxWidth(View var1, int var2);

    View loadViewMinHeight(View var1, int var2);

    View loadViewMaxHeight(View var1, int var2);

    View loadViewSize(View var1, int var2);

    View loadViewGroup(ViewGroup var1);

    int getWidthHeight(int var1);

    float getSize(int var1);
}
