package com.acg.harmonica.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.List;

/**
 * (CentreRole)表实体类
 *
 * @author lweijian
 * @since 2021-05-10 01:13:15
 */
@SuppressWarnings("serial")
public class CentreRole extends Model<CentreRole> {

    @TableId(type =IdType.ASSIGN_ID)
    private String roleId;

    private String roleName;

    @TableField(exist = false)
    private List<CentreUser> users;


    public void setUsers(List<CentreUser> users) {
        this.users = users;
    }

    public List<CentreUser> getUsers() {
        return users;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

    @Override
    public String toString() {
        return "CentreRole{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", users=" + users +
                '}';
    }
}
