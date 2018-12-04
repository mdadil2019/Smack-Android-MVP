package com.smack.mdadil2019.smack.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferencesHelper implements PreferencesHelper{

    private static final String PREF_KEY_AUTH_TOKEN = "PREF_KEY_AUTH_TOKEN";
    private static final String LOCAL_PREF_KEY = "LOCAL_PREF_KEY";
    private final SharedPreferences mPrefs;

    public AppPreferencesHelper(Context context){
        mPrefs = context.getSharedPreferences(LOCAL_PREF_KEY,Context.MODE_PRIVATE);
    }

    @Override
    public String getAuthToken() {
        return mPrefs.getString(PREF_KEY_AUTH_TOKEN,null);
    }

    @Override
    public void setAuthToken(String authToken) {
        mPrefs.edit().putString(PREF_KEY_AUTH_TOKEN,authToken).apply();
    }
}
