package com.acg.harmonica.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * (UserInfo)表实体类
 *
 * @author lweijian
 * @since 2021-05-25 15:50:07
 */
@SuppressWarnings("serial")
public class UserInfo extends Model<UserInfo> {

    @TableId
    private String userId;

    private String username;
    private String signature;
    private String roleName;


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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public UserInfo() {
    }

    public UserInfo(String userId, String username, String signature,String roleName) {
        this.userId = userId;
        this.username = username;
        this.signature = signature;
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


}
