package com.book.demo.Service.ServiceIMP;

import com.book.demo.Mapper.UserMapper;
import com.book.demo.Pojo.User;
import com.book.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceIMP implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(String username, String password) {
        return userMapper.UserLogin(username, password) != null;
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @param email
     * @param name
     * @param Sex
     * @return
     */
    @Override
    public boolean register(String username, String password, String email, String name, String Sex) {
        return userMapper.UserRegister(new User(username, password, email, name, Sex)) > 1;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public boolean register(User user) {
        return userMapper.UserRegister(user) > 0;
    }

    /**
     * @return
     */
    @Override
    public String generateRandomCode(int length) {
        char[] charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random random = new SecureRandom();
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            code.append(charset[random.nextInt(charset.length)]);
        }
        return code.toString();
    }

    /*分页查询*/

    public Map<String, Object> getUserList(int pageNum, int pageSize) {
        Map<String, Object> result = new HashMap<>();
        if (pageNum <= 0 || pageSize <= 0) {
            //默认查询第一页，每页10条数据
            pageNum = 1;
            pageSize = 10;
        }
        try {
            List<User> users = userMapper.getUsersByPage(pageSize, pageNum);
            // 隐藏密码
            for (User user : users){
                user.setPassword("******");
            }
            result.put("code", 200);
            result.put("msg", "查询成功");
            result.put("result", true);
            result.put("data", users);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "后台错误");
            result.put("result", false);
            result.put("data", null);
            e.printStackTrace();
        }
        return result;
    }


    @Value("${file.upload-dir}")
    private String uploadDir;


    @Autowired
    private HttpSession session;

    /**
     * 用户头像修改
     *
     * @param file
     * @return Map
     */
    @Override
    public Map<String, Object> updateAvatar(MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        // 从session中获取用户信息
        User user = (User) session.getAttribute("user");
        if (user == null) {
            result.put("code", 401);
            result.put("msg", "没有权限");
            result.put("result", false);
        } else {
            if (file.isEmpty()) {
                result.put("code", 200);
                result.put("msg", "文件为空");
                result.put("result", false);
            } else {
                try {
                    // 获取文件名，并去掉非法字符
                    String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
                    String sanitizedFileName = originalFileName.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
                    String timestampedFileName = Instant.now().getEpochSecond() + "_" + sanitizedFileName;

                    // 打印文件大小和文件名
                    System.out.println("File Name: " + originalFileName);
                    System.out.println("File Size: " + file.getSize());
                    System.out.println("File Style: " + file.getContentType());

                    // 保存文件到指定目录
                    Path targetLocation = Paths.get(uploadDir).toAbsolutePath().normalize().resolve(timestampedFileName);
                    Files.createDirectories(targetLocation.getParent());
                    Files.copy(file.getInputStream(), targetLocation);

                    // 返回文件访问路径
                    String fileDownloadUri = "/upload/" + timestampedFileName;
                    // 更新用户头像
                    user.setProfile(fileDownloadUri);
                    userMapper.updateProfile(user);

                    result.put("code", 200);
                    result.put("msg", "上传成功");
                    result.put("result", true);
                    result.put("profileUrl", fileDownloadUri);
                    result.put("username", user.getUsername());

                } catch (IOException ex) {
                    ex.printStackTrace();
                    result.put("code", 500);
                    result.put("msg", ex.getMessage());
                    result.put("result", false);
                }
            }
        }
        return result;
    }


    /*
       忘记密码
       @param User 要添加的用户（此时头像为null，create_time为null,id为null）
       @param file 头像文件
       @return 操作结果Map
   */
    @Override
    @Transactional
    public Map<String, Object> addWanZhengUser(User user, MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        //上传头像
        if (file == null) {
            try {
                user.setProfile("dataimg/%E6%9C%AA%E6%9D%A5%E7%9A%84%E5%9D%90%E6%A0%87%E5%85%A8%E7%90%83%E5%8C%96%E6%97%B6%E4%BB%A3%E7%9A%84%E4%B8%AD%E5%9B%BD%E7%A7%91%E5%B9%BB%E8%AE%BA%E9%9B%86%E5%BE%AE%E5%85%89.%E9%9D%92%E5%B9%B4%E6%89%B9%E8%AF%84%E5%AE%B6%E9%9B%86%E4%B8%9B(%E7%AC%AC2%E8%BE%91).jpg");
                //头像为空所以不上传直接插入用户
                if (userMapper.addWanZhengUser(user) > 0) {
                    result.put("code", 200);
                    result.put("msg", "添加成功");
                    result.put("result", true);
                    user.setPassword("******");
                    result.put("data", user);
                    result.put("xxx", "no profile file");
                } else {
                    result.put("code", 200);
                    result.put("msg", "添加失败");
                    result.put("result", false);
                    result.put("data", null);
                    result.put("xxx", "no profile file");
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.put("code", 500);
                result.put("xxx", "no profile file");
                result.put("msg", e.getMessage());
                result.put("result", false);
            }
        } else {
            //上传头像
            try {
                // 获取文件名，并去掉非法字符
                String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
                String sanitizedFileName = originalFileName.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
                String timestampedFileName = Instant.now().getEpochSecond() + "_" + sanitizedFileName;

                // 打印文件大小和文件名
                System.out.println("File Name: " + originalFileName);
                System.out.println("File Size: " + file.getSize());
                System.out.println("File Style: " + file.getContentType());

                // 保存文件到指定目录
                Path targetLocation = Paths.get(uploadDir).toAbsolutePath().normalize().resolve(timestampedFileName);
                Files.createDirectories(targetLocation.getParent());
                Files.copy(file.getInputStream(), targetLocation);

                // 返回文件访问路径
                String fileDownloadUri = "/upload/" + timestampedFileName;

                user.setProfile(fileDownloadUri);
                //新增用户
                if (userMapper.addWanZhengUser(user) > 0) {
                    result.put("code", 200);
                    result.put("msg", "添加成功");
                    result.put("result", true);
                    user.setPassword("******");
                    result.put("data", user);
                    result.put("xxx", "have profile file");
                } else {
                    result.put("code", 200);
                    result.put("msg", "添加失败");
                    result.put("result", false);
                    result.put("data", null);
                    result.put("xxx", "have profile file");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                result.put("code", 500);
                result.put("msg", ex.getMessage());
                result.put("result", false);
            }
        }
        return result;
    }

    /*
     * updateWanZhengUserNoProfile 修改用户信息（不修改头像）
     * @param user是用户的新信息
     * @return result 返回操作结果
     * */
    public Map<String, Object> updateWanZhengUserNoProfile(User userWithNewInfoNoProfile) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (userMapper.updateWanZhengUserNoProfile(userWithNewInfoNoProfile) > 0) {
                result.put("code", 200);
                result.put("msg", "修改成功");
                result.put("result", true);
                userWithNewInfoNoProfile.setPassword("******");
                result.put("data", userWithNewInfoNoProfile);
            } else {
                result.put("code", 200);
                result.put("msg", "修改失败");
                result.put("result", false);
                result.put("data", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", e.getMessage());
            result.put("result", false);
        }
        return result;
    }


    /*
     * updateWanZhengUser 修改用户信息（修改头像）
     * @param  userWithNewInfoHaveProfile 要修改的用户信息（包含头像）
     * @param file 头像文件
     * @return
     * */
    public Map<String, Object> updateWanZhengUserHaveProfile(User userWithNewInfoHaveProfile, MultipartFile file) {
        Map<String, Object> result = new HashMap<>();

        //上传头像
        try {
            // 获取文件名，并去掉非法字符
            String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
            String sanitizedFileName = originalFileName.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
            String timestampedFileName = Instant.now().getEpochSecond() + "_" + sanitizedFileName;

            // 打印文件大小和文件名
            System.out.println("File Name: " + originalFileName);
            System.out.println("File Size: " + file.getSize());
            System.out.println("File Style: " + file.getContentType());

            // 保存文件到指定目录
            Path targetLocation = Paths.get(uploadDir).toAbsolutePath().normalize().resolve(timestampedFileName);
            Files.createDirectories(targetLocation.getParent());
            Files.copy(file.getInputStream(), targetLocation);

            // 返回文件访问路径
            String fileDownloadUri = "/upload/" + timestampedFileName;

            userWithNewInfoHaveProfile.setProfile(fileDownloadUri);
            //新增用户
            if (userMapper.updateWanZhengUserHaveProfile(userWithNewInfoHaveProfile) > 0) {
                result.put("code", 200);
                result.put("msg", "添加成功");
                result.put("result", true);
                userWithNewInfoHaveProfile.setPassword("******");
                result.put("data", userWithNewInfoHaveProfile);
                result.put("xxx", "have profile file");
            } else {
                result.put("code", 200);
                result.put("msg", "添加失败");
                result.put("result", false);
                result.put("data", null);
                result.put("xxx", "have profile file");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            result.put("code", 500);
            result.put("msg", ex.getMessage());
            result.put("result", false);
            result.put("xxx", "have profile file");
        }

        return result;
    }


}