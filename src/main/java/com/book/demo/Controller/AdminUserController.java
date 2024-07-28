package com.book.demo.Controller;

import com.book.demo.Mapper.AdminUserMapper;
import com.book.demo.Pojo.AdminUser;
import com.book.demo.Service.AdminUserService;
import com.book.demo.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private HttpSession session;

    @Autowired
    private EmailService emailService;
    @Autowired
    private AdminUserMapper adminUserMapper;

    /*
     * 管理员认证
     * */
    @PostMapping("/YanZheng")
    public Map<String, Object> YanZheng(AdminUser adminUser) {
        //TODO: 管理员认证
        return adminUserService.AdminUserReZheng(adminUser);
    }

    @PostMapping("/postInfo")
    @Transactional
    public Map<String, Object> postInfo(AdminUser adminUser) {
        Map<String, Object> result = new HashMap<>();
        try {
            AdminUser YourAdminUser = adminUserMapper.getAdminUserByUsername(adminUser.getUsername());
            if (YourAdminUser == null) {
                result.put("code", 400);
                result.put("result", false);
                result.put("msg", "用户名不存在");
            } else {
                String adminCode = emailService.generateVerificationCode();
                System.out.println("!验证码：!" + adminCode);
                //发送邮箱验证码
                if (emailService.sendEmail(YourAdminUser.getEmail(), "亲爱的管理员->^v^:" + adminUser.getUsername() + "，您好！" + "您正在进行管理员登录，您的验证码为：" + adminCode + "，请在1分钟内输入。", "罗邓图书管理系统登录验证")) {
                    result.put("code", 200);
                    result.put("result", true);
                    result.put("msg", "验证码已发送至您的邮箱，请注意查收！");
                    // 获取提交的管理员信息保存到session中
                    session.setAttribute("adminRZUser", adminUser);
                    session.setAttribute("adminCode", adminCode);
                    //有效期为1分钟
                    session.setMaxInactiveInterval(60);
                } else {
                    result.put("code", 400);
                    result.put("result", false);
                    result.put("msg", "验证码发送失败，请检查您的邮箱是否正确！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", "服务器内部错误,请联系管理员!");
            result.put("result", false);
            //回滚事务
            throw e;
        }
        return result;
    }

    //TODO: 管理员登录
    @PostMapping("/emailCodeRZ")
    @Transactional
    public Map<String, Object> Login(String eamilCode) {
        Map<String, Object> result = new HashMap<>();
        System.out.println("用户输入的验证码:" + eamilCode);
        try {

            //获取session中的验证码、提交的管理员信息
            AdminUser adminUser = (AdminUser) session.getAttribute("adminRZUser");
            String adminCode = (String) session.getAttribute("adminCode");
            System.out.println("session中的验证码:" + adminCode);
            System.out.println("用户之前提交的信息：" + adminUser);
            if (adminCode == null || adminUser == null) {
                result.put("code", 400);
                result.put("result", false);
                result.put("msg", "验证码已失效，请重新获取");
                return result;
            }
            if (adminCode.equals(eamilCode)) {
                //验证管理员身份
                return adminUserService.AdminUserReZheng(adminUser);
            } else {
                result.put("code", 400);
                result.put("result", false);
                result.put("msg", "验证码错误，请重新输入！");
            }

        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "服务器内部错误,请联系管理员!");
            result.put("result", false);
            //回滚事务
            throw e;
        }
        return result;
    }


    /*
     * 获取登录用户信息
     * */
    @PostMapping("/GetLoginUserInfo")
    public Map<String, Object> GetLoginUserInfo() {
        //TODO: 获取登录用户信息
        return adminUserService.GetRZAdminUser();
    }


}
