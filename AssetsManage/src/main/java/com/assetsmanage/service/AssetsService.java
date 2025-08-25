package com.assetsmanage.service;

import com.assetsmanage.common.Result;
import com.assetsmanage.dto.AssetsDto;
import com.assetsmanage.dto.AssetsExportDto;
import com.assetsmanage.entity.Assets;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 资产信息业务处理
 **/
public interface AssetsService {
    /**
     * 新增
     */
    void add(AssetsDto assetsDto);

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
    void updateById(AssetsDto assetsDto);

    /**
     * 根据ID查询
     */
    Assets selectById(Integer id);

    /**
     * 查询所有
     */
    List<AssetsDto> selectAll(Assets assets);

    /**
     * 分页查询
     */
    PageInfo<AssetsDto> selectPage(Assets assets, Integer pageNum, Integer pageSize);


    int selectCountByCategory(String category);


    Result importData(MultipartFile file);

    List<AssetsExportDto> selectExportData();
}