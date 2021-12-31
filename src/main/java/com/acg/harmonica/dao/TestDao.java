package com.acg.harmonica.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.acg.harmonica.entity.Test;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Test)表数据库访问层
 *
 * @author lweijian
 * @since 2021-06-14 23:03:32
 */
@Mapper
public interface TestDao extends BaseMapper<Test> {

}
