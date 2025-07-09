package com.yxz.sboot.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

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
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> resp = new HashMap<String, Object>();
        resp.put("status", 500);
        resp.put("msg", "登录失败" + exception.getMessage());
        ObjectMapper om = new ObjectMapper();
        String s = om.writeValueAsString(resp);
        response.getWriter().write(s);

    }
}
