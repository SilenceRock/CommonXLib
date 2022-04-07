package com.sr.superhelperx.view;

import android.content.Context;
import android.os.Build;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Hang.Yang on 2018/8/17 16:31.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public abstract class AppImmerseTitle extends RelativeLayout {
    public AppImmerseTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void boundView(RecyclerView recyclerView, final RecyclerView.LayoutManager layoutManager) {
        if(Build.VERSION.SDK_INT >= 23) {
            recyclerView.setOnScrollChangeListener(new OnScrollChangeListener() {
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    AppImmerseTitle.this.onScroll(layoutManager);
                }
            });
        } else {
            recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    AppImmerseTitle.this.onScroll(layoutManager);
                }
            });
        }

    }

    public abstract void onScroll(RecyclerView.LayoutManager var1);
}