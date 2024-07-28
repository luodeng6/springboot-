package com.book.demo.Controller;

import com.book.demo.Mapper.UserMapper;
import com.book.demo.Pojo.User;
import com.book.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Qualifier("conversionService")


    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        if (userService.login(username, password)) {
            User loginUser = userMapper.GetUserByUsername(username);
            result.put("code", 200);
            result.put("msg", "登录成功");
            result.put("username", username);
            result.put("name", loginUser.getName());
            result.put("email", loginUser.getEmail());
            result.put("sex", loginUser.getSex());
            result.put("result", true);
            // 创建2小时的Session->用户信息
            session.setAttribute("user", loginUser);
            session.setMaxInactiveInterval(2 * 60 * 60); // 2小时
        } else {
            result.put("code", 401);
            result.put("result", false);
            result.put("msg", "用户名或密码错误");
        }
        return result;
    }


    @Autowired
    private HttpSession session;


    //产生code Session的接口
    @PostMapping("sendEmailCode")
    public Map<String, Object> sendEmailCode(User user) {
        Map<String, Object> response = new HashMap<>();
        //随机4个字符
        // 生成随机4字符代码
        String code = userService.generateRandomCode(4);
        // 将注册的用户信息保存到Session中
        session.setAttribute("registerUser", user);
        session.setAttribute("code", code);//保存验证码到Session中
        System.out.println("验证码：" + code);
        System.out.println("用户:" + user);
        // 发送电子邮件
        try {
            /* sendEmail("recipient@example.com", "Your Verification Code", "Your verification code is: " + code);*/
            response.put("status", "success");
            response.put("code", 200);
            response.put("email", user.getEmail());
            response.put("result", true);
            response.put("message", "验证码 已发送至您的邮箱，请查收。");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("code", 500);
            response.put("result", false);
            response.put("message", "验证码发送失败，请稍后再试。");
        }
        return response;
    }


    @GetMapping("/getLoginUser")
    public Map<String, Object> getLoginUser(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        // 从Session中获取登录用户
        User user = (User) session.getAttribute("user");
        System.out.println("用户:" + user);
        if (user == null) {
            result.put("code", 401);
            result.put("msg", "用户未登录");
            result.put("result", false);
        } else {
            user.setPassword("******");
            result.put("code", 200);
            result.put("msg", "亲爱的" + user.getUsername() + "，欢迎回来！");
            result.put("result", true);
            result.put("userData", user);

            // 获取Session剩余时间（秒）
            int maxInactiveInterval = session.getMaxInactiveInterval();
            long lastAccessedTime = session.getLastAccessedTime();
            long currentTime = System.currentTimeMillis();
            long remainingTime = maxInactiveInterval - (currentTime - lastAccessedTime) / 1000;
            result.put("sessionRemainingTime", remainingTime);
        }
        return result;
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpSession session) {
        Map<String, Object> result = new HashMap<>();

        try {
            // 使当前会话失效
            session.invalidate();
            result.put("code", 200);
            result.put("msg", "注销成功");
            result.put("result", true);
            // 返回响应
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "注销失败");
            result.put("result", false);
            // 可以记录异常日志
            // Logger.error("Logout failed", e);
            // 返回错误响应
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @GetMapping("/getUserInfoByid")
    public User getUserInfoByid(@RequestParam int id) {
        return userMapper.getUserById(id);
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestParam String code) {

        System.out.println("提交的验证码为：");
        System.out.println(code);
        Map<String, Object> response = new HashMap<>();
        try {
            User user = (User) session.getAttribute("registerUser");
            // 验证验证码
            String sessionCode = (String) session.getAttribute("code");
            System.out.println(user);

            if (sessionCode == null) {
                response.put("status", "error");
                response.put("code", 401);
                response.put("result", false);
                response.put("message", "验证码已过期，请重新获取");
            } else {
                if (code.equals(sessionCode)) {
                    //注册
                    if (userService.register(user)) {
                        response.put("status", "success");
                        response.put("code", 200);
                        response.put("result", true);
                        response.put("message", "注册成功");
                    } else {
                        response.put("status", "error");
                        response.put("code", 200);
                        response.put("result", false);
                        response.put("message", "注册失败");
                    }
                } else {
                    response.put("status", "error");
                    response.put("code", 200);
                    response.put("result", false);
                    response.put("message", "验证码错误");
                }
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("code", 500);
            response.put("result", false);
            response.put("message", "验证失败，后台错误");
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/getSomeUser6")
    public Map<String, Object> getSomeUser6() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<User> userData = userMapper.getSomUsers6();
            result.put("code", 200);
            result.put("msg", "获取成功");
            result.put("result", true);
            result.put("data", userData);
            result.put("count", userData.size());
            return result;
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "获取失败");
            result.put("result", false);
            result.put("data", null);
            result.put("count", 0);
            // 可以记录异常日志
            e.printStackTrace();
        }
        return result;
    }


    // 用户上传头像
    @PostMapping("/updateAvatar")
    @ResponseBody
    public Map<String, Object> handleFileUpload(@RequestParam("file") MultipartFile file) {
        return userService.updateAvatar(file);
    }


    @GetMapping("/GetGenderCounts")
    public Map<String, Object> getGenderCounts() {
        Map<String, Object> result = new HashMap<>();
        try {

            Map<String, Object> params = new HashMap<>();
            params.put("maleCount", null);
            params.put("femaleCount", null);
            userMapper.callGetGenderCounts(params);

            result.put("code", 200);
            result.put("msg", "获取成功");
            result.put("data", params);
            result.put("result", true);

        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "获取失败");
            result.put("result", false);
        }
        return result;
    }

    @PostMapping("/getAllUser")
    public Map<String, Object> getAllUser() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<User> users_list = userMapper.getAllUser();

            for (User user : users_list) {
                user.setPassword("******");
            }
            response.put("code", 200);
            response.put("msg", "获取成功");
            response.put("result", true);
            response.put("data", users_list);
            response.put("count", users_list.size());
        } catch (Exception e) {
            response.put("code", 500);
            response.put("msg", "获取失败");
            response.put("result", false);
            response.put("data", null);
            response.put("count", 0);
            e.printStackTrace();
        }
        return response;
    }

    //添加一个完整的user
    @PostMapping("/addWanZhengUser")
    public Map<String, Object> addWanZhengUser(User user, @RequestParam(value = "file", required = false) MultipartFile file) {
        System.out.println("添加一个完整的user");
        System.out.println(user);
        System.out.println(file);
        return userService.addWanZhengUser(user, file);
    }

    //修改一个完整的user
    @PostMapping("/updateWanZhengUserNoProfile")
    public Map<String, Object> updateWanZhengUser(User user) {
        System.out.println("修改一个完整的user,不带头像修改");
        System.out.println(user);
        return userService.updateWanZhengUserNoProfile(user);
    }


    //修改一个完整的user2
    @PostMapping("/updateWanZhengUserHaveProfile")
    public Map<String, Object> updateWanZhengUserHaveProfile(User newUserInfoWithProfile, @RequestParam(value = "file", required = true) MultipartFile file) {
        System.out.println("修改一个完整的user，带头像修改");
        System.out.println(newUserInfoWithProfile);
        return userService.updateWanZhengUserHaveProfile(newUserInfoWithProfile, file);
    }

    @PostMapping("/deleteUser")
    public Map<String, Object> deleteUser(@RequestParam("username") String username) {

        Map<String, Object> response = new HashMap<>();
        try {
            if (userMapper.deleteUserByUsername(username) > 0) {
                response.put("status", "success");
                response.put("code", 200);
                response.put("result", true);
                response.put("msg", "删除成功");
            } else {
                response.put("status", "error");
                response.put("code", 200);
                response.put("result", false);
                response.put("msg", "删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "error");
            response.put("code", 500);
            response.put("result", false);
            response.put("msg", "删除失败");
        }
        return response;
    }


    @GetMapping("/getUserPage")
    public Map<String, Object> getUserPage(@RequestParam(value = "pagenum", required = false, defaultValue = "1") int page,
                                           @RequestParam(value = "pagesize", required = false, defaultValue = "10") int pageSize) {
        return userService.getUserList(page, pageSize);
    }

    @GetMapping("/getUserCount")
    public Map<String, Object> getUserCount() {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("code", 200);
            result.put("msg", "获取成功");
            result.put("result", true);
            result.put("userCount", userMapper.getUserCount());
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "获取失败");
            result.put("result", false);
            result.put("userCount", 0);
            e.printStackTrace();
        }
        return result;
    }

}