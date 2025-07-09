package com.yxz.sboot.security.filter;

import com.yxz.security.jwt.TokenExtractor;
import com.yxz.security.model.JWTAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description TODO
 * @Date 2025-06-13
 * @Created by Yolo
 */
public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationFailureHandler failureHandler;
    private TokenExtractor tokenExtractor;

    public JWTAuthenticationFilter(AuthenticationFailureHandler failureHandler,
                                   TokenExtractor tokenExtractor,
                                   RequestMatcher requestMatcher) {
        super(requestMatcher);

        this.failureHandler = failureHandler;
        this.tokenExtractor = tokenExtractor;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        JWTAuthenticationToken authentication = new JWTAuthenticationToken(tokenExtractor.extract(request));
        return this.getAuthenticationManager().authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);

        chain.doFilter(request, response);
    }

}
