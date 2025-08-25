package com.assetsmanage.service;

import com.assetsmanage.entity.AssetsIn;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 资产入库业务处理
 **/

public interface AssetsInService {
    /**
     * 新增
     */
    void add(AssetsIn assetsIn);

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
    void updateById(AssetsIn assetsIn);

    /**
     * 根据ID查询
     */
    AssetsIn selectById(Integer id);

    /**
     * 查询所有
     */
    List<AssetsIn> selectAll(AssetsIn assetsIn);

    /**
     * 分页查询
     */
    PageInfo<AssetsIn> selectPage(AssetsIn assetsIn, Integer pageNum, Integer pageSize);

}