package cn.fudan.lib.app.xyj;

import cn.fudan.lib.app.xk.TestHandler;
import cn.fudan.lib.dto.DataItem;
import cn.fudan.lib.stream.core.DataHandler;
import cn.fudan.lib.stream.database.QueryData;
import cn.fudan.lib.stream.database.QueryParameter;
import cn.fudan.lib.stream.pojo.SubscribeItem;
import cn.fudan.lib.stream.read.StreamDataSubscribe;
import cn.fudan.lib.stream.utils.DataTypeConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.*;

public class App {
    public static void main (String[] args) {
        /**
         * setDataType
         *   表示你要订阅的数据表名称
         *
         * setStartDate
         *   指定要从那一天的数据开始读
         *   比如设置2018-05-14， 就表示从2018-05-14 aa:bb:cc开始读
         *   aa:bb:cc 表示你系统当前的 小时:分钟:秒
         **/
        SubscribeItem medicalExaminSystemSub = new SubscribeItem();
        medicalExaminSystemSub.setDataType(DataTypeConstants.MedicalExaminSystem);
        medicalExaminSystemSub.setStartDate("2018-06-15"); //从5月14号的当前时刻开始

        SubscribeItem trashBinSub = new SubscribeItem();
        trashBinSub.setDataType(DataTypeConstants.TrashBin);
        trashBinSub.setStartDate("2018-05-16");//从5月16号的当前时刻开始

        /**
         * 数据处理器
         * 自定义一个类，继承SimpleStreamDataHandler
         * 然后实现handle方法
         *
         * 细节请看 TestHandler
         * */
        DataHandler handler = new MedicalExaminSystemHandler();

        /**
         * 订阅一个流数据，
         *   第一个参数是数据你的handler,
         *   第二个参数是个可变参数， 可以传递一个或多个表名，表示你要订阅哪几张表的数据
         *   示例中订阅了两张表，bedMat和trashBin
         *   注意： 所有的表名，都已经封装在 DataTypeConstants 类中
         *
         * 最后调用 start 方法，开始运行应用
         * */
//        new StreamDataSubscribe(handler, medicalExaminSystemSub).start();

        /**
         * 历史数据查询
         * */
        QueryParameter parameter = new QueryParameter();
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        /****** 必填条件开始 ********/
        parameter.setTableName(DataTypeConstants.MedicalExaminSystem); //表名
        parameter.setBeginDate(dtf.parseDateTime("2018-06-26 06:43:06").toDate()); //设置开始时间
        parameter.setEndDate(dtf.parseDateTime("2018-09-14 00:14:14").toDate()); //设置结束时间
        /****** 必填条件结束 ********/

        /****** 选填条件开始 ********/
//        parameter.setDeviceId("deviceId");  //根据设备ID查找
        //parameter.setExcludeIds(new ArrayList<Long>()); //需要排除的数据ID集合
        /****** 选填条件结束 ********/

        List<DataItem> queryResult = QueryData.INSTANCE.query(parameter);
        System.out.println("查询出" + queryResult.size() + "条数据");

        List<String> queryId = QueryData.INSTANCE.getAllDeviceId(parameter);
        System.out.println("查询出" + queryId.size() + "条数据");
        for(String i:queryId){
            System.out.println(i);
        }


//        String[] projName = {"hwBmi", "heightPressure", "lowPressure", "pulse", "oxygenSaturation",
//                "templature", "fatRate", "waterRate", "basalMetabolism", "pulseRate"};
//        for (int i = 0; i < 1; i++) {
//            DataItem in = queryResult.get(i);
//            String data = in.getData();
//            List<DataContent> jsonArray;
//            jsonArray = JSONObject.parseArray(data, DataContent.class);
//            Map<String, List<String>> map = new HashMap<>();
//            Map<String, Float> wrongData = new HashMap<>();
//            JSONObject returnInfo = new JSONObject();
//            String wrongInfo;
//            int wrongNum = 0;
//            for (DataContent jsonObject : jsonArray) {
//                String name = jsonObject.getName();
//                List<String> values = jsonObject.getValues();
//                map.put(name, values);
//            }
//            for (String s : projName) {
//                String sState = s + "State";
//                if (map.get(s) == null || map.get(sState) == null) {
//                    continue;
//                }
//                List<String> temps = map.get(s);
//                List<String> tempState = map.get(sState);
//                float tempv = Float.parseFloat(temps.get(0));
//                float tempStatev = Float.parseFloat(tempState.get(0));
//
//                if (temps == null || tempStatev == 0) {
//                    continue;
//                }
//                if (tempStatev != 3) {
//                    wrongNum++;
//                    wrongData.put(s, tempv);
//                    wrongData.put(sState, tempStatev);
//                }
//            }
//            if (wrongNum < 3) {
//                wrongInfo = "普通";
//            } else if (wrongNum < 7) {
//                wrongInfo = "警告";
//            } else {
//                wrongInfo = "严重";
//            }
//            String wrongDataJson = JSON.toJSONString(wrongData);
//            returnInfo.put("device_code", in.getDeviceCode());
//            returnInfo.put("device_id", in.getDeviceId());
//            returnInfo.put("exception_date_time", String.valueOf(in.getUpTimestamp()));
//            returnInfo.put("exception_data", wrongDataJson);
//            returnInfo.put("alarm_level", wrongInfo);
//            returnInfo.put("app_code", "xyj_1");
//            System.out.println(returnInfo);
//        }
    }
}
