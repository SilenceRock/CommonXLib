package com.sr.superhelperx.view.web;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.KeyEvent;

import com.sr.superhelperx.activity.AppV4Activity;
import com.sr.superhelperx.R;
import com.sr.superhelperx.app.AppApplication;
import com.sr.superhelperx.fragment.navigation.NavigationManagerFactory;

/**
 * Created by Hang.Yang on 2018/8/17 16:48.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class AppWebV4Activity extends AppV4Activity {
    private AppWebV4Fragment appWebV4Fragment = new AppWebV4Fragment();

    public AppWebV4Activity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentViewAsy(R.layout.actviity_web);
    }

    public void onAsyLayoutInit(Bundle savedInstanceState) {
        this.appWebV4Fragment.loadUrl(this.getIntent().getStringExtra("url"));
        NavigationManagerFactory.createV4(this, R.id.web_content).addFragment(new Fragment[]{this.appWebV4Fragment}).show(this.appWebV4Fragment);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == 4 && !this.appWebV4Fragment.back()) {
            this.finish();
        }

        return false;
    }

    public static void StartActivity(String url) {
        AppApplication.AINSTANCE.startActivity((new Intent(AppApplication.AINSTANCE, AppWebV4Activity.class)).addFlags(268435456).putExtra("url", url));
    }
}