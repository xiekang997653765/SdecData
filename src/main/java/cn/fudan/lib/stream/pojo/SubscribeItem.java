package cn.fudan.lib.stream.pojo;

public class SubscribeItem {
    private String dataType; //订阅的表名称
    private String startDate; //起始日期点
    private String endDate;//结束日期
    private int    maxSize = -1; //订阅数据量，-1表示不限制

    public String getDataType () {
        return dataType;
    }

    public void setDataType (String dataType) {
        this.dataType = dataType;
    }

    public String getStartDate () {
        return startDate;
    }

    public void setStartDate (String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate () {
        return endDate;
    }

    public void setEndDate (String endDate) {
        this.endDate = endDate;
    }

    public int getMaxSize () {
        return maxSize;
    }

    public void setMaxSize (int maxSize) {
        this.maxSize = maxSize;
    }

}
