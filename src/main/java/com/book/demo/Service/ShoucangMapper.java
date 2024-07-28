package com.book.demo.Service;

import com.book.demo.Pojo.Shoucang;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShoucangMapper {
    // 用户添加收藏
    @Insert("insert into shoucang(user, bookid) VALUES(#{user}, #{bookid})")
    int addShoucang(int user, int bookid);

    // 用户取消收藏
    @Insert("delete from shoucang where id = #{id}")
    int deleteShoucangById(int id);


    // 用户查看收藏列表
    @Select("select * from shoucang where user = #{username}")
    List<Shoucang> getShoucangByUsername(int username);

    // 获取所有收藏
    @Select("select * from shoucang")
    List<Shoucang> getAllShoucang();
}
