package com.yxz.sboot.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Date 2025-06-09
 * @Created by Yolo
 */
@RestController
public class FilterController {

    @RequestMapping("/filter/test")
    public String testFilter() {
        return "niuniu";
    }

}
