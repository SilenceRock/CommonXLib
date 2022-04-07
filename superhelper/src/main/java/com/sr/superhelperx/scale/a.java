package com.sr.superhelperx.scale;

import android.view.View;
import android.widget.ImageView;

import com.sr.superhelperx.rebound.g;
import com.sr.superhelperx.rebound.h;

/**
 * Created by Hang.Yang on 2018/8/17 13:45.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

class a {

    private String[] a;

    a() {
    }

    void d(String c, View b, c d, h f) {
        if(c != null && !c.equals("")) {
            if(c.contains("%s")) {
                f.b(g.c(b, 1.0F));
            } else if(c.contains("%k")) {
                f.b(g.e(b, 1.0F));
            } else if(c.contains("%v")) {
                f.b(g.b(b, -b.getWidth()));
            } else if(c.contains("%r")) {
                f.b(g.b(b, d.b));
            } else if(c.contains("%p")) {
                f.b(g.h(b, -b.getHeight()));
            } else if(c.contains("%b")) {
                f.b(g.h(b, d.a));
            }

            if((this.a = this.c(c, "(", ")")) != null) {
                try {
                    if(this.a.length == 3) {
                        d.loadViewWidthHeightSize(b, this.b(this.a[0]), this.b(this.a[1]), this.b(this.a[2]));
                    } else if(this.a.length == 2) {
                        d.loadViewWidthHeight(b, this.b(this.a[0]), this.b(this.a[1]));
                    } else {
                        d.loadViewSize(b, this.b(this.a[0]));
                    }
                } catch (Exception var12) {
                    ;
                }
            }

            if((this.a = this.c(c, "[", "]")) != null) {
                try {
                    d.loadViewPadding(b, this.b(this.a[0]), this.b(this.a[1]), this.b(this.a[2]), this.b(this.a[3]));
                } catch (Exception var11) {
                    ;
                }
            }

            if((this.a = this.c(c, "{", "}")) != null) {
                try {
                    d.loadViewMargin(b, this.b(this.a[0]), this.b(this.a[1]), this.b(this.a[2]), this.b(this.a[3]));
                } catch (Exception var10) {
                    ;
                }
            }

            if((this.a = this.c(c, "(nw", "nw)")) != null) {
                try {
                    d.loadViewMinWidth(b, this.b(this.a[0]));
                } catch (Exception var9) {
                    ;
                }
            }

            if((this.a = this.c(c, "(xw", "xw)")) != null) {
                try {
                    d.loadViewMaxWidth(b, this.b(this.a[0]));
                } catch (Exception var8) {
                    ;
                }
            }

            if((this.a = this.c(c, "(nh", "nh)")) != null) {
                try {
                    d.loadViewMinHeight(b, this.b(this.a[0]));
                } catch (Exception var7) {
                    ;
                }
            }

            if((this.a = this.c(c, "(xh", "xh)")) != null) {
                try {
                    d.loadViewMaxHeight(b, this.b(this.a[0]));
                } catch (Exception var6) {
                    ;
                }
            }

            if(b instanceof ImageView) {
                b.setTag((Object)null);
            }
        }

    }

    private String[] c(String c, String s, String e) {
        return c.contains(s) && c.contains(e)?c.substring(c.indexOf(s) + 1 + s.length() - 1, c.indexOf(e)).split(","):null;
    }

    private int b(String s) {
        return Integer.parseInt(s);
    }
}
