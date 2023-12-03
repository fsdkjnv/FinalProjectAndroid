package com.example.drawer.fragment;

import androidx.lifecycle.ViewModel;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import com.example.drawer.DataClass;


import com.example.drawer.R;

public class SharedViewModel extends ViewModel {

    private int numberOfRooms;
    private static SharedViewModel instance;
    private List<DataClass> dataList = new ArrayList<>();
        public List<DataClass> getDataList() {
        if (dataList.isEmpty()) {
            // Initialize with default data if the list is empty
            dataList.add(new DataClass("Phòng Khách", R.string.camera, "", R.drawable.phongkhach));
            dataList.add(new DataClass("Phòng học", R.string.recyclerview, "", R.drawable.phonghoc));
            dataList.add(new DataClass("Phòng Ngủ", R.string.edit, "", R.drawable.phongngu));
        }
        return dataList;
    }

    public void addRoom(DataClass room) {
        dataList.add(room);

    }
    public static SharedViewModel getInstance() {
        if (instance == null) {
            instance = new SharedViewModel();
        }
        return instance;
    }

    public void setDataList(List<DataClass> dataList) {
            this.dataList = dataList;

        }

}
