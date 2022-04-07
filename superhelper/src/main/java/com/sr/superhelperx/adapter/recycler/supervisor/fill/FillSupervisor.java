package com.sr.superhelperx.adapter.recycler.supervisor.fill;

import android.os.Build;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.sr.superhelperx.R;

/**
 * Created by Hang.Yang on 2018/8/17 15:20.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class FillSupervisor extends RecyclerView.OnScrollListener {
    private LinearLayoutManager l;
    private RecyclerView r;
    private int s = -1;
    private int i;

    public FillSupervisor(RecyclerView r, LinearLayoutManager l) {
        this.r = r;
        this.l = l;
    }

    public void start(int i) {
        this.i = i;
        if(Build.VERSION.SDK_INT >= 23) {
            this.r.addOnScrollListener(this);
        } else {
            this.r.setOnScrollListener(this);
        }

    }

    public void stop() {
        if(Build.VERSION.SDK_INT >= 23) {
            this.r.removeOnScrollListener(this);
        } else {
            this.r.setOnScrollListener((RecyclerView.OnScrollListener)null);
        }

    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        View v = null;
        View t = null;
        int p;
        if((p = this.l.findFirstVisibleItemPosition()) == this.i) {
            int h = this.r.getHeight();

            try {
                for(int i = 0; i < this.l.getItemCount(); ++i) {
                    try {
                        if((v = this.l.findViewByPosition(p + i)).getTag(R.id.fill_tag_id).equals("fill_view")) {
                            t = v;
                        }
                    } catch (Exception var11) {
                        h -= v.getHeight();
                    }
                }
            } catch (Exception var12) {
                ;
            }

            try {
                t.findViewById(R.id.view_recycler_fill_view).setLayoutParams(new LinearLayout.LayoutParams(-1, h));
            } catch (Exception var10) {
                ;
            }
        }

    }
}