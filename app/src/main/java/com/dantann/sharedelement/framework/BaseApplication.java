package com.dantann.sharedelement.framework;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;

import timber.log.Timber;

public class BaseApplication extends Application {

    private CopyOnWriteArrayList<ApplicationStateCallbacks> mApplicationStateCallbacks = new CopyOnWriteArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacksInternal());
    }

    /**
     * Registers {@link ApplicationStateCallbacks}
     *
     * @param callback - {@link ApplicationStateCallbacks}
     */
    public void registerActivityLifecycleCallbacks(ApplicationStateCallbacks callback) {
        registerActivityLifecycleCallbacks(callback);
        mApplicationStateCallbacks.add(callback);
    }

    /**
     * Unregisters {@link ApplicationStateCallbacks}
     *
     * @param callback - {@link ApplicationStateCallbacks}
     */
    public void unregisterActivityLifecycleCallbacks(ApplicationStateCallbacks callback) {
        unregisterActivityLifecycleCallbacks(callback);
        mApplicationStateCallbacks.remove(callback);
    }

    private void notifyApplicationEnterForeground() {
        Timber.d("Application is entering foreground.");
        for (ApplicationStateCallbacks callbacks : mApplicationStateCallbacks) {
            callbacks.onApplicationEnterForeground();
        }
    }

    private void notifyApplicationEnterBackground() {
        Timber.d("Application is entering background.");
        for (ApplicationStateCallbacks callbacks : mApplicationStateCallbacks) {
            callbacks.onApplicationEnterBackground();
        }
    }

    private class ActivityLifecycleCallbacksInternal extends AbstractActivityLifecycleCallbacks {

        private WeakReference<Activity> mWeakActivityRef;

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            super.onActivityCreated(activity, savedInstanceState);
            if (mWeakActivityRef == null) {
                notifyApplicationEnterForeground();
            }
            if (mWeakActivityRef == null || mWeakActivityRef.get() != activity) {
                mWeakActivityRef = new WeakReference<>(activity);
            }
        }

        @Override
        public void onActivityStarted(Activity activity) {
            if (mWeakActivityRef == null) {
                notifyApplicationEnterForeground();
            }
            if (mWeakActivityRef == null || mWeakActivityRef.get() != activity) {
                mWeakActivityRef = new WeakReference<>(activity);
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {
            if (mWeakActivityRef != null && mWeakActivityRef.get() != activity) {
                mWeakActivityRef = new WeakReference<>(activity);
            }
        }

        @Override
        public void onActivityStopped(Activity activity) {
            if (mWeakActivityRef != null && mWeakActivityRef.get() == activity
                    && !activity.isChangingConfigurations()) {
                mWeakActivityRef = null;
                notifyApplicationEnterBackground();
            }
        }
    }

    /**
     * {@link android.app.Application.ActivityLifecycleCallbacks} that also has callbacks for the
     * application state.
     */
    public abstract class ApplicationStateCallbacks extends AbstractActivityLifecycleCallbacks {

        /**
         * Called when the application enters the foreground
         */
        public abstract void onApplicationEnterForeground();

        /**
         * Called when the application enters the background
         */
        public abstract void onApplicationEnterBackground();

    }
}
