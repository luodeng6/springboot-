package com.book.demo.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Liuyan)实体类
 *
 * @author makejava
 * @since 2024-07-24 18:39:11
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Liuyan implements Serializable {
    private static final long serialVersionUID = -57836907143789076L;
    /**
     * 留言编号
     */
    private Integer id;
    /**
     * 发布留言的用户
     */
    private String user;
    /**
     * 留下的留言
     */
    private String content;
    /**
     * 公告id，指出是哪个公告
     */
    private Integer gonggaoid;
    /**
     * 留言时间，默认是插入时间
     */
    private Date time;
}

