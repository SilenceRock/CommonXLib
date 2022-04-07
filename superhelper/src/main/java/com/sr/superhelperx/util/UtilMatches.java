package com.sr.superhelperx.util;

/**
 * Created by Hang.Yang on 2018/8/17 16:23.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class UtilMatches {
    private UtilMatches() {
    }

    public static boolean checkMobile(String mobile) {
        try {
            return mobile.matches("^((1[3,5,7,8][0-9])|(14[5,6,7,8])|(198|199|166))\\d{8}$");
        } catch (Exception var2) {
            return false;
        }
    }

    public static boolean checkEmail(String email) {
        try {
            return email.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        } catch (Exception var2) {
            return false;
        }
    }

    public static boolean checkUrl(String url) {
        try {
            return url.matches("[a-zA-z]+://[^s]*");
        } catch (Exception var2) {
            return false;
        }
    }

    public static boolean checkTelephone(String telephone) {
        try {
            return telephone.matches("d{3}-d{8}|d{4}-d{7}");
        } catch (Exception var2) {
            return false;
        }
    }

    public static boolean checkQQ(String qq) {
        try {
            return qq.matches("[1-9][0-9]{4,}");
        } catch (Exception var2) {
            return false;
        }
    }

    public static boolean checkPostal(String postal) {
        try {
            return postal.matches("[1-9]d{5}(?!d)");
        } catch (Exception var2) {
            return false;
        }
    }

    public static boolean checkIdentity(String identity) {
        try {
            return identity.matches("(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)");
        } catch (Exception var2) {
            return false;
        }
    }

    public static boolean checkIP(String ip) {
        try {
            return ip.matches("d+.d+.d+.d+");
        } catch (Exception var2) {
            return false;
        }
    }
}

