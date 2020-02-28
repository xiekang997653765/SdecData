package cn.fudan.lib.stream.core;

import cn.fudan.lib.dto.DataItem;

public interface DataHandler {
    /**
     * 处理数据的接口
     *
     * @param in       接收到的一条实时数据
     * @param dataType 数据类型，也就是表名称
     */
    void handle (DataItem in, String dataType);

}
