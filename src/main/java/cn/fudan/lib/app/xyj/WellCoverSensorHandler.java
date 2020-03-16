package cn.fudan.lib.app.xyj;

import cn.fudan.lib.dto.DataItem;
import cn.fudan.lib.stream.core.SimpleStreamDataHandler;
import cn.fudan.lib.stream.database.QueryData;
import cn.fudan.lib.stream.database.QueryParameter;
import cn.fudan.lib.stream.utils.DataTypeConstants;
import com.alibaba.fastjson.JSONObject;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.*;

public class WellCoverSensorHandler extends SimpleStreamDataHandler {

    @Override
    public void handle(DataItem in, String dataType) {
        String id = in.getDeviceId();
        Date upTimestamp = in.getUpTimestamp();
        String wrongInfo = null;
        Map<String, List<String>> map = DataContent.getDataContentList(in.getData());
        JSONObject returnInfo = new JSONObject();
        JSONObject wrongDataJson = new JSONObject();
        if (Boolean.parseBoolean(map.get("door_status").get(0))) {
            if (Boolean.parseBoolean(map.get("water_level_status").get(0))) {
                wrongDataJson.put("water_level_status", true);
                wrongInfo = "严重";
            } else {
                return;
            }
        }
        else {
            Calendar ca = Calendar.getInstance();
            ca.setTime(upTimestamp);
            ca.add(Calendar.HOUR_OF_DAY, -2);
            QueryParameter parameter = new QueryParameter();
            parameter.setTableName(DataTypeConstants.WellCoverSensor); //表名
            parameter.setBeginDate(ca.getTime()); //设置开始时间
            parameter.setEndDate(in.getUpTimestamp()); //设置结束时间
            parameter.setDeviceId(id);
            List<DataItem> queryResultOne = QueryData.INSTANCE.query(parameter);
            int p = queryResultOne.size() - 1;
            long diffMinute = 0;
            for (; p >= 0; p--) {
                DataItem oneResult = queryResultOne.get(p);
                long diff = upTimestamp.getTime() - oneResult.getUpTimestamp().getTime();
                diffMinute = diff / 60000;
                Map<String, List<String>> newMap;
                newMap = DataContent.getDataContentList(oneResult.getData());
                if (!Boolean.parseBoolean(newMap.get("door_status").get(0))) {
                    continue;
                }
                if (diffMinute >= 10 && diffMinute < 30) {
                    wrongInfo = "普通";
                } else if (diffMinute < 60) {
                    wrongInfo = "警告";
                } else {
                    wrongInfo = "严重";
                }
            }
            if (diffMinute >= 60) {
                wrongInfo = "严重";
            }
            wrongDataJson.put("time", diffMinute);
        }

        ExceptionParameter returnParameter = new ExceptionParameter();
        returnParameter.setDeviceCode(DataTypeConstants.WellCoverSensor);
        returnParameter.setDeviceId(in.getId());
        returnParameter.setExceptionDateTime(in.getUpTimestamp());
        returnParameter.setExceptionData(wrongDataJson);
        returnParameter.setAlarmLevel(wrongInfo);
        returnParameter.setAppCode("xyj_2");

        QueryData.INSTANCE.postExceptionData(returnParameter);
//
//        returnInfo.put("device_code", DataTypeConstants.WellCoverSensor);
//        returnInfo.put("device_id", in.getDeviceId());
//        returnInfo.put("exception_date_time", String.valueOf(in.getUpTimestamp()));
//        returnInfo.put("exception_data", wrongDataJson);
//        returnInfo.put("alarm_level", wrongInfo);
//        returnInfo.put("app_code", "xyj_2");
//        System.out.println("returnValue: " + returnInfo);
    }
}
