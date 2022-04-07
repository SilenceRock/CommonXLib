package com.sr.superhelperx.init;

import com.sr.superhelperx.secret.SecretAESDESede;

/**
 * Created by Hang.Yang on 2018/8/17 14:37.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

class e extends SecretAESDESede {
    public e(String a) {
        super(a, "AES/CBC/PKCS5Padding");
    }

    public String a(String a) {
        try {
            return this.encrypt(a);
        } catch (Exception var3) {
            return "";
        }
    }

    public String b(String b) {
        try {
            return this.decrypt(b);
        } catch (Exception var3) {
            return v.b.a();
        }
    }
}
