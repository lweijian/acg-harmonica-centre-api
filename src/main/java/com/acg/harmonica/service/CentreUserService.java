package com.acg.harmonica.service;

import com.acg.harmonica.entity.MusicVideo;
import com.acg.harmonica.entity.SheetMusic;
import com.acg.harmonica.utils.ReturnObj;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.acg.harmonica.entity.CentreUser;

import java.util.List;

/**
 * (CentreUser)表服务接口
 *
 * @author lweijian
 * @since 2021-05-10 01:13:19
 */
public interface CentreUserService extends IService<CentreUser> {
    public ReturnObj selectByName(String username);
    public ReturnObj removeByName(String username);
    public ReturnObj saveOrUpdate(String username,String password,String roleName);
    public ReturnObj createUser(String username, String password);
    public ReturnObj checkUsrnameAndPassword(String username,String password);
    public Page<CentreUser> pageFind(Integer current, Integer size);
    public List<CentreUser> findAll();
    public ReturnObj edit(String userId,String username,String password,String roleName);

}
