package com.book.demo.Controller;

import com.book.demo.Mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/data")
public class DataGetController {

    @Autowired
    DataMapper dataMapper;


    @GetMapping("/fourTableTongJi")
    public Map<String, Object> fourTableTongJi() {
        // TODO: 实现四张表统计数据
        // 四张表统计数据
        Map<String, Object> response = new HashMap<>();
        try {
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

            response.put("code", 200);
            response.put("msg", "成功");
            response.put("result", true);
            response.put("data", results);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("code", 500);
            response.put("msg", "服务器内部错误");
            response.put("result", false);
            Map<String, Object> params = new HashMap<>();
            params.put("userCount", -1);
            params.put("gonggaoCount", -1);
            params.put("bookCount", -1);
            params.put("wendaCount", -1);

            response.put("data", params);
        }
        return response;

    }
}
