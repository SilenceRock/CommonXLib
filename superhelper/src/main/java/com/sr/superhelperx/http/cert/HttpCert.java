package com.sr.superhelperx.http.cert;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Hang.Yang on 2018/8/17 14:58.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class HttpCert {
    public HttpCert() {
    }

    public static HttpCert.SSLParams getSslSocketFactory(InputStream[] certificates, InputStream bksFile, String password, String type) {
        HttpCert.SSLParams sslParams = new HttpCert.SSLParams();

        try {
            TrustManager[] e = prepareTrustManager(certificates);
            KeyManager[] keyManagers = prepareKeyManager(bksFile, password, type);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            Object trustManager = null;
            if(e != null) {
                trustManager = new HttpCert.MyTrustManager(chooseTrustManager(e));
            } else {
                trustManager = new HttpCert.UnSafeTrustManager();
            }

            sslContext.init(keyManagers, new TrustManager[]{(TrustManager)trustManager}, (SecureRandom)null);
            sslParams.sSLSocketFactory = sslContext.getSocketFactory();
            sslParams.trustManager = (X509TrustManager)trustManager;
            return sslParams;
        } catch (NoSuchAlgorithmException var9) {
            throw new AssertionError(var9);
        } catch (KeyManagementException var10) {
            throw new AssertionError(var10);
        } catch (KeyStoreException var11) {
            throw new AssertionError(var11);
        }
    }

    private static TrustManager[] prepareTrustManager(InputStream... certificates) {
        if(certificates != null && certificates.length > 0) {
            try {
                CertificateFactory e = CertificateFactory.getInstance("X.509");
                KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load((KeyStore.LoadStoreParameter)null);
                int index = 0;
                InputStream[] trustManagerFactory = certificates;
                int trustManagers = certificates.length;

                for(int var6 = 0; var6 < trustManagers; ++var6) {
                    InputStream certificate = trustManagerFactory[var6];
                    String certificateAlias = Integer.toString(index++);
                    keyStore.setCertificateEntry(certificateAlias, e.generateCertificate(certificate));

                    try {
                        if(certificate != null) {
                            certificate.close();
                        }
                    } catch (IOException var10) {
                        ;
                    }
                }

                trustManagerFactory = null;
                TrustManagerFactory var15 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                var15.init(keyStore);
                TrustManager[] var16 = var15.getTrustManagers();
                return var16;
            } catch (NoSuchAlgorithmException var11) {
                var11.printStackTrace();
            } catch (CertificateException var12) {
                var12.printStackTrace();
            } catch (KeyStoreException var13) {
                var13.printStackTrace();
            } catch (Exception var14) {
                var14.printStackTrace();
            }

            return null;
        } else {
            return null;
        }
    }

    private static KeyManager[] prepareKeyManager(InputStream bksFile, String password, String type) {
        try {
            if(bksFile != null && password != null) {
                KeyStore e = KeyStore.getInstance(type);
                e.load(bksFile, password.toCharArray());
                KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                keyManagerFactory.init(e, password.toCharArray());
                return keyManagerFactory.getKeyManagers();
            }

            return null;
        } catch (KeyStoreException var5) {
            var5.printStackTrace();
        } catch (NoSuchAlgorithmException var6) {
            var6.printStackTrace();
        } catch (UnrecoverableKeyException var7) {
            var7.printStackTrace();
        } catch (CertificateException var8) {
            var8.printStackTrace();
        } catch (IOException var9) {
            var9.printStackTrace();
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        return null;
    }

    private static X509TrustManager chooseTrustManager(TrustManager[] trustManagers) {
        TrustManager[] var1 = trustManagers;
        int var2 = trustManagers.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            TrustManager trustManager = var1[var3];
            if(trustManager instanceof X509TrustManager) {
                return (X509TrustManager)trustManager;
            }
        }

        return null;
    }

    private static class MyTrustManager implements X509TrustManager {
        private X509TrustManager defaultTrustManager;
        private X509TrustManager localTrustManager;

        public MyTrustManager(X509TrustManager localTrustManager) throws NoSuchAlgorithmException, KeyStoreException {
            TrustManagerFactory var4 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            var4.init((KeyStore)null);
            this.defaultTrustManager = HttpCert.chooseTrustManager(var4.getTrustManagers());
            this.localTrustManager = localTrustManager;
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            try {
                this.defaultTrustManager.checkServerTrusted(chain, authType);
            } catch (CertificateException var4) {
                this.localTrustManager.checkServerTrusted(chain, authType);
            }

        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class UnSafeTrustManager implements X509TrustManager {
        private UnSafeTrustManager() {
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private class UnSafeHostnameVerifier implements HostnameVerifier {
        private UnSafeHostnameVerifier() {
        }

        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    public static class SSLParams {
        public SSLSocketFactory sSLSocketFactory;
        public X509TrustManager trustManager;

        public SSLParams() {
        }
    }
}
