package com.example.drawer.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.drawer.DataDevice;
// ulatroihahaha
//sualan1
public class DataManager {
    private static DataManager instance;
    private Map<String, List<DataDevice>> roomData;

    private DataManager() {
        roomData = new HashMap<>();
        // Initialize with three default devices in each room
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public List<DataDevice> getRoomDevices(String roomName) {
        if (!roomData.containsKey(roomName)) {
            roomData.put(roomName, new ArrayList<>());
        }
        return roomData.get(roomName);
    }

    public void addDeviceToRoom(String roomName, DataDevice device) {
        List<DataDevice> roomDevices = getRoomDevices(roomName);
        roomDevices.add(device);
    }
}
