package cn.fudan.lib.dto;

import java.util.Date;

public class DataItem implements Comparable<DataItem> {
    private Long   id;
    private String deviceId;
    private String deviceCode;
    private String provider;
    private Date   upTimestamp;
    private String data;
    private int    h;
    private int    d;
    private int    m;
    private int    y;

    public String getDeviceId () {
        return deviceId;
    }

    public void setDeviceId (String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceCode () {
        return deviceCode;
    }

    public void setDeviceCode (String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getProvider () {
        return provider;
    }

    public void setProvider (String provider) {
        this.provider = provider;
    }

    public Date getUpTimestamp () {
        return upTimestamp;
    }

    public void setUpTimestamp (Date upTimestamp) {
        this.upTimestamp = upTimestamp;
    }

    public String getData () {
        return data;
    }

    public void setData (String data) {
        this.data = data;
    }

    public int getH () {
        return h;
    }

    public void setH (int h) {
        this.h = h;
    }

    public int getD () {
        return d;
    }

    public void setD (int d) {
        this.d = d;
    }

    public int getM () {
        return m;
    }

    public void setM (int m) {
        this.m = m;
    }

    public int getY () {
        return y;
    }

    public void setY (int y) {
        this.y = y;
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    @Override
    public int compareTo (DataItem o) {
        return (int) (o.getUpTimestamp().getTime() - this.upTimestamp.getTime());
    }

}
