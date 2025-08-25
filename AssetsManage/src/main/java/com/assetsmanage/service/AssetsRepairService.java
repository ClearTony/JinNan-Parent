package com.assetsmanage.service;

import com.assetsmanage.entity.AssetsRepair;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资产报修业务处理
 **/
public interface AssetsRepairService {


    /**
     * 新增
     */
    void add(AssetsRepair assetsRepair);

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
    void updateById(AssetsRepair assetsRepair);

    /**
     * 根据ID查询
     */
    AssetsRepair selectById(Integer id);

    /**
     * 查询所有
     */
    List<AssetsRepair> selectAll(AssetsRepair assetsRepair);

    /**
     * 分页查询
     */
    PageInfo<AssetsRepair> selectPage(AssetsRepair assetsRepair, Integer pageNum, Integer pageSize);

}