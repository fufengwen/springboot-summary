package com.ffw.common.CollectionUtils;

import java.util.Collection;

/**
 * @Description
 * 问题1：org.apache.commons.collections4.CollectionUtils不可被继承？
 * 答：那就自己写吧
 * @Author fufengwen
 * @Time 2020/7/23 14:44
 */
public class CollectionUtils{
    /**
     * 判断集合是否为空
     * @param collections
     * @return
     */
    public static boolean isEmpty(Collection<?> collections){
        if(collections == null || collections.size() == 0){
            return true;
        }
        return false;
    }
    /**
     * 判断集合是否不为空
     * @param collections
     * @return
     */
    public static boolean isNotEmpty(Collection collections){
        return !isEmpty(collections);
    }
}
