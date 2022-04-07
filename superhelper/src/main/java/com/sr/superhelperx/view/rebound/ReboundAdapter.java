package com.sr.superhelperx.view.rebound;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Hang.Yang on 2018/8/17 16:41.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public abstract class ReboundAdapter<T> extends ArrayAdapter<T> {
    private ReboundListView r;
    private int t = -1;

    public ReboundAdapter(Context context, ReboundListView reboundListView, List<T> objects) {
        super(context, 0, objects);
        this.r = reboundListView;
    }

    public View getView(int p, View c, ViewGroup a) {
        ReboundLayout l = this.getReboundLayout(p, (ReboundLayout)c, a);
        if(this.r.isFirst()) {
            this.t = p;
            l.setVisibility(4);
        } else if(this.t < p) {
            this.t = p;
            l.start();
        } else {
            l.stop();
        }

        return l;
    }

    public void totalPositionSubtraction(int count) {
        this.t = this.t >= count?this.t - count:0;
        this.notifyDataSetChanged();
    }

    public void totalPositionReset() {
        this.t = 0;
        this.notifyDataSetChanged();
        this.r.start();
    }

    public abstract ReboundLayout getReboundLayout(int var1, ReboundLayout var2, ViewGroup var3);
}
