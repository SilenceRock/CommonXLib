package com.sr.superhelperx.log;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Hang.Yang on 2018/8/17 14:44.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class LogParserJson {
    private static final int JSON_INDENT = 4;

    private LogParserJson() {
    }

    public static String json(String j) {
        if(TextUtils.isEmpty(j)) {
            return j;
        } else {
            try {
                return j.startsWith("{")?(new JSONObject(j)).toString(4):(j.startsWith("[")?(new JSONArray(j)).toString(4):j);
            } catch (JSONException var2) {
                return var2.getCause().getMessage() + "\n" + j;
            }
        }
    }
}