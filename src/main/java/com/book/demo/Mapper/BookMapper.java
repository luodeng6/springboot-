package com.book.demo.Mapper;

import com.book.demo.Pojo.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("select * from book where id = #{id}")
    Book getBookById(int id);

    // 通过书名查询
    @Select("SELECT * FROM book WHERE bookname LIKE CONCAT('%', #{name}, '%')")
    Book getBookByName(String name);

    // 删除 图书通过id
    @Delete("DELETE FROM book WHERE id = #{id}")
    Integer deleteBookById(int id);


    // 获取所有图书
    @Select("select * from book")
    List<Book> getAllBooks();



}
