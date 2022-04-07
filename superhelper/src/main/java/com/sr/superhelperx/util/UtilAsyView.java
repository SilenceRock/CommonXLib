package com.sr.superhelperx.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

/**
 * Created by Hang.Yang on 2018/8/17 14:30.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public abstract class UtilAsyView extends Handler {
    public UtilAsyView() {
        (new Thread() {
            public void run() {
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException var4) {
                    ;
                }

                Looper.prepare();
                Message m = UtilAsyView.this.obtainMessage();

                try {
                    m.what = 0;
                    m.obj = UtilAsyView.this.handler();
                } catch (Exception var3) {
                    m.what = 1;
                }

                m.sendToTarget();
                Looper.loop();
            }
        }).start();
    }

    public void handleMessage(Message m) {
        if(m.what == 0) {
            this.result((View)m.obj);
        } else {
            try {
                this.result(this.handler());
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }

    }

    protected abstract View handler();

    protected abstract void result(View var1);
}
