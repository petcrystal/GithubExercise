package com.zlm.project.utils;

import java.util.regex.Pattern;

public final class RegexUtil {

    // -------------------------------------------
    private static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[_-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    private static final String REGEX_SPECIAL = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？」「]";
    private static final String REGEX_PHONE = "[09][0-9]{8}";

    // -------------------------------------------
    private RegexUtil() {
    }

    // -------------------------------------------
    public static boolean isChinese(char c) {
        return (c >= 0x4E00 && c <= 0x9FA5) || (c >= 0x30 && c <= 0x39);
    }

    // -------------------------------------------

    /**
     * 比對是否符合Mail格式
     *
     * @param mail mail
     * @return true：符合、false：不符合
     */
    public static boolean regexMail(String mail) {
        return Pattern.matches(REGEX_EMAIL, mail);
    }

    // -------------------------------------------

    /**
     * 比對是否有特殊字元
     *
     * @param str 待轉換的資料
     * @return 轉換完畢的資料
     */
    public static boolean regexSpecial(String str) {
        return Pattern.matches(REGEX_SPECIAL, str);
    }

    // -------------------------------------------

    /**
     * 比對是否符合手機格式
     *
     * @param str 待轉換的資料
     * @return 轉換完畢的資料
     */
    public static boolean regexPhone(String str) {
        return Pattern.matches(REGEX_PHONE, str);
    }

    // -------------------------------------------
}
