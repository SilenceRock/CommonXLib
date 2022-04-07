package com.sr.superhelperx.view.asy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hang.Yang on 2018/8/17 16:37.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class p {
    private Map<String, a> a = new HashMap();

    public p() {
    }

    public void e(String k, a aa) {
        if(!this.a.containsKey(k)) {
            this.a.put(k, aa);
        }

    }

    public a d(String k) throws Exception {
        a as = (a)this.a.get(k);
        if(as == null) {
            throw new Exception();
        } else {
            return as;
        }
    }

    public void e(String key) {
        this.a.remove(key);
    }

    public void c() {
        this.a.clear();
    }

    public static p e() {
        return p.f.a;
    }

    private static class f {
        private static final p a = new p();

        private f() {
        }
    }
}
