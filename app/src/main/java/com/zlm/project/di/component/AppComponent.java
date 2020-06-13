package com.zlm.project.di.component;

import android.app.Application;

import com.zlm.project.di.builder.ActivityBuilder;
import com.zlm.project.di.module.AppModule;
import com.zlm.project.other.MyApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * @author Milla
 * @create 2019/3/29
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    // -------------------------------------------
    void inject(MyApplication app);

    // -------------------------------------------
    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    // -------------------------------------------
}
