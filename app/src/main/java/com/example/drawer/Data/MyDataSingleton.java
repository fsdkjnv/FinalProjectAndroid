package com.example.drawer.Data;

public class MyDataSingleton {
    private static MyDataSingleton instance;
    private String userEmail;
    private String userName; // Thêm biến để lưu trữ name

    private MyDataSingleton() {}

    public static synchronized MyDataSingleton getInstance() {
        if (instance == null) {
            instance = new MyDataSingleton();
        }
        return instance;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
