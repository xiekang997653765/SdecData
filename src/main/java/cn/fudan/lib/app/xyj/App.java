package cn.fudan.lib.app.xyj;

import cn.fudan.lib.stream.core.DataHandler;
import cn.fudan.lib.stream.pojo.SubscribeItem;
import cn.fudan.lib.stream.read.StreamDataSubscribe;
import cn.fudan.lib.stream.utils.DataTypeConstants;

public class App {
    public static void main (String[] args) {

        SubscribeItem MedicalExaminSystemSub = new SubscribeItem();
        MedicalExaminSystemSub.setDataType(DataTypeConstants.WellCoverSensor);
        MedicalExaminSystemSub.setStartDate("2018-06-14"); //从6月14号的当前时刻开始

        DataHandler handler = new MedicalExaminSystemHandler();

        new StreamDataSubscribe(handler, MedicalExaminSystemSub).start();

    }

}
