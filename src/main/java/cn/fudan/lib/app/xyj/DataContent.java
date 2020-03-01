package cn.fudan.lib.app.xyj;


import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataContent {

    private String name;
    private List<String> values;

    public DataContent(String name, List<String> values) {
        this.name = name;
        this.values = values;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public static Map<String, List<String>> getDataContentList(String data){
        List<DataContent> jsonArray;
        Map<String, List<String>> map = new HashMap<>();
        jsonArray = JSONObject.parseArray(data,DataContent.class);
        for (DataContent jsonObject : jsonArray){
            String name = jsonObject.getName();
            List<String> values= jsonObject.getValues();
            map.put(name,values);
        }
        return map;
    }
}
