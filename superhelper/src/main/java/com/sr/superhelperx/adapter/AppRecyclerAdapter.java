package com.sr.superhelperx.adapter;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sr.superhelperx.R;
import com.sr.superhelperx.adapter.recycler.DivisionItem;
import com.sr.superhelperx.adapter.recycler.DivisionView;
import com.sr.superhelperx.adapter.recycler.FillItem;
import com.sr.superhelperx.adapter.recycler.FillView;
import com.sr.superhelperx.adapter.recycler.LoadItem;
import com.sr.superhelperx.adapter.recycler.LoadView;
import com.sr.superhelperx.adapter.recycler.supervisor.fill.FillSupervisor;
import com.sr.superhelperx.adapter.recycler.supervisor.flotage.FlotageSupervisor;
import com.sr.superhelperx.adapter.recycler.supervisor.flotage.FlotageView;
import com.sr.superhelperx.bound.BoundViewHelper;
import com.sr.superhelperx.glide.x;
import com.sr.superhelperx.scale.ScaleScreenHelperFactory;
import com.sr.superhelperx.sign.g;
import com.sr.superhelperx.util.UtilAsyHandler;
import com.sr.superhelperx.util.UtilInstance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Hang.Yang on 2018/8/17 15:06.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

/*@f
@z*/
public class AppRecyclerAdapter<T extends ArrayList<AppRecyclerAdapter.Item>> extends RecyclerView.Adapter<AppRecyclerAdapter.ViewHolder> {
    private Map<Class<? extends ViewHolder>, ViewHolder<? extends Item>> h = new HashMap();
    private List<Class<? extends ViewHolder>> v = new ArrayList();
    private Map<Class<? extends AppRecyclerAdapter.Item>, Integer> m = new HashMap();
    private AppRecyclerAdapter.Item d = new LoadItem();
    private AppRecyclerAdapter.Item b = new FillItem();
    private FlotageSupervisor s;
    private FillSupervisor u;
    private LayoutInflater f;
    private boolean k;
    private boolean j;
    private int p = -1;
    protected ArrayList<AppRecyclerAdapter.Item> l;
    protected Context context;
    protected Object object;
    public RecyclerView.LayoutManager layoutManager;

    public AppRecyclerAdapter(Object object) {
        if((this.context = x.c(this.object = object)) != null && g.a(this)) {
            this.addItemHolder(LoadItem.class, LoadView.class);
            this.addItemHolder(DivisionItem.class, DivisionView.class);
            this.addItemHolder(FillItem.class, FillView.class);
            this.f = LayoutInflater.from(this.context);

            try {
                this.l = (ArrayList) UtilInstance.Instance(UtilInstance.GenericityClass(this.getClass(), 0));
            } catch (Exception var3) {
                this.l = new ArrayList();
            }
        }

    }

    public FlotageSupervisor openFlotage(RecyclerView recyclerView, FlotageView flotageView) {
        this.closeFlotage();
        return this.s = new FlotageSupervisor(recyclerView, this, flotageView);
    }

    public void closeFlotage() {
        try {
            this.s.stop();
            this.s = null;
        } catch (Exception var2) {
            ;
        }

    }

    public void openFill(RecyclerView recyclerView, int index) {
        this.closeFill();

        try {
            (this.u = new FillSupervisor(recyclerView, (LinearLayoutManager)this.layoutManager)).start(index);
            this.l.add(this.b);
        } catch (Exception var4) {
            ;
        }

        this.j = true;
        this.notifyDataSetChanged();
    }

    public void closeFill() {
        try {
            this.u.stop();
            this.u = null;
            this.l.remove(this.b);
        } catch (Exception var2) {
            ;
        }

        this.j = false;
    }

    public void setLoad(boolean isLoad) {
        this.k = isLoad;
        this.notifyDataSetChanged();
    }

    public void dismissLoad() {
        try {
            LoadView.l.setVisibility(View.INVISIBLE);
        } catch (Exception var2) {
            ;
        }

    }

    public void onLoad() {
    }

    public RecyclerView.LayoutManager verticalLayoutManager(Context context) {
        return this.layoutManager = new LinearLayoutManager(context);
    }

    public RecyclerView.LayoutManager horizontalLayoutManager(Context context) {
        ((LinearLayoutManager)((LinearLayoutManager)(this.layoutManager = new LinearLayoutManager(context)))).setOrientation(0);
        return this.layoutManager;
    }

    public RecyclerView.LayoutManager gridLayoutManager(Context context, int spanCount) {
        ((GridLayoutManager)((GridLayoutManager)(this.layoutManager = new GridLayoutManager(context, this.d.span = spanCount)))).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            public int getSpanSize(int p) {
                return ((AppRecyclerAdapter.Item)AppRecyclerAdapter.this.l.get(p)).span;
            }
        });
        return this.layoutManager;
    }

    public RecyclerView.LayoutManager verticalStaggeredGridLayoutManager(int spanCount) {
        return this.layoutManager = new StaggeredGridLayoutManager(spanCount, 1);
    }

    public RecyclerView.LayoutManager horizontalStaggeredGridLayoutManager(int spanCount) {
        return this.layoutManager = new StaggeredGridLayoutManager(spanCount, 0);
    }

    public T getList() {
        return (T) this.l;
    }

    public void setList(List<AppRecyclerAdapter.Item> list) {
        this.setList(list, true);
    }

    public void setList(List<AppRecyclerAdapter.Item> list, boolean isNotify) {
        this.l.clear();
        this.notifyDataSetChanged();
        this.addList(list, isNotify);

        try {
            this.layoutManager.scrollToPosition(0);
        } catch (Exception var4) {
            ;
        }

    }

    public void addItem(AppRecyclerAdapter.Item... items) {
        this.addList(Arrays.asList(items), true);
    }

    public void addList(List<AppRecyclerAdapter.Item> list) {
        this.addList(list, true);
    }

    public void addList(List<AppRecyclerAdapter.Item> list, boolean isNotify) {
        this.l.addAll(list);
        if(this.k) {
            this.l.remove(this.d);
            this.l.add(this.d);
        }

        if(this.j) {
            this.l.remove(this.b);
            this.l.add(this.b);
        }

        if(isNotify) {
            this.appNotifyDataSetChanged();
        }

    }

    public void remove(AppRecyclerAdapter.Item... items) {
        this.remove(Arrays.asList(items));
    }

    public void remove(List<AppRecyclerAdapter.Item> list) {
        this.l.removeAll(list);
        this.appNotifyDataSetChanged();
    }

    public void clear() {
        this.l.clear();
    }

    public void getItem(final AppRecyclerAdapter.GetItemCallBack getItemCallBack) {
        UtilAsyHandler var10001 = new UtilAsyHandler() {

            protected Object doHandler() {

                List s = (List)AppRecyclerAdapter.this.l.clone();

                try {
                    if(getItemCallBack.i > -1) {
                        for(int var6 = 0; var6 < s.size(); ++var6) {
                            AppRecyclerAdapter.Item var7 = (AppRecyclerAdapter.Item)s.get(var6);
                            if(var7.getClass().equals(getItemCallBack.c)) {
                                if(getItemCallBack.i == 0) {
                                    return var7;
                                }

                                --getItemCallBack.i;
                            }
                        }

                        return null;
                    } else {
                        ArrayList ss = new ArrayList();

                        for(int i = 0; i < s.size(); ++i) {
                            AppRecyclerAdapter.Item t = (AppRecyclerAdapter.Item)s.get(i);
                            if(t.getClass().equals(getItemCallBack.c)) {
                                ss.add(t);
                            }
                        }

                        return ss;
                    }
                } catch (Exception var5) {
                    return null;
                }
            }

            protected void doComplete(Object t) {
                if(t instanceof List) {
                    getItemCallBack.onItemList((List)t);
                } else {
                    getItemCallBack.onItem((AppRecyclerAdapter.Item)t);
                }

            }
        };
    }

    public void keepItem(final AppRecyclerAdapter.KeepItemCallBack keepItemCallBack) {
        UtilAsyHandler var10001 = new UtilAsyHandler() {

            protected List<AppRecyclerAdapter.Item> doHandler() {
                List k = (List)AppRecyclerAdapter.this.l.clone();
                Class[] cs = keepItemCallBack.getKeepItem();

                for(int i = 0; i < AppRecyclerAdapter.this.l.size(); ++i) {
                    boolean b = true;
                    AppRecyclerAdapter.Item t = (AppRecyclerAdapter.Item)AppRecyclerAdapter.this.l.get(i);

                    for(int j = 0; j < cs.length; ++j) {
                        if(t.getClass().equals(cs[j])) {
                            b = false;
                            break;
                        }
                    }

                    if(b) {
                        k.remove(t);
                    }
                }

                return k;
            }

            @Override
            protected void doComplete(Object t) {

                try {
                    AppRecyclerAdapter.this.l.clear();
                    AppRecyclerAdapter.this.l.addAll((List<Item>)t);
                    AppRecyclerAdapter.this.notifyDataSetChanged();
                    AppRecyclerAdapter.this.l.addAll(keepItemCallBack.getNewList());
                    if(AppRecyclerAdapter.this.k) {
                        AppRecyclerAdapter.this.l.remove(AppRecyclerAdapter.this.d);
                        AppRecyclerAdapter.this.l.add(AppRecyclerAdapter.this.d);
                    }

                    if(AppRecyclerAdapter.this.j) {
                        AppRecyclerAdapter.this.l.remove(AppRecyclerAdapter.this.b);
                        AppRecyclerAdapter.this.l.add(AppRecyclerAdapter.this.b);
                    }
                } catch (Exception var3) {
                    ;
                }

                AppRecyclerAdapter.this.appNotifyDataSetChanged();
            }

            protected void doComplete(List<AppRecyclerAdapter.Item> t) {

            }
        };
    }

    public AppRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup vg, int p) {
        AppRecyclerAdapter.ViewHolder vh = null;

        try {
            Class e = (Class)this.v.get(p);
            if(!this.h.containsKey(e)) {
                this.h.put(e, vh = this.n(e, new View(this.context)));
            } else {
                vh = (AppRecyclerAdapter.ViewHolder)this.h.get(e);
            }

            vh = this.n(e, this.f.inflate(vh.resourceId(), vg, false));
            vh.object(this.object);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return vh;
    }

    public void onBindViewHolder(AppRecyclerAdapter.ViewHolder vh, int p) {
        AppRecyclerAdapter.Item i = (AppRecyclerAdapter.Item)this.l.get(p);
        if(this.s != null && !i.i) {
            this.s.handler(i);

            try {
                vh.getItemView().setTag(R.id.flotage_tag_id, i.getClass());
            } catch (Exception var5) {
                ;
            }
        }

        vh.inject(p, i);
    }

    public int getItemViewType(int p) {
        return ((Integer)this.m.get(((AppRecyclerAdapter.Item)this.l.get(p)).getClass())).intValue();
    }

    public int getItemCount() {
        return this.l.size();
    }

    public final void appNotifyDataSetChanged() {
        UtilAsyHandler var10001 = new UtilAsyHandler() {
            protected Object doHandler() {
                AppRecyclerAdapter.Item s = null;

                for(int i = 0; i < AppRecyclerAdapter.this.l.size(); ++i) {
                    AppRecyclerAdapter.Item m = (AppRecyclerAdapter.Item)AppRecyclerAdapter.this.l.get(i);

                    try {
                        m.position = s.getClass().equals(m.getClass())?s.position + 1:0;
                    } catch (Exception var5) {
                        m.position = 0;
                    }

                    s = m;
                }

                return null;
            }

            protected void doComplete(Object o) {
                AppRecyclerAdapter.this.notifyDataSetChanged();
            }
        };
    }

    public void addItemHolder(Class<? extends AppRecyclerAdapter.Item> itemClass, Class<? extends AppRecyclerAdapter.ViewHolder> viewHolderClass) {
        this.v.add(viewHolderClass);
        this.m.put(itemClass, Integer.valueOf(this.v.size() - 1));
    }

    public Class<? extends AppRecyclerAdapter.ViewHolder> getItemHolder(Class<? extends AppRecyclerAdapter.Item> itemClass) {
        return (Class)this.v.get(((Integer)this.m.get(itemClass)).intValue());
    }

    protected AppRecyclerAdapter.ViewHolder n(Class s, View v) {
        return (AppRecyclerAdapter.ViewHolder)UtilInstance.Instance(s, new Class[]{AppRecyclerAdapter.class, Context.class, View.class}, new Object[]{this, this.context, v instanceof ViewGroup? ScaleScreenHelperFactory.getInstance().loadViewGroup((ViewGroup)v):v});
    }

    public interface KeepItemCallBack {
        Class<? extends AppRecyclerAdapter.Item>[] getKeepItem();

        List<AppRecyclerAdapter.Item> getNewList();
    }

    public abstract static class GetItemCallBack<T extends AppRecyclerAdapter.Item> {
        Class<?> c = UtilInstance.GenericityClass(this.getClass(), 0);
        int i = -1;

        public GetItemCallBack() {
        }

        public GetItemCallBack(int index) {
            this.i = index;
        }

        public void onItem(T t) {
        }

        public void onItemList(List<T> t) {
        }
    }

    public abstract static class ViewHolder<I extends AppRecyclerAdapter.Item> extends RecyclerView.ViewHolder {
        protected AppRecyclerAdapter adapter;
        protected Context context;
        protected Object object;

        public ViewHolder(AppRecyclerAdapter adapter, Context context, View view) {
            super(view);
            this.adapter = adapter;
            this.context = context;
            BoundViewHelper.boundView(this, view);
            view.setTag(R.id.flotage_tag_id, this.getClass());
        }

        void object(Object object) {
            this.object = object;
        }

        protected <T> T getAdapter(Class<T> c) {
            return (T) this.adapter;
        }

        private void inject(int p, I i) {
            if(this.reuse() || !i.i) {
                i.i = true;
                this.load(p, i);
            }

        }

        public boolean reuse() {
            return true;
        }

        public View getItemView() {
            return this.itemView;
        }

        public abstract int resourceId();

        public abstract void load(int var1, I var2);
    }

    public abstract static class Item implements Serializable {
        private static final long serialVersionUID = 1L;
        public int span = 1;
        public int position = -1;
        public Object object;
        boolean i = false;

        public Item() {
        }
    }
}
