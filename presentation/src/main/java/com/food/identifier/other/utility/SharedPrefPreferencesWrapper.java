package com.food.identifier.other.utility;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import static android.content.Context.MODE_PRIVATE;
import static com.food.identifier.application.FoodIdentifierApplication.TAG;

/**
 * Created by taras on 11/12/2017.
 */
public class SharedPrefPreferencesWrapper {
    private static final String SHARED_PREFERENCES = TAG + ".food.delivery.shared.preferences";
    private static final int DEFAULT_VALUE = -1;
    public static String KEY_TUTORIAL = TAG + ".tutorial";

    public SharedPrefPreferencesWrapper() {
    }

    public void saveToSharedPreferences(Activity activity, String key, String value) {
        SharedPreferences sharedPref = activity.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void saveToSharedPreferences(Activity activity, String key, int value) {
        SharedPreferences sharedPref = activity.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void saveToSharedPreferences(Activity activity, String key, boolean value) {
        SharedPreferences sharedPref = activity.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public String getValue(Activity activity, String key) {
        SharedPreferences sharedPref = activity.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }

    public int getIntValue(Activity activity, String key) {
        SharedPreferences sharedPref = activity.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        return sharedPref.getInt(key, DEFAULT_VALUE);
    }

    public boolean getBooleanValue(Activity activity, String key) {
        SharedPreferences sharedPref = activity.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        return sharedPref.getBoolean(key, false);
    }
}
