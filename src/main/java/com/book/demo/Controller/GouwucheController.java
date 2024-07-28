package com.book.demo.Controller;

import com.book.demo.Mapper.GouwucheMapper;
import com.book.demo.Mapper.GouwucheWithBookMapper;
import com.book.demo.Pojo.Gouwuche;
import com.book.demo.Pojo.GouwucheWithBook;
import com.book.demo.Pojo.User;
import com.book.demo.Service.GouwucheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Gouwuche)表控制层
 *
 * @author makejava
 * @since 2024-07-23 10:03:21
 */
@RestController
@RequestMapping("/gouwuche")
public class GouwucheController {
    @Autowired
    private GouwucheMapper gouwucheMapper;
    @Autowired
    private HttpSession session; // 使用 Spring 的 HttpSession 来管理会话
    @Autowired
    private GouwucheWithBookMapper gouwucheWithBookMapper;
    @Autowired
    private GouwucheService gouwucheService;

    /**
     * 用户添加购物车
     */

    @PostMapping("/addGouwuche")
    // 事务注解
    @Transactional
    public Map<String, Object> addGouwuche(Gouwuche gouwuche) {
        System.out.println("gouwuche:" + gouwuche);
        Map<String, Object> response = new HashMap<>();
        try {
            if (gouwucheService.addGouwuche(gouwuche)) {
                response.put("code", 200);
                response.put("msg", "添加购物车成功");
                response.put("data", gouwuche);
                response.put("result", true);
            } else {
                response.put("code", 200);
                response.put("msg", "添加购物车失败");
                response.put("data", gouwuche);
                response.put("result", false);
            }
        } catch (Exception e) {

            response.put("code", 500);
            response.put("msg", "添加购物车失败，后台错误");
            response.put("data", gouwuche);
            response.put("result", false);
            //回滚事务
            throw e;
        }
        return response;
    }

    /**
     * 获取登录的用户购物车列表
     *
     * @return Map<String, Object>
     */
    @GetMapping("/getGouwucheList")
    public Map<String, Object> getGouwucheList() {
        Map<String, Object> response = new HashMap<>();
        try {
            User currentUser = (User) session.getAttribute("user");
            System.out.println("当前登录的用户：");
            System.out.println(currentUser);
            if (currentUser == null) {
                response.put("code", 401);
                response.put("msg", "请先登录");
                response.put("result", false);
            } else {
                List<GouwucheWithBook> gouwucheWithBooks = gouwucheWithBookMapper.getGouwucheWithBookByUsername(currentUser.getUsername());
                response.put("code", 200);
                response.put("msg", "购物车信息获取成功");
                response.put("data", gouwucheWithBooks);
                response.put("result", true);
                response.put("count", gouwucheWithBooks.size());
            }
        } catch (Exception e) {
            response.put("code", 500);
            response.put("msg", e.getMessage());
            response.put("data", null);
            response.put("result", false);
        }
        return response;
    }


    /**
     * 删除数据
     *
     * @return 删除是否成功
     */
    @PostMapping("/deleteGouwuche")
    public Map<String, Object> deleteGouwuche(Gouwuche gouwuche) {
        System.out.println("删除购物车数据：" + gouwuche);
        return gouwucheService.deleteGouwuche(gouwuche);
    }

}