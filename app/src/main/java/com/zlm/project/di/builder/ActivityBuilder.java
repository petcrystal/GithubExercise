package com.zlm.project.di.builder;

import com.zlm.project.di.module.UserDetailModule;
import com.zlm.project.di.module.UsersModule;
import com.zlm.project.ui.users.UserDetailActivity;
import com.zlm.project.ui.users.UsersActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Milla
 * @create 2019/4/1
 */

@Module
public abstract class ActivityBuilder {


    @ContributesAndroidInjector(modules = {UsersModule.class})
    abstract UsersActivity bindUsersActivity();


    @ContributesAndroidInjector(modules = {UserDetailModule.class})
    abstract UserDetailActivity bindUserDetailActivity();
}
