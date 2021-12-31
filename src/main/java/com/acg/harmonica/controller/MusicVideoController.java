package com.acg.harmonica.controller;


import com.acg.harmonica.entity.MusicVideo;
import com.acg.harmonica.utils.JsonUtiles;
import com.acg.harmonica.utils.ReturnCode;
import com.acg.harmonica.utils.ReturnObj;
import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.acg.harmonica.service.MusicVideoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (MusicVideo)表控制层
 *
 * @author lweijian
 * @since 2021-05-31 21:39:12
 */
@RestController
@RequestMapping("/music_video")
public class MusicVideoController  {
    /**
     * 服务对象
     */
    @Resource
    private MusicVideoService musicVideoService;


    /**
     * 根据名字查找视频
     * @param jsonObject info
     * @return json
     */
    @PostMapping(value = "/find", produces = "application/json;charset=UTF-8")
    public String findMusicVideoByName(@RequestBody JSONObject jsonObject) {
        String info = jsonObject.getString("info");
        ReturnObj ret = musicVideoService.selectByName(info);
        Map<String, Object> map = new HashMap<>(3);
        if (ret.getMsg().equals(ReturnCode.OK)) {
            map.put("videos", ret.getRetObj());
            map.put("code", ret.getMsg());
            map.put("msg", "视频查询成功");
        } else {
            map.put("msg", "视频查询失败");
            map.put("code", ret.getMsg());
        }
        return JsonUtiles.toJson(map);
    }


    @GetMapping(value = "/find_all", produces = "application/json;charset=UTF-8")
    public String findAllMusicVideo() {

        List<MusicVideo> list = musicVideoService.findAll();
        Map<String, Object> map = new HashMap<>(4);
        if (!list.isEmpty()) {
            map.put("total", list.size());
            map.put("videos", list);
            map.put("code", ReturnCode.OK);
            map.put("msg", "视频查询成功");
        } else {
            map.put("msg", "视频查询失败");
            map.put("code",ReturnCode.NOT_FIND_MUSIC );
        }
        return JsonUtiles.toJson(map);
    }


    /**
     * 删除视频
     * @param jsonObject  info
     * @return String
     */
    @PostMapping(value = "/remove", produces = "application/json;charset=UTF-8")
    public String removeMusicVideo(@RequestBody JSONObject jsonObject) {
        String info = jsonObject.getString("info");
        ReturnObj ret = musicVideoService.removeByName(info);
        Map<String, Object> map = new HashMap<>();
        if (ret.getMsg().equals(ReturnCode.OK)) {
            map.put("code", ret.getMsg());
            map.put("msg", "视频删除成功");
        } else {
            map.put("msg", "视频删除失败");
            map.put("code", ret.getMsg());
        }
        return JsonUtiles.toJson(map);
    }

    /**
     * 保存视频
     * 传 info  readNumber imgSrc musicAuthor musicHref
     * @param jsonObject
     * @return String
     */
    @PostMapping(value = "/save", produces = "application/json;charset=UTF-8")
    public String save(@RequestBody JSONObject jsonObject) {
        String info = jsonObject.getString("info");

         String videoUrl=jsonObject.getString("videoUrl");

        String imgSrc=jsonObject.getString("imgSrc");

         String lenTime=jsonObject.getString("lenTime");

         Date date=jsonObject.getDate("date");

        Map<String, Object> map = new HashMap<>();

        if(info==null||videoUrl==null||imgSrc==null||lenTime==null||date==null){
            map.put("msg", "参数传入错误");
            map.put("code", ReturnCode.ERROR_PARAMS);
            return JsonUtiles.toJson(map);
        }

        ReturnObj ret= musicVideoService.save(videoUrl,imgSrc,lenTime,date,info);
        if (ret.getMsg().equals(ReturnCode.OK)) {
            map.put("msg", "视频上传成功");
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
        Map<String, Object> map = new HashMap<>(4);

        Page<MusicVideo> musicVideoPage = musicVideoService.pageFind(current, size);
        List<MusicVideo>list= musicVideoPage.getRecords();
        int total = musicVideoService.count();
        if (!list.isEmpty()) {
            map.put("total",total);
            map.put("videos", list);
            map.put("code", ReturnCode.OK);
            map.put("msg", "视频分页查询成功");
        } else {
            map.put("msg", "视频分页查询失败");
            map.put("code",ReturnCode.NOT_FIND_MUSIC );
        }
        return JsonUtiles.toJson(map);
    }
}
