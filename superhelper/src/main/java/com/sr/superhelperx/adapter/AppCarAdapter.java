package com.sr.superhelperx.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.sr.superhelperx.util.UtilAsyHandler;

import java.io.Serializable;
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
public abstract class AppCarAdapter extends AppRecyclerAdapter {
    private AppCarAdapter.ShopCarCallBack shopCarCallBack;
    private boolean isDeleteType;

    public AppCarAdapter(Object object) {
        super(object);
    }

    public void setList(List list) {
        super.setList(list, false);
        this.accounting();
    }

    public void onBindViewHolder(ViewHolder vh, int p) {
        try {
            ((AppCarAdapter.VH)vh).load(this.context, this, (AppCarAdapter.Item)this.l.get(p), this.isDeleteType);
        } catch (Exception var4) {
            super.onBindViewHolder(vh, p);
        }

    }

    public boolean isDeleteType() {
        return this.isDeleteType;
    }

    public void deleteType(final boolean isType) {
        this.accounting(new AppCarAdapter.OnAsyCallBack() {
            public boolean isClone() {
                return false;
            }

            public ArrayList<Item> doHandler(ArrayList<AppCarAdapter.Item> list) {
                if(AppCarAdapter.this.isDeleteType = isType) {
                    for(int i = 0; i < list.size(); ++i) {
                        try {
                            ((AppCarAdapter.Item)list.get(i)).isDelete = false;
                        } catch (Exception var4) {
                            ;
                        }
                    }
                }

                return list;
            }
        });
    }

    public void getSelected(final AppCarAdapter.OnSelectedCallBack onSelectedCallBack) {
        UtilAsyHandler var10001 = new UtilAsyHandler() {
            private List<AppCarAdapter.GoodItem> list = new ArrayList();
            private int amount = 0;

            protected Object doHandler() {
                int i;
                AppCarAdapter.GoodItem goodItem;
                if(AppCarAdapter.this.isDeleteType) {
                    for(i = 0; i < AppCarAdapter.this.l.size(); ++i) {
                        try {
                            goodItem = (AppCarAdapter.GoodItem)AppCarAdapter.this.l.get(i);
                            if(goodItem.isDelete) {
                                this.amount += goodItem.number;
                                this.list.add(goodItem);
                            }
                        } catch (Exception var4) {
                            ;
                        }
                    }
                } else {
                    for(i = 0; i < AppCarAdapter.this.l.size(); ++i) {
                        try {
                            goodItem = (AppCarAdapter.GoodItem)AppCarAdapter.this.l.get(i);
                            if(goodItem.isSelect) {
                                this.amount += goodItem.number;
                                this.list.add(goodItem);
                            }
                        } catch (Exception var3) {
                            ;
                        }
                    }
                }

                return null;
            }

            protected void doComplete(Object t) {
                onSelectedCallBack.onSelected(this.list, this.amount);
            }
        };
    }

    public void deleteGood(AppCarAdapter.GoodItem... goodItems) {
        this.deleteGood(Arrays.asList(goodItems));
    }

    public void deleteGood(final List<AppCarAdapter.GoodItem> goodItems) {
        this.accounting(new AppCarAdapter.OnAsyCallBack() {
            public boolean isClone() {
                return true;
            }

            public ArrayList<AppCarAdapter.Item> doHandler(ArrayList<AppCarAdapter.Item> list) {
                for(int i = 0; i < goodItems.size(); ++i) {
                    try {
                        AppCarAdapter.GoodItem e = (AppCarAdapter.GoodItem)goodItems.get(i);
                        list.remove(e);
                        AppCarAdapter.ShopItem shopItem = e.shopItem;
                        List goods = shopItem.goods;
                        goods.remove(e);
                        if(goods.size() == 0) {
                            list.remove(shopItem);
                            list.remove(shopItem.bottomItem);
                        } else {
                            shopItem.isSelect = true;

                            for(int k = 0; k < goods.size(); ++k) {
                                if(!((AppCarAdapter.GoodItem)goods.get(k)).isSelect) {
                                    shopItem.isSelect = false;
                                    break;
                                }
                            }

                            shopItem.bottomItem.isSelect = shopItem.isSelect;
                        }
                    } catch (Exception var7) {
                        var7.printStackTrace();
                    }
                }

                return list;
            }
        });
    }

    public void deleteShopItem(AppCarAdapter.ShopItem... shopItems) {
        this.deleteShopItem(Arrays.asList(shopItems));
    }

    public void deleteShopItem(final List<AppCarAdapter.ShopItem> shopItems) {
        this.accounting(new AppCarAdapter.OnAsyCallBack() {
            public boolean isClone() {
                return true;
            }

            public ArrayList<AppCarAdapter.Item> doHandler(ArrayList<AppCarAdapter.Item> list) {
                for(int i = 0; i < shopItems.size(); ++i) {
                    try {
                        AppCarAdapter.ShopItem e = (AppCarAdapter.ShopItem)shopItems.get(i);
                        list.remove(e);
                        list.removeAll(e.goods);
                        list.remove(e.bottomItem);
                    } catch (Exception var4) {
                        var4.printStackTrace();
                    }
                }

                return list;
            }
        });
    }

    public void selectAll(final boolean isSelectAll) {
        this.accounting(new AppCarAdapter.OnAsyCallBack() {
            public boolean isClone() {
                return false;
            }

            public ArrayList<AppCarAdapter.Item> doHandler(ArrayList<AppCarAdapter.Item> list) {
                int i;
                if(AppCarAdapter.this.isDeleteType) {
                    for(i = 0; i < AppCarAdapter.this.l.size(); ++i) {
                        try {
                            ((AppCarAdapter.Item)list.get(i)).isDelete = isSelectAll;
                        } catch (Exception var5) {
                            ;
                        }
                    }
                } else {
                    for(i = 0; i < AppCarAdapter.this.l.size(); ++i) {
                        try {
                            ((AppCarAdapter.Item)list.get(i)).isSelect = isSelectAll;
                        } catch (Exception var4) {
                            ;
                        }
                    }
                }

                return list;
            }
        });
    }

    public void shopSelect(final AppCarAdapter.ShopItem shopItem, final boolean isSelectAll) {
        this.accounting(new AppCarAdapter.OnAsyCallBack() {
            public boolean isClone() {
                return false;
            }

            public ArrayList<AppCarAdapter.Item> doHandler(ArrayList<AppCarAdapter.Item> list) {
                int i;
                if(AppCarAdapter.this.isDeleteType) {
                    shopItem.isDelete = isSelectAll;

                    for(i = 0; i < shopItem.goods.size(); ++i) {
                        ((AppCarAdapter.GoodItem)shopItem.goods.get(i)).isDelete = isSelectAll;
                    }

                    try {
                        shopItem.bottomItem.isDelete = isSelectAll;
                    } catch (Exception var4) {
                        ;
                    }
                } else {
                    shopItem.isSelect = isSelectAll;

                    for(i = 0; i < shopItem.goods.size(); ++i) {
                        ((AppCarAdapter.GoodItem)shopItem.goods.get(i)).isSelect = isSelectAll;
                    }

                    try {
                        shopItem.bottomItem.isSelect = isSelectAll;
                    } catch (Exception var3) {
                        ;
                    }
                }

                return list;
            }
        });
    }

    public void goodSelect(final AppCarAdapter.GoodItem goodItem, final boolean isSelect) {
        this.accounting(new AppCarAdapter.OnAsyCallBack() {
            public boolean isClone() {
                return false;
            }

            public ArrayList<AppCarAdapter.Item> doHandler(ArrayList<AppCarAdapter.Item> list) {
                AppCarAdapter.ShopItem e;
                List goods;
                int i;
                if(AppCarAdapter.this.isDeleteType) {
                    goodItem.isDelete = isSelect;

                    try {
                        e = goodItem.shopItem;
                        goods = e.goods;
                        e.isDelete = true;

                        for(i = 0; i < goods.size(); ++i) {
                            if(!((AppCarAdapter.GoodItem)goods.get(i)).isDelete) {
                                e.isDelete = false;
                                break;
                            }
                        }

                        e.bottomItem.isDelete = e.isDelete;
                    } catch (Exception var6) {
                        var6.printStackTrace();
                    }
                } else {
                    goodItem.isSelect = isSelect;

                    try {
                        e = goodItem.shopItem;
                        goods = e.goods;
                        e.isSelect = true;

                        for(i = 0; i < goods.size(); ++i) {
                            if(!((AppCarAdapter.GoodItem)goods.get(i)).isSelect) {
                                e.isSelect = false;
                                break;
                            }
                        }

                        e.bottomItem.isSelect = e.isSelect;
                    } catch (Exception var5) {
                        var5.printStackTrace();
                    }
                }

                return list;
            }
        });
    }

    public void accounting() {
        this.accounting(new AppCarAdapter.OnAsyCallBack() {
            public boolean isClone() {
                return false;
            }

            public ArrayList<AppCarAdapter.Item> doHandler(ArrayList<AppCarAdapter.Item> items) {
                return items;
            }
        });
    }

    @TargetApi(3)
    private synchronized void accounting(final AppCarAdapter.OnAsyCallBack onAsyCallBack) {
        (new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                return objects;
            }

            boolean isSelectAll = true;
            float total;

            protected List<AppCarAdapter.Item> doInBackground(Void... params) {
                ArrayList list = onAsyCallBack.doHandler(onAsyCallBack.isClone()?(ArrayList)AppCarAdapter.this.l.clone():AppCarAdapter.this.l);
                if(list.size() > 0) {
                    int i;
                    AppCarAdapter.Item item;
                    if(AppCarAdapter.this.isDeleteType) {
                        for(i = 0; i < list.size(); ++i) {
                            try {
                                item = (AppCarAdapter.Item)list.get(i);
                                if(!item.isDelete) {
                                    this.isSelectAll = false;
                                    break;
                                }
                            } catch (Exception var12) {
                                ;
                            }
                        }
                    } else {
                        for(i = 0; i < list.size(); ++i) {
                            try {
                                item = (AppCarAdapter.Item)list.get(i);
                                if(item instanceof AppCarAdapter.GoodItem) {
                                    if(item.isSelect) {
                                        try {
                                            this.total += AppCarAdapter.this.calculateTotal((AppCarAdapter.GoodItem)item);
                                        } catch (Exception var9) {
                                            var9.printStackTrace();
                                        }
                                    } else {
                                        this.isSelectAll = false;
                                    }
                                } else {
                                    try {
                                        AppCarAdapter.ShopItem e = (AppCarAdapter.ShopItem)item;
                                        e.bottomItem.count = 0;
                                        if(e.bottomItem.isTotal) {
                                            e.bottomItem.total = 0.0F;
                                        }

                                        for(int k = 0; k < e.goods.size(); ++k) {
                                            try {
                                                AppCarAdapter.GoodItem goodItem = (AppCarAdapter.GoodItem)e.goods.get(k);
                                                if(goodItem.isSelect) {
                                                    e.bottomItem.count += goodItem.number;
                                                    if(e.bottomItem.isTotal) {
                                                        e.bottomItem.total += AppCarAdapter.this.calculateTotal(goodItem);
                                                    }
                                                }
                                            } catch (Exception var8) {
                                                ;
                                            }
                                        }
                                    } catch (Exception var10) {
                                        var10.printStackTrace();
                                    }
                                }
                            } catch (Exception var11) {
                                ;
                            }
                        }
                    }
                } else {
                    this.isSelectAll = false;
                }

                return list;
            }

            protected void onPostExecute(List<AppCarAdapter.Item> list) {
                if(onAsyCallBack.isClone()) {
                    AppCarAdapter.this.l.clear();
                    AppCarAdapter.this.l.addAll(list);
                }

                if(AppCarAdapter.this.shopCarCallBack != null) {
                    AppCarAdapter.this.shopCarCallBack.onDeleteTypeChange(AppCarAdapter.this.isDeleteType);
                    AppCarAdapter.this.shopCarCallBack.onSelectAllChange(this.isSelectAll);
                    AppCarAdapter.this.shopCarCallBack.onTotalChange(this.total);
                    AppCarAdapter.this.shopCarCallBack.onAccountingEnd();
                }

                AppCarAdapter.this.notifyDataSetChanged();
            }
        }).execute((Object) new Void[0]);
    }

    public void setShopCarCallBack(AppCarAdapter.ShopCarCallBack shopCarCallBack) {
        this.shopCarCallBack = shopCarCallBack;
    }

    protected float calculateTotal(AppCarAdapter.GoodItem goodItem) {
        return (float)goodItem.number * goodItem.price;
    }

    public abstract static class ShopCarCallBack {
        public ShopCarCallBack() {
        }

        protected void onDeleteTypeChange(boolean isDelete) {
        }

        protected void onSelectAllChange(boolean isSelectAll) {
        }

        protected void onTotalChange(float total) {
        }

        protected void onAccountingEnd() {
        }
    }

    public interface OnSelectedCallBack {
        void onSelected(List<AppCarAdapter.GoodItem> var1, int var2);
    }

    interface OnAsyCallBack {
        boolean isClone();

        ArrayList<AppCarAdapter.Item> doHandler(ArrayList<AppCarAdapter.Item> var1);
    }

    public static class BottomItem extends AppCarAdapter.Item implements Serializable {
        private static final long serialVersionUID = 1L;
        public AppCarAdapter.ShopItem shopItem;
        public boolean isTotal = true;
        public float total;
        public int count;

        public BottomItem(AppCarAdapter.ShopItem shopItem) {
            this.shopItem = shopItem;
            this.shopItem.bottomItem = this;
        }
    }

    public static class GoodItem extends AppCarAdapter.Item implements Serializable {
        private static final long serialVersionUID = 1L;
        public AppCarAdapter.ShopItem shopItem;
        public int number;
        public float price;

        public GoodItem(AppCarAdapter.ShopItem shopItem) {
            try {
                this.shopItem = shopItem;
                this.shopItem.goods.add(this);
            } catch (Exception var3) {
                ;
            }

        }
    }

    public static class ShopItem extends AppCarAdapter.Item implements Serializable {
        private static final long serialVersionUID = 1L;
        public AppCarAdapter.BottomItem bottomItem;
        public List<AppCarAdapter.GoodItem> goods = new ArrayList();

        public ShopItem() {
        }
    }

    public abstract static class Item extends AppRecyclerAdapter.Item implements Serializable {
        private static final long serialVersionUID = 1L;
        public boolean isSelect;
        public boolean isDelete;

        public Item() {
        }
    }

    public abstract static class BottomViewHolder<GI extends AppCarAdapter.BottomItem> extends AppCarAdapter.VH<GI> {
        public BottomViewHolder(AppRecyclerAdapter adapter, Context context, View view) {
            super(adapter, context, view);
        }
    }

    public abstract static class GoodViewHolder<GI extends AppCarAdapter.GoodItem> extends AppCarAdapter.VH<GI> {
        public GoodViewHolder(AppRecyclerAdapter adapter, Context context, View view) {
            super(adapter, context, view);
        }
    }

    public abstract static class ShopViewHolder<SI extends AppCarAdapter.ShopItem> extends AppCarAdapter.VH<SI> {
        public ShopViewHolder(AppRecyclerAdapter adapter, Context context, View view) {
            super(adapter, context, view);
        }
    }

    public abstract static class VH<T extends AppCarAdapter.Item> extends ViewHolder<AppCarAdapter.Item> {
        public VH(AppRecyclerAdapter adapter, Context context, View view) {
            super(adapter, context, view);
        }

        public void load(int p, AppCarAdapter.Item i) {
        }

        public abstract void load(Context var1, AppCarAdapter var2, T var3, boolean var4);
    }
}