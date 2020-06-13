package com.zlm.project.data;

import android.content.Context;

import com.zlm.project.connect.ApiClientImp;
import com.zlm.project.connect.ApiService;
import com.zlm.project.data.local.PreferenceImp;
import com.zlm.project.other.uuid.DeviceUUIDImp;

import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Milla
 * @create 2019/3/29
 */

@Singleton
public class DataManager implements DataManagerImp {

    private final Context context;
    private final PreferenceImp preference;
    private final DeviceUUIDImp uuidImp;
    private final ApiClientImp apiClient;

    // -------------------------------------------
    @Inject
    public DataManager(Context context, PreferenceImp preference, DeviceUUIDImp uuidImp, ApiClientImp apiClient) {
        this.context = context;
        this.preference = preference;
        this.uuidImp = uuidImp;
        this.apiClient = apiClient;
    }

    // -------------------------------------------
    @Override
    public void setFCMToken(String fcmToken) {
        preference.setFCMToken(fcmToken);
    }

    @Override
    public String getFCMToken() {
        return preference.getFCMToken();
    }

    @Override
    public void setUserToken(String userToken) {
        preference.setUserToken(userToken);
    }

    @Override
    public String getUserToken() {
        return preference.getUserToken();
    }

    @Override
    public void setFirst(boolean isFirst) {
        preference.setFirst(isFirst);
    }

    @Override
    public boolean isFirst() {
        return preference.isFirst();
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        preference.setLoginStatus(isLogin);
    }

    @Override
    public boolean isLogin() {
        return preference.isLogin();
    }

    // -------------------------------------------
    @Override
    public UUID getUUID() {
        return uuidImp.getUUID();
    }

    // -------------------------------------------
    @Override
    public ApiService getApi() {
        return apiClient.getApi();
    }

    // -------------------------------------------
    @Override
    public Context getContext() {
        return context;
    }

    // -------------------------------------------
}
