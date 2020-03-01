package cn.fudan.lib.stream.database;

import cn.fudan.lib.dto.DataItem;
import cn.fudan.lib.app.xr.DeviceInfoDataItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DaoMapper {
    List<DataItem> readData (@Param ("param") QueryParameter parameter);
    List<DeviceInfoDataItem> readDeviceInfo (@Param ("param") QueryParameter parameter);
    void insertException (@Param ("param") InsertParameter parameter);

}
