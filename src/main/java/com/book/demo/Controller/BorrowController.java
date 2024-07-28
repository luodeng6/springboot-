package com.book.demo.Controller;
import com.book.demo.Service.BookServeice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    private BookServeice bookServeice;

    //用户借书

    @PostMapping("/borrowBook")
    public Map<String, Object> borrowBook(@RequestParam("bookId") int bookId,
                                          @RequestParam("user") String user) {
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> result = bookServeice.BookBorrow(bookId, user);
            if ((boolean) result.get("result")) {
                response.put("result", "success");
                response.put("message", "借书成功");
                response.put("result", true);
                response.put("code", 200);
            } else {
                response.put("result", false);
                response.put("message", result.get("msg"));
                response.put("code", 200);
            }
        } catch (Exception e) {
            response.put("result", false);
            response.put("code", 500);
            response.put("message", "后台错误：" + e.getMessage());
        }
        return response;
    }


}
