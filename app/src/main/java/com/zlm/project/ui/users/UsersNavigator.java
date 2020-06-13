package com.zlm.project.ui.users;


import com.zlm.project.data.model.api.UsersResponse;

import java.util.List;

/**
 * @author Milla
 * @create 2020/6/9
 */
public interface UsersNavigator {

    void refreshAdapter(List<UsersResponse> users);
    void back();
}
