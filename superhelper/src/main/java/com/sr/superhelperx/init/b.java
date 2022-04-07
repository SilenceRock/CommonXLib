package com.sr.superhelperx.init;

/**
 * Created by Hang.Yang on 2018/8/17 14:35.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

class b extends d {
    private c a;

    public b(c c) {
        super(w.e(c.b() + c.e(), 5));
        this.a = c;
    }

    public void h(String s) {
        this.putString(this.a.e(), s);
    }

    public String d() {
        try {
            return this.getString(this.a.e(), hh.f.encrypt(String.valueOf(v.a.a())));
        } catch (Exception var2) {
            var2.printStackTrace();
            return "";
        }
    }

    public void g(String p) {
        this.putString(this.a.d(), p);
    }

    public String c() {
        return this.getString(this.a.d(), "0");
    }

    public void f(String c) {
        this.putString(this.a.a(), c);
    }

    public String a() {
        try {
            return this.getString(this.a.a(), hh.f.encrypt("0"));
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public void i(String u) {
        this.putString(this.a.f(), u);
    }

    public String e() {
        return this.getString(this.a.f(), "");
    }
}
