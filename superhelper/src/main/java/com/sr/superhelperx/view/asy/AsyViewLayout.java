package com.sr.superhelperx.view.asy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sr.superhelperx.http.Asy;
import com.sr.superhelperx.receiver.NetWorkReceiver;
import com.sr.superhelperx.scale.ScaleScreenHelperFactory;
import com.sr.superhelperx.sign.g;
import com.sr.superhelperx.util.UtilClassName;

/**
 * Created by Hang.Yang on 2018/8/17 16:36.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

/*@f
@z*/
@AsyView
@SuppressLint({"HandlerLeak"})
public abstract class AsyViewLayout extends RelativeLayout {
    public static final int s = 22;
    public static final int r = 33;
    public static final int u = 44;
    public static final int n = 55;
    private LayoutInflater l;
    private String h;
    private Asy<?> e;
    private View d;
    private int f;
    private b a = new b() {
        private AsyView j = (AsyView)AsyViewLayout.this.getClass().getAnnotation(AsyView.class);
        private View y;
        private View p;
        private View k;

        protected void c(int t) {
            switch(AsyViewLayout.this.f = t) {
                case 22:
                    try {
                        AsyViewLayout.this.removeView(this.p);
                    } catch (Exception var6) {
                        ;
                    }

                    try {
                        AsyViewLayout.this.removeView(this.y);
                    } catch (Exception var5) {
                        ;
                    }

                    try {
                        AsyViewLayout.this.removeView(this.k);
                    } catch (Exception var4) {
                        ;
                    }

                    try {
                        AsyViewLayout.this.d.setVisibility(0);
                    } catch (Exception var3) {
                        ;
                    }
                    break;
                case 33:
                    if(this.j.refresh() && AsyViewLayout.this.d.getVisibility() != 0) {
                        if(this.y == null) {
                            this.y = ScaleScreenHelperFactory.getInstance().loadViewGroup(AsyViewLayout.this.onCreateRefresh(AsyViewLayout.this.l));
                        }

                        try {
                            AsyViewLayout.this.addView(this.y);
                        } catch (Exception var9) {
                            ;
                        }
                    }
                    break;
                case 44:
                    if(this.j.upload()) {
                        if(this.p == null) {
                            this.p = ScaleScreenHelperFactory.getInstance().loadViewGroup(AsyViewLayout.this.onCreateUpload(AsyViewLayout.this.l));
                        }

                        try {
                            AsyViewLayout.this.addView(this.p);
                        } catch (Exception var8) {
                            ;
                        }
                    }
                    break;
                case 55:
                    if(this.k == null) {
                        this.k = ScaleScreenHelperFactory.getInstance().loadViewGroup(AsyViewLayout.this.onCreateNothing(AsyViewLayout.this.l));
                    }

                    try {
                        AsyViewLayout.this.addView(this.k);
                    } catch (Exception var7) {
                        ;
                    }
            }

        }
    };
    private NetWorkReceiver m = new NetWorkReceiver() {
        protected void onConnectChange(boolean c) {
            if(AsyViewLayout.this.f == 33 && c) {
                AsyViewLayout.this.refresh();
            }

        }
    };

    public AsyViewLayout(Context c, AttributeSet t) {
        super(c, t);
        if(g.a(this)) {
            this.h = (String)this.getTag();
            this.l = LayoutInflater.from(c);

            try {
                p.e().e((String)this.getTag(), new a() {
                    public void u(Asy<?> a) {
                        AsyViewLayout.this.e = a;
                    }
                });
            } catch (Exception var4) {
                ;
            }
        }

    }

    protected void refresh() {
        try {
            this.e.e();
        } catch (Exception var2) {
            ;
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        try {
            this.d = this.getChildAt(0);
            this.getContext().registerReceiver(this.a, new IntentFilter(this.h));
            this.getContext().registerReceiver(this.m, new IntentFilter(this.m.createAction()));
        } catch (Exception var2) {
            ;
        }

    }

    protected void onDetachedFromWindow() {
        try {
            this.getContext().unregisterReceiver(this.m);
            this.getContext().unregisterReceiver(this.a);
            p.e().e((String)this.getTag());
        } catch (Exception var2) {
            ;
        }

        super.onDetachedFromWindow();
    }

    public static void end(Context context, Class<?> cls) {
        end(context, UtilClassName.getActionName(cls));
    }

    public static void end(Context context, String tag) {
        g(context, tag, 22);
    }

    public static void refresh(Context context, Class<?> cls) {
        refresh(context, UtilClassName.getActionName(cls));
    }

    public static void refresh(Context context, String tag) {
        g(context, tag, 33);
    }

    public static void upload(Context context, Class<?> cls) {
        upload(context, UtilClassName.getActionName(cls));
    }

    public static void upload(Context context, String tag) {
        g(context, tag, 44);
    }

    public static void nothing(Context context, Class<?> cls) {
        nothing(context, UtilClassName.getActionName(cls));
    }

    public static void nothing(Context context, String tag) {
        g(context, tag, 55);
    }

    public static void g(Context c, String cl, int t) {
        c.sendBroadcast((new Intent(cl)).putExtra("vegsdfe", t));
    }

    protected abstract ViewGroup onCreateRefresh(LayoutInflater var1);

    protected abstract ViewGroup onCreateUpload(LayoutInflater var1);

    protected abstract ViewGroup onCreateNothing(LayoutInflater var1);
}

