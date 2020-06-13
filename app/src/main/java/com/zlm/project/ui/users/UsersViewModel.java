package com.zlm.project.ui.users;

import androidx.lifecycle.MutableLiveData;

import com.zlm.project.data.DataManager;
import com.zlm.project.data.model.api.UsersResponse;
import com.zlm.project.other.base.BaseViewModel;

import java.util.Arrays;
import java.util.List;

/**
 * @author Milla
 * @create 2020/6/09
 */
public class UsersViewModel extends BaseViewModel<UsersNavigator> {

    public final MutableLiveData<List<UsersResponse>> users = new MutableLiveData<>();

    // -------------------------------------------
    public UsersViewModel(DataManager dataManager) {
        super(dataManager);
    }

    // -------------------------------------------
    public void setUserList(List<UsersResponse> userlist) {
        this.users.postValue(userlist);
        getNavigator().refreshAdapter(userlist);
    }

    // -------------------------------------------
    public void back() {
        getNavigator().back();
    }


    // -------------------------------------------

    // -------------------------------------------
    public void getUsers() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getApi().loadUser()
                .compose(getSchedulerProvider())
                .subscribe(response -> {
                    if(response !=null &&response.length > 0){
                        List<UsersResponse> userList = Arrays.asList(response);
                        setUserList(userList);
                    }

                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    throwable.printStackTrace();
                }));
    }
}
