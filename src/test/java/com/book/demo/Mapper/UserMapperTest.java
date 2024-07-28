package com.book.demo.Mapper;

import com.book.demo.Pojo.User;
import com.book.demo.Service.UserService;
import org.apache.ibatis.annotations.Insert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void testRandomInsertUser() {
        Map<String, Object> params = new HashMap<>();
        params.put("maleCount", null);
        params.put("femaleCount", null);
        System.out.println(userMapper.callGetGenderCounts(params));
        System.out.println(params);
    }
    @Autowired
    UserService userService;

    //分页查询测试
    @Test
    void testPageQuery() {
        System.out.println(((List<User>)userService.getUserList(1, 10).get("data")).size());
    }
}