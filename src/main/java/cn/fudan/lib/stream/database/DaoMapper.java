package cn.fudan.lib.stream.database;

import cn.fudan.lib.app.xyj.ExceptionParameter;
import cn.fudan.lib.dto.DataItem;
import cn.fudan.lib.app.xr.DeviceInfoDataItem;
import cn.fudan.lib.dto.DeviceInfo;
import cn.fudan.lib.dto.DeviceInfoQueryParameter;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface DaoMapper {
    List<DataItem> readData (@Param ("param") QueryParameter parameter);
    List<String> queryAllDeviceId (@Param ("param") QueryParameter parameter);
    void insertExceptionData(@Param ("param") ExceptionParameter parameter);
  
    List<DeviceInfoDataItem> readDeviceInfo (@Param ("param") QueryParameter parameter);
    void insertException (@Param ("param") InsertParameter parameter);

    DeviceInfo getDeviceInfoById (@Param ("id") Long id);

    DeviceInfo getDeviceInfoByDeviceNo (@Param ("deviceNo") String deviceNo);

    DeviceInfo getDeviceInfoByDeviceUi (@Param ("deviceUi") String deviceUi);

    List<DeviceInfo> getDeviceInfo (@Param ("param") DeviceInfoQueryParameter parameter);

    void updateLocationInfoById (@Param ("item") DeviceInfo item);

    List<DeviceInfo> get20KmDevices (@Param ("lat") BigDecimal lat, @Param ("lng") BigDecimal lng);

    void update20KmDevicesById (@Param ("info") DeviceInfo info);

}
