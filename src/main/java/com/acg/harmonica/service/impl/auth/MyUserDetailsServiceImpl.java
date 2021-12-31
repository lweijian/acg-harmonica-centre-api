package com.acg.harmonica.service.impl.auth;

import com.acg.harmonica.dao.CentreUserDao;
import com.acg.harmonica.entity.CentreUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service("MyUserDetailsServiceImpl")
public class MyUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    CentreUserDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QueryWrapper<CentreUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CentreUser::getUsername, username);
        CentreUser centreUser = dao.selectOne(queryWrapper);
        if (centreUser == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_"+centreUser.getRoleName());
        String password = centreUser.getPassword();
        return new User(username, password, auths);
    }
}
