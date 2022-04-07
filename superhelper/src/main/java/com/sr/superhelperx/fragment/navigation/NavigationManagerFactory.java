package com.sr.superhelperx.fragment.navigation;

import android.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.sr.superhelperx.activity.AppActivity;
import com.sr.superhelperx.activity.AppV4Activity;

/**
 * Created by Hang.Yang on 2018/8/17 15:49.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class NavigationManagerFactory {
    private NavigationManagerFactory() {
    }

    public static NavigationManager<Fragment> create(AppActivity appActivity, int layoutId) {
        return new a(appActivity, layoutId);
    }

    public static NavigationManager<androidx.fragment.app.Fragment> createV4(AppV4Activity appV4Activity, int layoutId) {
        return new b(appV4Activity, layoutId);
    }

    public static NavigationManager<androidx.fragment.app.Fragment> createV4Pager(AppV4Activity appV4Activity, ViewPager viewPager) {
        return new c(appV4Activity, viewPager);
    }
}
