package com.book.demo.Controller;

import com.book.demo.Mapper.GonggaoMapper;
import com.book.demo.Pojo.Gonggao;
import com.book.demo.Service.GonggaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * (Gonggao)表控制层
 *
 * @author makejava
 * @since 2024-07-23 10:03:24
 */
@RestController
@RequestMapping("/gonggao")
public class GonggaoController {

    @Autowired
    private GonggaoMapper gonggaoMapper;

    /**
     * getGonggaoById方法用于根据id查询公告信息
     *
     * @param id 公告id
     *           返回值类型：Map<String,Object>
     *           返回值说明：
     *           code：状态码，200为成功，500为失败
     *           msg：返回信息，成功为success，失败为具体失败原因
     *           result：是否成功，true为成功，false为失败
     */
    @GetMapping("/getGonggaoById")
    public Map<String, Object> getGonggaoById(@RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Gonggao gonggao = gonggaoMapper.getGonggaoById(id);
            if (gonggao == null) {
                map.put("code", 200);
                map.put("msg", "不存在此公告!");
                map.put("result", false);
                map.put("data", null);
            } else {
                map.put("code", 200);
                map.put("msg", "true");
                map.put("result", true);
                map.put("data", gonggao);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 500);
            map.put("result", false);
            map.put("msg", "公告获取失败:" + e.getMessage());
            map.put("data", null);
        }
        return map;
    }



/**
 * 通过获取前4条公告信息（最近的5条）
 *
 * @return 4条数据
 */
@GetMapping("/getgonggaotop4")
public Map<String, Object> getgonggaotop4() {
    Map<String, Object> map = new HashMap<>();
    try {
        map.put("code", 200);
        map.put("msg", "success");
        map.put("result", true);
        map.put("data", gonggaoMapper.getGonggaotop4());
    } catch (Exception e) {
        e.printStackTrace();
        map.put("code", 500);
        map.put("result", false);
        map.put("msg", "公告获取失败:" + e.getMessage());
        map.put("data", null);
    }
    return map;
}

/**
 * 新增数据
 *
 * @param gonggao 实体
 * @return 新增结果
 * <p>
 * 编辑数据
 * @param gonggao 实体
 * @return 编辑结果
 * <p>
 * 删除数据
 * @param id 主键
 * @return 删除是否成功
 */


/**
 * 编辑数据
 *
 * @param gonggao 实体
 * @return 编辑结果
 */


/**
 * 删除数据
 *
 * @param id 主键
 * @return 删除是否成功
 */


}

