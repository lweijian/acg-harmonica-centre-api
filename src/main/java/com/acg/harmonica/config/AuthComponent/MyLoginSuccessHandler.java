package com.acg.harmonica.config.AuthComponent;

import com.acg.harmonica.entity.CentreUser;
import com.acg.harmonica.entity.UserInfo;
import com.acg.harmonica.service.impl.CentreUserServiceImpl;
import com.acg.harmonica.service.impl.UserInfoServiceImpl;
import com.acg.harmonica.utils.RedisUtil;
import com.acg.harmonica.utils.ReturnCode;
import com.acg.harmonica.utils.JSONAuthentication;
import com.acg.harmonica.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lweijian
 * 登录成功处理器
 */
@Component
public class MyLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    CentreUserServiceImpl centreUserService;
    @Autowired
    UserInfoServiceImpl userInfoService;
    @Resource
    private RedisUtil redisUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {


        //取得账号信息
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        //拿到用户对象和用户信息
        CentreUser centreUser = (CentreUser) centreUserService.selectByName(username).getRetObj();
        UserInfo userInfo = (UserInfo) userInfoService.selectByName(username).getRetObj();
        // 生成token
        String accessToken = JwtTokenUtil.generateToken(centreUser);

        String refreshToken = JwtTokenUtil.generateToken(centreUser,7);
        //返回token
        httpServletResponse.setHeader("Authorization", accessToken);
        //设置返回的data
        Map<String, Object> map = new HashMap<>();
        map.put("accessToken", accessToken);
        map.put("refreshToken", refreshToken);
        map.put("msg", "登录成功");
        map.put("userInfo", userInfo);
        map.put("code", ReturnCode.LOGIN_OK);
        JSONAuthentication.WriteJSON(httpServletRequest, httpServletResponse, map);

    }
}
