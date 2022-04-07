package com.sr.superhelperx.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.sr.superhelperx.bound.BoundViewHelper;
import com.sr.superhelperx.scale.ScaleScreenHelperFactory;
import com.sr.superhelperx.util.UtilInstance;

import java.util.List;

/**
 * Created by Hang.Yang on 2018/8/17 15:06.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public abstract class AppHolderAdapter<T, H extends AppHolderAdapter.ViewHolder<T>> extends AppAdapter<T> {
    private Class<?> c = UtilInstance.GenericityClass(this.getClass(), 1);

    public AppHolderAdapter(Object object) {
        super(object);
    }

    public AppHolderAdapter(Object object, List<T> list) {
        super(object, list);
    }

    public View getView(int p, View v, ViewGroup g) {
        try {
            AppHolderAdapter.ViewHolder e;
            if(v == null) {
                v = ScaleScreenHelperFactory.getInstance().loadViewGroup((ViewGroup) BoundViewHelper.boundView(e = (AppHolderAdapter.ViewHolder)UtilInstance.Instance(this.c, new Class[]{AppHolderAdapter.class}, new Object[]{this}), this.inflater.inflate(e.resourceId(), (ViewGroup)null)));
                v.setTag(e);
                e.onCreated(this.context, v);
                e.object(this.object);
            } else {
                e = (AppHolderAdapter.ViewHolder)v.getTag();
            }

            e.load(this.context, p, v, this.getGenericityItem(p));
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return v;
    }

    public abstract static class ViewHolder<T> {
        protected AppHolderAdapter appHolderAdapter;
        protected Object object;

        protected ViewHolder(AppHolderAdapter appHolderAdapter) {
            this.appHolderAdapter = appHolderAdapter;
        }

        void object(Object object) {
            this.object = object;
        }

        protected void onCreated(Context context, View v) {
        }

        protected abstract int resourceId();

        protected abstract void load(Context var1, int var2, View var3, T var4);
    }
}
