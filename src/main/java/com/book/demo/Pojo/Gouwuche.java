package com.book.demo.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Gouwuche)实体类
 *
 * @author makejava
 * @since 2024-07-23 10:03:22
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Gouwuche implements Serializable {
    private static final long serialVersionUID = 437546533935295293L;
    /**
     * 购物车项目编号
     */
    private Integer id;
    /**
     * 是谁的购物车项目
     */
    private String user;
    /**
     * 购物车里的书是那本？图书编号
     */
    private Integer bookid;
    /**
     * 添加时间
     */
    private Date addtime;
    /**
     * 选择的个数
     */
    private Integer selectnum;
    /**
     * 总价
     */
    private Float totalprice;

    // 必填项目的构造函数
    public Gouwuche(String user, Integer bookid, Integer selectnum, Float totalprice) {
        this.user = user;
        this.bookid = bookid;
        this.selectnum = selectnum;
        this.totalprice = totalprice;
    }

    public Gouwuche(String user, Integer bookid) {
        this.user = user;
        this.bookid = bookid;
    }

    // 非必填项目的构造函数
    public Gouwuche(Integer id, String user) {
        this.id = id;
        this.user = user;
    }

    public Gouwuche(Integer id) {
        this.id = id;

    }
}

