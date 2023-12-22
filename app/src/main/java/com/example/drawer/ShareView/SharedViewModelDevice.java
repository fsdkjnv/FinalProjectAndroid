package com.example.drawer.ShareView;

import androidx.lifecycle.ViewModel;

import com.example.drawer.Data.DataHome.DataDevice;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModelDevice extends ViewModel {

    private static SharedViewModelDevice instance;
    private List<DataDevice> deviceList = new ArrayList<>();

    // Make the constructor public
    public SharedViewModelDevice() {
        // Private constructor to enforce singleton pattern
    }

    public static SharedViewModelDevice getInstance() {
        if (instance == null) {
            instance = new SharedViewModelDevice();
        }
        return instance;
    }

    public List<DataDevice> getDeviceList() {
        return deviceList;
    }

    public void addDevice(DataDevice device) {
        deviceList.add(device);
    }

    public void setDeviceList(List<DataDevice> deviceList) {
        this.deviceList = deviceList;// Add new data
    }
}
