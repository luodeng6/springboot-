package com.book.demo.Service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface WendaService {

    // 获取所有问答，区分是否回复
    Map<String, Object> getAllWendaIsReplyAndNotReply();

}
