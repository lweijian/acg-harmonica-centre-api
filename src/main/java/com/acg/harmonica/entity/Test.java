package com.acg.harmonica.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * (Test)表实体类
 *
 * @author lweijian
 * @since 2021-06-14 23:03:31
 */
@SuppressWarnings("serial")
public class Test extends Model<Test> {
@TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String imgsrc;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

}
