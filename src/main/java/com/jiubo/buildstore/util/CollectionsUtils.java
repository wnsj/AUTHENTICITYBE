package com.jiubo.buildstore.util;

import com.jiubo.buildstore.bean.BuildingBean;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class CollectionsUtils {

    public static Boolean isEmpty(Map<?, ?> map) {
        if (null == map) return true;
        if (map.containsKey(null)) return true;
        return map.size() == 0;
    }

    public static Boolean isEmpty(List<?> list) {
        if (null == list) return true;
        return 0 == list.size();
    }

    public static void main(String[] args) {
        List<String> buildingBeans = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        map.put(null,"");

        buildingBeans.add("");
        if (isEmpty(buildingBeans)) {
            log.debug("集合为空");
        } else {
            log.debug("集合不为空");
        }
    }
}
