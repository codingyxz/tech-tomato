package com.yxz.sboot.security.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Description TODO
 * @Date 2025-06-10
 * @Created by Yolo
 */

@RestController
public class LoginController {

    @Autowired
    Producer producer;

    @RequestMapping("/vc.jpg")
    public void getVerifyCode(HttpServletResponse resp, HttpSession session) {
        resp.setContentType("image/jpeg");
        String text = producer.createText();
        session.setAttribute("kaptcha", text);
        BufferedImage image = producer.createImage(text);
        try (ServletOutputStream out = resp.getOutputStream()) {
            ImageIO.write(image, "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreAuthorize("hasAuthority('a') or hasAnyAuthority('b')")
    @RequestMapping("/index_a")
    public String indexA() {
        return "loginAB";
    }

    @PreAuthorize("hasAuthority('c')")
    @RequestMapping("/index_c")
    public String indexC() {
        return "loginC";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }


}
