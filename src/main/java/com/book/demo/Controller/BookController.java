package com.book.demo.Controller;
import com.book.demo.Mapper.AdminUserMapper;
import com.book.demo.Mapper.BookMapper;
import com.book.demo.Pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookMapper bookMapper;


    // 根据ID获取书籍信息
    @GetMapping("/getbookbyid")
    public Map<String, Object> getbookbyid(@RequestParam int id) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (bookMapper.getBookById(id) == null) {
                map.put("data", null);
                map.put("code", 200);
                map.put("msg", "指定书籍不存在");
                map.put("result", false);
            } else {
                Book book = bookMapper.getBookById(id);
                map.put("data", book);
                map.put("code", 200);
                map.put("msg", "指定书籍信息获取成功");
                map.put("result", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("data", null);
            map.put("code", 500);
            map.put("msg", "服务器内部错误");
            map.put("result", false);
        }
        return map;
    }


    // 获取所有书籍信息
    @GetMapping("/getAllbooks")
    public Map<String, Object> getAllbooks() {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Book> books = bookMapper.getAllBooks();

            map.put("data", books);
            map.put("count", books.size());
            map.put("code", 200);
            map.put("msg", "所有书籍信息获取成功");
            map.put("result", true);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("data", null);
            map.put("count", 0);
            map.put("code", 500);
            map.put("msg", "服务器内部错误");
            map.put("result", false);
        }
        return map;
    }
}
