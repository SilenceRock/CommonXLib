package com.sr.superhelperx.paper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hang.Yang on 2018/8/17 16:05.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@SuppressLint({"HandlerLeak"})
public abstract class CarouselGroup<T> extends Carousel<T> {
    private List<View> vs = new ArrayList();
    private OnCarouselItemListener<T> o;

    public CarouselGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setItemList(final List<T> items) {
        this.cl();
        final Handler h = new Handler() {
            public void handleMessage(Message m) {
                switch(m.what) {
                    case 0:
                        CarouselGroup.this.d();
                        break;
                    case 1:
                        CarouselGroup.this.ns((List)m.obj);
                }

            }
        };
        (new Thread() {
            public void run() {
                CarouselGroup.this.vs.clear();
                Looper.prepare();
                GridView g = CarouselGroup.this.getPager(new GridView(CarouselGroup.this.getContext()));
                ArrayList ts = new ArrayList();

                for(int m = 0; m < items.size(); ++m) {
                    ts.add(items.get(m));
                    if((m + 1) % CarouselGroup.this.getNum() == 0) {
                        g.setAdapter(CarouselGroup.this.new ga(CarouselGroup.this.getContext(), ts));
                        CarouselGroup.this.vs.add(g);
                        g = CarouselGroup.this.getPager(new GridView(CarouselGroup.this.getContext()));
                        ts = new ArrayList();
                        h.sendEmptyMessage(0);
                    } else if(m == items.size() - 1) {
                        g.setAdapter(CarouselGroup.this.new ga(CarouselGroup.this.getContext(), ts));
                        CarouselGroup.this.vs.add(g);
                        h.sendEmptyMessage(0);
                    }
                }

                Message var4 = h.obtainMessage();
                var4.what = 1;
                var4.obj = CarouselGroup.this.vs;
                var4.sendToTarget();
                Looper.loop();
            }
        }).start();
    }

    public void setOnCarouselItemListener(OnCarouselItemListener<T> onCarouselItemClickListener) {
        this.o = onCarouselItemClickListener;
    }

    public void reset() {
        for(int i = 0; i < this.vs.size(); ++i) {
            CarouselGroup.ga g = (CarouselGroup.ga)((GridView)this.vs.get(i)).getAdapter();

            for(int j = 0; j < g.getCount(); ++j) {
                this.onReset((T) g.getItem(j));
            }
        }

    }

    public void notifyChanged() {
        for(int i = 0; i < this.vs.size(); ++i) {
            ((CarouselGroup.ga)((GridView)this.vs.get(i)).getAdapter()).notifyDataSetChanged();
        }

    }

    protected void onReset(T t) {
    }

    protected abstract int getNum();

    protected abstract GridView getPager(GridView var1);

    protected abstract View getView(int var1, T var2);

    private class ga extends ArrayAdapter<T> {
        public ga(Context var1, List<T> c) {
            super(var1, 0, c);
        }

        public View getView(int p, View c, ViewGroup r) {
            Object t = this.getItem(p);
            View v = CarouselGroup.this.getView(p, (T) t);
            v.setTag(t);
            v.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(CarouselGroup.this.o != null) {
                        try {
                            CarouselGroup.this.o.onCarouselItemClick((T) v.getTag());
                        } catch (Exception var3) {
                            var3.printStackTrace();
                        }
                    }

                }
            });
            return v;
        }
    }
}
