package com.book.demo.Service;

import com.book.demo.Pojo.AdminUser;

import java.util.Map;

public interface AdminUserService {


    /**
     * 管理员认证
     *
     * @param adminUser
     * @return
     */

    Map<String, Object> AdminUserReZheng(AdminUser adminUser);


    Map<String, Object> GetRZAdminUser();

}
