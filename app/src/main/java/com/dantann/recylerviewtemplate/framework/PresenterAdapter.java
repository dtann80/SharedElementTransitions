package com.dantann.recylerviewtemplate.framework;


import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import static com.dantann.recylerviewtemplate.util.Preconditions.checkNotNull;


/**
 * An abstract Adapter that uses {@link ViewHolderPresenter} to handle creating and binding its views.
 * Presenters are stored in a map for quick reference.
 */
public abstract class PresenterAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    /**
     * Map of current {@link ViewHolderPresenter} that is mapped by an integer.
     */
    protected ArrayMap<Integer, ViewHolderPresenter> mPresenterMap = new ArrayMap<>();

    /**
     * Adds a Presenter to the Adapter
     * @param viewType viewType the presenter should handle
     * @param presenter - Presenter that handles binding views of given type
     */
    public void addViewTypePresenter(int viewType, ViewHolderPresenter presenter) {
        mPresenterMap.put(viewType, presenter);
    }

    /**
     * Gets a Presenter for the given viewType
     * @param viewType - type
     * @return Presenter if one is present, null otherwise
     */
    @Nullable
    public ViewHolderPresenter getPresenter(int viewType) {
        return mPresenterMap.get(viewType);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderPresenter presenter = getPresenter(viewType);
        checkNotNull(presenter, "Could not find Presenter for ViewType= "
                + viewType + " , Was a presenter added for this ViewType?");

        return presenter.onCreateViewHolder(parent, viewType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        ViewHolderPresenter presenter = getPresenter(holder.getItemViewType());
        checkNotNull(presenter, "Could not find Presenter for ViewType= "
                + holder.getItemViewType() + " , Was a presenter added for this ViewType?");

        presenter.onBindViewHolder(holder, getDataForPosition(position));
    }

    @Override
    public void onViewRecycled(BaseViewHolder holder) {
        ViewHolderPresenter presenter = getPresenter(holder.getItemViewType());
        checkNotNull(presenter, "Could not find Presenter for ViewType= "
                + holder.getItemViewType() + " , Was a presenter added for this ViewType?");

        presenter.onUnbindViewHolder(holder);
        super.onViewRecycled(holder);
    }

    /**
     * Returns the data to bind at adapter position
     * @param position - adapter position
     * @return data model for specified adapter position
     */
    @Nullable
    public abstract Object getDataForPosition(int position);

    @Override
    public abstract int getItemViewType(int position);
}
