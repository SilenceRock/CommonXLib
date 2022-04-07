package com.sr.superhelperx.log;

import android.text.TextUtils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Created by Hang.Yang on 2018/8/17 16:04.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */


public final class LogParserXml {
    private LogParserXml() {
    }

    public static String xml(String x) {
        if(TextUtils.isEmpty(x)) {
            return "Empty/Null xml content.(This msg from logger)";
        } else {
            try {
                StreamSource e = new StreamSource(new StringReader(x));
                StreamResult xo = new StreamResult(new StringWriter());
                Transformer t = TransformerFactory.newInstance().newTransformer();
                t.setOutputProperty("indent", "yes");
                t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                t.transform(e, xo);
                return xo.getWriter().toString().replaceFirst(">", ">\n");
            } catch (TransformerException var4) {
                return var4.getCause().getMessage() + "\n" + x;
            }
        }
    }
}