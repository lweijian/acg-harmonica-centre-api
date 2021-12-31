package com.acg.harmonica.service;

import com.acg.harmonica.entity.SheetMusic;
import com.acg.harmonica.utils.ReturnObj;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.acg.harmonica.entity.UserInfo;

/**
 * (UserInfo)表服务接口
 *
 * @author lweijian
 * @since 2021-05-25 15:50:10
 */
public interface UserInfoService extends IService<UserInfo> {
    public ReturnObj selectByName(String username);
    public ReturnObj removeByName(String username);
    public ReturnObj saveOrUpdate(String username,String  signature);
    public Page<UserInfo> pageFind(Integer current, Integer size);
    public ReturnObj save(String username);
}
