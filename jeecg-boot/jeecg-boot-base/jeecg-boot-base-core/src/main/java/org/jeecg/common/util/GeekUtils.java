package org.jeecg.common.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class GeekUtils {
    public static String toString(Object o) {
        if (o == null) return "";
        return o.toString();
    }

    public static Double toDouble(Object o) {
        if (o == null) return 0d;
        try {
            return Double.parseDouble(o.toString());
        } catch (Exception e) {
            return 0d;
        }
    }

    public static String getTocken() {
        String loginurl = "https://geek-service.sellerwell.com/api/v1/login";
        JSONObject param = new JSONObject();
        param.put("username", "17720491608");
        param.put("password", "Eccang123");
        param.put("remember", "true");
        param.put("type", "1");
        JSONObject result = RestUtil.post(loginurl, param);
        return ((Map) result.get("data")).get("token").toString();
    }
}
