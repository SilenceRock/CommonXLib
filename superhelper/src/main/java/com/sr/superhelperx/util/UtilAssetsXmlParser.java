package com.sr.superhelperx.util;

import android.content.Context;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by Hang.Yang on 2018/8/17 16:16.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class UtilAssetsXmlParser {
    private UtilAssetsXmlParser() {
    }

    public static View getView(Context context, String filePath) {
        return LayoutInflater.from(context).inflate(getXmlPullParser(context, filePath), (ViewGroup)null);
    }

    public static XmlPullParser getXmlPullParser(Context context, String filePath) {
        XmlPullParser xmlPullParser = null;

        try {
            xmlPullParser = Xml.newPullParser();
            xmlPullParser.setInput(context.getAssets().open(filePath), "UTF-8");
        } catch (IOException var4) {
            var4.printStackTrace();
        } catch (XmlPullParserException var5) {
            var5.printStackTrace();
        }

        return xmlPullParser;
    }
}
