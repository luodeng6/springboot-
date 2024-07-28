package com.book.demo.Controller;

import com.book.demo.Pojo.Shoucang;
import com.book.demo.Service.ShoucangMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shoucang")
public class ShoucangController {
    @Autowired
    private ShoucangMapper shoucangMapper;

    //添加收藏
    @GetMapping("/addShoucang")
    public Map<String, Object> addShoucang(int user, int bookid) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (shoucangMapper.addShoucang(user, bookid) > 0) {
                map.put("code", 200);
                map.put("msg", "收藏成功");
                map.put("result", true);
            } else {
                map.put("code", 200);
                map.put("msg", "收藏失败");
                map.put("result", false);
            }
        } catch (Exception e) {
            map.put("code", 500);
            map.put("msg", "收藏失败,后台错误");
            map.put("result", false);
            e.printStackTrace();
        }
        return map;
    }

    //用户取消收藏
    @GetMapping("/deleteShoucang")
    public Map<String, Object> deleteShoucang(int shoucangid) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (shoucangMapper.deleteShoucangById(shoucangid) > 0) {
                map.put("code", 200);
                map.put("msg", "取消收藏成功");
                map.put("result", true);
            } else {
                map.put("code", 200);
                map.put("msg", "取消收藏失败");
                map.put("result", false);
            }
        } catch (Exception e) {
            map.put("code", 500);
            map.put("msg", "取消收藏失败,后台错误");
            map.put("result", false);
            e.printStackTrace();
        }
        return map;
    }

    @GetMapping("/getAllShoucang")
    public Map<String, Object> getAllShoucang() {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Shoucang> AllShoucang = shoucangMapper.getAllShoucang();
            map.put("code", 200);
            map.put("msg", "获取收藏列表成功");
            map.put("result", AllShoucang);
            map.put("count", AllShoucang.size());
        } catch (Exception e) {
            map.put("code", 500);
            map.put("msg", "获取收藏列表失败,后台错误");
            map.put("result", false);
            e.printStackTrace();
        }
        return map;
    }




}
