package com.sr.superhelperx.adapter.recycler;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.sr.superhelperx.R;
import com.sr.superhelperx.adapter.AppRecyclerAdapter;
import com.sr.superhelperx.scale.ScaleScreenHelperFactory;

/**
 * Created by Hang.Yang on 2018/8/17 15:11.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class DivisionView extends AppRecyclerAdapter.ViewHolder<DivisionItem> {
    private TextView textView;

    public DivisionView(AppRecyclerAdapter adapter, Context context, View view) {
        super(adapter, context, view);

        try {
            this.textView = (TextView)view.findViewById(R.id.view_recycler_division);
        } catch (Exception var5) {
            ;
        }

    }

    public int resourceId() {
        return R.layout.view_recycler_division;
    }

    public void load(int position, DivisionItem divisionItem) {
        if(this.adapter.layoutManager instanceof LinearLayoutManager && ((LinearLayoutManager)this.adapter.layoutManager).getOrientation() == 0) {
            ScaleScreenHelperFactory.getInstance().loadViewWidthHeight(this.textView, divisionItem.space, 0);
        } else {
            ScaleScreenHelperFactory.getInstance().loadViewWidthHeight(this.textView, 0, divisionItem.space);
        }

        this.textView.setBackgroundColor(divisionItem.color);
    }
}
