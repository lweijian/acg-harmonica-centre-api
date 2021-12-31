package com.acg.harmonica.config.AuthComponent;

import com.acg.harmonica.utils.ReturnCode;
import com.acg.harmonica.utils.JSONAuthentication;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyLoginFailureHandler implements AuthenticationFailureHandler
{
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
      httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
      Map<String, Object> map = new HashMap<>();
      map.put("msg","登录失败");
      map.put("code", ReturnCode.NOT_FIND_USER);
        JSONAuthentication.WriteJSON(httpServletRequest, httpServletResponse, map);
    }
}
