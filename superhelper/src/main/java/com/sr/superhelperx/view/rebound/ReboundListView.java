package com.sr.superhelperx.view.rebound;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.sr.superhelperx.sign.g;

/**
 * Created by Hang.Yang on 2018/8/17 16:43.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

/*@f
@z*/
@SuppressLint({"HandlerLeak"})
public final class ReboundListView extends ListView {
    private boolean s;
    private boolean f;
    private boolean p;
    private int c;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what < ReboundListView.this.c) {
                try {
                    ((ReboundLayout)ReboundListView.this.getChildAt(msg.what)).start();
                    this.sendEmptyMessageDelayed(msg.what + 1, 75L);
                    ReboundListView.this.p = true;
                } catch (Exception var3) {
                    if(!ReboundListView.this.p) {
//                        ReboundListView.access$008(ReboundListView.this);
                        this.sendEmptyMessage(msg.what + 1);
                    } else {
                        ReboundListView.this.end();
                    }
                }
            } else {
                ReboundListView.this.end();
            }

        }
    };

    public ReboundListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView a, int i) {
            }

            public void onScroll(AbsListView v, int f, int i, int t) {
                if(g.a(ReboundListView.this)) {
                    ReboundListView.this.c = i;
                }

            }
        });
    }

    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        this.start();
    }

    public void start() {
        if(!this.s) {
            this.f = this.s = true;
            this.p = false;
            this.handler.sendEmptyMessageDelayed(0, 500L);
            this.setEnabled(false);
        }

    }

    public void end() {
        this.f = this.s = false;
        this.setEnabled(true);
    }

    public boolean isFirst() {
        return this.f;
    }
}

