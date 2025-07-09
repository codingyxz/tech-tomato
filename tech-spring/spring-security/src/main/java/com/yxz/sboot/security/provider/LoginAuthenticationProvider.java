package com.yxz.sboot.security.provider;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Date 2025-06-16
 * @Created by Yolo
 */

@Component
public class LoginAuthenticationProvider extends DaoAuthenticationProvider {

    public LoginAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        super();
        setUserDetailsService(userDetailsService);
        setPasswordEncoder(passwordEncoder);
    }


}
