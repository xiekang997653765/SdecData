package cn.fudan.lib.app.xyj;

import cn.fudan.lib.dto.DataItem;
import cn.fudan.lib.stream.core.SimpleStreamDataHandler;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class MedicalExaminSystemHandler extends SimpleStreamDataHandler{

    ArrayList<String> projName = new ArrayList<>();
    String[] proj = {"hwBmi", "heightPressure", "lowPressure", "pulse", "oxygenSaturation",
            "templature", "fatRate", "waterRate", "basalMetabolism", "pulseRate"};

    @Override
    public void handle(DataItem in, String dataType) {
        Collections.addAll(projName, proj);
        String data = in.getData().toString();
        JSONArray jsonArray = JSONArray.parseArray(data);
        Map<String, String> map = new HashMap<>();
        Map<String, Float> wrongData = new HashMap<>();
        JSONObject returnInfo = new JSONObject();
        String wrongInfo;
        int wrongNum = 0;
        for(int j = 0; j < jsonArray.size(); j++) {
            JSONObject jsonObject = jsonArray.getJSONObject(j);
            String name = jsonObject.getString("name");
            String values = jsonObject.getString("values");
            map.put(name, values);
        }
        for (String s : projName) {
            String sState = s + "State";
            if(map.get(s) == null || map.get(sState) == null){
                continue;
            }

            String temps = map.get(s);
            String tempState = map.get(sState);
            float tempv = Float.parseFloat((temps.substring(2, temps.length() - 2)));
            float tempStatev = Float.parseFloat(tempState.substring(2, tempState.length() - 2));

            if (temps == null || tempStatev == 0){
                continue;
            }
            if(tempStatev != 3){
                wrongNum++;
                wrongData.put(s,tempv);
                wrongData.put(sState,tempStatev);
            }
        }
        if(wrongNum < 3){
            wrongInfo = "普通";
        }
        else if(wrongNum < 7){
            wrongInfo = "警告";
        }
        else{
            wrongInfo = "严重";
        }
        String wrongDataJson = JSON.toJSONString(wrongData);
        returnInfo.put("device_code", in.getDeviceCode());
        returnInfo.put("device_id", in.getDeviceId());
        returnInfo.put("exception_date_time", String.valueOf(in.getUpTimestamp()));
        returnInfo.put("exception_data", wrongDataJson);
        returnInfo.put("alarm_level", wrongInfo);
        System.out.println(returnInfo);
    }
}
