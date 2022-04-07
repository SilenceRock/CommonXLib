package com.sr.superhelperx.paper;

import android.annotation.TargetApi;
import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.sr.superhelperx.sign.g;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hang.Yang on 2018/8/17 16:06.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */
public abstract class Guide extends Pager {
    private LayoutInflater li;
    private Guide.OnEndListener o;
    private boolean ed;
    private int ei;
    private List<View> y = new ArrayList();

    @TargetApi(3)
    public Guide(Context c, AttributeSet a) {
        super(c, a);
        this.li = LayoutInflater.from(c);
        if(this.slideEnd()) {
            this.v.setOnTouchListener(new View.OnTouchListener() {
                GestureDetector g = new GestureDetector(Guide.this.getContext(), new GestureDetector.SimpleOnGestureListener() {
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                        float x = e2.getX() - e1.getX();
                        float y = e2.getY() - e1.getY();
                        if(x < 0.0F && Guide.this.ed && Guide.this.o != null) {
                            Guide.this.o.onEnd();
                        }

                        return true;
                    }
                });

                public boolean onTouch(View v, MotionEvent e) {
                    this.g.onTouchEvent(e);
                    return false;
                }
            });
        }

    }

    public void addLayoutId(int... layoutIds) {
        if(g.a(this)) {
            for(int i = 0; i < layoutIds.length; ++i) {
                this.y.add(this.getView(this.li.inflate(layoutIds[i], (ViewGroup)null)));
                this.d();
            }

            this.v.setAdapter(new Guide.gp());
            this.s(0);
        }

    }

    public void onPageScrollStateChanged(int state) {
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    public void onPageSelected(int position) {
        this.s(position);
        this.ed = position == this.y.size() - 1;
    }

    public void setOnEndListener(Guide.OnEndListener onEndListener) {
        this.setOnEndListener(-1, onEndListener);
    }

    public void setOnEndListener(int id, Guide.OnEndListener onEndListener) {
        this.ei = id;
        this.o = onEndListener;
    }

    protected View getView(View view) {
        return view;
    }

    protected boolean slideEnd() {
        return true;
    }

    protected boolean clickEnd() {
        return true;
    }

    public interface OnEndListener {
        void onEnd();
    }

    private class gp extends PagerAdapter {
        private gp() {
        }

        public Object instantiateItem(ViewGroup c, int p) {
            View v = (View)Guide.this.y.get(p);
            c.addView(v);
            return v;
        }

        public void setPrimaryItem(ViewGroup c, int p, Object ob) {
            View v;
            if(Guide.this.clickEnd() && Guide.this.ei != -1 && (v = ((View)ob).findViewById(Guide.this.ei)) != null && Guide.this.o != null) {
                v.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Guide.this.o.onEnd();
                    }
                });
            }

        }

        public void destroyItem(ViewGroup c, int p, Object o) {
            c.removeView((View)Guide.this.y.get(p));
        }

        public boolean isViewFromObject(View v, Object o) {
            return v == o;
        }

        public int getCount() {
            return Guide.this.y.size();
        }
    }
}

