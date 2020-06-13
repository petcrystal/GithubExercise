package com.zlm.project.di.module;

import com.zlm.project.ui.adapter.UsersAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * @author Milla
 * @create 2019/6/9
 */

@Module
public class UsersModule {

    @Provides
    UsersAdapter provideUsersAdapter() {
        return new UsersAdapter(new ArrayList<>());
    }


}
