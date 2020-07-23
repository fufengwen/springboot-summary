package com.ffw.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 视图控制器
 * @Author fufengwen
 * @Time 2020/6/30 10:07
 */
@Controller
public class PageController {
    @RequestMapping("/visitor/a")
    @ResponseBody
    public String a(){
        return "visitor";
    }
    @RequestMapping("/admin/a")
    @ResponseBody
    public String b(){
        return "admin";
    }
    @RequestMapping("/user/a")
    @ResponseBody
    public String c(){
        return "user";
    }
}
