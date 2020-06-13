package com.zlm.project.other;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.zlm.project.R;
import com.zlm.project.di.component.DaggerAppComponent;
import com.zlm.project.utils.FirebaseUtil;
import com.crashlytics.android.Crashlytics;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import io.fabric.sdk.android.Fabric;
import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import me.yokeyword.fragmentation.BuildConfig;
import me.yokeyword.fragmentation.Fragmentation;

public class MyApplication extends Application implements HasActivityInjector {

    public static Context context;

    // -------------------------------------------
    static {

        ClassicsHeader.REFRESH_HEADER_PULLING = "下拉更新";
        ClassicsHeader.REFRESH_HEADER_REFRESHING = "正在更新...";
        ClassicsHeader.REFRESH_HEADER_LOADING = "正在加載...";
        ClassicsHeader.REFRESH_HEADER_RELEASE = "放開立即更新";
        ClassicsHeader.REFRESH_HEADER_FINISH = "更新完成";
        ClassicsHeader.REFRESH_HEADER_FAILED = "更新失敗";
        ClassicsHeader.REFRESH_HEADER_UPDATE = "上次更新 M-d HH:mm";

        // Set global header.
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((Context context, RefreshLayout layout) -> {
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            return new ClassicsHeader(context);
        });
        // Set global footer.
        SmartRefreshLayout.setDefaultRefreshFooterCreator((Context context, RefreshLayout layout) -> {
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

    // -------------------------------------------
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    // -------------------------------------------
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        context = getApplicationContext();

        Logger.addLogAdapter(new AndroidLogAdapter());

        Fragmentation.builder()
                .stackViewMode(Fragmentation.NONE)
                .debug(BuildConfig.DEBUG)
                .handleException(Throwable::printStackTrace)
                .install();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);


    }

    // -------------------------------------------
}
