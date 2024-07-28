package com.book.demo.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminUserMapperTest {

    @Autowired
    private AdminUserMapper adminUserMapper;
    @Test
    void selectByPrimaryKey() {
        System.out.println(adminUserMapper.getAdminUserByUsername("admin"));
    }
}