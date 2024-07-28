package com.book.demo.Mapper;

import com.book.demo.Pojo.Gouwuche;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class GouwucheMapperTest {
    @Autowired
    private GouwucheMapper gouwucheMapper;

    @Test
    void insert() {

        System.out.println(gouwucheMapper.checkGouwuche(new Gouwuche("billie", 22)));
        // 返回null
    }

    @Test
    void delete() {
         Gouwuche gouwuche = new Gouwuche(12);
        System.out.println(gouwuche);
        System.out.println(gouwucheMapper.deleteGouwuche(gouwuche));
    }

}