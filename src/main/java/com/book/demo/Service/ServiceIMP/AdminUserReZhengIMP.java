package com.book.demo.Service.ServiceIMP;

import com.book.demo.Mapper.AdminUserMapper;
import com.book.demo.Pojo.AdminUser;
import com.book.demo.Service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminUserReZhengIMP implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;


    @Autowired
    HttpSession session;

    /**
     * 管理员认证
     *
     * @param adminUser
     * @return
     */
    @Override
    public Map<String, Object> AdminUserReZheng(AdminUser adminUser) {
        Map<String, Object> result = new HashMap<>();
        try {
            AdminUser RZadminUser = adminUserMapper.adminUserLogin(adminUser);
            if (RZadminUser == null) {
                result.put("code", 400);
                result.put("msg", "管理员认证失败");
                result.put("result", false);
            } else {
                result.put("code", 200);
                result.put("msg", "管理员认证成功");
                result.put("result", true);
                result.put("adminUser", RZadminUser);
                //使用session保存登录信息
                // 创建2小时的Session->用户信息
                session.setAttribute("adminUser", RZadminUser);
                session.setMaxInactiveInterval(2 * 60*60); // 2小时
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", "服务器内部错误");
            result.put("result", false);
        }
        return result;
    }

    /**
     * 获取当前登录的管理员信息
     *
     * @return
     */
    @Override
    public Map<String, Object> GetRZAdminUser() {
        Map<String, Object> result = new HashMap<>();
        try {
            AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");
            if (adminUser == null) {
                result.put("code", 200);
                result.put("msg", "无管理员认证信息");
                result.put("result", false);
            } else {
                result.put("code", 200);
                result.put("msg", "获取管理员认证信息成功");
                result.put("result", true);
                result.put("adminUser", adminUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", "服务器内部错误");
            result.put("result", false);
        }
        return result;
    }
}
