package com.acg.harmonica.config.AuthComponent;

import com.acg.harmonica.service.impl.CentreUserServiceImpl;
import com.acg.harmonica.service.impl.auth.MyUserDetailsServiceImpl;
import com.acg.harmonica.utils.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {


    private String Authorization = "Authorization";

    @Autowired
    CentreUserServiceImpl centreUserService;
    @Autowired
    MyUserDetailsServiceImpl myUserDetailsService;

    @Resource
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ReturnObj ret = new ReturnObj();
        String accessToken = request.getHeader(Authorization);

        if (!StringUtils.isEmpty(accessToken)) {
            try {
                String username = JwtTokenUtil.getUsernameFormToken(accessToken);

                if (username != null && !JwtTokenUtil.isTokenExpired(accessToken) && SecurityContextHolder.getContext().getAuthentication() == null) {

                    //根据用户名获取用户对象
                    UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
                    if (userDetails != null) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        //设置为已登录
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        JSONAuthentication.WriteJSON(request, response, ReturnCode.NOT_FIND_USER);
                    }
                }
            } catch (ExpiredJwtException e) {
                Map<String, Object> map = new HashMap<>(2);
                map.put("msg", e.getMessage());
                map.put("code", ReturnCode.OUT_TIME_TOKEN);
                e.printStackTrace();
                JSONAuthentication.WriteJSON(request, response, map);
            } catch (Exception e) {
                Map<String, Object> map = new HashMap<>(2);
                map.put("msg", e.getMessage());
                map.put("code", ReturnCode.ERROR_TOKEN);
                e.printStackTrace();
                JSONAuthentication.WriteJSON(request, response, map);
            }

        }
        filterChain.doFilter(request, response);
    }

}
