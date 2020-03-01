package cn.fudan.lib.app.xyj;


import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public class ExceptionParameter {
    private String deviceCode;
    private long  deviceId;
    private Date exceptionDateTime;
    private JSONObject exceptionData;
    private String alarmLevel;
    private String exceptionInfo;
    private int dataStatus;
    private String exceptionHandler;
    private Date handlingTime;
    private String handlingRemark;
    private String appCode;

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public Date getExceptionDateTime() {
        return exceptionDateTime;
    }

    public void setExceptionDateTime(Date exceptionDateTime) {
        this.exceptionDateTime = exceptionDateTime;
    }

    public JSONObject getExceptionData() {
        return exceptionData;
    }

    public void setExceptionData(JSONObject exceptionData) {
        this.exceptionData = exceptionData;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public int getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getExceptionHandler() {
        return exceptionHandler;
    }

    public void setExceptionHandler(String exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    public Date getHandlingTime() {
        return handlingTime;
    }

    public void setHandlingTime(Date handlingTime) {
        this.handlingTime = handlingTime;
    }

    public String getHandlingRemark() {
        return handlingRemark;
    }

    public void setHandlingRemark(String handlingRemark) {
        this.handlingRemark = handlingRemark;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}
