package com.assetsmanage.service;

import com.assetsmanage.entity.AssetsReceive;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资产领用业务处理
 **/
@Service
public interface AssetsReceiveService {


    void add(AssetsReceive assetsReceive);

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
    void updateById(AssetsReceive assetsReceive);

    /**
     * 根据ID查询
     */
    AssetsReceive selectById(Integer id);

    /**
     * 查询所有
     */
    List<AssetsReceive> selectAll(AssetsReceive assetsReceive);


    /**
     * 分页查询
     */
    PageInfo<AssetsReceive> selectPage(AssetsReceive assetsReceive, Integer pageNum, Integer pageSize);

}