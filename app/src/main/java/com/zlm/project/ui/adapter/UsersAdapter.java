package com.zlm.project.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.zlm.project.data.model.api.UsersResponse;
import com.zlm.project.databinding.ListUserItemBinding;
import com.zlm.project.other.base.BaseRecyclerView;
import com.zlm.project.other.base.BaseViewHolder;
import com.zlm.project.ui.users.UserItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Milla
 * @create 2020/6/9
 */
public class UsersAdapter extends BaseRecyclerView {


    private List<UsersResponse> userList;

    // -------------------------------------------
    public UsersAdapter(List<UsersResponse> list) {
        this.userList = list;
    }

    // -------------------------------------------
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ListUserItemBinding binding = ListUserItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            binding.setLifecycleOwner((LifecycleOwner) parent.getContext());
            return new UsersViewHolder(binding);

    }

    // -------------------------------------------
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    // -------------------------------------------
    @Override
    public int getItemCount() {
        return userList!= null?userList.size():0;
    }


    // -------------------------------------------
    public void setUserList(List<UsersResponse> list) {
        if(this.userList == null){
            this.userList = new ArrayList<>();
        } else {
            this.userList.clear();
        }
        this.userList.addAll(list);
        notifyDataSetChanged();
    }

    // -------------------------------------------
    public class UsersViewHolder extends BaseViewHolder {

        private final ListUserItemBinding binding;

        public UsersViewHolder(ListUserItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            UserItemViewModel viewModel = new UserItemViewModel(userList.get(position));
            binding.setViewModel(viewModel);
            binding.getRoot().setOnClickListener((v) -> {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            });
        }
    }

}
