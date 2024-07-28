package com.book.demo.Mapper;

import com.book.demo.Pojo.GouwucheWithBook;
import com.book.demo.Pojo.LiuyanWithUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GouwucheWithBookMapper {

    // 根据公告id查询留言及用户信息->获取留言数据项
    @Select("SELECT gouwuche.*, book.* FROM gouwuche JOIN book ON gouwuche.bookid = book.id WHERE gouwuche.user = #{username}")
    List<GouwucheWithBook> getGouwucheWithBookByUsername(String username);
}
