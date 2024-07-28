package com.book.demo.Service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface BookServeice {
    //借书
     Map<String, Object> BookBorrow(int bookid, String username);


}
