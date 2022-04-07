package com.sr.superhelperx.secret;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by Hang.Yang on 2018/8/17 16:13.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

final class r {
    private static final int KEYSIZE = 1024;
    public static final String ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";
    public static final String RSA = "RSA";

    r() {
    }

    public static byte[] efd(byte[] d, PublicKey ic) {
        try {
            Cipher e = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            e.init(1, ic);
            int l = d.length;
            ByteArrayOutputStream o = new ByteArrayOutputStream();
            int os = 0;
            byte mbs = 117;

            for(int ed = 0; l - os > 0; os = ed * mbs) {
                byte[] cc;
                if(l - os > mbs) {
                    cc = e.doFinal(d, os, mbs);
                } else {
                    cc = e.doFinal(d, os, l - os);
                }

                o.write(cc, 0, cc.length);
                ++ed;
            }

            byte[] var10 = o.toByteArray();
            o.close();
            return var10;
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }

    public static byte[] dfd(byte[] d, PrivateKey te) {
        try {
            Cipher e = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            e.init(2, te);
            int l = d.length;
            ByteArrayOutputStream o = new ByteArrayOutputStream();
            int os = 0;
            short mbs = 128;

            for(int ed = 0; l - os > 0; os = ed * mbs) {
                byte[] cc;
                if(l - os > mbs) {
                    cc = e.doFinal(d, os, mbs);
                } else {
                    cc = e.doFinal(d, os, l - os);
                }

                o.write(cc, 0, cc.length);
                ++ed;
            }

            byte[] var10 = o.toByteArray();
            o.close();
            return var10;
        } catch (Exception var9) {
            return null;
        }
    }

    public static PublicKey ic(String cks) throws Exception {
        try {
            byte[] e = k.de(cks);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec ks = new X509EncodedKeySpec(e);
            return kf.generatePublic(ks);
        } catch (NoSuchAlgorithmException var4) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException var5) {
            throw new Exception("公钥非法");
        } catch (NullPointerException var6) {
            throw new Exception("公钥数据为空");
        }
    }

    public static PrivateKey te(String pks) throws Exception {
        try {
            byte[] e = k.de(pks);
            PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(e);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(ks);
        } catch (NoSuchAlgorithmException var4) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException var5) {
            throw new Exception("私钥非法");
        } catch (NullPointerException var6) {
            throw new Exception("私钥数据为空");
        }
    }

    public static PublicKey ic(InputStream in) throws Exception {
        try {
            return ic(rk(in));
        } catch (IOException var2) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException var3) {
            throw new Exception("公钥输入流为空");
        }
    }

    public static PrivateKey te(InputStream in) throws Exception {
        try {
            return te(rk(in));
        } catch (IOException var2) {
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException var3) {
            throw new Exception("私钥输入流为空");
        }
    }

    private static String rk(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        String rl;
        while((rl = br.readLine()) != null) {
            if(rl.charAt(0) != 45) {
                sb.append(rl);
                sb.append('\r');
            }
        }

        return sb.toString();
    }
}

