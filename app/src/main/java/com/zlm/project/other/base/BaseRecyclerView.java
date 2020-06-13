package com.zlm.project.other.base;

import androidx.recyclerview.widget.RecyclerView;

import com.zlm.project.other.OnItemClickListener;

/**
 * @author Milla
 * @create 2019/4/19
 */
public abstract class BaseRecyclerView extends RecyclerView.Adapter<BaseViewHolder> {

    protected OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
