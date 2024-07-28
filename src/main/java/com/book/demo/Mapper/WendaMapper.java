package com.book.demo.Mapper;


import com.book.demo.Pojo.Wenda;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WendaMapper {
    // 获取所有问答
    @Select("SELECT * FROM wenda")
    List<Wenda> getAllWenda();

    // 根据id获取问答
    @Select("SELECT * FROM wenda WHERE id = #{id}")
    Wenda getWendaById(int id);

    // 根据问题获取问答
    @Select("SELECT * FROM wenda WHERE question  LIKE CONCAT('%', #{question}, '%') ")
    List<Wenda> getWendaByQuestion(String question);

    //使用存储过程分页查询
    @Select("call getWendaPage(#{pageNum}, #{pageSize})")
    List<Wenda> getWendaPage(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    //用户添加问答
    @Insert("insert into wenda(question,questiondetail, user) values (#{question},#{questiondetail},#{user})")
    int addWenda(@Param("question") String question, @Param("questiondetail") String questiondetail, @Param("user") String user);

    /* @Insert("insert into wenda(id, question, answer, user, isreply, create_time, replytime, quality, questiondetail) ")
     */
    //获取所有以及回复的问答记录
    @Select("SELECT * FROM wenda WHERE isreply = 1")
    List<Wenda> getAllReplyWenda();
    // 获取未回复的问答记录
    @Select("SELECT * FROM wenda WHERE isreply = 0")
    List<Wenda> getAllNoReplyWenda();

}
