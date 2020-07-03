package cn.com.controller;


import cn.com.service.impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class TsetCroller {

    @Autowired
    private NewsServiceImpl serviceImpl;


    @RequestMapping("test")
    public String test(){
        System.out.println("test.....");
        return "index";
    }
}
