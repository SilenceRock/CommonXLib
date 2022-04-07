package com.sr.superhelperx.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Hang.Yang on 2018/8/17 16:27.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

/**
 * edittext监听
 */
public class UtilWatcher {
    private UtilWatcher() {
    }

    public static TextWatcher Watcher(final EditText editText, final String content) {
        return new TextWatcher() {
            private String g = content;
            private String k;

            public void beforeTextChanged(CharSequence c, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence c, int i, int i1, int i2) {
                this.k = c.toString();
            }

            public void afterTextChanged(Editable editable) {
                try {
                    boolean y = false;
                    char[] l = this.k.toCharArray();

                    for(int i = 0; i < l.length; ++i) {
                        String o = String.valueOf(l[i]);
                        if(!this.g.contains(o)) {
                            this.k = this.k.replace(o, "");
                            y = true;
                        }
                    }

                    if(y) {
                        editText.setText(this.k);
                    }
                } catch (Exception var6) {
                    ;
                }

            }
        };
    }
}

