package com.dantann.sharedelement.framework;

import android.view.ViewGroup;

public interface ViewHolderPresenter<M> {

    BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    void onBindViewHolder(BaseViewHolder holder, M model);

    void onUnbindViewHolder(BaseViewHolder holder);

}
