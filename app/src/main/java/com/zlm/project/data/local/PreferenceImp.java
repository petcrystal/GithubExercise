package com.zlm.project.data.local;

/**
 * @author Milla
 * @create 2019/3/29
 */
public interface PreferenceImp {

    void setFCMToken(String fcmToken);

    String getFCMToken();

    void setUserToken(String userToken);

    String getUserToken();

    void setFirst(boolean isFirst);

    boolean isFirst();

    void setLoginStatus(boolean isLogin);

    boolean isLogin();
}
