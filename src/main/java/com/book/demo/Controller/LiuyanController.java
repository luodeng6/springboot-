package com.book.demo.Controller;

import com.book.demo.Mapper.LiuyanMapper;
import com.book.demo.Mapper.LiuyanWithUserMapper;
import com.book.demo.Pojo.LiuyanWithUser;
import com.book.demo.Pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Liuyan)表控制层
 *
 * @author makejava
 * @since 2024-07-24 18:39:11
 */
@RestController
@RequestMapping("/liuyan")
public class LiuyanController {
    @Autowired
    private LiuyanWithUserMapper liuyanWithUserMapper;

    @Autowired
    private LiuyanMapper liuyanMapper;
    /**
     * 分页查询
     *
     * @param liuyan 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */


    /**
     * 新增数据
     *
     * @param user      用户名
     * @param content   留言内容
     * @param gonggaoid 公告id
     * @return map
     */
    @PostMapping("/addLiuyan")
    public Map<String, Object> addLiuyan(@RequestParam String user, @RequestParam String content, @RequestParam int gonggaoid) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (liuyanMapper.addLiuyan(user, content, gonggaoid) > 0) {
                map.put("code", 200);
                map.put("msg", "新增留言成功");
                map.put("result", true);
            } else {
                map.put("code", 200);
                map.put("msg", "新增留言失败");
                map.put("result", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 500);
            map.put("msg", "新增留言失败,服务器内部错误");
            map.put("result", false);
        }
        return map;
    }


    @Autowired
    private HttpSession session; // 使用 Spring 的 HttpSession 来管理会话

    /**
     * 删除数据
     *
     * @param id 主键
     * @return Map
     */
    @PostMapping("/deleteLiuyanById")
    public Map<String, Object> deleteLiuyanById(@RequestParam int id, @RequestParam String user) {
        Map<String, Object> map = new HashMap<>();

        //这里应该有一个身份验证Session验证，本人删除本人的留言！
        // 从会话中获取当前登录的用户
        User currentUser = (User) session.getAttribute("user");
        System.out.println("当前登录的用户：");
        System.out.println(currentUser);
        if (currentUser == null) {
            map.put("code", 401);
            map.put("msg", "请先登录");
            map.put("result", false);
            map.put("resultcode", 0);
        } else {
            if (currentUser.getUsername().equals(user)) {
                try {
                    if (liuyanMapper.deleteLiuyanById(id) > 0) {
                        map.put("code", 200);
                        map.put("msg", "删除留言成功");
                        map.put("result", true);
                        map.put("resultcode", 2);
                    } else {
                        map.put("code", 200);
                        map.put("msg", "删除留言失败");
                        map.put("result", false);
                        map.put("resultcode", 3);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    map.put("code", 500);
                    map.put("msg", "删除留言失败,服务器内部错误");
                    map.put("result", false);
                    map.put("resultcode", 4);
                }
            } else {
                map.put("code", 401);
                map.put("msg", "你没有权限删除别人的留言！");
                map.put("result", false);
                map.put("resultcode", 5);
            }
        }
        return map;
    }


    /*
     * 获取一个公告的留言信息以及留言的用户信息
     * @param id 公告id
     * @return 留言信息以及留言的用户信息Map
     * */
    @GetMapping("/getLiuyan")
    public Map<String, Object> getLiuyan(@RequestParam int gonggaoid) {
        Map<String, Object> map = new HashMap<>();
        try {
            // 调用业务逻辑层方法，获取留言信息以及留言的用户信息
            // 封装返回结果
            List<LiuyanWithUser> liuyanWithUsers = liuyanWithUserMapper.getLiuyanWithUserById(gonggaoid);

            map.put("code", 200);
            map.put("msg", "获取留言信息成功");
            map.put("data", liuyanWithUsers);
            map.put("count", liuyanWithUsers.size());

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 500);
            map.put("msg", "获取留言信息失败,检查网络连接！");
            map.put("data", null);
            map.put("count", 0);
        }
        return map;
    }


    /*
     * 修改留言内容接口
     * @param user 用户名
     * @param content 留言内容
     * @param id 留言id
     * @return 修改结果Map
     */
    @PostMapping("/updateLiuyanbyId")
    public Map<String, Object> updateLiuyan(@RequestParam String user, @RequestParam String content, @RequestParam int id) {
        Map<String, Object> map = new HashMap<>();
        //这里应该有一个身份验证Session验证，本人删除本人的留言！
        // 从会话中获取当前登录的用户
        User currentUser = (User) session.getAttribute("user");
        System.out.println("当前登录的用户：");
        System.out.println(currentUser);
        if (currentUser == null) {
            map.put("code", 401);
            map.put("msg", "请先登录");
            map.put("result", false);
            map.put("resultcode", 0);
        } else {
            if (!currentUser.getUsername().equals(user)) {
                map.put("code", 401);
                map.put("msg", "你没有权限修改别人的留言！");
                map.put("result", false);
                map.put("resultcode", 1);
            } else {
                try {
                    if (liuyanMapper.updateLiuyanById(id, content) > 0) {
                        map.put("code", 200);
                        map.put("msg", "修改留言成功");
                        map.put("result", true);
                        map.put("resultcode", 2);
                    } else {
                        map.put("code", 200);
                        map.put("result", false);
                        map.put("msg", "修改留言失败");
                        map.put("resultcode", 3);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    map.put("code", 500);
                    map.put("msg", "修改留言失败,服务器内部错误");
                    map.put("result", false);
                    map.put("resultcode", 4);
                }
            }
        }
        return map;
    }
}

