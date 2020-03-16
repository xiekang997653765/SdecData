package cn.fudan.lib.app.xk;

import cn.fudan.lib.dao.MySqlSessionFactory;
import cn.fudan.lib.dto.DeviceInfo;
import cn.fudan.lib.dto.DeviceInfoQueryParameter;
import cn.fudan.lib.dto.Location;
import cn.fudan.lib.stream.database.DaoMapper;
import cn.fudan.lib.utils.LocationService;
import cn.fudan.lib.utils.TencentMapLocationService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.util.List;

public class UpdateAllDeviceLocationInfo {
    public static void main (String[] args) {
        SqlSession sqlSession = MySqlSessionFactory.createSqlSession().openSession();
        DaoMapper mapper = sqlSession.getMapper(DaoMapper.class);
        List<DeviceInfo> deviceInfoList = mapper.getDeviceInfo(new DeviceInfoQueryParameter());
        sqlSession.close();
        LocationService locationService = new TencentMapLocationService();

        Long startId = Long.parseLong(args[0]);
        Location locationInfo = null;
        DeviceInfo info = null;
        int r = 0;
        int size = deviceInfoList.size();
        for (int i = 0; i < size; ) {
            info = deviceInfoList.get(i);
            if (StringUtils.isNotBlank(info.getArea()) || info.getLat().compareTo(new BigDecimal(0)) == 0) {
                i++;
                continue;
            }
            try {
                locationInfo = locationService.getLocationInfo(info.getLat(), info.getLng());
            } catch (Exception e) {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Error, retry");
                r++;
                if (r > 5) i++;
                continue;
            }

            r = 0;
            info.setProvince(locationInfo.getProvince());
            info.setCity(locationInfo.getCity());
            info.setArea(locationInfo.getDistrict());
            info.setDetailAddress(locationInfo.getAddress());

            sqlSession = MySqlSessionFactory.createSqlSession().openSession();
            mapper = sqlSession.getMapper(DaoMapper.class);
            mapper.updateLocationInfoById(info);
            sqlSession.commit();
            sqlSession.close();

            System.out.println("[" + i + "/" + size + "] id=" + info.getId() + ",address=" + info.getDetailAddress());
            i++;
        }
    }

}
