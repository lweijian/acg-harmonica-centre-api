package com.acg.harmonica.controller;


import com.acg.harmonica.utils.JsonUtiles;
import com.acg.harmonica.utils.ReturnCode;
import com.acg.harmonica.utils.ReturnObj;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.acg.harmonica.entity.SheetMusic;
import com.acg.harmonica.service.SheetMusicService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (SheetMusic)表控制层
 *
 * @author lweijian
 * @since 2021-05-31 09:50:22
 */
@RestController
@RequestMapping("/sheet_music")
public class SheetMusicController  {

    /**
     * 服务对象
     */
    @Resource
    private SheetMusicService sheetMusicService;

    /**
     * 根据名字查找乐谱
     * @param jsonObject musicName
     * @return
     */
    @PostMapping(value = "/find", produces = "application/json;charset=UTF-8")
    public String findSheetMusicByName(@RequestBody JSONObject jsonObject) {
        String musicName = jsonObject.getString("musicName");
        ReturnObj ret = sheetMusicService.selectByName(musicName);
        Map<String, Object> map = new HashMap<>();
        if (ret.getMsg().equals(ReturnCode.OK)) {
            map.put("musics", ret.getRetObj());
            map.put("code", ret.getMsg());
            map.put("msg", "乐谱查询成功");
        } else {
            map.put("msg", "乐谱查询失败");
            map.put("code", ret.getMsg());
        }
        return JsonUtiles.toJson(map);
    }


    @GetMapping(value = "/find_all", produces = "application/json;charset=UTF-8")
    public String findAllSheetMusic() {

        List<SheetMusic> list = sheetMusicService.findAll();
        Map<String, Object> map = new HashMap<>();
        if (!list.isEmpty()) {
            map.put("total", list.size());
            map.put("musics", list);
            map.put("code", ReturnCode.OK);
            map.put("msg", "乐谱查询成功");
        } else {
            map.put("msg", "乐谱查询失败");
            map.put("code",ReturnCode.NOT_FIND_MUSIC );
        }
        return JsonUtiles.toJson(map);
    }


    /**
     * 删除乐谱
     * @param jsonObject  musicName
     * @return
     */
    @PostMapping(value = "/remove", produces = "application/json;charset=UTF-8")
    public String removeSheetMusic(@RequestBody JSONObject jsonObject) {
        String musicName = jsonObject.getString("musicName");
        ReturnObj ret = sheetMusicService.removeByName(musicName);
        Map<String, Object> map = new HashMap<>();
        if (ret.getMsg().equals(ReturnCode.OK)) {
            map.put("code", ret.getMsg());
            map.put("msg", "乐谱删除成功");
        } else {
            map.put("msg", "乐谱删除失败");
            map.put("code", ret.getMsg());
        }
        return JsonUtiles.toJson(map);
    }

    /**
     * 保存乐谱
     * 传 musicName  readNumber imgSrc musicAuthor musicHref
     * @param jsonObject
     * @return String
     */
    @PostMapping(value = "/save", produces = "application/json;charset=UTF-8")
    public String save(@RequestBody JSONObject jsonObject) {
        String musicName = jsonObject.getString("musicName");
        Integer readNumber = jsonObject.getInteger("readNumber");
        String imgSrc = jsonObject.getString("imgSrc");
        String  musicAuthor = jsonObject.getString("musicAuthor");
        String musicHref = jsonObject.getString("musicHref");
        Map<String, Object> map = new HashMap<>();

        if(musicHref==null||musicName==null){
            map.put("msg", "参数传入错误");
            map.put("code", ReturnCode.ERROR_PARAMS);
            return JsonUtiles.toJson(map);
        }

        ReturnObj ret= sheetMusicService.save(musicName,readNumber,imgSrc,musicAuthor,musicHref);
        if (ret.getMsg().equals(ReturnCode.OK)) {
            map.put("msg", "乐谱上传成功");
            map.put("code", ret.getMsg());
        }
        return JsonUtiles.toJson(map);
    }


    /**
     * 分页查询
     * 传 Integer current ,Integer size
     * @param jsonObject
     * @return String
     */
    @PostMapping(value = "/find_page", produces = "application/json;charset=UTF-8")
    public String findPage(@RequestBody JSONObject jsonObject) {

        Integer current = jsonObject.getInteger("current");
        Integer size = jsonObject.getInteger("size");
        Map<String, Object> map = new HashMap<>();

        Page<SheetMusic> sheetMusicPage = sheetMusicService.pageFind(current, size);
        List<SheetMusic>list= sheetMusicPage.getRecords();
        int total = sheetMusicService.count();
        if (!list.isEmpty()) {
            map.put("total",total);
            map.put("musics", list);
            map.put("code", ReturnCode.OK);
            map.put("msg", "乐谱分页查询成功");
        } else {
            map.put("msg", "乐谱分页查询失败");
            map.put("code",ReturnCode.NOT_FIND_MUSIC );
        }
        return JsonUtiles.toJson(map);
    }
}
