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
    private static final String KEY_RECYCLER_VIEW_DATA = "recyclerViewData";

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public Database(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    // Đọc dữ liệu từ SharedPreferences cho DataClass
    public List<DataClass> getRecyclerViewData() {
        String json = sharedPreferences.getString(KEY_RECYCLER_VIEW_DATA, null);
        Type type = new TypeToken<List<DataClass>>() {}.getType();
        return gson.fromJson(json, type);
    }

    // Ghi dữ liệu vào SharedPreferences cho DataClass
    public void saveRecyclerViewData(List<DataClass> dataList) {
        String json = gson.toJson(dataList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_RECYCLER_VIEW_DATA, json);
        editor.apply();
    }

    // Đọc dữ liệu từ SharedPreferences cho DataDevice
    // Đọc dữ liệu từ SharedPreferences cho DataDevice
    public List<DataDevice> getRecyclerViewDataDevice(String key) {
        String json = sharedPreferences.getString(key, null);
        Type type = new TypeToken<List<DataDevice>>() {}.getType();
        return gson.fromJson(json, type);
    }

    // Ghi dữ liệu vào SharedPreferences cho DataDevice
    public void saveRecyclerViewDataDevice(String key, List<DataDevice> dataList) {
        String json = gson.toJson(dataList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, json);
        editor.apply();
    }
    public void deleteRecyclerViewDataDevice(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }
}
