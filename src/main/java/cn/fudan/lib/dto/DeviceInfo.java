package cn.fudan.lib.dto;

import java.math.BigDecimal;

public class DeviceInfo {
    private Long       id;
    private String     deviceCode;
    private String     deviceNo;
    private String     deveui;
    private String     locationAddress;
    private BigDecimal lat;
    private BigDecimal lng;
    private String     deviceHeight;
    private String     deviceName;
    private String     province;
    private String     city;
    private String     area;
    private String     detailAddress;
    private String     area20KmOther;
    private String     area20Km;

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getDeviceCode () {
        return deviceCode;
    }

    public void setDeviceCode (String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceNo () {
        return deviceNo;
    }

    public void setDeviceNo (String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeveui () {
        return deveui;
    }

    public void setDeveui (String deveui) {
        this.deveui = deveui;
    }

    public String getLocationAddress () {
        return locationAddress;
    }

    public void setLocationAddress (String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public BigDecimal getLat () {
        return lat;
    }

    public void setLat (BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng () {
        return lng;
    }

    public void setLng (BigDecimal lng) {
        this.lng = lng;
    }

    public String getDeviceHeight () {
        return deviceHeight;
    }

    public void setDeviceHeight (String deviceHeight) {
        this.deviceHeight = deviceHeight;
    }

    public String getDeviceName () {
        return deviceName;
    }

    public void setDeviceName (String deviceName) {
        this.deviceName = deviceName;
    }

    public String getProvince () {
        return province;
    }

    public void setProvince (String province) {
        this.province = province;
    }

    public String getCity () {
        return city;
    }

    public void setCity (String city) {
        this.city = city;
    }

    public String getArea () {
        return area;
    }

    public void setArea (String area) {
        this.area = area;
    }

    public String getDetailAddress () {
        return detailAddress;
    }

    public void setDetailAddress (String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getArea20KmOther () {
        return area20KmOther;
    }

    public void setArea20KmOther (String area20KmOther) {
        this.area20KmOther = area20KmOther;
    }

    public String getArea20Km () {
        return area20Km;
    }

    public void setArea20Km (String area20Km) {
        this.area20Km = area20Km;
    }

}
