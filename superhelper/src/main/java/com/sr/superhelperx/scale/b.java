package com.sr.superhelperx.scale;

import com.sr.superhelperx.util.UtilScreen;

/**
 * Created by Hang.Yang on 2018/8/17 13:45.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

abstract class b {
    //屏幕宽度
    int b;
    //屏幕高度
    int a;

    public b() {
        int[] s = UtilScreen.screenSize();
        this.b = s[0];
        this.a = s[1];
        /*if(g.a(this)) {
            int[] s = UtilScreen.screenSize();
            this.b = s[0];
            this.a = s[1];
        }*/
    }
}
