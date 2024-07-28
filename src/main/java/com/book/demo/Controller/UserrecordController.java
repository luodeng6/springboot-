package com.book.demo.Controller;

import com.book.demo.Mapper.UserrecordMapper;
import com.book.demo.Pojo.Userrecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userrecord")
public class UserrecordController {

    @Autowired
    UserrecordMapper userrecordMapper;

    @GetMapping("/getAlluserrecord")
    public Map<String, Object> getAllUserrecord() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Userrecord> userrecordList = userrecordMapper.getAllUserrecords();
            response.put("code", 200);
            response.put("message", "success");
            response.put("data", userrecordList);
            response.put("count", userrecordList.size());
            response.put("result", true);
        } catch (Exception e) {
            response.put("code", 500);
            response.put("message", "后台出错，请联系管理员");
            response.put("data", null);
            response.put("count", 0);
            response.put("result", false);
        }
        return response;
    }
}
