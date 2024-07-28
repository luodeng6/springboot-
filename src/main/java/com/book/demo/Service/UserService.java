package com.book.demo.Service;

import com.book.demo.Pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


public interface UserService {
    //登录
    boolean login(String username, String password);

    //注册
    boolean register(String username, String password, String email, String name, String Sex);

    boolean register(User user);
    //产生随机验证码
   String  generateRandomCode(int length);

     /*
    用户头像修改
    @param file 头像文件
    @return 修改结果
    **/
    Map<String, Object> updateAvatar(MultipartFile file);

    /*
    忘记密码
    @param User 要添加的用户（此时头像为null，create_time为null,id为null）
    @param file 头像文件
    @return 操作结果Map
    */
    Map<String, Object> addWanZhengUser(User user,MultipartFile file);

    /*
    * updateWanZhengUserNoProfile 修改用户信息（不修改头像）
    * @param
    * @return
    * */
    Map<String, Object> updateWanZhengUserNoProfile(User userWithNewInfoNoProfile);

    /*
    * updateWanZhengUser 修改用户信息（修改头像）
    * @param  userWithNewInfoHaveProfile 要修改的用户信息（包含头像）
    * @param file 头像文件
    * @return
    * */
    Map<String, Object> updateWanZhengUserHaveProfile(User userWithNewInfoHaveProfile,MultipartFile file);


     Map<String, Object> getUserList(int pageNum, int pageSize) ;
}
