package com.sr.superhelperx.adapter.recycler;

import com.sr.superhelperx.adapter.AppRecyclerAdapter;

/**
 * Created by Hang.Yang on 2018/8/17 15:11.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class DivisionItem extends AppRecyclerAdapter.Item {
    public int space;
    public int color = 0;

    public DivisionItem(int space) {
        this.space = space;
    }

    public DivisionItem(int span, int space) {
        this.span = span;
        this.space = space;
    }
}
