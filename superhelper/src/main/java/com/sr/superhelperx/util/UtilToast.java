package com.sr.superhelperx.util;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.sr.superhelperx.app.AppApplication;

/**
 * Created by Hang.Yang on 2018/8/17 16:26.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class UtilToast {

    private static Toast T;

    private UtilToast() {
    }

    @SuppressLint("ShowToast")
    public static void show(Object object) {
        if(object != null) {

            if(T == null) {
                T = Toast.makeText(AppApplication.AINSTANCE, "", Toast.LENGTH_SHORT);
            }else {
                T.cancel();
                T = Toast.makeText(AppApplication.AINSTANCE, "", Toast.LENGTH_LONG);
                T.setDuration(Toast.LENGTH_SHORT);
            }

            T.setGravity(Gravity.CENTER,0,0);

            if(object instanceof CharSequence) {
                if(!object.equals("")) {
                    T.setText((CharSequence)object);
                    T.show();
                }
            } else if(object instanceof Integer) {
                T.setText((Integer) object);
                T.show();
            } else if(object instanceof View) {
                T.setView((View)object);
                T.show();
            }
        }
    }

    @SuppressLint("ShowToast")
    public static void show(Object object,int duration) {
        if(object != null) {

            if(T == null) {
                T = Toast.makeText(AppApplication.AINSTANCE, "", duration);
            }else {
                T.cancel();
                T = Toast.makeText(AppApplication.AINSTANCE, "", duration);
                T.setDuration(duration);
            }

            T.setGravity(Gravity.CENTER,0,0);

            if(object instanceof CharSequence) {
                if(!object.equals("")) {
                    T.setText((CharSequence)object);
                    T.show();
                }
            } else if(object instanceof Integer) {
                T.setText((Integer) object);
                T.show();
            } else if(object instanceof View) {
                T.setView((View)object);
                T.show();
            }
        }
    }

    public static void cancel(){
        if (T != null){
            T.cancel();
        }
    }
}