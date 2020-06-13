package com.zlm.project.utils;

public final class HtmlTextUtil {

    // -------------------------------------------
    private HtmlTextUtil() {
    }

    // -------------------------------------------
    public final static String getHtmlColor(String str1, String str2) {
        return String.format(str1, "<font color='#cd0505'>" + str2 + "</font>");
    }

    // -------------------------------------------
}
