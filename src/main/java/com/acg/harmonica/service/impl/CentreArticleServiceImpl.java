package com.acg.harmonica.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.acg.harmonica.dao.CentreArticleDao;
import com.acg.harmonica.entity.CentreArticle;
import com.acg.harmonica.service.CentreArticleService;
import org.springframework.stereotype.Service;

/**
 * (CentreArticle)表服务实现类
 *
 * @author lweijian
 * @since 2021-07-07 23:40:13
 */
@Service("centreArticleService")
public class CentreArticleServiceImpl extends ServiceImpl<CentreArticleDao, CentreArticle> implements CentreArticleService {

}
