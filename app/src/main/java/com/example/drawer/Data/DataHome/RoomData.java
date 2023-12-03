package com.example.drawer.Data.DataHome;

import com.example.drawer.Data.DataHome.DataDevice;
import java.util.List;

// RoomData class representing a room and its devices
public class RoomData {
    private String roomName;
    private List<DataDevice> devices;

    public RoomData(String roomName, List<DataDevice> devices) {
        this.roomName = roomName;
        this.devices = devices;
    }

    public String getRoomName() {
        return roomName;
    }

    public List<DataDevice> getDevices() {
        return devices;
    }
}

