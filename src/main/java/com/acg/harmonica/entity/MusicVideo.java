package com.acg.harmonica.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * (MusicVideo)表实体类
 *
 * @author lweijian
 * @since 2021-05-31 21:39:11
 */
@SuppressWarnings("serial")
public class MusicVideo extends Model<MusicVideo> {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String videoUrl;

    private String imgSrc;

    private String lenTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String info;

    public MusicVideo() {
    }

    public MusicVideo(String videoUrl, String imgSrc, String lenTime, Date date, String info) {
        this.videoUrl = videoUrl;
        this.imgSrc = imgSrc;
        this.lenTime = lenTime;
        this.date = date;
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getLenTime() {
        return lenTime;
    }

    public void setLenTime(String lenTime) {
        this.lenTime = lenTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
