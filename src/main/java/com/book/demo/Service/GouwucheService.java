package com.book.demo.Service;


import com.book.demo.Pojo.Gouwuche;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * (Gouwuche)表服务接口
 *
 * @author makejava
 * @since 2024-07-23 10:06:58
 */
public interface GouwucheService {


    /**
     * 通过主键删除数据
     * @param gouwuche 实例对象
     * @return 是否成功
     */
    Map<String, Object> deleteGouwuche(Gouwuche gouwuche);

    /* *
      * 添加购物车逻辑
      */
    boolean addGouwuche(Gouwuche gouwuche);

}
