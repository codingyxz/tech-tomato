package com.yxz.sboot.security.service;

import com.yxz.security.model.UserDetail;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Date 2025-06-17
 * @Created by Yolo
 */

@Service
public class UserServiceImpl implements UserDetailsService, InitializingBean {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Map<String, UserDetail> userMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        String password = bCryptPasswordEncoder.encode("123456");
        List<String> permissions = Arrays.asList("a", "b");
        List<GrantedAuthority> authorityList = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        userMap.put("li4", new UserDetail("1", "li4", password, authorityList));

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMap.get(username);
    }
}
