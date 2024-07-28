package com.book.demo.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DataMapperTest {

    @Autowired
    DataMapper dataMapper;
    @Test
    void insert() {
        Map<String, Object> params = new HashMap<>();
        params.put("userCount", 0);
        params.put("gonggaoCount", 0);
        params.put("bookCount", 0);
        params.put("wendaCount", 0);

        dataMapper.getTableCounts(params);

        Map<String, Integer> results = new HashMap<>();
        results.put("userCount", (Integer) params.get("userCount"));
        results.put("gonggaoCount", (Integer) params.get("gonggaoCount"));
        results.put("bookCount", (Integer) params.get("bookCount"));
        results.put("wendaCount", (Integer) params.get("wendaCount"));
        System.out.println(results);
    }
}