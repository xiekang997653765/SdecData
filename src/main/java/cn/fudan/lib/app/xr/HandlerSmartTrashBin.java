package cn.fudan.lib.app.xr;

import cn.fudan.lib.dto.DataItem;
import cn.fudan.lib.stream.core.SimpleStreamDataHandler;
import cn.fudan.lib.stream.database.InsertData;
import cn.fudan.lib.stream.database.InsertParameter;
import cn.fudan.lib.stream.database.QueryData;
import cn.fudan.lib.stream.database.QueryParameter;
import cn.fudan.lib.stream.utils.DataTypeConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandlerSmartTrashBin extends SimpleStreamDataHandler {
    @Override
    public void handle (DataItem in, String dataType) {
        //in 表示传入的数据，一次传入一条
        //dataType  为当前的数据是哪张表的，
        // 当你一次订阅多张表数据的时候， 通过 dataType 来区分数据是来自哪张表

        //实例： 输入订阅的实时数据
        //实际中需要在这里来做你们预测
        System.out.println("[" + dataType + "]:" + JSONObject.toJSONString(in));

        HashMap<String, String> trashBin = new HashMap<>();
        JSONArray res = JSONObject.parseArray(in.getData());
        for (int i = 0; i < res.size(); i++) {
            String name = res.getJSONObject(i).getString("name");
            String value = res.getJSONObject(i).getJSONArray("values").getString(0);
            trashBin.put(name, value);
        }

        System.out.println("distance is " + trashBin.get("garbage"));
        System.out.println("battery is " + trashBin.get("battery"));
        System.out.println("signal is " + trashBin.get("signal"));

        String exceptionData = "exceptionData";
        String alarmLevel;
        String exceptionInfo = "exceptionInfo";
        int dataStatus = 0;
        String exceptionHandler = "exceptionHandler";
        Date handlingTime = new Date(2020-1900, 6-1, 12);
        String handlingRemark = "handlingRemark";
        String appCode = "xr_1";

        if(Integer.parseInt(trashBin.get("garbage")) <= 5 ) alarmLevel = "Overflowed";
        else if (Integer.parseInt(trashBin.get("garbage")) <= 20 ) alarmLevel = "Overflowing";
        else  alarmLevel = "Normal";

        /**
         * 查询设备位置
         * */
        QueryParameter parameter = new QueryParameter();
        parameter.setTableName(DataTypeConstants.tDeviceInfo); //表名
        parameter.setDeviceId(in.getDeviceId());  //根据设备ID查找

        List<DeviceInfoDataItem> queryResult = QueryData.INSTANCE.queryDeviceInfo(parameter);
        System.out.println("查询出" + queryResult.size() + "条位置信息");

        InsertParameter insertParameter = new InsertParameter();

        insertParameter.setTableName(DataTypeConstants.tExceptionData);
        insertParameter.setId(in.getId());
        insertParameter.setDeviceCode(in.getDeviceCode());
        insertParameter.setDeviceId(in.getDeviceId());
        insertParameter.setExceptionDateTime(in.getUpTimestamp());
        insertParameter.setExceptionData(exceptionData);
        insertParameter.setAlarmLevel(alarmLevel);
        insertParameter.setExceptionInfo(exceptionInfo);
        insertParameter.setDataStatus(dataStatus);
        insertParameter. setExceptionHandler(exceptionHandler);
        insertParameter.setHandlingTime(handlingTime);
        insertParameter.setHandlingRemark(handlingRemark);
        insertParameter.setAppCode(appCode);

        System.out.println(JSON.toJSONString(insertParameter));
        InsertData.INSTANCE.insertException(insertParameter);


    }

}
