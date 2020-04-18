package com.dy.concurrency.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j(topic = "info")
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
