package com.sr.superhelperx.paper;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.viewpager.widget.PagerAdapter;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Hang.Yang on 2018/8/17 16:05.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@SuppressLint({"HandlerLeak"})
@TargetApi(9)
public abstract class Carousel<T> extends Pager {
    private List<View> vs = new ArrayList();
    private ScheduledExecutorService s;
    private Carousel<T>.pa a = new Carousel.pa();
    private Runnable r = new Runnable() {
        @TargetApi(9)
        public void run() {
            int c = Carousel.this.v.getCurrentItem();
            Carousel.this.h.sendEmptyMessage(c < Carousel.this.vs.size() - 1?c + 1:0);
        }
    };
    private Handler h = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message m) {
            Carousel.this.setCurrentItem(m.what);
        }
    };

    public Carousel(Context c, AttributeSet a) {
        super(c, a);
    }

    public void setCurrentItem(int position) {
        try {
            this.v.setCurrentItem(position);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    void ad(View v) {
        this.vs.add(v);
    }

    List<View> gi() {
        return this.vs;
    }

    void cl() {
        this.vs.clear();
        this.a.notifyDataSetChanged();
        this.c();
    }

    void ns(List<View> s) {
        this.vs.clear();
        this.vs.addAll(s);
        this.n();
    }

    void n() {
        this.v.setAdapter((PagerAdapter)null);
        if(this.vs.size() > 0) {
            this.v.setAdapter(this.a);
            this.s(0);
            this.tt();
        }

    }

    private void tp() {
        this.ss();

        try {
            this.s = Executors.newSingleThreadScheduledExecutor();
            this.s.scheduleAtFixedRate(this.r, (long)this.delayed(), (long)this.delayed(), TimeUnit.SECONDS);
        } catch (Exception var2) {
            ;
        }

    }

    private void ss() {
        try {
            this.s.shutdownNow();
        } catch (Exception var2) {
            ;
        }

    }

    private void tt() {
        if(this.isPlay()) {
            this.tp();
        }

    }

    protected boolean isPlay() {
        return false;
    }

    public void onPageSelected(int position) {
        this.s(position);
    }

    public void onPageScrollStateChanged(int state) {
        switch(state) {
            case 1:
                this.ss();
                break;
            default:
                this.tt();
        }

    }

    protected int delayed() {
        return 5;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.tt();
    }

    protected void onDetachedFromWindow() {
        this.ss();
        super.onDetachedFromWindow();
    }

    public abstract void setItemList(List<T> var1);

    public abstract void setOnCarouselItemListener(Carousel.OnCarouselItemListener<T> var1);

    public static class OnCarouselItemListener<T> {
        public OnCarouselItemListener() {
        }

        public void onCarouselItemClick(T t) throws Exception {
        }

        public void onCarouselItemSelected(int position, T t) throws Exception {
        }
    }

    private class pa extends PagerAdapter {
        private pa() {
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public Object instantiateItem(ViewGroup container, int position) {
            View view = (View)Carousel.this.vs.get(position);
            container.addView(view);
            return view;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            try {
                container.removeView((View)Carousel.this.vs.get(position));
            } catch (Exception var5) {
                ;
            }

        }

        public int getCount() {
            return Carousel.this.vs.size();
        }
    }
}

