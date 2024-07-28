package com.book.demo.Mapper;

import com.book.demo.Pojo.Borrow;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BorrowMapper {
    // 用户借书
    @Insert("insert into borrow(bookid, user) values (#{bookid}, #{username})")
    int  insertBorrow(@Param("bookid") int bookid, @Param("username") String username);


    //用户是否有未还的书
    @Select("select * from borrow where user=#{username} and status='超时'")
    List<Borrow> hasUnreturnedBook(@Param("username") String username);

    // 更新借书状态
    @Update(" UPDATE borrow  SET status = '超时'  WHERE duedate < NOW() AND status != '超时';  ")
    int updateBorrowStatus();
}
