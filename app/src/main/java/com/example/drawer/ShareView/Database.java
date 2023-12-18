package com.example.drawer.ShareView;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.drawer.Data.DataHome.DataClass;
import com.example.drawer.Data.DataHome.DataDevice;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String SHARED_PREF_NAME = "RecyclerViewData";
    private static final String KEY_RECYCLER_VIEW_DATA_PREFIX = "recyclerViewData_";  // Use a prefix for user-specific keys

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public Database(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    private String getUserKey(String username) {
        // Append the username to the prefix to create a unique key for each user
        return KEY_RECYCLER_VIEW_DATA_PREFIX + username;
    }

    // Đọc dữ liệu từ SharedPreferences cho DataClass
    public List<DataClass> getRecyclerViewData(String username) {
        String key = getUserKey(username);
        String json = sharedPreferences.getString(key, null);
        Type type = new TypeToken<List<DataClass>>() {}.getType();
        return gson.fromJson(json, type);
    }

    // Ghi dữ liệu vào SharedPreferences cho DataClass
    public void saveRecyclerViewData(String username, List<DataClass> dataList) {
        String key = getUserKey(username);
        String json = gson.toJson(dataList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, json);
        editor.apply();
    }

    // Đọc dữ liệu từ SharedPreferences cho DataDevice
    public List<DataDevice> getRecyclerViewDataDevice(String username, String key) {
        String userKey = getUserKey(username) + "_" + key;
        String json = sharedPreferences.getString(userKey, null);
        Type type = new TypeToken<List<DataDevice>>() {}.getType();
        return gson.fromJson(json, type);
    }

    // Ghi dữ liệu vào SharedPreferences cho DataDevice
    public void saveRecyclerViewDataDevice(String username, String key, List<DataDevice> dataList) {
        String userKey = getUserKey(username) + "_" + key;
        String json = gson.toJson(dataList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(userKey, json);
        editor.apply();
    }

    // Xóa dữ liệu từ SharedPreferences cho DataDevice

}
