package cn.fudan.lib.app.xk;

/**
 * SELECT deviceId  FROM (
 * SELECT * FROM (
 * SELECT  deviceId  FROM WellCoverSensor GROUP BY deviceId)  a
 * <p>
 * LEFT JOIN t_device_info b ON a.deviceId = b.device_no OR a.deviceId = b.deveui) c
 * WHERE c.id IS NULL;
 */
public class UnknownDevices {
    public static void main (String[] args) {
    }

}
