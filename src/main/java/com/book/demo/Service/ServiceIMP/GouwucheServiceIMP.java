package com.book.demo.Service.ServiceIMP;

import com.book.demo.Mapper.GouwucheMapper;
import com.book.demo.Pojo.Gouwuche;
import com.book.demo.Pojo.User;
import com.book.demo.Service.GouwucheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class GouwucheServiceIMP implements GouwucheService {
    @Autowired
    private GouwucheMapper gouwucheMapper;

    @Autowired
    private HttpSession session;

    /**
     * 通过主键删除数据
     * 取消购物车deleteById方法
     *
     * @param gouwuche 购物车对象
     * @return 是否成功
     */
    @Override
    // 事务注解
    @Transactional
    public Map<String, Object> deleteGouwuche(Gouwuche gouwuche) {
        System.out.println("删除购物车");
        System.out.println(gouwuche);
        Map<String, Object> map = new HashMap<>();
        try {
            //获取Session中的用户
            User user = (User) session.getAttribute("user");
            // 获取此购物车id的用户名
            String gouwucheUser = gouwucheMapper.getGouwucheById(gouwuche.getId()).getUser();
            if (user == null || !Objects.equals(user.getUsername(), gouwucheUser) || !user.getUsername().equals(gouwuche.getUser())) {
                map.put("code", 401);
                map.put("msg", "没有权限");
                map.put("result", false);
                map.put("resultcode", "1");

            } else {
                //身份验证成功，删除购物车记录
                if (gouwucheMapper.deleteGouwuche(gouwuche) > 0) {
                    map.put("code", 200);
                    map.put("msg", "删除成功");
                    map.put("result", true);
                    map.put("resultcode", "2");
                } else {
                    map.put("code", 200);
                    map.put("msg", "删除失败");
                    map.put("result", false);
                    map.put("resultcode", "3");
                }
            }
        } catch (Exception e) {
            //
            e.printStackTrace();
            map.put("code", 500);
            map.put("msg", "服务器内部错误");
            map.put("result", false);
            map.put("resultcode", "4");
            // 事务回滚
            throw e;
        }
        return map;
    }


    /* *
     * 添加购物车逻辑
     */
    @Override
    public boolean addGouwuche(Gouwuche gouwuche) {
        try {
            Gouwuche gouwucheCheck = gouwucheMapper.checkGouwuche(gouwuche);
            //检查是否已有此书的购物车记录，如果有，则数量+，如果没有，则新增一条记录
            if (gouwucheCheck == null) {
                //正常新增记录
                gouwucheMapper.addGouwuche(gouwuche);
            } else {
                //更新数量
                gouwucheCheck.setSelectnum(gouwucheCheck.getSelectnum() + gouwuche.getSelectnum());
                gouwucheCheck.setTotalprice(gouwucheCheck.getTotalprice() + gouwuche.getTotalprice());
                //执行更新操作
                gouwucheMapper.updateGouwuche(gouwucheCheck);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}