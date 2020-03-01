package cn.fudan.lib.dto;

public class DeviceInfoQueryParameter {
    private Long   id;
    private String deviceCode;
    private String deviceNo;
    private String deveui;
    private String province;
    private String city;
    private String area;

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

}
