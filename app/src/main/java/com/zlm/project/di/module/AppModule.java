package com.zlm.project.di.module;

import android.app.Application;
import android.content.Context;

import com.zlm.project.connect.ApiClient;
import com.zlm.project.connect.ApiClientImp;
import com.zlm.project.data.DataManager;
import com.zlm.project.data.DataManagerImp;
import com.zlm.project.data.local.PreferenceHelper;
import com.zlm.project.data.local.PreferenceImp;
import com.zlm.project.di.PreferenceInfo;
import com.zlm.project.other.AppConstants;
import com.zlm.project.other.uuid.DeviceUUID;
import com.zlm.project.other.uuid.DeviceUUIDImp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Milla
 * @create 2019/3/29
 */

@Module
public class AppModule {

    // -------------------------------------------
    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    // -------------------------------------------
    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferenceImp providePreferenceHelper(PreferenceHelper preferenceHelper) {
        return preferenceHelper;
    }

    // -------------------------------------------
    @Provides
    @Singleton
    DataManagerImp provideDataManager(DataManager dataManager) {
        return dataManager;
    }

    // -------------------------------------------
    @Provides
    @Singleton
    DeviceUUIDImp provideDeviceUUID(DeviceUUID deviceUUID) {
        return deviceUUID;
    }

    // -------------------------------------------
    @Provides
    @Singleton
    ApiClientImp provideApiClient(ApiClient apiClient) {
        return apiClient;
    }

    // -------------------------------------------

}
