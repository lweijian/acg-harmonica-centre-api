package com.acg.harmonica.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.acg.harmonica.entity.CentreUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * (CentreUser)表数据库访问层
 *
 * @author lweijian
 * @since 2021-05-10 01:13:19
 */
@Mapper
public interface CentreUserDao extends BaseMapper<CentreUser> {

}
