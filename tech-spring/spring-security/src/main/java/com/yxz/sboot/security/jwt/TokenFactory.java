package com.yxz.sboot.security.jwt;

import com.yxz.security.model.UserDetail;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Date 2025-06-13
 * @Created by Yolo
 */

@Component
public class TokenFactory {

    @Value("${jwt.sign.Key:qqwweerr}")
    private String jwtSignKey;

    @Value("${url.effective.time:20}")
    private long urlEffectiveTime;

    public String createAccessToken(UserDetail user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        claims.put("scopes", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        claims.put("loginTime", user.getLoginTime());

        return Jwts.builder().setSubject(user.getUsername())
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtSignKey.getBytes())
                .compact();
    }

}
