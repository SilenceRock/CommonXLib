package com.sr.superhelperx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import com.sr.superhelperx.glide.x;
import com.sr.superhelperx.sign.g;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Hang.Yang on 2018/8/17 15:05.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

/*@f
@z*/
public abstract class AppAdapter<T> extends BaseAdapter {
    protected List<T> list;
    protected LayoutInflater inflater;
    protected Context context;
    protected Object object;

    public AppAdapter(Object object) {
        this(object, (List)null);
    }

    public AppAdapter(Object object, List<T> list) {
        this.list = new ArrayList();
        if((this.context = x.c(this.object = object)) != null && g.a(this)) {
            this.inflater = LayoutInflater.from(this.context);
            if(list != null) {
                this.setList(list);
            }
        }

    }

    public void setItem(T... ts) {
        this.setList(Arrays.asList(ts));
    }

    public void setList(List<T> items) {
        this.list.clear();
        this.addList(items);
    }

    public void addItem(T... ts) {
        this.addList(Arrays.asList(ts));
    }

    public void addList(List<T> items) {
        this.list.addAll(items);
        this.notifyDataSetChanged();
    }

    public void remove(int position) {
        this.list.remove(position);
        this.notifyDataSetChanged();
    }

    public void remove(T... ts) {
        this.remove(Arrays.asList(ts));
    }

    public void remove(List<T> items) {
        this.list.removeAll(items);
        this.notifyDataSetChanged();
    }

    public T getGenericityItem(int position) {
        return (T) this.getItem(position);
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int arg0) {
        return this.list.get(arg0);
    }

    public long getItemId(int arg0) {
        return (long)arg0;
    }
}

