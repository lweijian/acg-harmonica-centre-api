package com.acg.harmonica.service;

import com.acg.harmonica.entity.SheetMusic;
import com.acg.harmonica.utils.ReturnObj;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.acg.harmonica.entity.MusicVideo;

import java.util.Date;
import java.util.List;

/**
 * (MusicVideo)表服务接口
 *
 * @author lweijian
 * @since 2021-05-31 21:39:12
 */
public interface MusicVideoService extends IService<MusicVideo> {
    public ReturnObj selectByName(String info);
    public ReturnObj removeByName(String info);
    public ReturnObj save(String videoUrl, String imgSrc, String lenTime, Date date, String info);
    public Page<MusicVideo> pageFind(Integer current, Integer size);
    public List<MusicVideo> findAll();
}
