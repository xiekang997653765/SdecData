package cn.fudan.lib.stream.database;

import cn.fudan.lib.app.xyj.ExceptionParameter;
import cn.fudan.lib.dto.DataItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DaoMapper {
    List<DataItem> readData (@Param ("param") QueryParameter parameter);
    List<String> queryAllDeviceId (@Param ("param") QueryParameter parameter);
    void insertExceptionData(@Param ("param") ExceptionParameter parameter);

}
