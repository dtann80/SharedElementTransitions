package com.dantann.recylerviewtemplate.framework;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Custom {@link RecyclerView.ItemDecoration} to add spacing between items.
 */
public final class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private final int mLeftSpacing;
    private final int mRightSpacing;
    private final int mTopSpacing;
    private final int mBottomSpacing;

    public SpacesItemDecoration(int space) {
        this(space, space, space, space);
    }

    public SpacesItemDecoration( int left, int right, int top, int bottom) {
        mLeftSpacing = left;
        mRightSpacing = right;
        mTopSpacing = top;
        mBottomSpacing = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mLeftSpacing;
        outRect.right = mRightSpacing;
        outRect.top = mTopSpacing;
        outRect.bottom = mBottomSpacing;
    }
}
