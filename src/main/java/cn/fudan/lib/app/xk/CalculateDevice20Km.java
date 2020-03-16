package cn.fudan.lib.app.xk;

import cn.fudan.lib.dao.MySqlSessionFactory;
import cn.fudan.lib.dto.DataItem;
import cn.fudan.lib.dto.DeviceInfo;
import cn.fudan.lib.dto.DeviceInfoQueryParameter;
import cn.fudan.lib.dto.Location;
import cn.fudan.lib.stream.database.DaoMapper;
import cn.fudan.lib.utils.LocationService;
import cn.fudan.lib.utils.TencentMapLocationService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class CalculateDevice20Km {
    public static void main (String[] args) {
        SqlSession sqlSession = MySqlSessionFactory.createSqlSession().openSession();
        DaoMapper mapper = sqlSession.getMapper(DaoMapper.class);
        List<DeviceInfo> deviceInfoList = mapper.getDeviceInfo(new DeviceInfoQueryParameter());
        sqlSession.close();

        Location locationInfo = null;
        DeviceInfo info = null;
        int size = deviceInfoList.size();
        for (int i = 0; i < size; i++) {
            if(i < 1176) continue;

            info = deviceInfoList.get(i);
            sqlSession = MySqlSessionFactory.createSqlSession().openSession();
            mapper = sqlSession.getMapper(DaoMapper.class);
            List<DeviceInfo> kmDevices = mapper.get20KmDevices(info.getLat(), info.getLng());
            List<DeviceInfo> mine = new ArrayList<>();
            for (DeviceInfo kmDevice : kmDevices) {
                if (StringUtils.isNotBlank(kmDevice.getDeviceCode()) && kmDevice.getDeviceCode().equals(info.getDeviceCode())) {
                    mine.add(kmDevice);
                }
            }

            kmDevices.removeAll(mine);

            info.setArea20Km(JSONObject.toJSONString(mine));
            info.setArea20KmOther(JSONObject.toJSONString(kmDevices));
            mapper.update20KmDevicesById(info);
            sqlSession.commit();
            sqlSession.close();
            kmDevices.clear();
            mine.clear();


            System.out.println("[" + (i + 1) + "/" + size + "]");
        }
    }

}
