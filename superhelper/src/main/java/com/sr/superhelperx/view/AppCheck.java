package com.sr.superhelperx.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Hang.Yang on 2018/8/17 16:29.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public abstract class AppCheck extends ImageView {
    private AppCheck.OnCheckChangeListener onCheckChangeListener;
    private boolean isCheck;

    public AppCheck(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setScaleType(ScaleType.FIT_XY);
        if(this.getOnClick()) {
            this.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    AppCheck.this.setCheck(!AppCheck.this.isCheck);
                }
            });
        }

        this.setCheck(this.isCheck, false);
    }

    public void setCheck(boolean isCheck) {
        this.setCheck(isCheck, true);
    }

    public void setCheck(boolean isCheck, boolean isClick) {
        this.isCheck = isCheck;
        this.setImageResource(isCheck?this.getCheckYes():this.getCheckNo());
        if(this.onCheckChangeListener != null && isClick) {
            this.onCheckChangeListener.onCheckChange(this, isCheck);
        }

    }

    public boolean isCheck() {
        return this.isCheck;
    }

    public void setOnCheckChangeListener(AppCheck.OnCheckChangeListener onCheckChangeListener) {
        this.onCheckChangeListener = onCheckChangeListener;
    }

    protected abstract boolean getOnClick();

    protected abstract int getCheckYes();

    protected abstract int getCheckNo();

    public interface OnCheckChangeListener {
        void onCheckChange(View var1, boolean var2);
    }
}

