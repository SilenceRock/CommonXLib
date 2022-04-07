package com.sr.superhelperx.view.line;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Hang.Yang on 2018/8/17 16:38.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public abstract class RadioLineView<T extends LineEntity> extends NewLineView<T> {
    private RadioLineView.OnItemClickListener<T> o;

    public RadioLineView(Context c, AttributeSet a) {
        super(c, a);
    }

    protected View setListener(View v, T t) {
        v.setTag(t);
        v.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                RadioLineView.this.s(v);
            }
        });
        if(t.isSelect) {
            this.onOn(v);
        } else {
            this.onOff(v);
        }

        return v;
    }

    public void setSelect(int index) {
        this.s(this.getChildAt(index));
    }

    private void s(View v) {
        try {
            for(int i = 0; i < this.getChildCount(); ++i) {
                View v1 = this.getChildAt(i);
                ((LineEntity)v1.getTag()).isSelect = false;
                this.onOff(v1);
            }

            ((LineEntity)v.getTag()).isSelect = true;
            this.o.onItemClick(v, (LineEntity)v.getTag());
            this.onOn(v);
        } catch (Exception var4) {
            ;
        }

    }

    public void setOnItemClickListener(RadioLineView.OnItemClickListener<T> onItemClickListener) {
        this.o = onItemClickListener;
    }

    protected abstract void onOff(View var1);

    protected abstract void onOn(View var1);

    public interface OnItemClickListener<T> {
        T onItemClick(View var1, LineEntity var2);
    }
}

