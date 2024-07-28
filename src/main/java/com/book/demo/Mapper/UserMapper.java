package com.book.demo.Mapper;

import com.book.demo.Pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    // 根据id查询用户信息
    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserById(int id);

    // 用户登录
    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User UserLogin(@Param("username") String username, @Param("password") String password);

    // 通过用户名查询用户信息
    @Select("select * from user where username = #{username}")
    User GetUserByUsername(String username);


    // 用户注册 自动映射
    @Insert("INSERT INTO user(name, username, password, email, sex) VALUES (#{name}, #{username}, #{password}, #{email}, #{sex})")
    int UserRegister(User user);

    // 获取最近注册的6个用户用于展示公告
    @Select("select profile,username, name, create_time from user order by create_time desc limit 20;")
    List<User> getSomUsers6();

    //更新用户信息 押金减掉30
    @Update("update  user set balance=balance-30 where id = #{id}")
    int lowUserbalance30(int id);

    // 更新用户头像
    @Update("update `user` set profile=#{profile} where id = #{id}")
    int updateProfile(User user);

    //完整的添加用户
    @Insert("INSERT INTO user(name, username, password, email, sex,profile,balance) VALUES (#{name}, #{username}, #{password}, #{email}, #{sex}, #{profile},#{balance})")
    int addWanZhengUser(User user);

    // 完整的修改用户
    @Update("UPDATE user SET name=#{name}, username=#{username}, password=#{password}, email=#{email}, sex=#{sex}, profile=#{profile}, balance=#{balance} WHERE id = #{id}")
    int updateWanZhengUserHaveProfile(User user);

    // 完整的修改用户，没有头像
    @Update("UPDATE user SET name=#{name}, username=#{username}, password=#{password}, email=#{email}, sex=#{sex}, balance=#{balance} WHERE id = #{id}")
    int updateWanZhengUserNoProfile(User user);

    // 获取性别统计
    @Select("{CALL GetGenderCounts(#{maleCount, mode=OUT, jdbcType=INTEGER}, #{femaleCount, mode=OUT, jdbcType=INTEGER})}")
    @Options(statementType = StatementType.CALLABLE)
    Map<String, Object> callGetGenderCounts(Map<String, Object> params);

    // 获取所有用户
    @Select("select * from user")
    List<User> getAllUser();

    @Select("select count(*) from user")
    int getUserCount();

    //删除用户
    @Delete("delete from user where username = #{username}")
    int deleteUserByUsername(String username);


    /* 公式说明
     页面页码 page： 从 1 开始计数的当前页码。
     每页记录数 size： 每页显示的记录数量。
     起始位置 start： SQL 查询中 OFFSET 的值，表示跳过前 start 条记录。
     公式：

     start = (page - 1) * size*/
    //分页查询用户:方法1
  /*  @Select("SELECT * FROM user LIMIT #{size} OFFSET #{start}")
    List<User> getUserByPage(@Param("start") int start, @Param("size") int size);*/

    // 分页查询用户:方法2
    @Select("{ CALL GetUsersByPage(#{pageSize, mode=IN, jdbcType=INTEGER}, #{pageNum, mode=IN, jdbcType=INTEGER}) }")
    @Options(statementType = StatementType.CALLABLE)
    List<User> getUsersByPage(@Param("pageSize") int pageSize, @Param("pageNum") int pageNum);




}


