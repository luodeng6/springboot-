package com.book.demo.Service.ServiceIMP;

import com.book.demo.Mapper.WendaMapper;
import com.book.demo.Service.WendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WendaServiceIMP implements WendaService {

    @Autowired
    private WendaMapper wendaMapper;

    /**
     * 获取所有问答，区分是否回复
     *
     * @return
     */
    @Override
    public Map<String, Object> getAllWendaIsReplyAndNotReply() {
        Map<String, Object> Result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        try {
            data.put("isReply", wendaMapper.getAllReplyWenda());
            data.put("notReply", wendaMapper.getAllNoReplyWenda());
            Result.put("code", 200);
            Result.put("result", true);
            Result.put("msg", "获取问答成功");
            Result.put("data", data);

        } catch (Exception e) {
            e.printStackTrace();
            Result.put("code", 500);
            Result.put("msg", "获取问答失败,后台错误");
            Result.put("result", false);
        }
        return Result;
    }
}
