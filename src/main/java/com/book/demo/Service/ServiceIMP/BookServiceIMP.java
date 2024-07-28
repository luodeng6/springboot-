package com.book.demo.Service.ServiceIMP;

import com.book.demo.Mapper.BookMapper;
import com.book.demo.Mapper.BorrowMapper;
import com.book.demo.Mapper.UserMapper;
import com.book.demo.Pojo.User;
import com.book.demo.Service.BookServeice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookServiceIMP implements BookServeice {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BorrowMapper borrowMapper;

    /**
     * 借书
     *
     * @param bookid
     * @param username
     * @return
     */
    @Override
    // 事务注解
    @Transactional
    public Map<String, Object> BookBorrow(int bookid, String username) {
        //检查用户押金是否足够
        User user = userMapper.GetUserByUsername(username);
        Map<String, Object> resultMap = new HashMap<>();
        //检查用户是否有未还的书
        if (borrowMapper.hasUnreturnedBook(username).size() > 0) {
            resultMap.put("status", false);
            resultMap.put("msg", "有未还的书，请先还书");
        } else {
            if (user.getBalance() < 30) {
                resultMap.put("result", false);
                resultMap.put("msg", "押金不足");
                return resultMap;
            } else {
                try {
                    /*要用事务处理*/
                    //扣除押金
                    userMapper.lowUserbalance30(user.getId());
                    //插入借阅记录
                    borrowMapper.insertBorrow(bookid, user.getUsername());
                    resultMap.put("result", true);
                    resultMap.put("msg", "借阅成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    resultMap.put("result", false);
                    resultMap.put("msg", "修改失败");
                    //回滚事务
                    throw e;
                }
            }
        }
        return resultMap;
    }


}