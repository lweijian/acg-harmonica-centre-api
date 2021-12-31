package com.acg.harmonica.service;

import com.acg.harmonica.entity.CentreUser;
import com.acg.harmonica.entity.SheetMusic;
import com.acg.harmonica.utils.ReturnObj;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.acg.harmonica.entity.CentreRole;

/**
 * (CentreRole)表服务接口
 *
 * @author lweijian
 * @since 2021-05-10 01:13:17
 */
public interface CentreRoleService extends IService<CentreRole> {
    public ReturnObj selectByName(String roleName);
    public ReturnObj updateName(String roleName,String updateName);
    public ReturnObj removeByName(String roleName);
    public ReturnObj insertRole(String roleName);
    public Page<CentreRole> pageFind(Integer current, Integer size);
}

