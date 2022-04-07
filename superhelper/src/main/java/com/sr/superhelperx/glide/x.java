package com.sr.superhelperx.glide;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;

import androidx.fragment.app.FragmentActivity;

/**
 * Created by Hang.Yang on 2018/8/17 15:52.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class x {
    public x() {
    }

    public static Context c(Object o) {
        return (Context)(o instanceof Fragment ?((Fragment)o).getActivity():(o instanceof Fragment ?((Fragment)o).getActivity():(o instanceof Activity ?(Activity)o:(o instanceof FragmentActivity ?(FragmentActivity)o:(o instanceof Context?(Context)o:null)))));
    }
}
