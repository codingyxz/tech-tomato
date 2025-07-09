package com.yxz.sboot.security.config;

import com.yxz.security.handler.LoginAuthenticationFailureHandler;
import com.yxz.security.handler.LoginAuthenticationSuccessHandler;
import com.yxz.security.handler.LogoutAuthenticationSuccessHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

/**
 * @Description TODO
 * @Date 2025-06-07
 * @Created by Yolo
 */

//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigKapt extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        InMemoryUserDetailsManager users = new InMemoryUserDetailsManager();
//        users.createUser(User.withUsername("root").password("root").roles("admin").build());
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .permitAll()
//                .and()
//                .userDetailsService(users)
//                .csrf().disable();


        http.authorizeRequests()
                .antMatchers("/vc.jpg").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .loginPage("/loginNew.html")
                .loginPage("/MyLogin.html")
                .loginProcessingUrl("/doLogin")
                .defaultSuccessUrl("/index")
                .successHandler(new LoginAuthenticationSuccessHandler())
//                .failureUrl("/loginNew.html")
                .failureHandler(new LoginAuthenticationFailureHandler())
                .usernameParameter("uname")
                .passwordParameter("passwd")
                .permitAll()
                .and()
                .logout()
//                .logoutUrl("/logout")
                .logoutRequestMatcher(
                        new OrRequestMatcher(
                                new AntPathRequestMatcher("/logout1", "GET"),
                                new AntPathRequestMatcher("/logout2", "POST")
                        )
                )
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutSuccessHandler(new LogoutAuthenticationSuccessHandler())
                .and()
                .csrf().disable();

    }

//    @Bean
//    public UserDetailsService currentUserDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("li4").password("{noop}123456").roles("admin").authorities("a", "b").build());
//        return manager;
//    }
//
//    @Bean
//    public AuthenticationProvider kaptchaAuthenticationProvider() {
//        KaptchaAuthenticationProvider provider = new KaptchaAuthenticationProvider();
//        provider.setUserDetailsService(currentUserDetailsService());
//        return provider;
//    }
//
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return new ProviderManager(kaptchaAuthenticationProvider());
//    }
}
