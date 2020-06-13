package com.zlm.project.ui.users;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.zlm.project.R;
import com.zlm.project.data.model.api.UsersResponse;
import com.zlm.project.databinding.ActivityUserDetailBinding;
import com.zlm.project.other.AppConstants;
import com.zlm.project.other.ViewModelProviderFactory;
import com.zlm.project.other.base.BaseActivity;

import javax.inject.Inject;

public class UserDetailActivity extends BaseActivity implements UserDetailNavigator {

    // -------------------------------------------
    @Inject
    ViewModelProviderFactory factory;

    private ActivityUserDetailBinding binding;
    private UserDetailViewModel viewModel;

    // -------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this, factory).get(UserDetailViewModel.class);
        viewModel.setNavigator(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        init();
    }

    // -------------------------------------------
    private void init() {
        if (getIntent().getExtras() != null && getIntent().getParcelableExtra(AppConstants.USER_DATA) != null) {
            UsersResponse user = getIntent().getParcelableExtra(AppConstants.USER_DATA);
            viewModel.getUsers(user);
        }
    }

    // -------------------------------------------
    @Override
    public void back() {
        finish();
    }

    // -------------------------------------------
}
