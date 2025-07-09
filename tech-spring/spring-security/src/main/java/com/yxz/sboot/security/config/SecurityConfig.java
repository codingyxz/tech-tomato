package com.yxz.sboot.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxz.security.filter.JWTAuthenticationFilter;
import com.yxz.security.filter.LoginAuthenticationFilter;
import com.yxz.security.handler.AjaxAuthenticationFailureHandler;
import com.yxz.security.handler.LoginAuthenticationSuccessHandler;
import com.yxz.security.jwt.JwtHeaderTokenExtractor;
import com.yxz.security.provider.JWTAuthenticationProvider;
import com.yxz.security.provider.LoginAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @Description TODO
 * @Date 2025-06-16
 * @Created by Yolo
 */

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    private LoginAuthenticationProvider loginAuthenticationProvider;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AjaxAuthenticationFailureHandler failureHandler;
    @Autowired
    private LoginAuthenticationSuccessHandler successHandler;
    @Autowired
    private JwtHeaderTokenExtractor tokenExtractor;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(15);
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("li4").password(bCryptPasswordEncoder().encode("123456")).roles("admin").authorities("a", "b").build());
//        return manager;
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(buildLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildJWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    private JWTAuthenticationFilter buildJWTAuthenticationFilter() throws Exception {
        JWTAuthenticationFilter filter = new JWTAuthenticationFilter(failureHandler, tokenExtractor, new AntPathRequestMatcher("/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    private LoginAuthenticationFilter buildLoginAuthenticationFilter() {
        LoginAuthenticationFilter filter = new LoginAuthenticationFilter(successHandler, failureHandler, objectMapper, "/auth/login");
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        auth.authenticationProvider(jwtAuthenticationProvider);
        auth.authenticationProvider(loginAuthenticationProvider);
    }

}
