package com.zlm.project.ui.users;

import androidx.lifecycle.MutableLiveData;

import com.zlm.project.data.DataManager;
import com.zlm.project.data.model.api.UserDetailResponse;
import com.zlm.project.data.model.api.UsersResponse;
import com.zlm.project.other.base.BaseViewModel;

import java.util.Arrays;
import java.util.List;

/**
 * @author Milla
 * @create 2020/6/09
 */
public class UserDetailViewModel extends BaseViewModel<UserDetailNavigator> {

    private UsersResponse user;
    public final MutableLiveData<String> imgAvatorURL = new MutableLiveData<>();
    public final MutableLiveData<String> name = new MutableLiveData<>();
    public final MutableLiveData<String> login = new MutableLiveData<>();
    public final MutableLiveData<String> bio = new MutableLiveData<>();
    public final MutableLiveData<String> location = new MutableLiveData<>();
    public final MutableLiveData<String> link = new MutableLiveData<>();

    // -------------------------------------------
    public UserDetailViewModel(DataManager dataManager) {
        super(dataManager);
    }



    // -------------------------------------------
    public void back() {
        getNavigator().back();
    }


    // -------------------------------------------
    public void getUsers(UsersResponse user) {
        this.user = user;
        this.login.postValue(user.getLogin());
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getApi().loadUserInfo(user.getLogin())
                .compose(getSchedulerProvider())
                .subscribe(response -> {
                    if(response !=null && response instanceof UserDetailResponse){
                        setUserData(response);
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    throwable.printStackTrace();
                }));
    }

    private void setUserData(UserDetailResponse data){
        this.bio.postValue(data.getBio());
        this.imgAvatorURL.postValue(data.getAvatar_url());
        this.name.postValue(data.getName());
        this.location.postValue(data.getLocation());
        this.link.postValue(data.getHtml_url());
    }
}
