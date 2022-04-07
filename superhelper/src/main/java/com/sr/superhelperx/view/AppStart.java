package com.sr.superhelperx.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Hang.Yang on 2018/8/17 16:32.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public abstract class AppStart extends LinearLayout {
    private AppStart.OnItemSelectListener onItemSelectListener;
    private int count;

    public AppStart(Context context, AttributeSet attrs) {
        super(context, attrs);

        for(int i = 0; i < this.max(); ++i) {
            View view = this.getView(new View(context));
            view.setTag(Integer.valueOf(i));
            if(this.isClick()) {
                view.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        AppStart.this.setSelect(((Integer)v.getTag()).intValue());
                    }
                });
            }

            this.addView(view);
        }

        this.setSelect(this.count = 0);
    }

    public void setSelect(int count) {
        for(int i = 0; i < this.getChildCount(); ++i) {
            this.getChildAt(i).setBackgroundResource(i <= (this.count = count)?this.selectYes():this.selectNo());
        }

        if(this.onItemSelectListener != null) {
            this.onItemSelectListener.onItemSelect(this.count = count);
        }

    }

    public void setOnItemSelectListener(AppStart.OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
        onItemSelectListener.onItemSelect(this.count);
    }

    protected abstract boolean isClick();

    protected abstract int max();

    protected abstract int selectYes();

    protected abstract int selectNo();

    protected abstract View getView(View var1);

    public interface OnItemSelectListener {
        void onItemSelect(int var1);
    }
}
