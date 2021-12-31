package com.acg.harmonica.service.impl;

import com.acg.harmonica.entity.SheetMusic;
import com.acg.harmonica.utils.ReturnCode;
import com.acg.harmonica.utils.ReturnObj;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.acg.harmonica.dao.MusicVideoDao;
import com.acg.harmonica.entity.MusicVideo;
import com.acg.harmonica.service.MusicVideoService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * (MusicVideo)表服务实现类
 *
 * @author lweijian
 * @since 2021-05-31 21:39:12
 */
@Service("musicVideoService")
public class MusicVideoServiceImpl extends ServiceImpl<MusicVideoDao, MusicVideo> implements MusicVideoService {

    @Override
    public ReturnObj selectByName(String info) {
        ReturnObj ret = new ReturnObj();
        QueryWrapper<MusicVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MusicVideo::getInfo, info);
        List<MusicVideo> list=list(queryWrapper);
        if ( list.isEmpty()) {
            ret.setRetObj(null);
            ret.setMsg(ReturnCode.NOT_FIND_VIDEO);
        } else {
            ret.setRetObj(list);
            ret.setMsg(ReturnCode.OK);
        }
        return ret;
    }

    @Override
    public ReturnObj removeByName(String info) {
        ReturnObj ret = new ReturnObj();
        QueryWrapper<MusicVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MusicVideo::getInfo, info);
        if (!remove(queryWrapper)) {
            ret.setMsg(ReturnCode.NOT_FIND_VIDEO);
        } else {
            ret.setMsg(ReturnCode.OK);
        }
        return ret;
    }

    @Override
    public ReturnObj save(String videoUrl, String imgSrc, String lenTime, Date date, String info) {
       ReturnObj ret=new ReturnObj();
        MusicVideo musicVideo = new MusicVideo(videoUrl,imgSrc,lenTime,date,info);
        save(musicVideo);
        ret.setMsg(ReturnCode.OK);
        ret.setRetObj(musicVideo);
        return  ret;
    }

    @Override
    public Page<MusicVideo> pageFind(Integer current, Integer size) {
        QueryWrapper<MusicVideo> queryWrapper =  new QueryWrapper<>();
        queryWrapper.lambda().orderByDesc(MusicVideo::getId);
        Page<MusicVideo> page=new Page<>(current,size);
        Page<MusicVideo> musicVideoPage = baseMapper.selectPage(page, queryWrapper);
        return musicVideoPage;
    }

    @Override
    public List<MusicVideo> findAll() {
        List<MusicVideo> list = list();
        return list;
    }
}
