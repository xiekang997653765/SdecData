package cn.fudan.lib.utils;

import java.util.*;

public class GenDeviceNo {
    private static final Map<String, String> AREA_CODE_MAP = new HashMap<String, String>() {
        {
            this.put("宝山区", "D151");
            this.put("普陀区", "D152");
            this.put("徐汇区", "D153");
            this.put("长宁区", "D154");
            this.put("杨浦区", "D155");
            this.put("黄浦区", "D156");
            this.put("虹口区", "D157");
        }
    };

    private static final Set<String> pool = new HashSet<>();

    private GenDeviceNo () {
    }

    private static String doGen (String areaName) {
        StringBuilder sb = new StringBuilder();
        sb.append(AREA_CODE_MAP.get(areaName));
        int i = UUID.randomUUID().toString().hashCode();
        if (i < 0) i *= -1;
        sb.append(i);
        if (sb.length() < 14) {
            sb.append((System.currentTimeMillis() + "").substring(sb.length() - 1, 13));
        }
        return sb.toString().substring(0, 14);
    }

    private static String gen (String areaName) {
        String id;
        for (id = doGen(areaName); pool.contains(id); ) ;
        pool.add(id);
        return id;
    }

    public static void main (String[] args) {
        Set<String> a = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            String id = gen("宝山区");
            if (a.contains(id)) {
                System.out.println("碰撞:" + id);
            } else {
                a.add(id);
                System.out.println(id);
            }
        }
    }

}
