package com.book.demo.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2024-07-21 22:20:40
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -19792675423504424L;
    /**
     * 用户编号·
     */
    private Integer id;
    /**
     * 用户头像链接
     */
    private String profile;
    /**
     * 姓名信息
     */
    private String name;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户余额
     */
    private Float balance;
    /**
     * QQ邮箱
     */
    private String email;

    private String sex;
    /**
     * 账户创建时间
     */
    private Date create_time;

    // 构造方法: 必填项目
    public User(String name, String username, String password, String email, String sex) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.sex = sex;
    }
}

