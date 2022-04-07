package com.sr.superhelperx.dialog;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sr.superhelperx.R;
import com.sr.superhelperx.scale.ScaleScreenHelperFactory;
import com.sr.superhelperx.util.UtilInstance;

/**
 * Created by Hang.Yang on 2018/8/17 15:25.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public abstract class AppWaitDialog<T extends View> extends AppDialog {
    private Class<?> c = UtilInstance.GenericityClass(this.getClass(), 0);
    private FrameLayout l;
    private TextView v;
    private T t;

    public AppWaitDialog(Context context) {
        super(context, R.style.Transparent_Dialog);
        this.setContentView(R.layout.dialog_wait);
        this.setCanceledOnTouchOutside(false);
        FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(this.width(), this.height());
        p.gravity = 17;
        this.t = (T) UtilInstance.Instance(this.c, new Class[]{Context.class}, new Object[]{context});
        this.t.setLayoutParams(p);
        this.layoutBackdrop(this.l = (FrameLayout)this.findViewById(R.id.wait_layout));
        this.l.setLayoutParams(new android.widget.LinearLayout.LayoutParams(this.layoutWidth(), this.layoutHeight()));
        this.l.addView(this.t);
        this.v = (TextView) ScaleScreenHelperFactory.getInstance().loadViewSize(new TextView(this.getContext()), 23);
        p = new FrameLayout.LayoutParams(-2, -2);
        p.gravity = 17;
        this.v.setLayoutParams(p);
        this.v.setTextColor(-1);
        this.l.addView(this.v);
    }

    protected void onStart() {
        super.onStart();
        this.start(this.t);
    }

    protected void onStop() {
        this.stop(this.t);
        super.onStop();
    }

    public void percentage(int p) {
        this.v.setText(p + "%");
    }

    protected void layoutBackdrop(FrameLayout layout) {
        layout.setBackgroundResource(R.drawable.shape_wait_backdrop);
    }

    protected int layoutWidth() {
        return ScaleScreenHelperFactory.getInstance().getWidthHeight(150);
    }

    private int layoutHeight() {
        return ScaleScreenHelperFactory.getInstance().getWidthHeight(150);
    }

    protected int width() {
        return ScaleScreenHelperFactory.getInstance().getWidthHeight(120);
    }

    private int height() {
        return ScaleScreenHelperFactory.getInstance().getWidthHeight(120);
    }

    abstract void start(T var1);

    abstract void stop(T var1);
}
