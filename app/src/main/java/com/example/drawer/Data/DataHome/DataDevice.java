package com.example.drawer.Data.DataHome;

public class DataDevice {
    private String dataTitle; // Tiêu đề dữ liệu
    private String dataDesc; // Mô tả dữ liệu
    private String dataLang; // Ngôn ngữ dữ liệu
    private int dataImage; // Hình ảnh dữ liệu
    private int nhietdo; // Mô tả dữ liệu
    private int doam; // Ngôn ngữ dữ liệu
    private boolean isSwitchOn; // Trạng thái của switch

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

    public int getNhietdo() {
        return nhietdo;
    }

    public int getDoam() {
        return doam;
    }

    public boolean isSwitchOn() {
        return isSwitchOn;
    }

    public void setSwitchOn(boolean switchOn) {
        isSwitchOn = switchOn;
    }

    public DataDevice() {
        // Có thể để trống hoặc thêm các khởi tạo mặc định nếu cần
    }
    public boolean isTemperatureAboveThreshold(int threshold) {
            return nhietdo > threshold;
        }
    public DataDevice(String dataTitle, String dataDesc, String dataLang, int dataImage, int nhietdo, int doam, boolean isSwitchOn) {
        this.dataTitle = dataTitle; // Khởi tạo tiêu đề dữ liệu
        this.dataDesc = dataDesc; // Khởi tạo mô tả dữ liệu
        this.dataLang = dataLang; // Khởi tạo ngôn ngữ dữ liệu
        this.dataImage = dataImage; // Khởi tạo hình ảnh dữ liệu
        this.nhietdo = nhietdo; // Khởi tạo nhiệt độ
        this.doam = doam; // Khởi tạo độ ẩm
        this.isSwitchOn = isSwitchOn; // Khởi tạo trạng thái của switch
    }
}
