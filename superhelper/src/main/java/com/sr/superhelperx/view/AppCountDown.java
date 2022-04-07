package com.sr.superhelperx.view;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.sr.superhelperx.receiver.AppReceiver;
import com.sr.superhelperx.service.CountDownService;
import com.sr.superhelperx.util.UtilTime;

/**
 * Created by Hang.Yang on 2018/8/17 16:30.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public abstract class AppCountDown extends LinearLayout {
    private String tag = "default";
    private AppCountDown.CountDownReceiver receiver = new AppCountDown.CountDownReceiver();
    private IntentFilter intentFilter;

    public AppCountDown(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.intentFilter = new IntentFilter("qwertasdfg_" + this.tag);
    }

    public void setTimerTag(String tag) {
        this.getContext().registerReceiver(this.receiver, this.intentFilter = new IntentFilter("qwertasdfg_" + (this.tag = tag)));

        try {
            long millis = CountDownService.INSTANCE.getDownTimer(tag);
            if(millis > 0L) {
                this.receiver.setTime(millis);
            } else {
                this.onFinish();
            }
        } catch (Exception var4) {
            ;
        }

    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.getContext().registerReceiver(this.receiver, this.intentFilter);

        try {
            long millis = CountDownService.INSTANCE.getDownTimer(this.tag);
            if(millis > 0L) {
                this.receiver.setTime(millis);
            } else {
                this.onFinish();
            }
        } catch (Exception var3) {
            ;
        }

    }

    protected void onDetachedFromWindow() {
        this.getContext().unregisterReceiver(this.receiver);
        super.onDetachedFromWindow();
    }

    public void onFinish() {
    }

    public abstract void onTime(String var1, String var2, String var3, String var4);

    public class CountDownReceiver extends AppReceiver {
        public static final String ACTION = "qwertasdfg_";
        public static final String MILLIS = "millis";
        public static final String TYPE = "type";
        public static final int RUN = 22;
        public static final int FINISH = 33;
        private UtilTime utilTime = new UtilTime() {
            public void onConversion(String s, String s1, String s2, String s3) {
                AppCountDown.this.onTime(s, s1, s2, s3);
            }
        };

        public CountDownReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            switch(intent.getIntExtra("type", 0)) {
                case 22:
                    this.setTime(intent.getLongExtra("millis", 0L));
                    break;
                case 33:
                    AppCountDown.this.onFinish();
            }

        }

        public void setTime(long time) {
            this.utilTime.loadMillisecond(UtilTime.DATE_HOUR_MINUTE_SECOND, time);
        }
    }
}
