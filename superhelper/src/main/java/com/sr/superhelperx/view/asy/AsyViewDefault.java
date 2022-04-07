package com.sr.superhelperx.view.asy;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sr.superhelperx.view.progress.WaitBackdropView;

/**
 * Created by Hang.Yang on 2018/8/17 16:36.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */
public class AsyViewDefault extends AsyViewLayout {
    public AsyViewDefault(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected ViewGroup onCreateRefresh(LayoutInflater layoutInflater) {
        TextView view = new TextView(this.getContext());
        view.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        view.setText("网络连接异常\n请点击重新加载");
        view.setGravity(17);
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AsyViewDefault.this.refresh();
            }
        });
        RelativeLayout relativeLayout = new RelativeLayout(this.getContext());
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        relativeLayout.setGravity(17);
        relativeLayout.addView(view);
        return relativeLayout;
    }

    protected ViewGroup onCreateUpload(LayoutInflater layoutInflater) {
        RelativeLayout relativeLayout = new RelativeLayout(this.getContext());
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        relativeLayout.setOnClickListener((View.OnClickListener) null);
        relativeLayout.setGravity(17);
        relativeLayout.addView(new WaitBackdropView(this.getContext()));
        return relativeLayout;
    }

    protected ViewGroup onCreateNothing(LayoutInflater layoutInflater) {
        TextView view = new TextView(this.getContext());
        view.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        view.setGravity(17);
        view.setText("暂无数据");
        RelativeLayout relativeLayout = new RelativeLayout(this.getContext());
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        relativeLayout.setGravity(17);
        relativeLayout.addView(view);
        return relativeLayout;
    }
}
