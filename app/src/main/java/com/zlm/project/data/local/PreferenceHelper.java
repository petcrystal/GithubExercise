package com.zlm.project.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.IntDef;

import com.zlm.project.di.PreferenceInfo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Inject;

public class PreferenceHelper implements PreferenceImp {

    // ---------------Read Mode-------------------
    public static final int READ_LIGHT = 1;
    public static final int READ_DARK = 0;

    @IntDef({READ_DARK, READ_LIGHT})
    @Retention(RetentionPolicy.SOURCE)
    @interface ReadMode {
    }

    // -------------Speech Mode------------------
    public static final int SPEECH_CONTINUOUS = 1;
    public static final int SPEECH_SINGLE = 0;

    @IntDef({SPEECH_SINGLE, SPEECH_CONTINUOUS})
    @Retention(RetentionPolicy.SOURCE)
    @interface SpeechMode {
    }

    // -------------------------------------------
    // is first start application.
    private final String IS_FIRST = "IsFirst";
    // user account.
    private final String ACCOUNT = "Account";
    // user read mode.
    private final String READ_MODE = "ReadMode";
    // notification for day.
    private final String NOTIFICATION_DAILY = "NotificationDaily";
    // notification for week.
    private final String NOTIFICATION_WEEKLY = "NotificationWeekly";
    // article txt setting.
    private final String TXT_SETTING = "TxtSetting";
    // article speech mode.
    private final String SPEECH_MODE = "SpeechMode";
    // user token.
    private final String USER_TOKEN = "UserToken";

    private final String LOGIN_STATUS = "LoginStatus";

    private final String IS_UPDATE_DEVICE = "IsUpdateDevice";
    private final String FCM_TOKEN = "FCMToken";

    // -------------------------------------------
    private SharedPreferences sp;

    // -------------------------------------------
    @Inject
    public PreferenceHelper(Context context, @PreferenceInfo String prefFileName) {
        sp = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    // -------------------------------------------


    // -------------------------------------------

    /**
     * Save Firebase token.
     */
    @Override
    public void setFCMToken(String token) {
        sp.edit().putString(FCM_TOKEN, token).apply();
    }

    // -------------------------------------------

    /**
     * Get Firebase token.
     */
    @Override
    public String getFCMToken() {
        return sp.getString(FCM_TOKEN, "");
    }

    // -------------------------------------------

    /**
     * If need to update device information.
     */
    public void setUpdateDevice(boolean isUpdateDevice) {
        sp.edit().putBoolean(IS_UPDATE_DEVICE, isUpdateDevice).apply();
    }

    // -------------------------------------------
    public boolean isUpdateDevice() {
        return sp.getBoolean(IS_UPDATE_DEVICE, false);
    }

    // -------------------------------------------

    /**
     * Save login status.
     */
    @Override
    public void setLoginStatus(boolean isLogin) {
        sp.edit().putBoolean(LOGIN_STATUS, isLogin).apply();
    }

    // -------------------------------------------

    /**
     * Get login status.
     */
    @Override
    public boolean isLogin() {
        return sp.getBoolean(LOGIN_STATUS, false);
    }

    // -------------------------------------------

    /**
     * Save user token.
     */
    @Override
    public void setUserToken(String token) {
        sp.edit().putString(USER_TOKEN, token).apply();
    }

    // -------------------------------------------

    /**
     * Get user token.
     */
    @Override
    public String getUserToken() {
        return sp.getString(USER_TOKEN, "");
    }

    // -------------------------------------------

    /**
     * Set to enable daily notifications.
     *
     * @param isTrue true:yes,false:no
     */
    public void setNotificationDaily(boolean isTrue) {
        sp.edit().putBoolean(NOTIFICATION_DAILY, isTrue).apply();
    }

    // -------------------------------------------

    /**
     * is enable daily notifications.
     *
     * @return true:yes,false:no
     */
    public boolean isNotificationDaily() {
        return sp.getBoolean(NOTIFICATION_DAILY, true);
    }

    // -------------------------------------------

    /**
     * Set to enable weekly notifications.
     *
     * @param isTrue true:yes,false:no
     */
    public void setNotificationWeelyk(boolean isTrue) {
        sp.edit().putBoolean(NOTIFICATION_WEEKLY, isTrue).apply();
    }

    // -------------------------------------------

    /**
     * is enable weekly notifications.
     *
     * @return true:yes,false:no
     */
    public boolean isNotificationWeekly() {
        return sp.getBoolean(NOTIFICATION_WEEKLY, true);
    }

    // -------------------------------------------

    /**
     * 設定文字設定
     *
     * @param level 文字等級
     */
    public void setTxtSetting(int level) {
        sp.edit().putInt(TXT_SETTING, level).apply();
    }

    // -------------------------------------------

    /**
     * 取得文字設定
     *
     * @return default 0
     */
    public int getTxtSetting() {
        return sp.getInt(TXT_SETTING, 0);
    }

    // -------------------------------------------

    /**
     * 設定朗讀模式
     *
     * @param speechMode 朗讀模式
     * @see SpeechMode
     */
    public void setSpeechMode(@SpeechMode int speechMode) {
        sp.edit().putInt(SPEECH_MODE, speechMode).apply();
    }

    // -------------------------------------------

    /**
     * 取得朗讀模式
     *
     * @see SpeechMode
     */
    public int getSpeechMode() {
        return sp.getInt(SPEECH_MODE, SPEECH_SINGLE);
    }

    // -------------------------------------------

    /**
     * 設定閱讀模式模式
     *
     * @param readMode 觀看ReadMode設定
     * @see ReadMode
     */
    public void setReadMode(@ReadMode int readMode) {
        sp.edit().putInt(READ_MODE, readMode).apply();
    }

    // -------------------------------------------

    /**
     * Get read mode.
     *
     * @return read mode.
     * @see ReadMode
     */
    public int getReadMode() {
        return sp.getInt(READ_MODE, READ_LIGHT);
    }

    // -------------------------------------------

    /**
     * Save first open app.
     *
     * @param isFirst false: not first.
     */
    @Override
    public void setFirst(boolean isFirst) {
        sp.edit().putBoolean(IS_FIRST, isFirst).apply();
    }

    // -------------------------------------------

    /**
     * User is first open app.
     */
    @Override
    public boolean isFirst() {
        return sp.getBoolean(IS_FIRST, true);
    }

    // -------------------------------------------

    /**
     * Save user account.
     *
     * @param account user email.
     */
    public void setAccount(String account) {
        sp.edit().putString(ACCOUNT, account).apply();
    }

    // -------------------------------------------

    /**
     * Get user email.
     *
     * @return account
     */
    public String getAccount() {
        return sp.getString(ACCOUNT, "");
    }

    // -------------------------------------------
}
