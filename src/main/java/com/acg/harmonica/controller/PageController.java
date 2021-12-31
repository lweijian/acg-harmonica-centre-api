package com.acg.harmonica.controller;

import com.acg.harmonica.entity.CentreUser;
import com.acg.harmonica.service.impl.CentreUserServiceImpl;
import com.acg.harmonica.utils.JsonUtiles;
import com.acg.harmonica.utils.JwtTokenUtil;
import com.acg.harmonica.utils.ReturnCode;
import com.acg.harmonica.utils.ReturnObj;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class PageController {

//    @Autowired
//    CentreUserServiceImpl centreUserService;
//
//    @GetMapping(value = "/to_admin", produces = "application/json;charset=UTF-8")
//    public String toAdmin(@RequestHeader("Authorization") String token) {
//        ReturnObj ret = new ReturnObj();
//        Map<String, Object> map = new HashMap<>();
//        String usernameFormToken = null;
//
//        try {
//            usernameFormToken = JwtTokenUtil.getUsernameFormToken(token);
//        } catch (Exception e) {
//            map.put("msg", "token错误");
//            map.put("code", ReturnCode.ERROR_TOKEN);
//            e.printStackTrace();
//            return JsonUtiles.toJson(map);
//        }
//        ret = centreUserService.selectByName(usernameFormToken);
//        if (ret.getMsg().equals(ReturnCode.OK)) {
//            CentreUser centreUser = (CentreUser) ret.getRetObj();
//
//            //            判断是否是admin身份
//            Boolean isPass = "admin".equals(centreUser.getRoleName());
//            if (isPass) {
//                map.put("msg", "admin认证成功");
//                map.put("code", ReturnCode.OK);
//            } else {
//                map.put("msg", "admin认证失败");
//                map.put("code", ReturnCode.ERROR_AUTHORITY);
//            }
//            map.put("isPass", isPass);
//
//        } else {
//            map.put("msg", "token错误");
//            map.put("code", ReturnCode.ERROR_TOKEN);
//            map.put("isPass", false);
//        }
//        return JsonUtiles.toJson(map);
//    }
}
