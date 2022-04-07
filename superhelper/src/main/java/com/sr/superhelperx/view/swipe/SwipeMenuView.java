package com.sr.superhelperx.view.swipe;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Hang.Yang on 2018/8/17 16:45.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@SuppressLint({"ViewConstructor"})
public class SwipeMenuView extends LinearLayout implements View.OnClickListener {
    private SwipeMenuListView mListView;
    private SwipeMenuLayout mLayout;
    private SwipeMenu mMenu;
    private SwipeMenuView.OnSwipeItemClickListener onItemClickListener;
    private int position;

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public SwipeMenuView(SwipeMenu menu, SwipeMenuListView listView) {
        super(menu.getContext());
        this.mListView = listView;
        this.mMenu = menu;
        List items = menu.getMenuItems();
        int id = 0;
        Iterator var5 = items.iterator();

        while(var5.hasNext()) {
            SwipeMenuItem item = (SwipeMenuItem)var5.next();
            this.addItem(item, id++);
        }

    }

    private void addItem(SwipeMenuItem item, int id) {
        LayoutParams params = new LayoutParams(item.getWidth(), -1);
        LinearLayout parent = new LinearLayout(this.getContext());
        parent.setId(id);
        parent.setGravity(17);
        parent.setOrientation(1);
        parent.setLayoutParams(params);
        parent.setBackgroundDrawable(item.getBackground());
        parent.setOnClickListener(this);
        this.addView(parent);
        if(item.getIcon() != null) {
            parent.addView(this.createIcon(item));
        }

        if(!TextUtils.isEmpty(item.getTitle())) {
            parent.addView(this.createTitle(item));
        }

    }

    private ImageView createIcon(SwipeMenuItem item) {
        ImageView iv = new ImageView(this.getContext());
        iv.setImageDrawable(item.getIcon());
        return iv;
    }

    private TextView createTitle(SwipeMenuItem item) {
        TextView tv = new TextView(this.getContext());
        tv.setText(item.getTitle());
        tv.setGravity(17);
        tv.setTextSize((float)item.getTitleSize());
        tv.setTextColor(item.getTitleColor());
        return tv;
    }

    public void onClick(View v) {
        if(this.onItemClickListener != null && this.mLayout.isOpen()) {
            this.onItemClickListener.onItemClick(this, this.mMenu, v.getId());
        }

    }

    public SwipeMenuView.OnSwipeItemClickListener getOnSwipeItemClickListener() {
        return this.onItemClickListener;
    }

    public void setOnSwipeItemClickListener(SwipeMenuView.OnSwipeItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setLayout(SwipeMenuLayout mLayout) {
        this.mLayout = mLayout;
    }

    public interface OnSwipeItemClickListener {
        void onItemClick(SwipeMenuView var1, SwipeMenu var2, int var3);
    }
}
