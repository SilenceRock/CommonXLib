package com.sr.superhelperx.rebound;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import com.sr.superhelperx.rebound.simple.a;

/**
 * Created by Hang.Yang on 2018/8/17 14:01.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@SuppressLint({"HandlerLeak"})
public class h {
    private List<a> a = new ArrayList();

    public h() {
    }

    public void b(a r) {
        r.b.setVisibility(View.INVISIBLE);
        r.a.setCurrentValue(1.0D);
        this.a.add(r);
    }

    public void c() {
        (new Handler() {
            private int size;

            {
                this.size = h.this.a.size();
            }

            public void handleMessage(Message msg) {
                try {
                    a r = (a)h.this.a.get(msg.what);
                    r.d(0.0D);
                    r.b.setVisibility(View.VISIBLE);
                    if(msg.what < this.size - 1) {
                        this.sendEmptyMessageDelayed(msg.what + 1, 100L);
                    } else {
                        h.this.a.clear();
                    }
                } catch (Exception var3) {
                    ;
                }

            }
        }).sendEmptyMessageDelayed(0, 200L);
    }
}
