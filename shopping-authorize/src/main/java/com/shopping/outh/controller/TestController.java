package com.shopping.outh.controller;

import api.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {


    @RequestMapping("/test")
    public R test() {
        System.out.println("start");
        return R.success("");
    }

}
