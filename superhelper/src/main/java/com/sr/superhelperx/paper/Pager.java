package com.sr.superhelperx.paper;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sr.superhelperx.paper.trans.Trans;
import com.sr.superhelperx.paper.trans.a;
import com.sr.superhelperx.paper.trans.b;
import com.sr.superhelperx.sign.g;

/**
 * Created by Hang.Yang on 2018/8/17 16:06.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

/*@f
@z*/
abstract class Pager extends RelativeLayout implements ViewPager.OnPageChangeListener {
    LinearLayout l;
    ViewPager v;

    @TargetApi(9)
    public Pager(Context c, AttributeSet a) {
        super(c, a);
        if(g.a(this)) {
            this.addView(this.v = new ViewPager(this.getContext()));
            this.v.setLayoutParams(new LayoutParams(-1, -1));
            if(Build.VERSION.SDK_INT > 23) {
                this.v.addOnPageChangeListener(this);
            } else {
                this.v.setOnPageChangeListener(this);
            }

            this.v.setPageTransformer(true, this.getTrans());
            this.addView(this.indicatorLayout(this.l = new LinearLayout(this.getContext())));
            LayoutParams p = new LayoutParams(-1, -2);
            p.addRule(12, -1);
            this.l.setLayoutParams(p);
        }

    }

    void d() {
        android.widget.LinearLayout.LayoutParams p = new android.widget.LinearLayout.LayoutParams(-2, -2);
        p.leftMargin = p.rightMargin = 5;
        View w = new View(this.getContext());
        w.setLayoutParams(p);
        this.l.addView(this.indicator(w));
    }

    void s(int p) {
        for(int i = 0; i < this.l.getChildCount(); ++i) {
            this.l.getChildAt(i).setBackgroundResource(this.indicatorOff());
        }

        this.l.getChildAt(p).setBackgroundResource(this.indicatorOn());
    }

    void c() {
        this.l.removeAllViews();
    }

    private ViewPager.PageTransformer getTrans() {
        return (ViewPager.PageTransformer)(this.trans() == Trans.CLIP?new b():(this.trans() == Trans.CHAIN?new a():new ViewPager.PageTransformer() {
            public void transformPage(View v, float p) {
                Pager.this.transformPage(v, p);
            }
        }));
    }

    public void onPageScrollStateChanged(int state) {
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    public void onPageSelected(int position) {
    }

    protected Trans trans() {
        return null;
    }

    protected void transformPage(View page, float position) {
    }

    protected View indicatorLayout(LinearLayout indicatorLayout) {
        return indicatorLayout;
    }

    protected View indicator(View indicator) {
        return indicator;
    }

    protected int indicatorOff() {
        return 0;
    }

    protected int indicatorOn() {
        return 0;
    }
}

