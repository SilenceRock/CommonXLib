package com.sr.superhelperx.fragment.navigation;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.sr.superhelperx.activity.AppActivity;
import com.sr.superhelperx.sign.g;
import com.sr.superhelperx.util.UtilInstance;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Hang.Yang on 2018/8/17 15:48.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@TargetApi(11)
class a extends NavigationManager<Fragment> {
    private FragmentManager f;
    private Fragment cf;
    private int l;

    public a(AppActivity a, int layoutId) {
        if(g.a(this)) {
            this.f = a.getFragmentManager();
            this.l = layoutId;
        }

    }

    public NavigationManager<Fragment> addFragment(Class... clazz) {
        for(int i = 0; i < clazz.length; ++i) {
            try {
                this.m.put(clazz[i], null);
            } catch (Exception var4) {
                ;
            }
        }

        this.notifyDataSetChanged();
        return this;
    }

    public NavigationManager<Fragment> addFragment(Fragment... fragments) {
        for(int i = 0; i < fragments.length; ++i) {
            try {
                this.m.put(fragments[i].getClass(), fragments[i]);
            } catch (Exception var4) {
                ;
            }
        }

        this.notifyDataSetChanged();
        return this;
    }

    public void show(Class<? extends Fragment> clazz) {
        Fragment r = null;
        if(this.m.containsKey(clazz)) {
            if((r = (Fragment)this.m.get(clazz)) == null) {
                try {
                    r = (Fragment) UtilInstance.Instance(clazz);
                    this.m.put(clazz, r);
                    this.notifyDataSetChanged();
                } catch (Exception var6) {
                    ;
                }
            }
        } else {
            try {
                r = (Fragment)UtilInstance.Instance(clazz);
                this.m.put(clazz, r);
                this.notifyDataSetChanged();
            } catch (Exception var5) {
                ;
            }
        }

        try {
            this.load(r);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public boolean show(Bundle savedInstanceState) {
        boolean flag = savedInstanceState == null?false:(this.cf = this.f.getFragment(savedInstanceState, "cf")) != null;
        if(flag) {
            this.show(this.cf);
        }

        return flag;
    }

    public void show(Fragment t) {
        try {
            if(!this.m.containsValue(t)) {
                this.m.put(t.getClass(), t);
                this.notifyDataSetChanged();
            }

            this.load(t);
        } catch (Exception var3) {
            ;
        }

    }

    public void show(int item) {
        this.show((Class)(new ArrayList(this.m.keySet())).get(item));
    }

    protected void load(Fragment t) throws Exception {
        FragmentTransaction n = this.f.beginTransaction();
        Iterator var3 = this.m.values().iterator();

        while(var3.hasNext()) {
            Fragment r = (Fragment)var3.next();
            if(r != null) {
                n.hide(r);
            }
        }

        if(t.isAdded()) {
            n.show(t);
        } else {
            n.add(this.l, t).show(t);
        }

        n.commitAllowingStateLoss();

        try {
            this.o.onNavigationChange(t, this.v.indexOf(t));
        } catch (Exception var5) {
            ;
        }

        this.cf = t;
    }

    public void remove(Class... clazzs) {
        FragmentTransaction n = this.f.beginTransaction();

        for(int i = 0; i < clazzs.length; ++i) {
            try {
                n.remove((Fragment)this.m.get(clazzs[i]));
                this.m.remove(clazzs[i]);
                this.notifyDataSetChanged();
            } catch (Exception var5) {
                ;
            }
        }

        n.commitAllowingStateLoss();
    }

    protected void notifyDataSetChanged() {
        this.v.clear();
        this.v.addAll(this.m.values());
    }

    public void setOnNavigationChangeCallBack(OnNavigationChangeCallBack<Fragment> onNavigationChangeCallBack) {
        this.o = onNavigationChangeCallBack;
    }

    public void onSaveInstanceState(Bundle outState) {
        try {
            this.f.putFragment(outState, "cf", this.cf);
        } catch (Exception var3) {
            ;
        }

    }
}