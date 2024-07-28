package com.book.demo.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Borrow)实体类
 *
 * @author makejava
 * @since 2024-07-25 23:02:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borrow implements Serializable {
    private static final long serialVersionUID = -67358002851712715L;
    /**
     * 借书id
     */
    private Integer id;
    /**
     * 书的id
     */
    private Integer bookid;
    /**
     * 借书的用户
     */
    private String user;
    /**
     * 借书时间，默认是当前
     */
    private Date time;
    /**
     * 在读，超时，已还
     */
    private String status;
    /**
     * 应还日期，默认是可以借书1个月，用触发器来插入
     */
    private Date duedate;
}

