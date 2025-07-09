package com.yxz.sboot.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

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
public class LogoutAuthenticationSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", 200);
        result.put("msg", "注销成功！");
        ObjectMapper om = new ObjectMapper();
        String s = om.writeValueAsString(result);
        response.getWriter().write(s);
    }
}
