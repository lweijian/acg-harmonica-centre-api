package com.acg.harmonica.config;


import com.acg.harmonica.config.AuthComponent.*;
import com.acg.harmonica.service.impl.auth.MyUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;


@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    private final MyLoginSuccessHandler myLoginSuccessHandler;
    private final MyUserDetailsServiceImpl myUserDetailsService;

    private final MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter;

    private final MyLoginFailureHandler myLoginFailureHandler;

    private final MyLogoutHandler myLogoutHandler;
    private final MyLogoutSuccessHandler myLogoutSuccessHandler;
    private final PasswordEncoder passwordEncoder;
    final
    JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter;

    public WebSecurityConfigurer(MyUserDetailsServiceImpl myUserDetailsService, MyLoginSuccessHandler myLoginSuccessHandler, MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter, MyLoginFailureHandler myLoginFailureHandler, MyLogoutHandler myLogoutHandler, MyLogoutSuccessHandler myLogoutSuccessHandler, PasswordEncoder passwordEncoder, JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter) {
        this.myUserDetailsService = myUserDetailsService;
        this.myLoginSuccessHandler = myLoginSuccessHandler;
        this.myUsernamePasswordAuthenticationFilter = myUsernamePasswordAuthenticationFilter;
        this.myLoginFailureHandler = myLoginFailureHandler;
        this.myLogoutHandler = myLogoutHandler;
        this.myLogoutSuccessHandler = myLogoutSuccessHandler;
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthorizationTokenFilter = jwtAuthorizationTokenFilter;
    }


    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_admin > ROLE_user");
        return hierarchy;
    }




    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/img/**");
        web.ignoring().antMatchers("/refresh_token");
        web.ignoring().antMatchers("/test");

    }

    /**
     * ??????????????????????????????AuthenticationManager
     * @return AuthenticationManager
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /**
     * ????????????????????????????????????
     * @param
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        //?????????????????????cors ??????????????????,???Spring security ????????????preflight request???cors ???????????????
        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll().and().cors();;

        //???Security??????????????????HttpSession??????????????????HttpSession?????????SecurityContext
        http.csrf()
                .disable().
                sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().
                headers().
                cacheControl();

        //  ??????
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasRole("user")
                .antMatchers("/centre_user/registered").permitAll()
//                .antMatchers("/test").permitAll()
                .anyRequest().authenticated();



        //??????S
        http.logout()
                .addLogoutHandler(myLogoutHandler)
                .logoutSuccessHandler(myLogoutSuccessHandler);
        //??????????????????????????????
        myUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(myLoginSuccessHandler);
        myUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(myLoginFailureHandler);
        //        ?????????????????????
        http.addFilterAt(myUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        //  token ??????
        http.addFilterBefore(jwtAuthorizationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //        http.exceptionHandling().authenticationEntryPoint();
    }


}
