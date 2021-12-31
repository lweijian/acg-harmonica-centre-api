package com.acg.harmonica.controller;


import com.acg.harmonica.entity.MusicVideo;
import com.acg.harmonica.utils.*;
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
public class TokenController {

@Resource
RedisUtil redisUtil;
    /**
     * 根据名字查找视频
     *
     * @param jsonObject info
     * @return json
     */
    @PostMapping(value = "/refresh_token", produces = "application/json;charset=UTF-8")
    public String refreshToken(@RequestBody JSONObject jsonObject) {
        String refreshToken = jsonObject.getString("refreshToken");
        String username=null;
        Map<String, Object> map = new HashMap<>(3);
        try {
            username=JwtTokenUtil.getUsernameFormToken(refreshToken);
            //得到新的token
            Map tokenMap = JwtTokenUtil.refreshToken(refreshToken);
            String accessToken = (String) tokenMap.get("accessToken");
            redisUtil.set(username,accessToken);
            map.put("msg", "token刷新成功");
            map.put("code", ReturnCode.OK);
            map.put("data",tokenMap);
        } catch (Exception e) {
            map.put("msg", "refreshToken过期");
            map.put("code", ReturnCode.OUT_TIME_REFRESHTOKEN);
            e.printStackTrace();
        }
        return JsonUtiles.toJson(map);
    }


}
