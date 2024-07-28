package com.book.demo.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Shoucang)实体类
 *
 * @author makejava
 * @since 2024-07-25 23:02:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shoucang implements Serializable {
    private static final long serialVersionUID = 664016692686066882L;
    /**
     * 收藏编号
     */
    private Integer id;
    /**
     * 用户名
     */
    private String user;
    /**
     * 书的id
     */
    private Integer bookid;

    private Date time;

}

