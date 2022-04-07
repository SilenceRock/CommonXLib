package com.sr.superhelperx.secret;

import com.sr.superhelperx.app.AppApplication;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by Hang.Yang on 2018/8/17 16:14.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class SecretRSA implements Secret<String, String> {
    private PrivateKey v;
    private PublicKey e;

    public SecretRSA(String publicPem, String privatePem) {
        try {
            if(publicPem.contains(".pem")) {
                this.e = r.ic(AppApplication.AINSTANCE.getResources().getAssets().open(publicPem));
            } else {
                this.e = r.ic(publicPem);
            }

            if(privatePem.contains(".pem")) {
                this.v = r.te(AppApplication.AINSTANCE.getResources().getAssets().open(privatePem));
            } else {
                this.v = r.te(privatePem);
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public String encrypt(String plaintext) throws Exception {
        return k.ee(r.efd(plaintext.getBytes(), this.e));
    }

    public String decrypt(String ciphertext) throws Exception {
        byte[] b = r.dfd(k.de(ciphertext), this.v);
        return b != null?new String(b):ciphertext;
    }
}
