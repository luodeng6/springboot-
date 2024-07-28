package com.book.demo.Mapper;

import com.book.demo.Pojo.LiuyanWithUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LiuyanWithUserMapper {


    // 根据公告id查询留言及用户信息->获取留言数据项
    @Select("SELECT liuyan.*, user.* FROM liuyan JOIN `user` ON liuyan.user = user.username WHERE liuyan.gonggaoid = #{id}")
   List<LiuyanWithUser>  getLiuyanWithUserById(int id);

}
