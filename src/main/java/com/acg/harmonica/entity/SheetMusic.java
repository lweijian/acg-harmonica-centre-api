package com.acg.harmonica.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * (SheetMusic)表实体类
 *
 * @author lweijian
 * @since 2021-05-31 09:50:21
 */
@SuppressWarnings("serial")
public class SheetMusic extends Model<SheetMusic> {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private Integer readNumber;

    private String imgSrc;

    private String musicName;

    private String musicAuthor;

    private String musicHref;

    public SheetMusic() {
    }

    public SheetMusic(Integer readNumber, String imgSrc, String musicName, String musicAuthor, String musicHref) {
        this.readNumber = readNumber;
        this.imgSrc = imgSrc;
        this.musicName = musicName;
        this.musicAuthor = musicAuthor;
        this.musicHref = musicHref;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getReadNumber() {
        return readNumber;
    }

    public void setReadNumber(Integer readNumber) {
        this.readNumber = readNumber;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicAuthor() {
        return musicAuthor;
    }

    public void setMusicAuthor(String musicAuthor) {
        this.musicAuthor = musicAuthor;
    }

    public String getMusicHref() {
        return musicHref;
    }

    public void setMusicHref(String musicHref) {
        this.musicHref = musicHref;
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
