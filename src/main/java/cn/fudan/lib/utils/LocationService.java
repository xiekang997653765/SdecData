package cn.fudan.lib.utils;

import cn.fudan.lib.dto.Location;

import java.math.BigDecimal;

public interface LocationService {

    Location getLocationInfo (BigDecimal lat, BigDecimal lng);

    String getSig (String... params);

}
