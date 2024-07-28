package com.book.demo.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (AdminUser)实体类
 *
 * @author makejava
 * @since 2024-07-27 16:42:23
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminUser implements Serializable {
    private static final long serialVersionUID = -31789256077572770L;
    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * QQ邮箱
     */
    private String email;
}

