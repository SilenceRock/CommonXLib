package com.sr.superhelperx.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.CountDownTimer;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Hang.Yang on 2018/8/17 16:30.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

@SuppressLint({"NewApi"})
public class AppGetVerification extends AppCompatTextView {
    public AppGetVerification(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void startCountDown() {
        if(this.isClickable()) {
            this.setClickable(false);
            (new CountDownTimer(60000L, 1L) {
                private String temp = AppGetVerification.this.getText().toString();

                public void onTick(long millisInFuture) {
                    AppGetVerification.this.setText(AppGetVerification.this.timeString(millisInFuture));
                }

                public void onFinish() {
                    AppGetVerification.this.setText(this.temp);
                    AppGetVerification.this.setClickable(true);
                }
            }).start();
        }

    }

    protected String timeString(long millisInFuture) {
        return millisInFuture / 1000L + "s";
    }
}
