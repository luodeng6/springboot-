package com.book.demo.Mapper;

import com.book.demo.Pojo.Gonggao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GonggaoMapper {
    // 获取前4条公告，最近时间的公告排在前面
    @Select("select * from gonggao order by time desc limit 4")
    List<Gonggao> getGonggaotop4();

    // 通过id获取公告
    @Select("select * from gonggao where id = #{id}")
    Gonggao getGonggaoById(int id);

    //统计公告总数
    @Select("select count(*) from gonggao")
    int getGonggaoCount();
}
