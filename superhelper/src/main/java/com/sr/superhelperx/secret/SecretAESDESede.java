package com.sr.superhelperx.secret;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Hang.Yang on 2018/8/17 16:13.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class SecretAESDESede implements Secret<String, String> {
    public static final String DESEDE_CBC_PKCS5PADDING = "DESede/CBC/PKCS5Padding";
    public static final String DESEDE_CBC_PKCS7PADDING = "DESede/CBC/PKCS7Padding";
    public static final String AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5Padding";
    public static final String AES_CBC_PKCS7PADDING = "AES/CBC/PKCS7Padding";
    public static final String DESEDE_CBC_NOPADDING = "DESede/CBC/NoPadding";
    private IvParameterSpec b;
    private SecretKeySpec c;
    private Cipher a;

    public SecretAESDESede(String md5, String cipherType) {
        this(md5.substring(0, 15), md5.substring(16, 31), cipherType);
    }

    public SecretAESDESede(String secretKey, String iv, String cipherType) {
        try {
            String e = cipherType.split("/")[0];
            this.a = Cipher.getInstance(cipherType);
            this.c = new SecretKeySpec(this.a(secretKey, e.equals("AES")?16:24), e);
            this.b = new IvParameterSpec(this.a(iv, e.equals("AES")?16:8));
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public String encrypt(String plaintext) throws Exception {
        this.a.init(1, this.c, this.b);
        return new String(Base64.encode(this.a.doFinal(plaintext.getBytes()), 0));
    }

    public String decrypt(String ciphertext) throws Exception {
        this.a.init(2, this.c, this.b);
        byte[] b = this.a.doFinal(Base64.decode(ciphertext.getBytes(), 0));
        return b != null?new String(b):ciphertext;
    }

    private byte[] a(String s, int z) {
        byte[] a = s.getBytes();
        byte[] b = new byte[z];

        for(int i = 0; i < a.length && i < b.length; ++i) {
            b[i] = a[i];
        }

        return b;
    }
}

