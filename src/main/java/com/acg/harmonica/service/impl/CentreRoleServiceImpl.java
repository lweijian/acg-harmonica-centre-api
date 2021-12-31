package com.acg.harmonica.service.impl;

import com.acg.harmonica.entity.CentreUser;
import com.acg.harmonica.entity.SheetMusic;
import com.acg.harmonica.utils.ReturnCode;
import com.acg.harmonica.utils.ReturnObj;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.acg.harmonica.dao.CentreRoleDao;
import com.acg.harmonica.entity.CentreRole;
import com.acg.harmonica.service.CentreRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (CentreRole)表服务实现类
 *
 * @author lweijian
 * @since 2021-05-10 01:13:17
 */
@Service("centreRoleService")
public class CentreRoleServiceImpl extends ServiceImpl<CentreRoleDao, CentreRole> implements CentreRoleService {

    @Autowired
    CentreUserServiceImpl userService;


    /**
     * 查找角色和拥有该角色的用户
     * @param roleName
     * @return
     */
    @Override
    public ReturnObj selectByName(String roleName) {
        ReturnObj ret = new ReturnObj();
        //        查询角色
        QueryWrapper<CentreRole> roleQueryWrapper = new QueryWrapper<>();

        roleQueryWrapper.lambda().eq(CentreRole::getRoleName, roleName);
        CentreRole centreRole = getOne(roleQueryWrapper);

        if (centreRole == null) {
            ret.setMsg(ReturnCode.NOT_FIND_ROLE);
        } else {
            //        查询拥有该角色的用户，得到列表，把列表赋予该角色
            QueryWrapper<CentreUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(CentreUser::getRoleName, roleName);
            List<CentreUser> centreUsers = userService.list(queryWrapper);
            centreRole.setUsers(centreUsers);
            ret.setRetObj(centreRole);
            ret.setMsg(ReturnCode.OK);
        }
        return ret;
    }

    /**
     * 谨慎使用
     * 更新角色表，同步更新用户表的角色
     * @param roleName
     * @param updateName
     * @return
     */
    @Override
    public ReturnObj updateName(String roleName, String updateName) {
        ReturnObj ret = new ReturnObj();
        if (selectByName(updateName).getMsg().equals(ReturnCode.OK)) {
            ret.setMsg(ReturnCode.ROLE_ALREADY_EXISTS);
            return ret;
        }
        UpdateWrapper<CentreRole> roleUpdateWrapper = new UpdateWrapper<>();
        roleUpdateWrapper
                .lambda()
                .eq(CentreRole::getRoleName, roleName)
                .set(CentreRole::getRoleName, updateName);
        if (!update(roleUpdateWrapper)) {
            ret.setMsg(ReturnCode.NOT_FIND_ROLE);
            return ret;
        } else {
            ret.setMsg(ReturnCode.OK);
        }
    //同步更新user表
        QueryWrapper<CentreUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CentreUser::getRoleName, roleName);
        List<CentreUser> users = userService.list(queryWrapper);
        users.forEach(centreUser -> {
            centreUser.setRoleName(updateName);
            userService.saveOrUpdate(centreUser);
        });
        return ret;
    }

    /**
     * 谨慎使用
     * 删除角色，不会同步用户表删除角色
     * @param roleName
     * @return
     */
    @Override
    public ReturnObj removeByName(String roleName) {
        ReturnObj ret = new ReturnObj();
        QueryWrapper<CentreRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CentreRole::getRoleName, roleName);
        if (!remove(queryWrapper)) {
            ret.setMsg(ReturnCode.NOT_FIND_ROLE);
        } else {
            ret.setMsg(ReturnCode.OK);
        }
        return ret;

    }

    /**
     * 生成新角色
     * @param roleName
     * @return
     */
    @Override
    public ReturnObj insertRole(String roleName) {
        ReturnObj ret = new ReturnObj();
        CentreRole centreRole = (CentreRole) selectByName(roleName).getRetObj();
        if (centreRole != null) {
            //        数据库有该角色
            ret.setRetObj(centreRole);
            ret.setMsg(ReturnCode.ROLE_ALREADY_EXISTS);
        } else {
//            数据库没有该角色
            centreRole = new CentreRole();
            centreRole.setRoleName(roleName);
            save(centreRole);
            ret.setMsg(ReturnCode.OK);
            ret.setRetObj(centreRole);
        }
        return ret;
    }

    /**
     * @param current 当前页
     * @param size 一页的数量
     * @return
     */
    @Override
    public Page<CentreRole> pageFind(Integer current, Integer size) {
        QueryWrapper<CentreRole> queryWrapper =  new QueryWrapper<>();
        queryWrapper.lambda().orderByDesc(CentreRole::getRoleId);
        Page<CentreRole> page=new Page<>(current,size);
        Page<CentreRole> centreRolePage = baseMapper.selectPage(page, queryWrapper);
        return centreRolePage;
    }
}
