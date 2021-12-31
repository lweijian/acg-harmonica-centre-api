package com.acg.harmonica.service.impl;

import com.acg.harmonica.config.AuthComponent.PasswordEncoder;
import com.acg.harmonica.entity.CentreRole;
import com.acg.harmonica.utils.ReturnCode;
import com.acg.harmonica.utils.ReturnObj;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.acg.harmonica.dao.CentreUserDao;
import com.acg.harmonica.entity.CentreUser;
import com.acg.harmonica.service.CentreUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (CentreUser)表服务实现类
 *
 * @author lweijian
 * @since 2021-05-10 01:13:20
 */
@Service("centreUserService")
public class CentreUserServiceImpl extends ServiceImpl<CentreUserDao, CentreUser> implements CentreUserService {
    @Autowired
    UserInfoServiceImpl userInfoService;
    @Autowired
    CentreRoleServiceImpl roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ReturnObj selectByName(String username) {
        ReturnObj ret = new ReturnObj();
        QueryWrapper<CentreUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CentreUser::getUsername, username);
        CentreUser centreUser = getOne(queryWrapper);
        if (centreUser == null) {
            ret.setMsg(ReturnCode.NOT_FIND_USER);
        } else {
            ret.setRetObj(centreUser);
            ret.setMsg(ReturnCode.OK);
        }
        return ret;
    }


    @Override
    public ReturnObj removeByName(String username) {
        ReturnObj ret = new ReturnObj();
        QueryWrapper<CentreUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CentreUser::getUsername, username);

        if (!remove(queryWrapper)) {
            ret.setMsg(ReturnCode.NOT_FIND_USER);
        } else {
            ret.setMsg(ReturnCode.OK);
        }
        return ret;

    }


    /**
     * 保存或感谢用户
     *
     * @param username
     * @param password
     * @param roleName
     * @return ReturnObj
     */
    @Override
    public ReturnObj saveOrUpdate(String username, String password, String roleName) {
        ReturnObj ret = new ReturnObj();
        ReturnObj roleRet = roleService.selectByName(roleName);
        CentreUser centreUser = new CentreUser();
        centreUser.setUsername(username);
        centreUser.setPassword(passwordEncoder.encode(password));
        //        如果角色表没有这个角色
        if (!roleRet.getMsg().equals(ReturnCode.OK)) {
            ret.setMsg(roleRet.getMsg());
            return ret;
        }
        //   如果角色表有这个角色
        CentreRole centreRole = (CentreRole) roleRet.getRetObj();
        //       将角色消息给用户
        centreUser.setRoleId(centreRole.getRoleId());
        centreUser.setRoleName(centreRole.getRoleName());
        //更新条件构造器
        UpdateWrapper<CentreUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(CentreUser::getUsername, centreUser.getUsername());
        saveOrUpdate(centreUser, updateWrapper);
        ret.setMsg(ReturnCode.OK);
        ret.setRetObj(centreUser);
        return ret;
    }

    @Override
    public ReturnObj createUser(String username, String password) {

        ReturnObj ret = new ReturnObj();
        String roleName = "user";
        //        查找是否有这个角色
        ReturnObj roleRet = roleService.selectByName(roleName);
        //        查找是否有这个用户
        ReturnObj retUser = selectByName(username);
        if (retUser.getMsg().equals(ReturnCode.OK)) {
            ret.setMsg(ReturnCode.USER_ALREADY_EXISTS);
            return ret;
        }
        //        如果查询角色表没有这个角色
        if (!roleRet.getMsg().equals(ReturnCode.OK)) {
            ret.setMsg(roleRet.getMsg());
            return ret;
        }
        //        创建新的user
        CentreUser centreUser = new CentreUser();
        centreUser.setUsername(username);
        centreUser.setPassword(passwordEncoder.encode(password));
        CentreRole centreRole = (CentreRole) roleRet.getRetObj();
        //       将角色消息给用户
        centreUser.setRoleId(centreRole.getRoleId());
        centreUser.setRoleName(centreRole.getRoleName());
//        保存user到数据库中
        save(centreUser);
//        同步生成userInfo的数据
        userInfoService.save(centreUser.getUsername());

        ret.setMsg(ReturnCode.OK);
        ret.setRetObj(centreUser);
        return ret;
    }

    @Override
    public ReturnObj checkUsrnameAndPassword(String username, String password) {
        ReturnObj ret = selectByName(username);
        if (ret.getMsg().equals(ReturnCode.OK)) {
            CentreUser centreUser = (CentreUser) ret.getRetObj();
            if (!centreUser.getPassword().equals(passwordEncoder.encode(password))) {
                ret.setMsg(ReturnCode.NOT_FIND_USER);
            }
        }
        return ret;
    }


    /**
     * @param current 当前页
     * @param size 一页的数量
     * @return
     */
    @Override
    public Page<CentreUser> pageFind(Integer current, Integer size) {
        QueryWrapper<CentreUser> queryWrapper =  new QueryWrapper<>();
        queryWrapper.lambda().orderByDesc(CentreUser::getUserId);
        Page<CentreUser> page=new Page<>(current,size);
        Page<CentreUser> centreUserPage = baseMapper.selectPage(page, queryWrapper);
        return centreUserPage;
    }

    @Override
    public List<CentreUser> findAll() {
        List<CentreUser> list = list();
        return  list;
    }

    @Override
    public ReturnObj edit(String userId, String username, String password, String roleName) {
     ReturnObj ret=new ReturnObj();
      CentreUser centreUser=getById(userId);
      if(centreUser==null){
          ret.setMsg(ReturnCode.NOT_FIND_USER);
          return  ret;
      }
      centreUser.setRoleName(roleName);
      centreUser.setUsername(username);
      centreUser.setPassword(passwordEncoder.encode(password));
     saveOrUpdate(centreUser);
      ret.setRetObj(centreUser);
      ret.setMsg(ReturnCode.OK);

        return  ret;
    }

}
