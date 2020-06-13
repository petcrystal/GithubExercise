package com.zlm.project.ui.users;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.zlm.project.R;
import com.zlm.project.data.model.api.UsersResponse;
import com.zlm.project.databinding.ActivityUsersBinding;
import com.zlm.project.other.AppConstants;
import com.zlm.project.other.ViewModelProviderFactory;
import com.zlm.project.other.base.BaseActivity;
import com.zlm.project.ui.adapter.UsersAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UsersActivity extends BaseActivity implements UsersNavigator {

    // -------------------------------------------
    @Inject
    ViewModelProviderFactory factory;

    @Inject
    UsersAdapter adapter;

    private ActivityUsersBinding binding;
    private UsersViewModel viewModel;
    private List<UsersResponse> userList;

    // -------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, factory).get(UsersViewModel.class);
        viewModel.setNavigator(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_users);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        init();
    }

    // -------------------------------------------
    private void init() {

        LinearLayoutManager manager = new LinearLayoutManager(this);

        binding.listView.setLayoutManager(manager);
        binding.listView.setAdapter(adapter);
        adapter.setOnItemClickListener(position -> {
            Intent intent = new Intent();
            intent.setClass(UsersActivity.this, UserDetailActivity.class);
            intent.putExtra(AppConstants.USER_DATA, userList.get(position));
            startActivity(intent);
        });
        viewModel.getUsers();
    }

    @Override
    public void refreshAdapter(List<UsersResponse> users) {
        if(userList == null) {
            userList = new ArrayList<>();
        } else {
            userList.clear();
        }
        userList.addAll(users);
        if(adapter != null){
            adapter.setUserList(users);
        }
    }

    // -------------------------------------------
    @Override
    public void back() {
        finish();
    }

    // -------------------------------------------
}
