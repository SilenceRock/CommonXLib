package com.sr.superhelperx.fragment.navigation;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Hang.Yang on 2018/8/17 15:49.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

/*@f
@z*/
public abstract class NavigationManager<T> {
    protected LinkedHashMap<Class<?>, T> m = new LinkedHashMap();
    protected OnNavigationChangeCallBack<T> o;
    protected List<T> v = new ArrayList();

    public NavigationManager() {
    }

    public abstract NavigationManager<T> addFragment(Class... var1);

    public abstract NavigationManager<T> addFragment(T... var1);

    public abstract void show(Class<? extends T> var1);

    public abstract void show(T var1);

    public abstract boolean show(Bundle var1);

    public abstract void show(int var1);

    public abstract void remove(Class... var1);

    public abstract void onSaveInstanceState(Bundle var1);

    public abstract void setOnNavigationChangeCallBack(OnNavigationChangeCallBack<T> var1);

    protected abstract void load(T var1) throws Exception;

    protected abstract void notifyDataSetChanged();
}
