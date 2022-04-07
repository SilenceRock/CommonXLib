package com.sr.superhelperx.secret;

import java.io.UnsupportedEncodingException;

/**
 * Created by Hang.Yang on 2018/8/17 16:13.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

class k {
    private static char[] ec = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static byte[] dc = new byte[]{(byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)62, (byte)-1, (byte)-1, (byte)-1, (byte)63, (byte)52, (byte)53, (byte)54, (byte)55, (byte)56, (byte)57, (byte)58, (byte)59, (byte)60, (byte)61, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)0, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5, (byte)6, (byte)7, (byte)8, (byte)9, (byte)10, (byte)11, (byte)12, (byte)13, (byte)14, (byte)15, (byte)16, (byte)17, (byte)18, (byte)19, (byte)20, (byte)21, (byte)22, (byte)23, (byte)24, (byte)25, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)26, (byte)27, (byte)28, (byte)29, (byte)30, (byte)31, (byte)32, (byte)33, (byte)34, (byte)35, (byte)36, (byte)37, (byte)38, (byte)39, (byte)40, (byte)41, (byte)42, (byte)43, (byte)44, (byte)45, (byte)46, (byte)47, (byte)48, (byte)49, (byte)50, (byte)51, (byte)-1, (byte)-1, (byte)-1, (byte)-1, (byte)-1};

    k() {
    }

    public static String ee(byte[] data) {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;

        while(i < len) {
            int b1 = data[i++] & 255;
            if(i == len) {
                sb.append(ec[b1 >>> 2]);
                sb.append(ec[(b1 & 3) << 4]);
                sb.append("==");
                break;
            }

            int b2 = data[i++] & 255;
            if(i == len) {
                sb.append(ec[b1 >>> 2]);
                sb.append(ec[(b1 & 3) << 4 | (b2 & 240) >>> 4]);
                sb.append(ec[(b2 & 15) << 2]);
                sb.append("=");
                break;
            }

            int b3 = data[i++] & 255;
            sb.append(ec[b1 >>> 2]);
            sb.append(ec[(b1 & 3) << 4 | (b2 & 240) >>> 4]);
            sb.append(ec[(b2 & 15) << 2 | (b3 & 192) >>> 6]);
            sb.append(ec[b3 & 63]);
        }

        return sb.toString();
    }

    public static byte[] de(String str) {
        try {
            return decodePrivate(str);
        } catch (UnsupportedEncodingException var2) {
            var2.printStackTrace();
            return new byte[0];
        }
    }

    private static byte[] decodePrivate(String str) throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        Object data = null;
        byte[] var9 = str.getBytes("US-ASCII");
        int len = var9.length;
        int i = 0;

        while(i < len) {
            byte b1;
            do {
                b1 = dc[var9[i++]];
            } while(i < len && b1 == -1);

            if(b1 == -1) {
                break;
            }

            byte b2;
            do {
                b2 = dc[var9[i++]];
            } while(i < len && b2 == -1);

            if(b2 == -1) {
                break;
            }

            sb.append((char)(b1 << 2 | (b2 & 48) >>> 4));

            byte b3;
            do {
                b3 = var9[i++];
                if(b3 == 61) {
                    return sb.toString().getBytes("iso8859-1");
                }

                b3 = dc[b3];
            } while(i < len && b3 == -1);

            if(b3 == -1) {
                break;
            }

            sb.append((char)((b2 & 15) << 4 | (b3 & 60) >>> 2));

            byte b4;
            do {
                b4 = var9[i++];
                if(b4 == 61) {
                    return sb.toString().getBytes("iso8859-1");
                }

                b4 = dc[b4];
            } while(i < len && b4 == -1);

            if(b4 == -1) {
                break;
            }

            sb.append((char)((b3 & 3) << 6 | b4));
        }

        return sb.toString().getBytes("iso8859-1");
    }
}
