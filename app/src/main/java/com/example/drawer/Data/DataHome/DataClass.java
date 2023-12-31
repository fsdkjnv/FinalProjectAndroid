package com.example.drawer.Data.DataHome;

public class DataClass {
    private String dataTitle; // Tiêu đề dữ liệu
    private int dataDesc; // Mô tả dữ liệu
    private String dataLang; // Ngôn ngữ dữ liệu
    private int dataImage; // Hình ảnh dữ liệu

    public String getDataTitle() {
        return dataTitle;
    }

    public int getDataDesc() {
        return dataDesc;
    }

    public String getDataLang() {
        return dataLang;
    }

    public int getDataImage() {
        return dataImage;
    }
    public DataClass() {
        // Default constructor, can be empty or initialized with default values
    }
    public DataClass(String dataTitle, int dataDesc, String dataLang, int dataImage) {
        this.dataTitle = dataTitle; // Khởi tạo tiêu đề dữ liệu
        this.dataDesc = dataDesc; // Khởi tạo mô tả dữ liệu
        this.dataLang = dataLang; // Khởi tạo ngôn ngữ dữ liệu
        this.dataImage = dataImage; // Khởi tạo hình ảnh dữ liệu
    }
}
