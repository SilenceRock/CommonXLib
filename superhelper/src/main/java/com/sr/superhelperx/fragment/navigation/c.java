package com.sr.superhelperx.fragment.navigation;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.sr.superhelperx.activity.AppV4Activity;
import com.sr.superhelperx.sign.g;
import com.sr.superhelperx.util.UtilInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hang.Yang on 2018/8/17 15:48.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

class c extends NavigationManager<Fragment> {
    private c.NavigationAdapter p;
    private FragmentManager f;
    private ViewPager i;
    private Fragment cf;
    protected List<Class<?>> c = new ArrayList();

    public c(AppV4Activity a, ViewPager viewPager) {
        if(g.a(this)) {
            (this.i = viewPager).setAdapter(this.p = new c.NavigationAdapter(this.f = a.getSupportFragmentManager()));
            this.i.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                public void onPageSelected(int a) {
                    try {
                        c.this.cf = (Fragment)c.this.v.get(a);
                        c.this.o.onNavigationChange(c.this.cf, a);
                    } catch (Exception var3) {
                        ;
                    }

                }

                public void onPageScrolled(int a, float a1, int a2) {
                }

                public void onPageScrollStateChanged(int a) {
                }
            });
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
        if(this.m.containsKey(clazz)) {
            this.show(this.c.indexOf(clazz));
        } else {
            try {
                this.m.put(clazz, UtilInstance.Instance(clazz));
                this.notifyDataSetChanged();
                this.show(this.c.indexOf(clazz));
            } catch (Exception var3) {
                ;
            }
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
        if(this.v.contains(t)) {
            this.show(this.c.indexOf(t));
        } else {
            try {
                this.m.put(t.getClass(), t);
                this.notifyDataSetChanged();
                this.show(this.c.indexOf(t));
            } catch (Exception var3) {
                ;
            }
        }

    }

    public void show(int item) {
        try {
            this.i.setCurrentItem(item);
        } catch (Exception var3) {
            ;
        }

    }

    public void remove(Class... clazzs) {
        for(int i = 0; i < clazzs.length; ++i) {
            try {
                this.m.remove(clazzs[i]);
            } catch (Exception var4) {
                ;
            }
        }

        this.notifyDataSetChanged();
    }

    protected void notifyDataSetChanged() {
        this.c.clear();
        this.v.clear();
        this.c.addAll(this.m.keySet());
        this.v.addAll(this.m.values());
        this.p.notifyDataSetChanged();
    }

    public void setOnNavigationChangeCallBack(OnNavigationChangeCallBack<Fragment> onNavigationChangeCallBack) {
        this.o = onNavigationChangeCallBack;
    }

    protected void load(Fragment t) throws Exception {
    }

    public void onSaveInstanceState(Bundle outState) {
        try {
            this.f.putFragment(outState, "cf", this.cf);
        } catch (Exception var3) {
            ;
        }

    }

    private class NavigationAdapter extends FragmentPagerAdapter {
        public NavigationAdapter(FragmentManager fm) {
            super(fm);
        }

        public int getCount() {
            return c.this.v.size();
        }

        public Fragment getItem(int i) {
            Fragment t = (Fragment)c.this.v.get(i);
            if(t == null) {
                try {
                    Class cls = (Class)c.this.c.get(i);
                    c.this.m.put(cls, t = (Fragment)UtilInstance.Instance(cls));
                    c.this.v.remove(i);
                    c.this.v.add(i, t);
                } catch (Exception var4) {
                    ;
                }
            }

            return t;
        }
    }
}
