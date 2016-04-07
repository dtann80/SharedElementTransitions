package com.dantann.recylerviewtemplate.framework;


import android.support.annotation.CallSuper;
import android.view.View;

public abstract class ThrottledOnClickListener implements View.OnClickListener {

    private static final long CLICKABLE_INTERVAL = 500L;

    @CallSuper
    @Override
    public void onClick(final View v) {
        v.setEnabled(false);
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                v.setEnabled(true);
            }
        },CLICKABLE_INTERVAL);
    }
}
