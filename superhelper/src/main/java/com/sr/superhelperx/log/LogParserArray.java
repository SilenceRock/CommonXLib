package com.sr.superhelperx.log;

import android.util.Pair;

/**
 * Created by Hang.Yang on 2018/8/17 16:03.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class LogParserArray {
    private LogParserArray() {
    }

    public static int getArrayDimension(Object o) {
        int d = 0;

        for(int i = 0; i < o.toString().length() && o.toString().charAt(i) == 91; ++i) {
            ++d;
        }

        return d;
    }

    public static Pair<Pair<Integer, Integer>, String> arrayToObject(Object o) {
        StringBuilder b = new StringBuilder();
        int c;
        int v;
        int var6;
        int var7;
        if(o instanceof int[][]) {
            int[][] os = (int[][])((int[][])o);
            c = os.length;
            v = c == 0?0:os[0].length;
            int[][] var5 = os;
            var6 = os.length;

            for(var7 = 0; var7 < var6; ++var7) {
                int[] o1 = var5[var7];
                b.append(arrayToString(o1).second).append("\n");
            }
        } else if(o instanceof byte[][]) {
            byte[][] var9 = (byte[][])((byte[][])o);
            c = var9.length;
            v = c == 0?0:var9[0].length;
            byte[][] var12 = var9;
            var6 = var9.length;

            for(var7 = 0; var7 < var6; ++var7) {
                byte[] var25 = var12[var7];
                b.append(arrayToString(var25).second).append("\n");
            }
        } else if(o instanceof short[][]) {
            short[][] var10 = (short[][])((short[][])o);
            c = var10.length;
            v = c == 0?0:var10[0].length;
            short[][] var14 = var10;
            var6 = var10.length;

            for(var7 = 0; var7 < var6; ++var7) {
                short[] var26 = var14[var7];
                b.append(arrayToString(var26).second).append("\n");
            }
        } else if(o instanceof long[][]) {
            long[][] var11 = (long[][])((long[][])o);
            c = var11.length;
            v = c == 0?0:var11[0].length;
            long[][] var16 = var11;
            var6 = var11.length;

            for(var7 = 0; var7 < var6; ++var7) {
                long[] var27 = var16[var7];
                b.append(arrayToString(var27).second).append("\n");
            }
        } else if(o instanceof float[][]) {
            float[][] var13 = (float[][])((float[][])o);
            c = var13.length;
            v = c == 0?0:var13[0].length;
            float[][] var18 = var13;
            var6 = var13.length;

            for(var7 = 0; var7 < var6; ++var7) {
                float[] var28 = var18[var7];
                b.append(arrayToString(var28).second).append("\n");
            }
        } else if(o instanceof double[][]) {
            double[][] var15 = (double[][])((double[][])o);
            c = var15.length;
            v = c == 0?0:var15[0].length;
            double[][] var20 = var15;
            var6 = var15.length;

            for(var7 = 0; var7 < var6; ++var7) {
                double[] var29 = var20[var7];
                b.append(arrayToString(var29).second).append("\n");
            }
        } else if(o instanceof boolean[][]) {
            boolean[][] var17 = (boolean[][])((boolean[][])o);
            c = var17.length;
            v = c == 0?0:var17[0].length;
            boolean[][] var22 = var17;
            var6 = var17.length;

            for(var7 = 0; var7 < var6; ++var7) {
                boolean[] var30 = var22[var7];
                b.append(arrayToString(var30).second).append("\n");
            }
        } else if(o instanceof char[][]) {
            char[][] var19 = (char[][])((char[][])o);
            c = var19.length;
            v = c == 0?0:var19[0].length;
            char[][] var23 = var19;
            var6 = var19.length;

            for(var7 = 0; var7 < var6; ++var7) {
                char[] var31 = var23[var7];
                b.append(arrayToString(var31).second).append("\n");
            }
        } else {
            Object[][] var21 = (Object[][])((Object[][])o);
            c = var21.length;
            v = c == 0?0:var21[0].length;
            Object[][] var24 = var21;
            var6 = var21.length;

            for(var7 = 0; var7 < var6; ++var7) {
                Object[] var32 = var24[var7];
                b.append(arrayToString(var32).second).append("\n");
            }
        }

        return Pair.create(Pair.create(Integer.valueOf(c), Integer.valueOf(v)), b.toString());
    }

    public static Pair arrayToString(Object o) {
        StringBuilder b = new StringBuilder("[");
        int l;
        int var5;
        int var6;
        if(o instanceof int[]) {
            int[] os = (int[])((int[])o);
            l = os.length;
            int[] var4 = os;
            var5 = os.length;

            for(var6 = 0; var6 < var5; ++var6) {
                int o1 = var4[var6];
                b.append(o1).append(",\t");
            }
        } else if(o instanceof byte[]) {
            byte[] var9 = (byte[])((byte[])o);
            l = var9.length;
            byte[] var12 = var9;
            var5 = var9.length;

            for(var6 = 0; var6 < var5; ++var6) {
                byte var25 = var12[var6];
                b.append(var25).append(",\t");
            }
        } else if(o instanceof short[]) {
            short[] var10 = (short[])((short[])o);
            l = var10.length;
            short[] var14 = var10;
            var5 = var10.length;

            for(var6 = 0; var6 < var5; ++var6) {
                short var26 = var14[var6];
                b.append(var26).append(",\t");
            }
        } else if(o instanceof long[]) {
            long[] var11 = (long[])((long[])o);
            l = var11.length;
            long[] var16 = var11;
            var5 = var11.length;

            for(var6 = 0; var6 < var5; ++var6) {
                long var27 = var16[var6];
                b.append(var27).append(",\t");
            }
        } else if(o instanceof float[]) {
            float[] var13 = (float[])((float[])o);
            l = var13.length;
            float[] var18 = var13;
            var5 = var13.length;

            for(var6 = 0; var6 < var5; ++var6) {
                float var28 = var18[var6];
                b.append(var28).append(",\t");
            }
        } else if(o instanceof double[]) {
            double[] var15 = (double[])((double[])o);
            l = var15.length;
            double[] var20 = var15;
            var5 = var15.length;

            for(var6 = 0; var6 < var5; ++var6) {
                double var29 = var20[var6];
                b.append(var29).append(",\t");
            }
        } else if(o instanceof boolean[]) {
            boolean[] var17 = (boolean[])((boolean[])o);
            l = var17.length;
            boolean[] var22 = var17;
            var5 = var17.length;

            for(var6 = 0; var6 < var5; ++var6) {
                boolean var30 = var22[var6];
                b.append(var30).append(",\t");
            }
        } else if(o instanceof char[]) {
            char[] var19 = (char[])((char[])o);
            l = var19.length;
            char[] var23 = var19;
            var5 = var19.length;

            for(var6 = 0; var6 < var5; ++var6) {
                char var31 = var23[var6];
                b.append(var31).append(",\t");
            }
        } else {
            Object[] var21 = (Object[])((Object[])o);
            l = var21.length;
            Object[] var24 = var21;
            var5 = var21.length;

            for(var6 = 0; var6 < var5; ++var6) {
                Object var32 = var24[var6];
                b.append(LogParserObj.objectToString(var32)).append(",\t");
            }
        }

        return Pair.create(Integer.valueOf(l), b.replace(b.length() - 2, b.length(), "]").toString());
    }
}

