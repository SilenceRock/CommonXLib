package com.sr.superhelperx.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.sr.superhelperx.bound.BoundViewHelper;
import com.sr.superhelperx.scale.ScaleScreenHelperFactory;

/**
 * Created by Hang.Yang on 2018/8/17 15:24.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class AppDialog extends Dialog {
    public AppDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        BoundViewHelper.boundView(this, ScaleScreenHelperFactory.getInstance().loadViewGroup((ViewGroup)this.getWindow().getDecorView()));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = this.getWindow();
        WindowManager.LayoutParams l = w.getAttributes();
        l.width = -1;
        w.setAttributes(l);
    }
}
