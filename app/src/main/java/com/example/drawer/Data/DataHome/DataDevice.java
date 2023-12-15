package com.example.drawer.Data.DataHome;

public class DataDevice {
    private String dataTitle; // Tiêu đề dữ liệu
    private String dataDesc; // Mô tả dữ liệu
    private String dataLang; // Ngôn ngữ dữ liệu
    private int dataImage; // Hình ảnh dữ liệu

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public String getDataLang() {
        return dataLang;
    }

    public int getDataImage() {
        return dataImage;
    }

    public DataDevice(String dataTitle, String dataDesc, String dataLang, int dataImage) {
        this.dataTitle = dataTitle; // Khởi tạo tiêu đề dữ liệu
        this.dataDesc = dataDesc; // Khởi tạo mô tả dữ liệu
        this.dataLang = dataLang; // Khởi tạo ngôn ngữ dữ liệu
        this.dataImage = dataImage; // Khởi tạo hình ảnh dữ liệu
    }
}
