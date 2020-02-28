package cn.fudan.lib.app.xk;

import cn.fudan.lib.dto.DataItem;
import cn.fudan.lib.stream.core.DataHandler;
import cn.fudan.lib.stream.database.QueryData;
import cn.fudan.lib.stream.database.QueryParameter;
import cn.fudan.lib.stream.pojo.SubscribeItem;
import cn.fudan.lib.stream.read.StreamDataSubscribe;
import cn.fudan.lib.stream.utils.DataTypeConstants;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestApp {
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
        SubscribeItem bedMatSub = new SubscribeItem();
        bedMatSub.setDataType(DataTypeConstants.BedMat);
        bedMatSub.setStartDate("2018-06-15"); //从5月14号的当前时刻开始

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
        DataHandler handler = new TestHandler();

        /**
         * 订阅一个流数据，
         *   第一个参数是数据你的handler,
         *   第二个参数是个可变参数， 可以传递一个或多个表名，表示你要订阅哪几张表的数据
         *   示例中订阅了两张表，bedMat和trashBin
         *   注意： 所有的表名，都已经封装在 DataTypeConstants 类中
         *
         * 最后调用 start 方法，开始运行应用
         * */
        new StreamDataSubscribe(handler, bedMatSub).start();

        /**
         * 历史数据查询
         * */
        QueryParameter parameter = new QueryParameter();
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        /****** 必填条件开始 ********/
        parameter.setTableName(DataTypeConstants.BedMat); //表名
        parameter.setBeginDate(dtf.parseDateTime("2018-05-15 10:12:05").toDate()); //设置开始时间
        parameter.setEndDate(dtf.parseDateTime("2018-05-15 11:12:05").toDate()); //设置结束时间
        /****** 必填条件结束 ********/

        /****** 选填条件开始 ********/
        //parameter.setDeviceId("deviceId");  //根据设备ID查找
        //parameter.setExcludeIds(new ArrayList<Long>()); //需要排除的数据ID集合
        /****** 选填条件结束 ********/

        List<DataItem> queryResult = QueryData.INSTANCE.query(parameter);
        System.out.println("查询出" + queryResult.size() + "条数据");


    }

}
