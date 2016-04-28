package com.dantann.sharedelement;

import com.dantann.sharedelement.framework.BaseApplication;
import com.dantann.sharedelement.framework.DebugTree;

import timber.log.Timber;


public class MyApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new DebugTree());
    }
}
