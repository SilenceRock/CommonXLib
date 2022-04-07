package com.sr.superhelperx.init;

import android.content.Context;

import com.sr.superhelperx.http.AsyCallBack;
import com.sr.superhelperx.http.AsyPostForm;

/**
 * Created by Hang.Yang on 2018/8/17 14:39.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

class m<t> extends AsyPostForm<t> {
    public m(AsyCallBack<t> a) {
        super(a);
    }

    public void e(Context c) {
        this.execute(c, false);
    }

    public void e(Context c, int t) {
        this.execute(c, false, t);
    }
}
