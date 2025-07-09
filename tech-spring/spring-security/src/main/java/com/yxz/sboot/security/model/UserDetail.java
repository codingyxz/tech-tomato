package com.yxz.sboot.security.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @Description TODO
 * @Date 2025-06-13
 * @Created by Yolo
 */

@Getter
@Setter
public class UserDetail extends User {

    private static final long serialVersionUID = 6925643964432451014L;

    private String id;
    private long loginTime;
    private String phone;
    private String email;


    public UserDetail(String id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
