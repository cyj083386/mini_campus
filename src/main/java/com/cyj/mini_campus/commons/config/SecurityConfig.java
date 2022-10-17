package com.dokuny.mini_campus.commons.config;


import com.dokuny.mini_campus.commons.handler.CustomAccessDeniedHandler;
import com.dokuny.mini_campus.commons.handler.MemberAuthenticationFailureHandler;
import com.dokuny.mini_campus.commons.handler.MemberAuthenticationSuccessHandler;
import com.dokuny.mini_campus.member.entity.LoginHistory;
import com.dokuny.mini_campus.member.repository.LoginHistoryRepository;
import com.dokuny.mini_campus.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests()
                .antMatchers(
                        "/member/login",
                        "/member/register/**",
                        "/member/find/**",
                        "/member/reset/**").anonymous()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll();

        http.formLogin()
                .loginPage("/member/login")
                .successHandler(getSuccessHandler())
                .failureHandler(getFailureHandler());



        http.exceptionHandling()
                        .accessDeniedHandler(getAccessDeniedHandler());

        http.logout()
                .logoutUrl("/member/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);


        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/css/**","/js/**","/files/**","/favicon.ico","/res/**");
    }

    @Bean
    public AuthenticationFailureHandler getFailureHandler() {
        return new MemberAuthenticationFailureHandler();
    }

    @Bean
    public AuthenticationSuccessHandler getSuccessHandler() {
        return new MemberAuthenticationSuccessHandler();
    }


    @Bean
    public AccessDeniedHandler getAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
