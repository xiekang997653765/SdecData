package cn.fudan.lib.app.xk;

import cn.fudan.lib.dto.DataItem;
import cn.fudan.lib.stream.core.SimpleStreamDataHandler;
import com.alibaba.fastjson.JSONObject;

public class TestHandler extends SimpleStreamDataHandler {
    @Override
    public void handle (DataItem in, String dataType) {
        //in 表示传入的数据，一次传入一条
        //dataType  为当前的数据是哪张表的，
        // 当你一次订阅多张表数据的时候， 通过 dataType 来区分数据是来自哪张表

        //实例： 输入订阅的实时数据
        //实际中需要在这里来做你们预测
        System.out.println("[" + dataType + "]:" + JSONObject.toJSONString(in));
    }

}
