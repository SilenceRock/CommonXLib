package com.sr.superhelperx.adapter.recycler;

import android.content.Context;
import android.view.View;

import com.sr.superhelperx.R;
import com.sr.superhelperx.adapter.AppRecyclerAdapter;

/**
 * Created by Hang.Yang on 2018/8/17 15:16.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class FillView extends AppRecyclerAdapter.ViewHolder<FillItem> {
    public static final String TAG = "fill_view";

    public FillView(AppRecyclerAdapter adapter, Context context, View view) {
        super(adapter, context, view);
    }

    public int resourceId() {
        return R.layout.view_recycler_fill;
    }

    public void load(int position, FillItem fillItem) {
        this.itemView.setTag(R.id.fill_tag_id, "fill_view");
    }
}

