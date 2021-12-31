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

/**
 * admin角色才能使用的api
 *
 * @author lweijian
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    CentreRoleServiceImpl centreRoleService;
    @Autowired
    CentreUserServiceImpl centreUserService;

    /**
     * admin
     * 删除角色
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/remove_role", produces = "application/json;charset=UTF-8")
    public String removeRole(@RequestBody JSONObject jsonObject) {
        String roleName = jsonObject.getString("roleName");
        ReturnObj ret = centreRoleService.removeByName(roleName);
        Map<String, Object> map = new HashMap<>(2);
        if (ret.getMsg().equals(ReturnCode.OK)) {
            map.put("msg", "角色删除成功");
            map.put("code", ret.getMsg());
        } else {
            map.put("msg", "角色删除失败");
            map.put("code", ret.getMsg());
        }
        return JsonUtiles.toJson(map);
    }

    /**
     * admin
     * 创建角色
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/insert_role", produces = "application/json;charset=UTF-8")
    public String insertRole(@RequestBody JSONObject jsonObject) {
        String roleName = jsonObject.getString("roleName");
        Map<String, Object> map = new HashMap<>(2);
        ReturnObj ret = centreRoleService.insertRole(roleName);
        if (ret.getMsg().equals(ReturnCode.OK)) {
            map.put("code", ret.getMsg());
            map.put("msg", "角色创建成功");
        } else {
            map.put("code", ret.getMsg());
            map.put("msg", "角色创建失败");
        }
        return JsonUtiles.toJson(map);
    }

    /**
     * admin
     * 更新角色
     * @param jsonObject
     * @return String
     */
    @PostMapping(value = "/update_role", produces = "application/json;charset=UTF-8")
    public String updateRole(@RequestBody JSONObject jsonObject) {
        String roleName = jsonObject.getString("roleName");
        String updateName = jsonObject.getString("updateName");
        Map<String, Object> map = new HashMap<>();
        ReturnObj ret = centreRoleService.updateName(roleName, updateName);
        if (ret.getMsg().equals(ReturnCode.OK)) {
            map.put("msg", "角色更新成功");
            map.put("code", ret.getMsg());
        } else {
            map.put("msg", "角色更新失败");
            map.put("code", ret.getMsg());
        }
        return JsonUtiles.toJson(map);
    }

    /**
     * admin
     * 保存或更新用户信息
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/save_user", produces = "application/json;charset=UTF-8")
    public String saveOrUpdateUser(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String roleName = jsonObject.getString("roleName");
        Map<String, Object> map = new HashMap<>(2);
        ReturnObj ret = centreUserService.saveOrUpdate(username, password, roleName);
        if (ret.getMsg().equals(ReturnCode.OK)) {
            map.put("code", ret.getMsg());
            map.put("msg", "用户保存成功");
        } else {
            map.put("code", ret.getMsg());
            map.put("msg", "用户保存失败");
        }
        return JsonUtiles.toJson(map);
    }

    /**
     * admin
     * 修改用户信息
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/edit_user", produces = "application/json;charset=UTF-8")
    public String editUser(@RequestBody JSONObject jsonObject) {
        String userId = jsonObject.getString("userId");
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String roleName = jsonObject.getString("roleName");
        Map<String, Object> map = new HashMap<>(2);
        ReturnObj ret = centreUserService.edit(userId,username, password, roleName);
        if (ret.getMsg().equals(ReturnCode.OK)) {
            map.put("code", ret.getMsg());
            map.put("msg", "用户修改成功");
        } else {
            map.put("code", ret.getMsg());
            map.put("msg", "用户修改失败");
        }
        return JsonUtiles.toJson(map);
    }

}
