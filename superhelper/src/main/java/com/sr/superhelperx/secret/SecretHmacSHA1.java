package com.sr.superhelperx.secret;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Hang.Yang on 2018/8/17 16:14.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class SecretHmacSHA1 implements Secret<byte[], String> {
    private SecretKeySpec c;
    private Mac m;

    public SecretHmacSHA1(String secretKey) {
        try {
            this.m = Mac.getInstance("HmacSHA1");
            this.m.init(this.c = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA1"));
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public byte[] encrypt(String plaintext) {
        try {
            return this.m.doFinal(plaintext.getBytes("UTF-8"));
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public String decrypt(String ciphertext) {
        return ciphertext;
    }
}
