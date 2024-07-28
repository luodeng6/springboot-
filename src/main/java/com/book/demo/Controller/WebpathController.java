package com.book.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebpathController {

    @RequestMapping(value = "/")
    public String webpath() {
        return "login.html";
    }
}

