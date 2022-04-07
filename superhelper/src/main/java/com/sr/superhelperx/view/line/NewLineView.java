package com.sr.superhelperx.view.line;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.sr.superhelperx.sign.g;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Hang.Yang on 2018/8/17 16:38.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

/*@f
@z*/
@SuppressLint({"DrawAllocation"})
public abstract class NewLineView<T extends LineEntity> extends ViewGroup {
    List<List<View>> a = new ArrayList();
    List<Integer> d = new ArrayList();

    public NewLineView(Context c, AttributeSet a) {
        super(c, a);
    }

    public void setItem(T... t) {
        this.addItem(Arrays.asList(t));
    }

    public void setItem(List<T> list) {
        this.removeAllViews();
        this.addItem(list);
    }

    public void addItem(T... t) {
        this.addItem(Arrays.asList(t));
    }

    public void addItem(List<T> list) {
        for(int i = 0; i < list.size(); ++i) {
            LineEntity t = (LineEntity)list.get(i);
            this.addView(this.setListener(this.getView((T) t), (T) t));
        }

    }

    protected View setListener(View view, T t) {
        return view;
    }

    public LayoutParams generateLayoutParams(AttributeSet a) {
        return new MarginLayoutParams(this.getContext(), a);
    }

    protected void onMeasure(int w, int h) {
        super.onMeasure(w, h);
        int sw = MeasureSpec.getSize(w);
        int sh = MeasureSpec.getSize(h);
        int mw = MeasureSpec.getMode(w);
        int mh = MeasureSpec.getMode(h);
        int t = 0;
        int g = 0;
        int lw = 0;
        int lh = 0;
        int cc = this.getChildCount();

        for(int i = 0; i < cc; ++i) {
            View v = this.getChildAt(i);
            this.measureChild(v, w, h);
            MarginLayoutParams lp = (MarginLayoutParams)v.getLayoutParams();
            int childWidth = v.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = v.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            if(lw + childWidth > sw) {
                t = Math.max(lw, childWidth);
                lw = childWidth;
                g += lh;
                lh = childHeight;
            } else {
                lw += childWidth;
                lh = Math.max(lh, childHeight);
            }

            if(i == cc - 1) {
                t = Math.max(t, lw);
                g += lh;
            }
        }

        this.setMeasuredDimension(mw == 1073741824?sw:t, mh == 1073741824?sh:g);
    }

    protected void onLayout(boolean c, int l, int t, int r, int b) {
        if(g.a(this)) {
            this.a.clear();
            this.d.clear();
            int width = this.getWidth();
            int lw = 0;
            int lh = 0;
            ArrayList lvs = new ArrayList();
            int cc = this.getChildCount();

            int lf;
            int i;
            int j;
            for(lf = 0; lf < cc; ++lf) {
                View tp = this.getChildAt(lf);
                MarginLayoutParams ln = (MarginLayoutParams)tp.getLayoutParams();
                i = tp.getMeasuredWidth();
                j = tp.getMeasuredHeight();
                if(i + ln.leftMargin + ln.rightMargin + lw > width) {
                    this.d.add(Integer.valueOf(lh));
                    this.a.add(lvs);
                    lw = 0;
                    lvs = new ArrayList();
                }

                lw += i + ln.leftMargin + ln.rightMargin;
                lh = Math.max(lh, j + ln.topMargin + ln.bottomMargin);
                lvs.add(tp);
            }

            this.d.add(Integer.valueOf(lh));
            this.a.add(lvs);
            lf = 0;
            int var23 = 0;
            int var24 = this.a.size();

            for(i = 0; i < var24; ++i) {
                List var22 = (List)this.a.get(i);
                lh = ((Integer)this.d.get(i)).intValue();

                for(j = 0; j < var22.size(); ++j) {
                    View ld = (View)var22.get(j);
                    if(ld.getVisibility() != 8) {
                        MarginLayoutParams lp = (MarginLayoutParams)ld.getLayoutParams();
                        int lc = lf + lp.leftMargin;
                        int tc = var23 + lp.topMargin;
                        int rc = lc + ld.getMeasuredWidth();
                        int bc = tc + ld.getMeasuredHeight();
                        ld.layout(lc, tc, rc, bc);
                        lf += ld.getMeasuredWidth() + lp.rightMargin + lp.leftMargin;
                    }
                }

                lf = 0;
                var23 += lh;
            }
        }

    }

    protected abstract View getView(T var1);
}

