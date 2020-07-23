package com.ffw.mybatis.dao;

import com.ffw.mybatis.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface demoMapper {
    @Select("select * from user where id = #{id}")
    public User queryUserById(Integer id);
}
