package com.sr.superhelperx.view.progress;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.sr.superhelperx.R;
import com.sr.superhelperx.scale.ScaleScreenHelperFactory;
import com.sr.superhelperx.util.UtilInstance;

/**
 * Created by Hang.Yang on 2018/8/17 16:40.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class AppWaitView<T extends View> extends LinearLayout {
    private Class<?> s = UtilInstance.GenericityClass(this.getClass(), 0);
    private LinearLayout l;
    private View t;

    public AppWaitView(Context c) {
        super(c);
        this.i(c);
    }

    public AppWaitView(Context c, AttributeSet a) {
        super(c, a);
        this.i(c);
    }

    public void i(Context c) {
        LayoutInflater.from(c).inflate(R.layout.view_wait, this);
        this.t = (View) UtilInstance.Instance(this.s, new Class[]{Context.class}, new Object[]{c});
        this.t.setLayoutParams(new LayoutParams(this.width(), this.height()));
        this.layoutBackdrop(this.l = (LinearLayout)this.findViewById(R.id.wait_layout));
        this.l.setLayoutParams(new LayoutParams(this.layoutWidth(), this.layoutHeight()));
        this.l.addView(this.t);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.start(this.t);
    }

    protected void onDetachedFromWindow() {
        this.stop(this.t);
        super.onDetachedFromWindow();
    }

    protected void layoutBackdrop(LinearLayout layout) {
    }

    protected int layoutWidth() {
        return ScaleScreenHelperFactory.getInstance().getWidthHeight(150);
    }

    protected int layoutHeight() {
        return ScaleScreenHelperFactory.getInstance().getWidthHeight(150);
    }

    protected int width() {
        return ScaleScreenHelperFactory.getInstance().getWidthHeight(120);
    }

    private int height() {
        return ScaleScreenHelperFactory.getInstance().getWidthHeight(120);
    }

    protected void start(View t) {
    }

    protected void stop(View t) {
    }
}

