package com.sr.superhelperx.adapter.recycler.supervisor.flotage;

import android.os.Build;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.sr.superhelperx.R;
import com.sr.superhelperx.adapter.AppRecyclerAdapter;
import com.sr.superhelperx.adapter.AppRecyclerAdapter.Item;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Hang.Yang on 2018/8/17 15:21.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class FlotageSupervisor {
    private List<Class<? extends Item>> list = new ArrayList();
    private RecyclerView.OnScrollListener onScrollListener;
    private AppRecyclerAdapter appRecyclerAdapter;
    private AppRecyclerAdapter.Item currentItem;
    private RecyclerView recyclerView;
    private FlotageView flotageView;

    public FlotageSupervisor(RecyclerView recyclerView, AppRecyclerAdapter appRecyclerAdapter, FlotageView flotageView) {
        try {
            this.appRecyclerAdapter = appRecyclerAdapter;
            this.recyclerView = recyclerView;
            this.flotageView = flotageView;
            if(appRecyclerAdapter.layoutManager instanceof LinearLayoutManager) {
                this.onScrollListener = new FlotageSupervisor.LinearLayoutOnScrollListener();
            } else if(appRecyclerAdapter.layoutManager instanceof GridLayoutManager) {
                this.onScrollListener = new FlotageSupervisor.GridLayoutOnScrollListener();
            }

            this.addOnScrollListener(this.onScrollListener);
        } catch (Exception var5) {
            ;
        }

    }

    private void addOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        if(Build.VERSION.SDK_INT >= 23) {
            this.recyclerView.addOnScrollListener(onScrollListener);
        } else {
            this.recyclerView.setOnScrollListener(onScrollListener);
        }

    }

    public void stop() {
        try {
            if(Build.VERSION.SDK_INT >= 23) {
                this.recyclerView.removeOnScrollListener(this.onScrollListener);
            } else {
                this.recyclerView.setOnScrollListener((RecyclerView.OnScrollListener)null);
            }
        } catch (Exception var2) {
            ;
        }
    }

    public FlotageSupervisor boundViewItem(Class... itemClasss) {

        for (int i = 0; i < itemClasss.length; i++) {
            this.list.add(itemClasss[i]);
        }
//        this.list.addAll((Collection<? extends Class<? extends Item>>) Arrays.asList(itemClasss));
        return this;
    }

    public void handler(AppRecyclerAdapter.Item item) {
        if(this.list.contains(item.getClass())) {
            this.currentItem = item;
        }

        item.object = this.currentItem;
    }

    private void moveTop(View view, int height) {
        try {
            view.setY((float)height);
        } catch (Exception var4) {
            ;
        }

    }

    private class GridLayoutOnScrollListener extends RecyclerView.OnScrollListener {
        private GridLayoutManager gridLayoutManager;

        private GridLayoutOnScrollListener() {
            this.gridLayoutManager = (GridLayoutManager)FlotageSupervisor.this.appRecyclerAdapter.layoutManager;
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        }
    }

    private class LinearLayoutOnScrollListener extends RecyclerView.OnScrollListener {
        private LinearLayoutManager linearLayoutManager;

        private LinearLayoutOnScrollListener() {
            this.linearLayoutManager = (LinearLayoutManager)FlotageSupervisor.this.appRecyclerAdapter.layoutManager;
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            int position = this.linearLayoutManager.findFirstVisibleItemPosition();

            try {
                FlotageSupervisor.this.flotageView.checkView(FlotageSupervisor.this.appRecyclerAdapter, FlotageSupervisor.this.appRecyclerAdapter.getItemHolder(((AppRecyclerAdapter.Item)FlotageSupervisor.this.appRecyclerAdapter.getList().get(position)).object.getClass()));
            } catch (Exception var7) {
                ;
            }

            View view = this.linearLayoutManager.findViewByPosition(position + 1);
            if(FlotageSupervisor.this.list.contains(view.getTag(R.id.flotage_tag_id))) {
                int height = FlotageSupervisor.this.flotageView.getHeight();
                if(view.getTop() <= height) {
                    FlotageSupervisor.this.moveTop(FlotageSupervisor.this.flotageView, -(height - view.getTop()));
                } else {
                    FlotageSupervisor.this.moveTop(FlotageSupervisor.this.flotageView, 0);
                }
            } else {
                FlotageSupervisor.this.moveTop(FlotageSupervisor.this.flotageView, 0);
            }

        }
    }
}
