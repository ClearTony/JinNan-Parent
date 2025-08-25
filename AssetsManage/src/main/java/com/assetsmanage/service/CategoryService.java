package com.assetsmanage.service;

import com.assetsmanage.entity.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 资产分类业务处理
 **/
public interface CategoryService {

    /**
     * 新增
     */
    void add(Category category);

    /**
     * 删除
     */
    void deleteById(Integer id);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改
     */
    void updateById(Category category);

    /**
     * 根据ID查询
     */
    Category selectById(Integer id);

    /**
     * 查询所有
     */
    List<Category> selectAll(Category category);

    /**
     * 分页查询
     */
    PageInfo<Category> selectPage(Category category, Integer pageNum, Integer pageSize);

}