package cn.fudan.lib.app.xr;

import java.util.Date;

public class DeviceInfoDataItem {
    private Long   id;
    private String deviceCode;
    private String deviceNo;
    private String deveUI;
    private String locationAddress;
    private double lat;
    private double lng;
    private int    deviceHeight;
    private String deviceName;
    private String province;
    private String city;
    private String area;
    private String detailAddress;
    private String area20kmOther;
    private String area20km;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeveUI() {
        return deveUI;
    }

    public void setDeveUI(String deveUI) {
        this.deveUI = deveUI;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getDeviceHeight() {
        return deviceHeight;
    }

    public void setDeviceHeight(int deviceHeight) {
        this.deviceHeight = deviceHeight;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getArea20kmOther() {
        return area20kmOther;
    }

    public void setArea20kmOther(String area20kmOther) {
        this.area20kmOther = area20kmOther;
    }

    public String getArea20km() {
        return area20km;
    }

    public void setArea20km(String area20km) {
        this.area20km = area20km;
    }

}
