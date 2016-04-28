package com.dantann.sharedelement.framework;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

/**
 * Class encapsulates setting/getting values in shared preferences
 */
public class StringPreference {
  private final SharedPreferences mPreference;
  private final String mKey;
  private final String mDefaultValue;

  public StringPreference(SharedPreferences preferences, String key) {
    this(preferences, key, null);
  }

  public StringPreference(SharedPreferences preferences, String key, String defaultValue) {
    mPreference = preferences;
    mKey = key;
    mDefaultValue = defaultValue;
  }

  public String get() {
    return mPreference.getString(mKey, mDefaultValue);
  }

  public boolean isSet() {
    return mPreference.contains(mKey);
  }

  @SuppressLint("CommitPrefEdits")
  public void set(String value) {
    mPreference.edit().putString(mKey, value).commit();
  }

  @SuppressLint("CommitPrefEdits")
  public void delete() {
    mPreference.edit().remove(mKey).commit();
  }
}
