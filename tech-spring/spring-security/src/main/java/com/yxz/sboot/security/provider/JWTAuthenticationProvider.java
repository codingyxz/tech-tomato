package com.yxz.sboot.security.provider;

import com.yxz.security.model.JWTAuthenticationToken;
import com.yxz.security.model.UserDetail;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Date 2025-06-16
 * @Created by Yolo
 */
@Slf4j
@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

    @Value("${jwt.sign.Key:qqwweerr}")
    private String jwtSignKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String accessToken = (String) authentication.getCredentials();
        if (StringUtils.isBlank(accessToken)) {
            throw new BadCredentialsException("token为空");
        }

        try {
            Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(jwtSignKey.getBytes()).parseClaimsJws(accessToken);
            Claims body = jwsClaims.getBody();

            String username = body.getSubject();
            String name = body.get("username", String.class);
            String id = body.get("id", String.class);
            Long loginTime = body.get("loginTime", Long.class);
            List<String> scopes = body.get("scopes", List.class);

            log.info("登录时间：" + loginTime);

            List<GrantedAuthority> authorities = new ArrayList<>();
            if (!CollectionUtils.isEmpty(scopes)) {
                authorities = scopes.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
            }
            UserDetail user = new UserDetail(id, username, "FAKE", authorities);
            return new JWTAuthenticationToken(user);

        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            throw new BadCredentialsException("Invalid JWT token", ex);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
