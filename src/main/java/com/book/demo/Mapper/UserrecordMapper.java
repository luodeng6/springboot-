package com.book.demo.Mapper;

import com.book.demo.Pojo.Userrecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserrecordMapper {

    // 获取所有注册、注销记录
    @Select("SELECT * FROM userrecord")
    List<Userrecord> getAllUserrecords();


   /* // 测试用，添加记录
    @Insert("insert into  userrecord(username, time, action) values(#{id}, #{username}, #{time}, #{action})")*/
}
