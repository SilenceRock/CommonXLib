package com.sr.superhelperx.adapter.recycler.supervisor.flotage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sr.superhelperx.adapter.AppRecyclerAdapter;
import com.sr.superhelperx.scale.ScaleScreenHelperFactory;

import java.util.HashMap;
import java.util.Map;
import com.sr.superhelperx.adapter.AppRecyclerAdapter.ViewHolder;
import com.sr.superhelperx.util.UtilInstance;

/**
 * Created by Hang.Yang on 2018/8/17 15:22.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class FlotageView extends RelativeLayout {
    private Map<Class<AppRecyclerAdapter.ViewHolder>, View> map = new HashMap();
    private LayoutInflater layoutInflater;
    private View currentView;

    public FlotageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.layoutInflater = LayoutInflater.from(context);
    }

    public View checkView(AppRecyclerAdapter appRecyclerAdapter, Class<AppRecyclerAdapter.ViewHolder> viewHolderClass) {
        if(this.map.containsKey(viewHolderClass)) {
            return this.getView((View)this.map.get(viewHolderClass));
        } else {
            AppRecyclerAdapter.ViewHolder viewHolder = this.createHolder(appRecyclerAdapter, viewHolderClass, new View(this.getContext()));
            viewHolder = this.createHolder(appRecyclerAdapter, viewHolderClass, this.layoutInflater.inflate(viewHolder.resourceId(), this, false));
            View view = viewHolder.getItemView();
            view.setTag(viewHolder);
            this.map.put(viewHolderClass, view);
            return this.getView(view);
        }
    }

    private View getView(View view) {
        if(this.currentView == null || this.currentView != view) {
            this.addView(view);

            try {
                this.removeView(this.currentView);
            } catch (Exception var3) {
                ;
            }

            this.currentView = view;
        }

        return view;
    }

    private ViewHolder createHolder(AppRecyclerAdapter appRecyclerAdapter, Class s, View v) {
        return (ViewHolder) UtilInstance.Instance(s, new Class[]{AppRecyclerAdapter.class, Context.class, View.class}, new Object[]{appRecyclerAdapter, this.getContext(), v instanceof ViewGroup ? ScaleScreenHelperFactory.getInstance().loadViewGroup((ViewGroup)v):v});
    }
}