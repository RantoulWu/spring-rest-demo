package com.mercury.SpringBootRESTDemo.security;

import com.mercury.SpringBootRESTDemo.security.handler.*;
import com.mercury.SpringBootRESTDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
// SpringBootRestDemoApplication 中的 passwordencoder 这里做也一样！
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //let spring security use our UserDetailsService and passwordEncoder
    // when spring implement/login and verify user
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    //注入AuthenticationManagerBuilder 从而让 spring security 可以使用 userService 和 passwordEncoder
    public void setup(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Autowired
    AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

    @Autowired
    AuthenticationFailureHandlerImpl authenticationFailureHandler;
    @Autowired
    AuthenticationEntryPointImpl authenticationEntryPoint;
    @Autowired
    AccessDeniedHandlerImpl accessDeniedHandler;
    @Autowired
    LogoutSuccessHandlerImpl logoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // let spring security implement  POST/login for use to authenticate user
        // by default , success login will redirect user to "/, fail will return to login page
        http.formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);
        // authorization
        //1. declarative approach
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/products").permitAll()
//                .antMatchers(HttpMethod.GET,"/samples").authenticated()
//                .antMatchers(HttpMethod.GET,"/orders").hasAnyAuthority("ROLE_ADMIN");

        // exception handling
        // visitor access / samples -> unauthenticated user access authenticated resource
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        // user access /orders ->  user access resource which are permitted
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        //logout: remove logged in user from session
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler);

        //cors : cross origin resource sharing
        http.cors();

    }

    /* cors support -- 后期frontend讲
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
//      configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.addAllowedOrigin("*"); // You should only set trusted site here. e.g. http://localhost:8081 means only this site can access.
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"));
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
