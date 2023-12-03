
package com.example.drawer.ShareView;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class MysharePreferences {
    private static final String PREF_NAME = "MyAppPreferences";
    private static final String KEY_LIST = "myListKey";

    // Save a list to SharedPreferences
    public static void saveList(Context context, List<String> myList) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(myList);

        editor.putString(KEY_LIST, json);
        editor.apply();
    }

    // Retrieve a list from SharedPreferences
    public static List<String> getList(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = preferences.getString(KEY_LIST, null);

        Type type = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
