package com.sr.superhelperx.permission;

import android.content.Intent;
import android.os.Bundle;

import com.sr.superhelperx.activity.AppActivity;
import com.sr.superhelperx.app.AppApplication;
import com.sr.superhelperx.http.Http;

/**
 * Created by Hang.Yang on 2018/8/17 14:17.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class PermissionsActivity extends AppActivity {
    public static PermissionsActivity.PermissionsCallBack P;
    private boolean f = true;

    public PermissionsActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String[] p = this.getIntent().getStringArrayExtra("permissions");
        this.permission(p, new PermissionsResultAction() {
            public void onGranted() {
                try {
                    PermissionsActivity.P.onSuccess();
                } catch (Exception var2) {
                    ;
                }

                for(int i = 0; i < p.length; ++i) {
                    Http.getInstance().log(this.getClass().toString() + "->show: %s", p[i] + "权限申请成功：");
                }

                PermissionsActivity.this.finish();
            }

            public void onDenied(String permission) {
                try {
                    PermissionsActivity.P.onFail();
                } catch (Exception var3) {
                    ;
                }

                Http.getInstance().log(this.getClass().toString() + "->show: %s", "未申请：" + permission);
                PermissionsActivity.this.finish();
            }
        });
    }

    protected void onResume() {
        super.onResume();
        if(this.f = !this.f) {
            this.finish();
        }

    }

    protected void onDestroy() {
        P = null;
        super.onDestroy();
    }

    public static void StartActivity(String[] permissions, PermissionsActivity.PermissionsCallBack permissionsCallBack) {
        P = permissionsCallBack;
        AppApplication.AINSTANCE.startActivity((new Intent(AppApplication.AINSTANCE, PermissionsActivity.class)).addFlags(268435456).putExtra("permissions", permissions));
    }

    public static class PermissionsCallBack {
        public PermissionsCallBack() {
        }

        public void onSuccess() {
        }

        public void onFail() {
        }
    }
}
