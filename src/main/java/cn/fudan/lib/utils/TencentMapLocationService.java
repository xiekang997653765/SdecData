package cn.fudan.lib.utils;

import cn.fudan.lib.dto.Location;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 腾讯地图接口签名方法：https://lbs.qq.com/FAQ/key_faq.html#6
 * <p>
 * 接口：https://apis.map.qq.com/ws/geocoder/v1/?location=39.984154,116.307490&key=SKBBZ-H3BCF-ZYNJ4-JKSDM-AW3P3-HEFG3&get_poi=1
 * <p>
 * sk:6lmWSIzGZ6wTBunds3ik60t0HDKZJLy
 */
public class TencentMapLocationService implements LocationService {
    private static final String SK      = "6lmWSIzGZ6wTBunds3ik60t0HDKZJLy";
    private static final String KEY     = "SKBBZ-H3BCF-ZYNJ4-JKSDM-AW3P3-HEFG3";
    private static final String API_FMT = "https://apis.map.qq.com/ws/geocoder/v1/?location=%s,%s&key=" + KEY + "&get_poi=1&sig=%s";

    @Override
    public Location getLocationInfo (BigDecimal lat, BigDecimal lng) {
        String api = getApi(lat, lng);
        try {
            String jsonResult = JsoupUtil.genConnection(api, "").get().body().text();
            JSONObject jsonObject = JSONObject.parseObject(jsonResult);
            JSONObject result = jsonObject.getJSONObject("result");
            String address = result.getString("address");
            JSONObject addressComponent = result.getJSONObject("address_component");
            Location location = new Location();
            location.setAddress(address);
            location.setCity(addressComponent.getString("city"));
            location.setDistrict(addressComponent.getString("district"));
            location.setProvince(addressComponent.getString("province"));
            location.setStreet(addressComponent.getString("street"));
            location.setStreetNumber(addressComponent.getString("street_number"));
            return location;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getApi (BigDecimal lat, BigDecimal lng) {
        String latStr = lat.toString();
        String lngStr = lng.toString();
        String sig = getSig("/ws/geocoder/v1/?get_poi=1&key=", KEY, "&location=", latStr, ",", lngStr, SK);
        String api = String.format(API_FMT, latStr, lngStr, sig);
        return api;
    }

    @Override
    public String getSig (String... params) {
        StringBuilder sb = new StringBuilder();
        for (String param : params) {
            sb.append(param);
        }
        return MD5Utils.stringToMD5(sb.toString());
    }

}
