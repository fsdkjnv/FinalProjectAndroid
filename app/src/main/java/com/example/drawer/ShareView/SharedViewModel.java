package com.example.drawer.ShareView;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import com.example.drawer.Data.DataHome.DataClass;


import com.example.drawer.R;

public class SharedViewModel extends ViewModel {

    private int numberOfRooms;
    private static SharedViewModel instance;
    private List<DataClass> dataList = new ArrayList<>();
        public List<DataClass> getDataList() {
        if (dataList.isEmpty()) {
            // Initialize with default data if the list is empty
            dataList.add(new DataClass("Living Rom", R.string.camera, "", R.drawable.phongkhach));
            dataList.add(new DataClass("Bed Room", R.string.recyclerview, "", R.drawable.phonghoc));
            dataList.add(new DataClass("Kitchen Room", R.string.edit, "", R.drawable.phongngu));
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
