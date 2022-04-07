package com.sr.superhelperx.init;

import com.sr.superhelperx.http.AsyCallBack;

/**
 * Created by Hang.Yang on 2018/8/17 14:39.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

class n<i> extends AsyCallBack<i> {
    n() {
    }

    public void onStart(int t, Object e) throws Exception {
        this.z(t);
    }

    public void onSuccess(String o, int t, Object e, i i) throws Exception {
        this.v(o, t, i);
    }

    public void onFail(String o, int t, Object e) throws Exception {
        this.k(o, t);
    }

    public void z(int t) throws Exception {
    }

    public void v(String o, int t, i i) throws Exception {
    }

    public void k(String o, int t) throws Exception {
    }
}
