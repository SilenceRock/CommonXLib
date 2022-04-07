package com.sr.superhelperx.init;

import com.sr.superhelperx.app.AppApplication;
import com.sr.superhelperx.http.note.HttpInlet;
import com.sr.superhelperx.http.note.HttpServer;
import com.sr.superhelperx.util.UtilApp;
import com.sr.superhelperx.sign.j;

/**
 * Created by Hang.Yang on 2018/8/17 14:37.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class hh {
    static e f;
    static e g;
    static String h = "";
    private static b c;
    private static c d;
    private static hh.ck j;
    private static boolean e;
    private static String a = "";
    private static a b = new a(new n() {
        public void z(int t) throws Exception {
            hh.a = hh.f.b(hh.c.d());
        }

        public void v(String o, int t, a.q i) throws Exception {
            hh.c.h(hh.g.a(hh.a = v.c.a()));
            hh.c.g(hh.g.a(i.b));
            hh.c.f(hh.g.a(String.valueOf(System.currentTimeMillis() + Long.valueOf(i.a).longValue() * 24L * 60L * 60L * 1000L)));
            hh.c.i(w.e(((HttpServer)hh.b.getClass().getAnnotation(HttpServer.class)).value() + ((HttpInlet)hh.b.getClass().getAnnotation(HttpInlet.class)).value(), 4));
            hh.j.s();
            hh.a();
        }

        public void k(String o, int t) throws Exception {
            if(t == 1) {
                if(o.equals("x")) {
                    hh.j.f();
                } else {
                    hh.j.s();
                }
            } else {
                hh.j.f();
            }

            hh.d(o);
            hh.a();
        }
    });

    public static void ze(AppApplication ap, String an, hh.ck oc) {
        e = true;
        j = oc;
        b.apptitle = an;
        b.passtitle = w.e(UtilApp.packageName(), 2);
        f = new e(h = (d = new c()).g());
        g = new e(w.d(h));
        if((a = f.b((c = new b(d)).d())).equals(v.b.a())) {
            a = v.a.a();
            b.e(ap.getApplication());
        } else if(a.equals(v.a.a())) {
            b.e(ap.getApplication(), 1);
        } else if(a.equals(v.c.a())) {
            if(((j)k.k.getClass().getAnnotation(j.class)).value().equals(c.e())) {
                if(System.currentTimeMillis() > Long.valueOf(f.b(c.a())).longValue()) {
                    b.e(ap.getApplication(), 1);
                } else {
                    j.s();
                    d(h);
                    a();
                }
            } else {
                c.h(f.a(a = v.b.a()));
                b.e(ap.getApplication());
            }
        }

    }

    public static String b() {
        return a;
    }

    public static boolean c() {
        return e;
    }

    private static void d(String t) {
        c.h(g.a(t.equals("x")?(a = v.b.a()):f.b(c.d())));
        c.g(g.a(f.b(c.c())));
        c.f(g.a(f.b(c.a())));
    }

    private static void a() {
        b = null;
        c = null;
        f = null;
        g = null;
        e = false;
    }

    private hh() {
    }

    public interface ck {
        void s();

        void f();
    }
}
