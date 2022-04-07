package com.sr.superhelperx.view;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import com.sr.superhelperx.util.UtilToast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hang.Yang on 2018/8/17 16:29.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class AppAmimiaEdit extends EditText {
    private String inputAfterText;
    private boolean resetText;
    private Context context;
    private int cursorPos;

    public AppAmimiaEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {
                if(!AppAmimiaEdit.this.resetText) {
                    AppAmimiaEdit.this.cursorPos = AppAmimiaEdit.this.getSelectionEnd();
                    AppAmimiaEdit.this.inputAfterText = s.toString();
                }

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!AppAmimiaEdit.this.resetText) {
                    if(count >= 2) {
                        CharSequence input = s.subSequence(AppAmimiaEdit.this.cursorPos, AppAmimiaEdit.this.cursorPos + count);
                        if(AppAmimiaEdit.containsEmoji(input.toString())) {
                            AppAmimiaEdit.this.resetText = true;
                            UtilToast.show("不支持输入Emoji表情符号");
                            AppAmimiaEdit.this.setText(AppAmimiaEdit.this.inputAfterText);
                            Editable text = AppAmimiaEdit.this.getText();
                            if(text instanceof Spannable) {
                                Spannable spanText = (Spannable)text;
                                Selection.setSelection(spanText, text.length());
                            }
                        }
                    }
                } else {
                    AppAmimiaEdit.this.resetText = false;
                }

            }

            public void afterTextChanged(Editable s) {
            }
        });
        this.setFilters(new InputFilter[]{new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat = "[`~！!@#$%^&*()+=|{}\\[\\]<>/~@#￥%……&*（）——+|{}【】 ]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                return matcher.find()?"":null;
            }
        }});
    }

    public static boolean containsEmoji(String source) {
        int len = source.length();

        for(int i = 0; i < len; ++i) {
            char codePoint = source.charAt(i);
            if(!isEmojiCharacter(codePoint)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return codePoint == 0 || codePoint == 9 || codePoint == 10 || codePoint == 13 || codePoint >= 32 && codePoint <= '\ud7ff' || codePoint >= '\ue000' && codePoint <= '�' || codePoint >= 65536 && codePoint <= 1114111;
    }
}

