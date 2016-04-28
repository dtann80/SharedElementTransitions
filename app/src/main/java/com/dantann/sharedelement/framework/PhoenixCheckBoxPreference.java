package com.dantann.sharedelement.framework;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.preference.CheckBoxPreference;
import android.util.AttributeSet;

import com.jakewharton.processphoenix.ProcessPhoenix;

/**
 * {@link CheckBoxPreference} that when checked/unchecked will restart the app.
 */
public class PhoenixCheckBoxPreference extends CheckBoxPreference {

    public PhoenixCheckBoxPreference(Context context) {
        super(context);
    }

    public PhoenixCheckBoxPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PhoenixCheckBoxPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PhoenixCheckBoxPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected boolean persistBoolean(boolean value) {
        if (shouldPersist()) {
            if (value == getPersistedBoolean(!value)) {
                // It's already there, so the same as persisting
                return true;
            }

            SharedPreferences.Editor editor = getEditor();
            editor.putBoolean(getKey(), value).commit();
            return true;
        }
        return false;
    }

    @Override
    protected void onClick() {
        super.onClick();
        performAppRestart();
    }

    protected void performAppRestart() {
        setEnabled(false);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                ProcessPhoenix.triggerRebirth(getContext());
            }
        });
    }

}
