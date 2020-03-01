package cn.fudan.lib.stream.utils;

import java.util.HashSet;
import java.util.Set;

public class DataTypeConstants {
    public static final String BedMat                    = "BedMat";//	床脚垫
    public static final String ElevatorSensor            = "ElevatorSensor";//	电梯运行监测
    public static final String DoorSensor                = "DoorSensor";//	单元门洞门磁监测
    public static final String ElectronicFence           = "ElectronicFence";//	电子围栏系统
    public static final String FireAlarmSensor           = "FireAlarmSensor";//	火警预警系统
    public static final String TemperatureHumiditySensor = "TemperatureHumiditySensor";//	温湿度传感器
    public static final String TrashBin                  = "TrashBin";//	智能垃圾桶
    public static final String AreaDustMonitor           = "AreaDustMonitor";//	区域灰尘传感器
    public static final String PuddleSensor              = "PuddleSensor";//	道路积水
    public static final String SmokeDetectionSensor      = "SmokeDetectionSensor";//	消防烟感温感
    public static final String PassiveInfraredSensor     = "PassiveInfraredSensor";//	被动式红外探测器
    public static final String OldManCareKit             = "OldManCareKit";//	老人五件套
    public static final String TurbiditySensor           = "TurbiditySensor";//	浊度传感器
    public static final String DistributionBoxSensor     = "DistributionBoxSensor";//	电箱传感器
    public static final String WellCoverSensor           = "WellCoverSensor";//	井盖传感器
    public static final String GeomagneticSensor         = "GeomagneticSensor";//	地磁传感器
    public static final String WaterPumpSensor           = "WaterPumpSensor";//	水泵水压传感器
    public static final String RegisterDoorSensor        = "RegisterDoorSensor";//	居户门磁监测
    public static final String ThermalImagerySensor      = "ThermalImagerySensor";//	热成像感知器
    public static final String WaterPressureSensor       = "WaterPressureSensor";//	消防栓水压传感器
    public static final String TankCoverSensor           = "TankCoverSensor";//	水箱盖传感器
    public static final String MedicalExaminSystem       = "MedicalExaminSystem";//	智能体检
    public static final String tDeviceInfo               = "t_device_info";//	位置信息
    public static final String tExceptionData            = "t_exception_data";//	异常信息表

    private static final Set<String> ALL_DATA_TYPES = new HashSet<String>() {
        {
            this.add(DataTypeConstants.BedMat);
            this.add(DataTypeConstants.ElevatorSensor);
            this.add(DataTypeConstants.DoorSensor);
            this.add(DataTypeConstants.ElectronicFence);
            this.add(DataTypeConstants.FireAlarmSensor);
            this.add(DataTypeConstants.TemperatureHumiditySensor);
            this.add(DataTypeConstants.TrashBin);
            this.add(DataTypeConstants.AreaDustMonitor);
            this.add(DataTypeConstants.PuddleSensor);
            this.add(DataTypeConstants.SmokeDetectionSensor);
            this.add(DataTypeConstants.PassiveInfraredSensor);
            this.add(DataTypeConstants.OldManCareKit);
            this.add(DataTypeConstants.TurbiditySensor);
            this.add(DataTypeConstants.DistributionBoxSensor);
            this.add(DataTypeConstants.WellCoverSensor);
            this.add(DataTypeConstants.GeomagneticSensor);
            this.add(DataTypeConstants.WaterPumpSensor);
            this.add(DataTypeConstants.RegisterDoorSensor);
            this.add(DataTypeConstants.ThermalImagerySensor);
            this.add(DataTypeConstants.WaterPressureSensor);
            this.add(DataTypeConstants.TankCoverSensor);
            this.add(DataTypeConstants.MedicalExaminSystem);
            this.add(DataTypeConstants.tDeviceInfo);
        }
    };

    /**
     * 查看输入的数据类型是否合法
     */
    public static boolean isValidDataType (String dataType) {
        return ALL_DATA_TYPES.contains(dataType);
    }

}
