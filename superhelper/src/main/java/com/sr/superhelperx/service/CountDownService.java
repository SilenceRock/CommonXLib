package com.sr.superhelperx.service;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import androidx.annotation.Nullable;

import com.sr.superhelperx.app.AppApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hang.Yang on 2018/8/17 16:14.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class CountDownService extends Service {
    public static CountDownService INSTANCE;
    private static CountDownService.OnServiceCallBack OnServiceCallBack;
    private Map<String, DownTimer> timers = new HashMap();

    public CountDownService() {
    }

    public static void StartService(CountDownService.OnServiceCallBack onServiceCallBack) {
        if(INSTANCE != null) {
            onServiceCallBack.onService(INSTANCE);
        } else {
            OnServiceCallBack = onServiceCallBack;
            AppApplication.AINSTANCE.startService(new Intent(AppApplication.AINSTANCE, CountDownService.class));
        }

    }

    public void onCreate() {
        super.onCreate();
        CountDownService.OnServiceCallBack var10000 = OnServiceCallBack;
        INSTANCE = this;
        var10000.onService(this);
    }

    public long getDownTimer(String tag) {
        try {
            return ((CountDownService.DownTimer)this.timers.get(tag)).millis;
        } catch (Exception var3) {
            return 0L;
        }
    }

    public void addDownTimerStateCallBack(CountDownService.OnTimerStateCallBack onTimerStateCallBack) {
        this.addDownTimerStateCallBack("default", onTimerStateCallBack);
    }

    public void addDownTimerStateCallBack(String tag, CountDownService.OnTimerStateCallBack onTimerStateCallBack) {
        try {
            ((CountDownService.DownTimer)this.timers.get(tag)).addOnTimerStateCallBack(onTimerStateCallBack);
        } catch (Exception var6) {
            try {
                onTimerStateCallBack.onTimerState(false);
            } catch (Exception var5) {
                ;
            }
        }

    }

    public void countDown(int time) {
        this.countDown("default", time);
    }

    public void countDown(int time, CountDownService.OnTimerStateCallBack onTimerStateCallBack) {
        this.countDown("default", time, onTimerStateCallBack);
    }

    public void countDown(String tag, int time) {
        this.countDown(tag, time, (CountDownService.OnTimerStateCallBack)null);
    }

    public void countDown(String tag, int time, CountDownService.OnTimerStateCallBack onTimerStateCallBack) {
        CountDownService.DownTimer timer;
        if(this.timers.containsKey(tag)) {
            (timer = (CountDownService.DownTimer)this.timers.get(tag)).cancel();
            this.timers.remove(timer);
        }

        this.timers.put(tag, timer = new CountDownService.DownTimer(tag, (long)time));
        timer.addOnTimerStateCallBack(onTimerStateCallBack);
        timer.start();
    }

    public void onDestroy() {
        INSTANCE = null;
        OnServiceCallBack = null;
        super.onDestroy();
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class DownTimer extends CountDownTimer {
        private List<OnTimerStateCallBack> callBacks = new ArrayList();
        public long millis;
        public Intent intent;

        public DownTimer(String tag, long millisInFuture) {
            super(millisInFuture, 1000L);
            this.millis = millisInFuture;
            this.intent = new Intent("qwertasdfg_" + tag);
        }

        public void onTick(long millis) {
            CountDownService.this.sendBroadcast(this.intent.putExtra("type", 22).putExtra("millis", this.millis = millis));
        }

        public void onFinish() {
            CountDownService.this.sendBroadcast(this.intent.putExtra("type", 22).putExtra("millis", this.millis = 0L));
            CountDownService.this.sendBroadcast(this.intent.putExtra("type", 33));

            for(int i = 0; i < this.callBacks.size(); ++i) {
                try {
                    ((CountDownService.OnTimerStateCallBack)this.callBacks.get(i)).onTimerState(false);
                } catch (Exception var3) {
                    ;
                }
            }

            this.callBacks.clear();
            CountDownService.this.timers.remove(this);
        }

        public void addOnTimerStateCallBack(CountDownService.OnTimerStateCallBack onTimerStateCallBack) {
            if(onTimerStateCallBack != null) {
                try {
                    onTimerStateCallBack.onTimerState(this.millis != 0L);
                } catch (Exception var3) {
                    ;
                }

                this.callBacks.add(onTimerStateCallBack);
            }

        }
    }

    public interface OnTimerStateCallBack {
        void onTimerState(boolean var1) throws Exception;
    }

    public interface OnServiceCallBack {
        void onService(CountDownService var1);
    }
}

