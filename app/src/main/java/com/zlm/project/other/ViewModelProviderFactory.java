package com.zlm.project.other;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.zlm.project.data.DataManager;
import com.zlm.project.ui.users.UserDetailViewModel;
import com.zlm.project.ui.users.UsersViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Milla
 * @create 2019/3/29
 */

@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    // -------------------------------------------
    private final DataManager dataManager;

    // -------------------------------------------
    @Inject
    public ViewModelProviderFactory(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    // -------------------------------------------
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(UsersViewModel.class)) {
            return (T) new UsersViewModel(dataManager);
        } else if (modelClass.isAssignableFrom(UserDetailViewModel.class)) {
            return (T) new UserDetailViewModel(dataManager);
        }







        throw new IllegalArgumentException("Unknown ViewModel Class: " + modelClass.getName());
    }

    // -------------------------------------------
}
