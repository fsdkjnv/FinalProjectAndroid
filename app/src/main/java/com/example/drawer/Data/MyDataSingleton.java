package com.example.drawer.Data;
public class MyDataSingleton {
    private static MyDataSingleton instance;
    private String userEmail;

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
}

