package com.sr.superhelperx.init;

import com.sr.superhelperx.util.UtilApp;

import java.util.Random;

/**
 * Created by Hang.Yang on 2018/8/17 14:35.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

class c extends d {
    public c() {
        super(w.e(UtilApp.packageName(), 4));
    }

    public String e() {
        String s = this.getString("a", "");
        if(s.equals("")) {
            this.putString("a", s = w.e(String.valueOf(this.b()), 5));
        }

        return s;
    }

    public String d() {
        String e = this.getString("b", "");
        if(e.equals("")) {
            this.putString("b", e = w.e(String.valueOf(this.b()), 6));
        }

        return e;
    }

    public String a() {
        String f = this.getString("z", "");
        if(f.equals("")) {
            this.putString("z", f = w.e(String.valueOf(this.b()), 7));
        }

        return f;
    }

    public String f() {
        String g = this.getString("d", "");
        if(g.equals("")) {
            this.putString("d", g = w.e(String.valueOf(this.b()), 8));
        }

        return g;
    }

    public String g() {
        String h;
        this.putString("e", w.d(h = this.getString("e", w.d(String.valueOf(this.b())))));
        return h;
    }

    public int b() {
        int i = this.getInt("x", -1);
        if(i == -1) {
            this.putInt("x", i = (new Random(999999L)).nextInt());
        }

        return i;
    }
}
