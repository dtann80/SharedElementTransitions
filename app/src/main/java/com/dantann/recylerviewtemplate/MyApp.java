package com.dantann.recylerviewtemplate;

import com.dantann.recylerviewtemplate.framework.BaseApplication;
import com.dantann.recylerviewtemplate.framework.DebugTree;

import timber.log.Timber;


public class MyApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new DebugTree());
    }
}
