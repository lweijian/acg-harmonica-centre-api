package com.acg.harmonica.service.impl;

import com.acg.harmonica.entity.CentreRole;
import com.acg.harmonica.entity.CentreUser;
import com.acg.harmonica.entity.SheetMusic;
import com.acg.harmonica.utils.ReturnCode;
import com.acg.harmonica.utils.ReturnObj;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.acg.harmonica.dao.UserInfoDao;
import com.acg.harmonica.entity.UserInfo;
import com.acg.harmonica.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * (UserInfo)表服务实现类
 *
 * @author lweijian
 * @since 2021-05-25 15:50:11
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements UserInfoService {
    @Autowired
    CentreUserServiceImpl centreUserService;

    @Override
    public ReturnObj selectByName(String username) {
        ReturnObj ret = new ReturnObj();
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserInfo::getUsername, username);
        UserInfo userInfo = getOne(queryWrapper);
        if (userInfo == null) {
            ret.setRetObj(null);
            ret.setMsg(ReturnCode.NOT_FIND_USER);
        } else {
            ret.setRetObj(userInfo);
            ret.setMsg(ReturnCode.OK);
        }
        return ret;
    }


    @Override
    public ReturnObj removeByName(String username) {
        ReturnObj ret = new ReturnObj();
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserInfo::getUsername, username);
        if (!remove(queryWrapper)) {
            ret.setMsg(ReturnCode.NOT_FIND_USER);
        } else {
            ret.setMsg(ReturnCode.OK);
        }
        return ret;

    }

    @Override
    public ReturnObj saveOrUpdate(String username, String signature) {
        {
            ReturnObj ret;

            UserInfo userInfo = new UserInfo();
            ret = centreUserService.selectByName(username);
            if (!ret.getMsg().equals(ReturnCode.OK)) {
                return ret;
            }
            CentreUser centreUser = (CentreUser) ret.getRetObj();
//            设置属性
            userInfo.setUserId(centreUser.getUserId());
            userInfo.setUsername(centreUser.getUsername());
            userInfo.setRoleName(centreUser.getRoleName());
            userInfo.setSignature(signature);

            saveOrUpdate(userInfo);
            ret.setMsg(ReturnCode.OK);
            ret.setRetObj(userInfo);
            return ret;
        }

    }
    @Override
    public ReturnObj save(String username) {
        {
            ReturnObj ret;

            UserInfo userInfo = new UserInfo();
            ret = centreUserService.selectByName(username);
            if (!ret.getMsg().equals(ReturnCode.OK)) {
                return ret;
            }
            CentreUser centreUser = (CentreUser) ret.getRetObj();
//            设置属性
            userInfo.setUserId(centreUser.getUserId());
            userInfo.setUsername(centreUser.getUsername());
            userInfo.setRoleName(centreUser.getRoleName());
            userInfo.setSignature("signature");

            saveOrUpdate(userInfo);
            ret.setMsg(ReturnCode.OK);
            ret.setRetObj(userInfo);
            return ret;
        }
    }

    /**
     * @param current 当前页
     * @param size 一页的数量
     * @return
     */
    @Override
    public Page<UserInfo> pageFind(Integer current, Integer size) {
        QueryWrapper<UserInfo> queryWrapper =  new QueryWrapper<>();
        queryWrapper.lambda().orderByDesc(UserInfo::getUserId);
        Page<UserInfo> page=new Page<>(current,size);
        Page<UserInfo> userInfoPage = baseMapper.selectPage(page, queryWrapper);
        return userInfoPage;
    }
}
