package com.book.demo;

import com.book.demo.Mapper.BookMapper;
import com.book.demo.Mapper.UserMapper;
import com.book.demo.Mapper.WendaMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookSystemApplicationTests {

    @Autowired
    BookMapper bookMapper;

    @Test
    void contextLoads() {
        bookMapper.getBookById(1);
    }

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads2() {
        userMapper.getUserById(1);
    }

    @Autowired
    WendaMapper wendaMapper;

    @Test
    void contextLoads3() {
        wendaMapper.getWendaPage(1, 2);
    }
}
