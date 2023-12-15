package com.example.drawer.ShareView;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.example.drawer.Data.DataHome.DataClass;

public class Database {
    private static final String SHARED_PREF_NAME = "RecyclerViewData";
    private static final String KEY_RECYCLER_VIEW_DATA = "recyclerViewData";

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public Database(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    // Đọc dữ liệu từ SharedPreferences
    public List<DataClass> getRecyclerViewData() {
        String json = sharedPreferences.getString(KEY_RECYCLER_VIEW_DATA, null);
        Type type = new TypeToken<List<DataClass>>() {}.getType();
        return gson.fromJson(json, type);
    }

    // Ghi dữ liệu vào SharedPreferences
    public void saveRecyclerViewData(List<DataClass> dataList) {
        String json = gson.toJson(dataList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_RECYCLER_VIEW_DATA, json);
        editor.apply();
    }
    public void deleteAndSaveData(List<DataClass> dataList, int position) {
    dataList.remove(position);
    saveRecyclerViewData(dataList);
    }

}
