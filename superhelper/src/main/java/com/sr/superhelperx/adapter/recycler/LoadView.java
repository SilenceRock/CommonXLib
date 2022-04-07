package com.sr.superhelperx.adapter.recycler;

import android.content.Context;
import android.view.View;

import com.sr.superhelperx.R;
import com.sr.superhelperx.adapter.AppRecyclerAdapter;

/**
 * Created by Hang.Yang on 2018/8/17 15:18.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class LoadView extends AppRecyclerAdapter.ViewHolder<LoadItem> {
    public static View l;

    public LoadView(AppRecyclerAdapter adapter, Context context, View view) {
        super(adapter, context, view);

        try {
            l = view.findViewById(R.id.view_recycler_load);
        } catch (Exception var5) {
            ;
        }

    }

    public int resourceId() {
        return R.layout.view_recycler_load;
    }

    public void load(int position, LoadItem i) {
        if(l.getVisibility() == View.INVISIBLE) {
            l.setVisibility(View.VISIBLE);
            this.adapter.onLoad();
        }

    }
}
