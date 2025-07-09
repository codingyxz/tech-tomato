package com.yxz.sboot.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxz.security.jwt.TokenFactory;
import com.yxz.security.model.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Date 2025-06-10
 * @Created by Yolo
 */

@Component
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private TokenFactory tokenFactory;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        UserDetail user = (UserDetail) authentication.getPrincipal();
        user.setLoginTime(System.currentTimeMillis());
        String token = tokenFactory.createAccessToken(user);

        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> resp = new HashMap<String, Object>();
        resp.put("status", 200);
        resp.put("msg", "登录成功");
        resp.put("accessToken", token);
        ObjectMapper om = new ObjectMapper();
        String s = om.writeValueAsString(resp);
        response.getWriter().write(s);
    }
}
