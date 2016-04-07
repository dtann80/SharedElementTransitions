package com.dantann.recylerviewtemplate.framework;

import android.support.v7.widget.RecyclerView;
import android.view.View;


public class BaseViewHolder<M> extends RecyclerView.ViewHolder {

    private M mModel;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void setModel(M model) {
        mModel = model;
    }

    public M getModel() {
        return mModel;
    }
}
