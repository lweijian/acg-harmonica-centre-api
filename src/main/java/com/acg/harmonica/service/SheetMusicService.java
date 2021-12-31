package com.acg.harmonica.service;

import com.acg.harmonica.utils.ReturnObj;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.acg.harmonica.entity.SheetMusic;

import java.util.List;

/**
 * (SheetMusic)表服务接口
 *
 * @author lweijian
 * @since 2021-05-31 09:50:22
 */
public interface SheetMusicService extends IService<SheetMusic> {
    public ReturnObj selectByName(String musicName);
    public ReturnObj removeByName(String musicName);
    public ReturnObj save(String musicName,Integer readNumber,String imgSrc,String musicAuthor,String musicHref);
    public Page<SheetMusic> pageFind(Integer current, Integer size);
    public List<SheetMusic> findAll();
}
