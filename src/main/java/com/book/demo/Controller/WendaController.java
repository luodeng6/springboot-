package com.book.demo.Controller;

import com.book.demo.Mapper.UserMapper;
import com.book.demo.Mapper.WendaMapper;
import com.book.demo.Service.WendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * (Wenda)表控制层
 *
 * @author makejava
 * @since 2024-07-23 22:07:13
 */
@RestController
@RequestMapping("/wenda")
public class WendaController {
    @Autowired
    private WendaMapper wendaMapper;
    @Autowired
    private WendaService wendaService;

    /*
     * 获取未回复和回复的问答
     * */
    @GetMapping("/getNotReplyAndReplyWenda")

    public Map<String, Object> getNotReplyAndReplyWenda() {
        Map<String, Object> result = new HashMap<>();
        try {

            Map<String, Object> dataMap = wendaService.getAllWendaIsReplyAndNotReply();
            if ((boolean) dataMap.get("result")) {
                result.put("code", 200);
                result.put("msg", "获取问答成功!");
                result.put("result", true);
                result.put("data", dataMap);
            } else {
                return dataMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", e.getMessage());
            result.put("result", false);

        }
        return result;
    }

/**
 * 分页查询
 *
 * @param wenda 筛选条件
 * @param pageRequest      分页对象
 * @return 查询结果
 */

/**
 * 通过主键查询单条数据
 *
 * @param id 主键
 * @return 单条数据
 */


    /**
     * 用户新增问答
     *
     * @param question 问题标题
     * @param detail   问题详情
     * @param user     发布的用户
     * @return 新增结果
     */
    @PostMapping("/addwenda")
    public Map<String, Object> addWenda(@RequestParam String question,
                                        @RequestParam String detail,
                                        @RequestParam String user) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (wendaMapper.addWenda(question, detail, user) != 0) {
                result.put("result", true);
                result.put("code", 200);
                result.put("msg", "新增问答成功");
            } else {
                result.put("code", 200);
                result.put("msg", "新增问答失败!");
                result.put("result", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", e.getMessage());
            result.put("result", false);
        }
        return result;
    }


/**
 * 编辑数据
 *
 * @param wenda 实体
 * @return 编辑结果
 */

/**
 * 删除数据
 *
 * @param id 主键
 * @return 删除是否成功
 */

}

