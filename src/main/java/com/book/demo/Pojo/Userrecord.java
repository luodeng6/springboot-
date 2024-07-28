package com.book.demo.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Userrecord)实体类
 *
 * @author makejava
 * @since 2024-07-27 20:50:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Userrecord implements Serializable {
    private static final long serialVersionUID = -92383803890646458L;
    /**
     * 记录编号
     */
    private Integer id;
    /**
     * 操作的用户名
     */
    private String username;
    /**
     * 操作时间，默认为插入时间
     */
    private Date time;
    /**
     * 动作类型，设置值为 注销或者注册，check约束
     */
    private String action;


}

