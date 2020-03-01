package cn.fudan.lib.app.xk;

import cn.fudan.lib.dto.Location;
import cn.fudan.lib.utils.LocationService;
import cn.fudan.lib.utils.TencentMapLocationService;

import java.math.BigDecimal;

public class LocationTest {
    public static void main (String[] args) {
        LocationService locationService = new TencentMapLocationService();

        Location locationInfo = locationService.getLocationInfo(new BigDecimal("31.232131"), new BigDecimal("121.454858"));

    }

}
