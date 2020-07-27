package com.ffw.common.IntegerUtils;

import com.ffw.common.StringUtils.StringUtils;

/**
 * @Description TODO
 * @Author fufengwen
 * @Time 2020/7/24 8:50
 */
public class IntegerUtils{
    /**
     * 字符串转
     * @param i
     * @return
     */
    public static Integer toInteger(String i){
        if(StringUtils.isNotBlank(i)){
            return Integer.parseInt(i);
        }else{
            throw new NumberFormatException("null");
        }
    }
}
