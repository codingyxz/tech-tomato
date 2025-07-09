package com.yxz.sboot.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @Description TODO
 * @Date 2025-06-16
 * @Created by Yolo
 */
public class JWTAuthenticationToken extends AbstractAuthenticationToken {

    private String jwtToken;
    private UserDetail user;

    public JWTAuthenticationToken(String jwtToken) {
        super(null);
        this.jwtToken = jwtToken;
        this.setAuthenticated(false);
    }

    public JWTAuthenticationToken(UserDetail user) {
        super(user.getAuthorities());
        this.setAuthenticated(true);
        this.user = user;
    }

    @Override
    public Object getCredentials() {
        return jwtToken;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
