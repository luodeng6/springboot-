package com.book.demo.Mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface LiuyanMapper {
    @Insert("insert into liuyan(user, content, gonggaoid) VALUES (#{user}, #{content}, #{gonggaoid})")
    int addLiuyan(@Param("user") String user, @Param("content") String content, @Param("gonggaoid") int gonggaoid);

    @Delete("delete from liuyan where id = #{id}")
    int deleteLiuyanById(@Param("id") int id);

    @Update("update liuyan set content = #{content} where id = #{id}")
    int updateLiuyanById(@Param("id") int id, @Param("content") String content);
}
