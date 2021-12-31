package com.acg.harmonica.controller;

import com.acg.harmonica.service.impl.CentreRoleServiceImpl;
import com.acg.harmonica.service.impl.CentreUserServiceImpl;
import com.acg.harmonica.utils.JsonUtiles;
import com.acg.harmonica.utils.ReturnCode;
import com.acg.harmonica.utils.ReturnObj;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/centre_role")
public class CentreRoleController {
    @Autowired
    CentreRoleServiceImpl centreRoleService;

    @PostMapping(value = "/find", produces = "application/json;charset=UTF-8")
    public String findRole(@RequestBody JSONObject jsonObject) {
        String roleName = jsonObject.getString("roleName");
        ReturnObj ret = centreRoleService.selectByName(roleName);
        Map<String, Object> map = new HashMap<>();
        if (ret.getMsg().equals(ReturnCode.OK)) {
            map.put("role", ret.getRetObj());
            map.put("code", ret.getMsg());
            map.put("msg", "角色查询成功");
        } else {
            map.put("msg", "角色查询失败");
            map.put("code", ret.getMsg());
        }
        return JsonUtiles.toJson(map);
    }

}
