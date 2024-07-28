package com.book.demo.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Wenda)实体类
 *
 * @author makejava
 * @since 2024-07-23 22:05:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wenda implements Serializable {
    private static final long serialVersionUID = 638048743564783553L;
/**
     * 问答编号
     */
    private Integer id;
/**
     * 问题内容
     */
    private String question;
/**
     * 审核后回复的内容
     */
    private String answer;
/**
     * 回复的用户，一般是管理员
     */
    private String user;
/**
     * 是否已经回复，默认为0即未回复，已回复则为1
     */
    private Integer isreply;
/**
     * 问答创建时间，即用户发起问答请求的,默认为插入时间即CURRENT_TIMESTAMP()
     */
    private Date createTime;
/**
     * 回答的时间
     */
    private Date replytime;
/**
     * 此问题的质量，由管理员决定1-5
     */
    private Integer quality;
    /**
     * 问题详细描述
     */
    private String questiondetail;

}

