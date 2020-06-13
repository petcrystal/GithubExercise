package com.zlm.project.other.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Milla
 * @create 2019/4/8
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBind(int position);

}
