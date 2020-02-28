package cn.fudan.lib.app.xyj;

import cn.fudan.lib.dto.DataItem;
import cn.fudan.lib.stream.core.SimpleStreamDataHandler;
import com.alibaba.fastjson.JSONObject;


public class MedicalExaminSystemHandler extends SimpleStreamDataHandler{

    @Override
    public void handle(DataItem in, String dataType) {
//        System.out.println("[" + dataType + "]");
//
//        System.out.println("Id: " + in.getId());
//        System.out.println("DeviceId: " + in.getDeviceId());
//        System.out.println("DeviceCode: " + in.getDeviceCode());
//        System.out.println("upTimestamp: " + in.getUpTimestamp());
//        System.out.println("Data: " + in.getData());

        System.out.println("[" + dataType + "]:" + JSONObject.toJSONString(in));
    }
}
