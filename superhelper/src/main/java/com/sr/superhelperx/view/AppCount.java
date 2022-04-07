package com.sr.superhelperx.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Hang.Yang on 2018/8/17 16:30.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class AppCount extends AppCompatTextView {
    private AppCount.Receiver receiver = new AppCount.Receiver();
    private int count;

    public AppCount(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setCount(this.count);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.getContext().registerReceiver(this.receiver, new IntentFilter("cart_amount"));
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.getContext().unregisterReceiver(this.receiver);
    }

    private void setCount(int count) {
        this.setVisibility(count > 0?0:4);
        this.setText(String.valueOf(count));
    }

    public static void SendCount(Context context, int count) {
        context.sendBroadcast((new Intent("cart_amount")).putExtra("count", count));
    }

    class Receiver extends BroadcastReceiver {
        Receiver() {
        }

        public void onReceive(Context context, Intent intent) {
            AppCount.this.setCount(AppCount.this.count = intent.getIntExtra("count", 0));
        }
    }
}

