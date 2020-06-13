package com.zlm.project.ui.users;

import androidx.lifecycle.MutableLiveData;

import com.zlm.project.data.model.api.UsersResponse;


/**
 * @author Milla
 * @create 2020/6/9
 */
public class UserItemViewModel {

    // -------------------------------------------
    public final MutableLiveData<String> userName = new MutableLiveData<>();
    public final MutableLiveData<String> userAvator = new MutableLiveData<>();

    // -------------------------------------------
    public UserItemViewModel(UsersResponse user) {
        userName.postValue(user.getLogin());
        userAvator.postValue(user.getAvatar_url());
    }

    // -------------------------------------------
}
