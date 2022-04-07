package com.sr.superhelperx.paper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by Hang.Yang on 2018/8/17 16:05.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public abstract class CarouselChild<T> extends Carousel<T> {
    private OnCarouselItemListener<T> o;

    public CarouselChild(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setItemList(List<T> items) {
        this.cl();

        for(int i = 0; i < items.size(); ++i) {
            Object t = items.get(i);
            View v = this.getView((T) t);
            v.setTag(t);
            v.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(CarouselChild.this.o != null) {
                        try {
                            CarouselChild.this.o.onCarouselItemClick((T) v.getTag());
                        } catch (Exception var3) {
                            var3.printStackTrace();
                        }
                    }

                }
            });
            this.ad(v);
            this.d();
        }

        this.n();
    }

    void n() {
        super.n();
        this.setOnSelected(0);
    }

    public void onPageSelected(int p) {
        super.onPageSelected(p);
        this.setOnSelected(p);
    }

    public void setOnCarouselItemListener(OnCarouselItemListener<T> onCarouselItemClickListener) {
        this.o = onCarouselItemClickListener;
    }

    private void setOnSelected(int p) {
        if(this.o != null && this.gi().size() > 0) {
            try {
                this.o.onCarouselItemSelected(p, (T) ((View)this.gi().get(p)).getTag());
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }

    }

    protected abstract View getView(T var1);
}

