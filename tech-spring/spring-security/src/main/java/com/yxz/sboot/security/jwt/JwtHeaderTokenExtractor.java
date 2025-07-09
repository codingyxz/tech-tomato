package com.yxz.sboot.security.jwt;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description TODO
 * @Date 2025-06-16
 * @Created by Yolo
 */

@Component
public class JwtHeaderTokenExtractor implements TokenExtractor {


    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    public String extract(HttpServletRequest request) {

        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.isEmpty(authorizationHeader)) {
            throw new AuthenticationServiceException("Authorization header cannot be blank");
        }

        return authorizationHeader.replace(TOKEN_PREFIX, "");
    }
}
