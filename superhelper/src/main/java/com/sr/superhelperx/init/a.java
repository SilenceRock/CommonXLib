package com.sr.superhelperx.init;

import com.sr.superhelperx.http.note.HttpInlet;
import com.sr.superhelperx.http.note.HttpServer;

import org.json.JSONObject;

/**
 * Created by Hang.Yang on 2018/8/17 14:34.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

class a extends m<a.q> {
    public String apptitle;
    public String passtitle;
    public String apptype = "android";

    public a(n<a.q> l) {
        super(l);
        this.SECRET_SERVER = new e(w.e(HttpServer.class.toString(), 6));
        this.SECRET_INLET = new f(w.e(HttpInlet.class.toString(), 5));
    }

    protected a.q parser(JSONObject o) {
        if(o.optString("code").equals("1")) {
            a.q w = new a.q();
            w.b = o.optString("posttime");
            w.a = o.optString("cycle");
            return w;
        } else {
            this.TOAST = "x";
            return null;
        }
    }

    public static class q {
        public String b;
        public String a;

        public q() {
        }
    }
}
