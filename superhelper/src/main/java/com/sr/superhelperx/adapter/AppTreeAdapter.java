package com.sr.superhelperx.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sr.superhelperx.bound.BoundViewHelper;
import com.sr.superhelperx.scale.ScaleScreenHelperFactory;
import com.sr.superhelperx.sign.g;
import com.sr.superhelperx.util.UtilAsyHandler;
import com.sr.superhelperx.util.UtilInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hang.Yang on 2018/8/17 15:10.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

/*@f
@z*/
public abstract class AppTreeAdapter extends RecyclerView.Adapter<AppTreeAdapter.ViewHolder> {
    private Map<Class<? extends ViewHolder>, ViewHolder> map = new HashMap();
    private ArrayList<Item> items = new ArrayList();
    private LinearLayoutManager linearLayoutManager;
    private AppTreeAdapter.OnSelectCallBack onSelectCallBack;
    private LayoutInflater layoutInflater;
    private Context context;

    public AppTreeAdapter(Context context, LinearLayoutManager linearLayoutManager) {
        if(g.a(this)) {
            this.layoutInflater = LayoutInflater.from(this.context = context);
            this.linearLayoutManager = linearLayoutManager;
        }
    }

    public int getItemCount() {
        return this.items.size();
    }

    public int getItemViewType(int position) {
        return ((AppTreeAdapter.Item)this.items.get(position)).grade;
    }

    public AppTreeAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        AppTreeAdapter.ViewHolder vh = null;

        try {
            Class e = this.onCreateViewHolderByGrade(type);
            if(!this.map.containsKey(e)) {
                this.map.put(e, vh = (AppTreeAdapter.ViewHolder) UtilInstance.Instance(e, new Class[]{View.class}, new Object[]{new View(this.context)}));
            } else {
                vh = (AppTreeAdapter.ViewHolder)this.map.get(e);
            }

            vh = (AppTreeAdapter.ViewHolder)UtilInstance.Instance(e, new Class[]{View.class}, new Object[]{ScaleScreenHelperFactory.getInstance().loadViewGroup((ViewGroup)this.layoutInflater.inflate(vh.resourceId(), viewGroup, false))});
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return vh;
    }

    public void onBindViewHolder(AppTreeAdapter.ViewHolder vh, int position) {
        vh.loadData(this.context, this, this.items.get(position));
    }

    public void processData() {
        this.refresh((AppTreeAdapter.Item)null, true, false, new AppTreeAdapter.OnAsyCallBack() {
            public ArrayList<AppTreeAdapter.Item> doHandler(ArrayList<AppTreeAdapter.Item> list) {
                list.clear();
                return AppTreeAdapter.this.loadData(list);
            }
        });
    }

    public void selectItem(final AppTreeAdapter.Item item) {
        this.refresh(item, true, false, new AppTreeAdapter.OnAsyCallBack() {
            public ArrayList<AppTreeAdapter.Item> doHandler(ArrayList<AppTreeAdapter.Item> list) {
                item.selecteType = item.selecteType == 1.0F?0.0F:1.0F;
                this.selectSuperior(item.superior);
                this.selectInferiors(item);
                return list;
            }

            public void selectSuperior(AppTreeAdapter.Item itemx) {
                if(itemx != null) {
                    float temp = 0.0F;

                    for(int i = 0; i < itemx.items.size(); ++i) {
                        temp += ((AppTreeAdapter.Item)itemx.items.get(i)).selecteType;
                    }

                    itemx.selecteType = temp == 0.0F?0.0F:(temp == 0.0F?1.0F:1.0E-5F);
                    this.selectSuperior(itemx.superior);
                }

            }

            public void selectInferiors(AppTreeAdapter.Item itemx) {
                for(int i = 0; i < itemx.items.size(); ++i) {
                    AppTreeAdapter.Item m = (AppTreeAdapter.Item)itemx.items.get(i);
                    m.selecteType = itemx.selecteType;
                    this.selectInferiors(m);
                }

            }
        });
    }

    public void spreadItem(AppTreeAdapter.Item item) {
        this.spreadItem(item, false, false);
    }

    public void spreadItem(AppTreeAdapter.Item item, boolean isAll) {
        this.spreadItem(item, isAll, false);
    }

    public void spreadItem(final AppTreeAdapter.Item item, final boolean isAll, final boolean isOther) {
        this.refresh(item, true, isOther, new AppTreeAdapter.OnAsyCallBack() {
            public ArrayList<AppTreeAdapter.Item> doHandler(ArrayList<AppTreeAdapter.Item> list) {
                this.spread(list, item, !item.isSpread, isAll, isOther);
                return list;
            }

            private int spread(ArrayList<AppTreeAdapter.Item> list, AppTreeAdapter.Item itemx, boolean isSpread, boolean isAllx, boolean isOtherx) {
                ArrayList items = (ArrayList)itemx.items;
                int index = -1;
                AppTreeAdapter.Item var11;
                int var12;
                if(itemx.isSpread = isSpread) {
                    if(isOtherx) {
                        ArrayList temp = (ArrayList)((ArrayList)(itemx.superior == null?list.clone():itemx.superior.items));

                        for(int i1 = 0; i1 < temp.size(); ++i1) {
                            AppTreeAdapter.Item i;
                            if((i = (AppTreeAdapter.Item)temp.get(i1)) != itemx) {
                                this.spread(list, i, i.isSpread = false, isAllx, isOtherx);
                            }
                        }
                    }

                    index = list.indexOf(itemx) + 1;
                    if(isAllx) {
                        for(var12 = 0; var12 < items.size(); ++var12) {
                            list.add(index, var11 = (AppTreeAdapter.Item)items.get(var12));
                            index = this.spread(list, var11, isSpread, isAllx, false);
                        }
                    } else {
                        list.addAll(index, items);
                    }
                } else {
                    for(var12 = 0; var12 < items.size(); ++var12) {
                        this.spread(list, var11 = (AppTreeAdapter.Item)items.get(var12), isSpread, isAllx, isOtherx);
                        list.remove(var11);
                    }
                }

                return index;
            }
        });
    }

    public void refresh() {
        this.refresh((AppTreeAdapter.Item)null, false, false, new AppTreeAdapter.OnAsyCallBack() {
            public ArrayList<AppTreeAdapter.Item> doHandler(ArrayList<AppTreeAdapter.Item> list) {
                return list;
            }
        });
    }

    @TargetApi(3)
    private synchronized void refresh(final AppTreeAdapter.Item item, final boolean isClone, final boolean isOther, final AppTreeAdapter.OnAsyCallBack onAsyCallBack) {
        (new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                return objects;
            }

            protected ArrayList<AppTreeAdapter.Item> doInBackground(Void... arg0) {
                return onAsyCallBack.doHandler((ArrayList)((ArrayList)(isClone?AppTreeAdapter.this.items.clone():AppTreeAdapter.this.items)));
            }

            protected void onPostExecute(ArrayList<AppTreeAdapter.Item> list) {
                if(isClone) {
                    AppTreeAdapter.this.items.clear();
                    AppTreeAdapter.this.items.addAll(list);
                }

                if(isOther && item.isSpread) {
                    AppTreeAdapter.this.linearLayoutManager.scrollToPosition(AppTreeAdapter.this.items.indexOf(item));
                }

                AppTreeAdapter.this.notifyDataSetChanged();
            }
        }).execute((Object) new Void[0]);
    }

    public void accountingSelected(final boolean isEnd) {
        UtilAsyHandler var10001 = new UtilAsyHandler() {
            @Override
            protected Object doInBackground(Object[] objects) {
                return null;
            }

            protected ArrayList<AppTreeAdapter.Item> doHandler() {
                final ArrayList list = new ArrayList();
                if(isEnd) {
                    AppTreeAdapter.this.filterSelectEnd(AppTreeAdapter.this.filterRootDirectory(AppTreeAdapter.this.items), new AppTreeAdapter.OnRootItemCallBack() {
                        public void onItemCallBack(AppTreeAdapter.Item item) {
                            list.add(item);
                        }
                    });
                } else {
                    list.addAll(AppTreeAdapter.this.filterSelectAll(AppTreeAdapter.this.filterRootDirectory(AppTreeAdapter.this.items)));
                }

                return list;
            }

            @Override
            protected void doComplete(Object var1) {

                if(AppTreeAdapter.this.onSelectCallBack != null) {
                    AppTreeAdapter.this.onSelectCallBack.onSelect((ArrayList<AppTreeAdapter.Item>) var1);
                }
            }

            protected void doComplete(ArrayList<AppTreeAdapter.Item> list) {
                if(AppTreeAdapter.this.onSelectCallBack != null) {
                    AppTreeAdapter.this.onSelectCallBack.onSelect(list);
                }

            }
        };
    }

    private ArrayList<AppTreeAdapter.Item> filterRootDirectory(ArrayList<AppTreeAdapter.Item> items) {
        ArrayList list = new ArrayList();

        for(int i = 0; i < items.size(); ++i) {
            AppTreeAdapter.Item item = (AppTreeAdapter.Item)items.get(i);
            if(item.grade == 0) {
                list.add(item);
            }
        }

        return list;
    }

    private ArrayList<AppTreeAdapter.Item> filterSelectAll(ArrayList<AppTreeAdapter.Item> items) {
        ArrayList list = new ArrayList();

        for(int i = 0; i < list.size(); ++i) {
            AppTreeAdapter.Item item = (AppTreeAdapter.Item)list.get(i);
            if(item.selecteType != 0.0F) {
                item.items = this.filterSelectAll((ArrayList)item.items);
                list.add(item);
            }
        }

        return list;
    }

    public void setOnSelectCallBack(AppTreeAdapter.OnSelectCallBack onSelectCallBack) {
        this.onSelectCallBack = onSelectCallBack;
    }

    private void filterSelectEnd(ArrayList<AppTreeAdapter.Item> items, AppTreeAdapter.OnRootItemCallBack onRootItemCallBack) {
        for(int i = 0; i < items.size(); ++i) {
            AppTreeAdapter.Item item = (AppTreeAdapter.Item)items.get(i);
            if(item.items.size() == 0 && item.selecteType != 0.0F) {
                onRootItemCallBack.onItemCallBack(item);
            } else {
                this.filterSelectEnd((ArrayList)item.items, onRootItemCallBack);
            }
        }

    }

    protected abstract Class<? extends AppTreeAdapter.ViewHolder> onCreateViewHolderByGrade(int var1);

    protected abstract ArrayList<AppTreeAdapter.Item> loadData(ArrayList<AppTreeAdapter.Item> var1);

    public interface OnSelectCallBack {
        void onSelect(ArrayList<AppTreeAdapter.Item> var1);
    }

    interface OnRootItemCallBack {
        void onItemCallBack(AppTreeAdapter.Item var1);
    }

    interface OnAsyCallBack {
        ArrayList<AppTreeAdapter.Item> doHandler(ArrayList<AppTreeAdapter.Item> var1);
    }

    public abstract static class Item<I extends AppTreeAdapter.Item> {
        public static final float NULL = 0.0F;
        public static final float ALL = 1.0F;
        public static final float PART = 1.0E-5F;
        public static final int DIVISION = -1;
        public int grade;
        public AppTreeAdapter.Item superior;
        public boolean isSpread;
        public float selecteType;
        public List<I> items = new ArrayList();

        public Item() {
        }

        public I createNaxtItem() {
            AppTreeAdapter.Item i = null;

            try {
                this.items.add((I) (i = (Item)UtilInstance.Instance(this.getClass())));
                i.superior = this;
                i.grade = this.grade + 1;
            } catch (Exception var3) {
                ;
            }

            return (I) i;
        }

        public I createDivision() {
            AppTreeAdapter.Item i = null;

            try {
                i = (AppTreeAdapter.Item)UtilInstance.Instance(this.getClass());
                i.grade = -1;
            } catch (Exception var3) {
                ;
            }

            return (I) i;
        }
    }

    public abstract static class ViewHolder<I> extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            BoundViewHelper.boundView(this, itemView);
        }

        public abstract int resourceId();

        public abstract void loadData(Context var1, AppTreeAdapter var2, I var3);
    }
}
