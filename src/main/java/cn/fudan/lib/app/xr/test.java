package cn.fudan.lib.app.xr;


import cn.fudan.lib.stream.database.InsertData;
import cn.fudan.lib.stream.database.InsertParameter;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

public class test {
    public static void main (String[] args) {
        String test = "[{\"name\":\"garbage\",\"values\":[\"81\"]},{\"name\":\"battery\",\"values\":[\"96\"]},{\"name\":\"signal\",\"values\":[\"25\"]}]";
        JSONArray res = JSONObject.parseArray(test);
        System.out.println(res.get(0).toString());
        System.out.println(res.get(1).toString());
        System.out.println(res.get(2).toString());

        String name = res.getJSONObject(0).getString("name");
        String value = res.getJSONObject(0).getJSONArray("values").getString(0);
        System.out.println(name + " " + value);

        InsertParameter parameter = new InsertParameter();
        parameter.setAppCode("许睿测试");
        parameter.setTableName("t_exception_data");
        InsertData.INSTANCE.insertException(parameter);
    }

}
