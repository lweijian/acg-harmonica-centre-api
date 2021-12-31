package com.acg.harmonica.service.impl;

import com.acg.harmonica.utils.ReturnCode;
import com.acg.harmonica.utils.ReturnObj;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.acg.harmonica.dao.SheetMusicDao;
import com.acg.harmonica.entity.SheetMusic;
import com.acg.harmonica.service.SheetMusicService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (SheetMusic)表服务实现类
 *
 * @author lweijian
 * @since 2021-05-31 09:50:22
 */
@Service("sheetMusicService")
public class SheetMusicServiceImpl extends ServiceImpl<SheetMusicDao, SheetMusic> implements SheetMusicService {

    @Override
    public ReturnObj selectByName(String musicName) {
        ReturnObj ret = new ReturnObj();
        QueryWrapper<SheetMusic> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(SheetMusic::getMusicName, musicName);
        List<SheetMusic> list=list(queryWrapper);

        if ( list.isEmpty()) {
            ret.setRetObj(null);
            ret.setMsg(ReturnCode.NOT_FIND_MUSIC);
        } else {
            ret.setRetObj(list);
            ret.setMsg(ReturnCode.OK);
        }
        return ret;
    }

    @Override
    public ReturnObj removeByName(String musicName) {
        ReturnObj ret = new ReturnObj();
        QueryWrapper<SheetMusic> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SheetMusic::getMusicName, musicName);
        if (!remove(queryWrapper)) {
            ret.setMsg(ReturnCode.NOT_FIND_MUSIC);
        } else {
            ret.setMsg(ReturnCode.OK);
        }
        return ret;
    }

    @Override
    public ReturnObj save(String musicName, Integer readNumber, String imgSrc, String musicAuthor, String musicHref) {
        {
            ReturnObj ret = new ReturnObj();
            SheetMusic sheetMusic=new SheetMusic(readNumber,imgSrc,musicName,musicAuthor,musicHref);
            save(sheetMusic);
            ret.setMsg(ReturnCode.OK);
            ret.setRetObj(sheetMusic);
            return ret;
        }
    }

    /**
     * @param current 当前页
     * @param size 一页的数量
     * @return
     */
    @Override
    public Page<SheetMusic> pageFind(Integer current, Integer size) {
        QueryWrapper<SheetMusic> queryWrapper =  new QueryWrapper<>();
        queryWrapper.lambda().orderByDesc(SheetMusic::getId);
       Page<SheetMusic> page=new Page<>(current,size);
        Page<SheetMusic> sheetMusicPage = baseMapper.selectPage(page, queryWrapper);
        return sheetMusicPage;
    }

    @Override
    public List<SheetMusic> findAll() {
        List<SheetMusic> list = list();
        return list;
    }

}
