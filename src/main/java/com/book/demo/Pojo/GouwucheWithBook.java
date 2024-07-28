package com.book.demo.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* 数据类，用于保存购物车和对应书籍信息
* */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GouwucheWithBook extends Gouwuche {
  /*  *//**
     * 图书编号
     *//*
    private Integer id;*/
    /**
     * 书名
     */
    private String bookname;
    /**
     * 定价
     */
    private Float price;
    /**
     * 库存量
     */
    private Integer num;
    /**
     * 书籍简介
     */
    private String jianjie;
    /**
     * 借出了多少
     */
    private Integer borrownum;
    /**
     * 图书链接
     */
    private String url;
    /**
     * 图书出版社信息
     */
    private String cbs;
    /**
     * 图书作者
     */
    private String author;
}
