package cn.fudan.lib.stream.database;

import java.util.Date;
import java.util.List;

public class QueryParameter {
    private String     tableName;
    private Date       beginDate;
    private Date       endDate;
    private List<Long> excludeIds;
    private String     deviceId;

    public String getTableName () {
        return tableName;
    }

    public void setTableName (String tableName) {
        this.tableName = tableName;
    }

    public Date getBeginDate () {
        return beginDate;
    }

    public void setBeginDate (Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate () {
        return endDate;
    }

    public void setEndDate (Date endDate) {
        this.endDate = endDate;
    }

    public List<Long> getExcludeIds () {
        return excludeIds;
    }

    public void setExcludeIds (List<Long> excludeIds) {
        this.excludeIds = excludeIds;
    }

    public String getDeviceId () {
        return deviceId;
    }

    public void setDeviceId (String deviceId) {
        this.deviceId = deviceId;
    }

}
