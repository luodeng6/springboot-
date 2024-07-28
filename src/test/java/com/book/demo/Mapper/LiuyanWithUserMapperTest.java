package com.book.demo.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LiuyanWithUserMapperTest {

    @Autowired
    private LiuyanWithUserMapper liuyanWithUserMapper;

    @Test
    void getLiuyanWithUserById() {
        liuyanWithUserMapper.getLiuyanWithUserById(1);
    }
}