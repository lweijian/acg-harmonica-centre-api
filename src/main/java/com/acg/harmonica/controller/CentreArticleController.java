package com.acg.harmonica.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.acg.harmonica.entity.CentreArticle;
import com.acg.harmonica.service.CentreArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (CentreArticle)表控制层
 *
 * @author lweijian
 * @since 2021-07-07 23:40:14
 */
@RestController
@RequestMapping("centreArticle")
public class CentreArticleController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private CentreArticleService centreArticleService;

    /**
     * 分页查询所有数据
     *
     * @param page          分页对象
     * @param centreArticle 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<CentreArticle> page, CentreArticle centreArticle) {
        return success(this.centreArticleService.page(page, new QueryWrapper<>(centreArticle)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.centreArticleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param centreArticle 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody CentreArticle centreArticle) {
        return success(this.centreArticleService.save(centreArticle));
    }

    /**
     * 修改数据
     *
     * @param centreArticle 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody CentreArticle centreArticle) {
        return success(this.centreArticleService.updateById(centreArticle));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.centreArticleService.removeByIds(idList));
    }
}
