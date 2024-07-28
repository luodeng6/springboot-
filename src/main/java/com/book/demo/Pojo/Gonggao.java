package com.book.demo.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Gonggao)实体类
 *
 * @author makejava
 * @since 2024-07-23 10:03:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gonggao implements Serializable {
    private static final long serialVersionUID = -63176822127370391L;
    /**
     * 公告编号
     */
    private Integer id;
    /**
     * 公告标题
     */
    private String title;
    /**
     * 公告内容
     */
    private String content;
    /**
     * 发布时间
     */
    private Date time;
    /**
     * 发布者
     */
    private String user;
    /**
     * 重要程度1-5
     */
    private Integer important;
}

