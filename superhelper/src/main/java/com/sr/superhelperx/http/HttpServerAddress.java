package com.sr.superhelperx.http;

/**
 * Create on 2020/5/23
 * By SilenceRock
 * Learn and Live
 */
public class HttpServerAddress {

    private static String serverAddress = "";

    private static boolean debugAddress = false;

    //动态设置网络请求服务器前缀
    public static void setServerAddress(String address){
        serverAddress = address;
    }

    static String getServerAddress() {
        return serverAddress;
    }

    //设置是否开始Debug模式
    public static void setDebugAddress(boolean isDebug){
        debugAddress = isDebug;
    }

    static boolean getDebugAddress() {
        return debugAddress;
    }
}
