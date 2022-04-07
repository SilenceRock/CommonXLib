package com.sr.superhelperx.http.base64;

import com.sr.superhelperx.util.UtilBase64;

/**
 * Created by Hang.Yang on 2018/8/17 14:57.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public class AsyFileBase64 implements AsyBase64 {
    private String f;

    public AsyFileBase64(String filePath) {
        this.f = filePath;
    }

    public String toBase64() {
        return UtilBase64.encodeBase64File(this.f);
    }
}