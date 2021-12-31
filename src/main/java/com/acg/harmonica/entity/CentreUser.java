package com.acg.harmonica.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * (CentreUser)表实体类
 *
 * @author lweijian
 * @since 2021-05-10 01:13:18
 */
@SuppressWarnings("serial")
public class CentreUser extends Model<CentreUser> {

    @TableId(type = IdType.ASSIGN_ID)
    private String userId;
    private String username;
    private String password;
    private String roleId;
    private  String roleName;
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "CentreUser{" +
                "userId='" + userId + '\'' +
                ", userName='" + username + '\'' +
                ", userPassword='" + password + '\'' +
                ", roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
