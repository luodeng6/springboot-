package com.book.demo.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface DataMapper {
   /* @Select("CALL GetTableCounts(#{userCount, mode=OUT, jdbcType=INTEGER}, #{gonggaoCount, mode=OUT, jdbcType=INTEGER}, #{bookCount, mode=OUT, jdbcType=INTEGER}, #{wendaCount, mode=OUT, jdbcType=INTEGER})")*/
    void getTableCounts(Map<String, Object> params);
}
