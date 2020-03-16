package cn.fudan.lib.app.xyj;

import cn.fudan.lib.dto.DataItem;
import cn.fudan.lib.stream.core.SimpleStreamDataHandler;
import cn.fudan.lib.stream.database.QueryData;
import cn.fudan.lib.stream.utils.DataTypeConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;


public class MedicalExaminSystemHandler extends SimpleStreamDataHandler{

    String[] projName = {"hwBmi", "heightPressure", "lowPressure", "pulse", "oxygenSaturation",
            "templature", "fatRate", "waterRate", "basalMetabolism", "pulseRate"};

    @Override
    public void handle(DataItem in, String dataType) {
        String data = in.getData();
        List<DataContent> jsonArray;
        jsonArray = JSONObject.parseArray(data, DataContent.class);
        Map<String, List<String>> map = new HashMap<>();
        JSONObject wrongData = new JSONObject();
        JSONObject returnInfo = new JSONObject();
        String wrongInfo;
        int wrongNum = 0;

        for (DataContent jsonObject : jsonArray) {
            String name = jsonObject.getName();
            List<String> values = jsonObject.getValues();
            map.put(name, values);
        }
        for (String s : projName) {
            String sState = s + "State";
            if (map.get(s) == null || map.get(sState) == null) {
                continue;
            }
            List<String> temps = map.get(s);
            List<String> tempState = map.get(sState);
            float tempv = Float.parseFloat(temps.get(0));
            float tempStatev = Float.parseFloat(tempState.get(0));

            if (temps == null || tempStatev == 0) {
                continue;
            }
            if (tempStatev != 3) {
                wrongNum++;
                wrongData.put(s, tempv);
                wrongData.put(sState, tempStatev);
            }
        }
        if (wrongNum < 3) {
            wrongInfo = "普通";
        } else if (wrongNum < 7) {
            wrongInfo = "警告";
        } else {
            wrongInfo = "严重";
        }
//        String wrongDataJson = JSON.toJSONString(wrongData);

        ExceptionParameter returnParameter = new ExceptionParameter();
        returnParameter.setDeviceCode(DataTypeConstants.MedicalExaminSystem);
        returnParameter.setDeviceId(in.getId());
        returnParameter.setExceptionDateTime(in.getUpTimestamp());
        returnParameter.setExceptionData(wrongData);
        returnParameter.setAlarmLevel(wrongInfo);
        returnParameter.setAppCode("xyj_1");

        QueryData.INSTANCE.postExceptionData(returnParameter);

//        returnInfo.put("device_code", DataTypeConstants.MedicalExaminSystem);
//        returnInfo.put("device_id", in.getDeviceId());
//        returnInfo.put("exception_date_time", String.valueOf(in.getUpTimestamp()));
//        returnInfo.put("exception_data", wrongData);
//        returnInfo.put("alarm_level", wrongInfo);
//        returnInfo.put("app_code", "xyj_1");
//        System.out.println(returnInfo);
    }
}
