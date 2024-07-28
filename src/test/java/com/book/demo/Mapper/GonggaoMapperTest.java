package com.book.demo.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GonggaoMapperTest {

    @Autowired
    GonggaoMapper gonggaoMapper;
    @Test
    void getGonggao() {
        System.out.println(gonggaoMapper.getGonggaotop4());
    }
    @Test
    void getGonggaoNum() {
        System.out.println(gonggaoMapper.getGonggaoCount());
    }
}