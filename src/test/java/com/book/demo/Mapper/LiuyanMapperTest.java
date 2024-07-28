package com.book.demo.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LiuyanMapperTest {
    @Autowired
    LiuyanMapper liuyanMapper;

    @Test
    void addLiuyan() {
        System.out.println(liuyanMapper.addLiuyan("billie", "广西中医药大学位于有“中国绿城”美誉的广西壮族自治区首府南宁市,是我国5个少数民族自治区中唯一独立建制的高等中医药院校。学校创建于1956年,其前身为1934...", 7));
    }

    @Test
    void deleteLiuyan() {
        System.out.println(liuyanMapper.deleteLiuyanById(7));
    }
}