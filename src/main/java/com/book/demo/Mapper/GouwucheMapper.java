package com.book.demo.Mapper;

import com.book.demo.Pojo.Gouwuche;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GouwucheMapper {
    //用户添加购物车
    @Insert("insert into  gouwuche(user, bookid, selectnum, totalprice) VALUES( #{user}, #{bookid}, #{selectnum}, #{totalprice});")
    int addGouwuche(Gouwuche gouwuche);

    //用户取消购物车
    @Delete("delete from gouwuche where id=#{id}")
    int deleteGouwuche(Gouwuche gouwuche);

    // 获取用户的购物车信息
    @Select("select * from gouwuche where user=#{username}")
    List<Gouwuche> getGouwuchesByUserName(String username);

    // 检查该用户购物车是否存在此书籍 如果查不到，则返回null。。。。要映射的话参数名要和pojo中的一致，否则会报错
    @Select("select * from gouwuche where user=#{user} and bookid=#{bookid}")
    Gouwuche checkGouwuche(Gouwuche gouwuche);

    // 更新购物车信息
    @Update("update gouwuche set selectnum=#{selectnum}, totalprice=#{totalprice} where id=#{id}")
    int updateGouwuche(Gouwuche gouwuche);

    // 获取购物车信息
    @Select("select * from gouwuche where id=#{id}")
    Gouwuche getGouwucheById(int id);
}
