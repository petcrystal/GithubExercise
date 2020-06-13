package com.zlm.project.utils;

public final class TextSettingUtil {

    private static final int CONTENT_TXT = 20;

    // -------------------------------------------
    private TextSettingUtil() {

    }

    // -------------------------------------------

    /**
     * Setting text font size.
     *
     * @param multiple size multiple.
     * @return font size.
     */
    public static float getFontSize(int multiple) {
        return CONTENT_TXT * (1 + multiple * 0.1f);
    }

    // -------------------------------------------
}
