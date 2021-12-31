package com.acg.harmonica.controller;


import com.acg.harmonica.entity.CentreUser;
import com.acg.harmonica.entity.MusicVideo;
import com.acg.harmonica.service.impl.CentreUserServiceImpl;

import com.acg.harmonica.utils.JsonUtiles;
import com.acg.harmonica.utils.ReturnCode;
import com.acg.harmonica.utils.ReturnObj;

import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/centre_user")
public class CentreUserController {
    @Autowired
    CentreUserServiceImpl centreUserService;

    @PostMapping(value = "/find", produces = "application/json;charset=UTF-8")
    public String findUser(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        ReturnObj ret = centreUserService.selectByName(username);
        Map<String, Object> map = new HashMap<>();
        if (ret.getMsg().equals(ReturnCode.OK)) {
            map.put("user", ret.getRetObj());
            map.put("code", ret.getMsg());
            map.put("msg", "用户查询成功");
        } else {
            map.put("msg", "用户查询失败");
            map.put("code", ret.getMsg());
        }
        return JsonUtiles.toJson(map);
    }

    @PostMapping(value = "/remove", produces = "application/json;charset=UTF-8")
    public String removeUser(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        ReturnObj ret = centreUserService.removeByName(username);
        Map<String, Object> map = new HashMap<>();
        if (ret.getMsg().equals(ReturnCode.OK)) {
            map.put("code", ret.getMsg());
            map.put("msg", "用户删除成功");
        } else {
            map.put("msg", "用户删除失败");
            map.put("code", ret.getMsg());
        }
        return JsonUtiles.toJson(map);
    }

    /**
     * 注册用户
     *
     * @param jsonObject
     * @return String
     */
    @PostMapping(value = "/registered", produces = "application/json;charset=UTF-8")
    public String registeredUser(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        Map<String, Object> map = new HashMap<>();
        ReturnObj ret = centreUserService.createUser(username, password);
        if (ret.getMsg().equals(ReturnCode.OK)) {
            map.put("msg", "用户注册成功");
            map.put("code", ret.getMsg());
        } else {
            map.put("msg", "用户注册失败");
            map.put("code", ret.getMsg());
        }
        return JsonUtiles.toJson(map);
    }


    @GetMapping(value = "/find_all", produces = "application/json;charset=UTF-8")
    public String findAllMusicVideo() {

        List<CentreUser> list = centreUserService.findAll();
        Map<String, Object> map = new HashMap<>(4);
        if (!list.isEmpty()) {
            map.put("total", list.size());
            map.put("users", list);
            map.put("code", ReturnCode.OK);
            map.put("msg", "用户查询成功");
        } else {
            map.put("msg", "用户查询失败");
            map.put("code", ReturnCode.NOT_FIND_MUSIC);
        }
        return JsonUtiles.toJson(map);

    }
}