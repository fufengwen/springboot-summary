package com.ffw.common.System;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @Description TODO
 * @Author fufengwen
 * @Time 2020/8/4 9:19
 */
//@Configuration
public class HardWareCheck {
    /**
     * 获取系统参数
     * @return
     */
    public static Map getOsParam(){
        Map map = new HashMap();
        Properties p = System.getProperties();
        Iterator it = p.entrySet().iterator();
        System.out.println("--------------系统配置如下----------------");
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            map.put(entry.getKey(),entry.getValue());
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
        System.out.println("-------------系统配置读取完毕-------------");
        return map;
    }

    public static void main(String[] args) {
        getOsParam();
    }
}
