package app.engine.android.util;

import android.content.Context;
import android.content.SharedPreferences;

import app.engine.android.AppEngine;

import java.util.HashMap;
import java.util.Iterator;

public class SharedPrefUtils {
    public SharedPrefUtils(){

    }

    //------ Write Shared Preferences
    public void putPref(HashMap<String, String> map, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(AppEngine.getInstance().constants.SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) iterator.next();
            editor.putString(pair.getKey().toString().trim(), pair.getValue().toString().trim());
            iterator.remove();
        }
        editor.apply();
    }

    //--- Read Preferences -----
    public String getPref(String prefKey, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(AppEngine.getInstance().constants.SHARED_PREF, Context.MODE_PRIVATE);
        return preferences.getString(prefKey.trim(), "");
    }

    //--- Clear All Preferences ---
    public void clearAllPref(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(AppEngine.getInstance().constants.SHARED_PREF, Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
    }

    //--- Clear single preferences ---
    public void clearPref(String prefKey, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(AppEngine.getInstance().constants.SHARED_PREF, Context.MODE_PRIVATE);
        preferences.edit().remove(prefKey).apply();
    }

    //------ Write Shared Preferences
    public void putPref(String key, String value, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(AppEngine.getInstance().constants.SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
}//end of SharedPrefUtils class
