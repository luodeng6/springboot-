package com.book.demo.Pojo;

import lombok.*;

import java.util.Date;

// 留言类，包含用户信息，继承自留言类，包含两个表的信息，这个表并不存在哦！ 这是一个完整的留言项数据类
//@Data 只会生成当前类中字段的 getter 和 setter 方法，而不会自动生成父类字段的 getter 和 setter 方法。
@Data
@AllArgsConstructor
@NoArgsConstructor
/*@Getter
@Setter*/
public class LiuyanWithUser extends Liuyan {
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
}
