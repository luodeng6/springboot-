package com.book.demo.Mapper;

import com.book.demo.Pojo.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminUserMapper {

    //登录验证
   @Select("select * from admin_user where username = #{username} and password = #{password}")
    AdminUser adminUserLogin(AdminUser adminUser);

   // 获取管理员信息

   @Select("select * from admin_user where username = #{username}")
   AdminUser getAdminUserByUsername(String username);
}
