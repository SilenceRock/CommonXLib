package com.sr.superhelperx.view.web;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.sr.superhelperx.activity.AppActivity;
import com.sr.superhelperx.R;
import com.sr.superhelperx.app.AppApplication;
import com.sr.superhelperx.fragment.navigation.NavigationManagerFactory;

/**
 * Created by Hang.Yang on 2018/8/17 16:47.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class AppWebActivity extends AppActivity {
    private AppWebFragment appWebFragment = new AppWebFragment();

    public AppWebActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentViewAsy(R.layout.actviity_web);
    }

    public void onAsyLayoutInit(Bundle savedInstanceState) {
        this.appWebFragment.loadUrl(this.getIntent().getStringExtra("url"));
        NavigationManagerFactory.create(this, R.id.web_content).addFragment(new Fragment[]{this.appWebFragment}).show(this.appWebFragment);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == 4 && !this.appWebFragment.back()) {
            this.finish();
        }

        return false;
    }

    public static void StartActivity(String url) {
        AppApplication.AINSTANCE.startActivity((new Intent(AppApplication.AINSTANCE, AppWebV4Activity.class)).addFlags(268435456).putExtra("url", url));
    }
}
