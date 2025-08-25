package com.assetsmanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.assetsmanage.common.config.InsertListMapper;
import com.assetsmanage.dto.AssetsDto;
import com.assetsmanage.dto.AssetsExportDto;
import com.assetsmanage.entity.Assets;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作assets相关数据接口
 */
public interface AssetsMapper extends BaseMapper<Assets>,InsertListMapper<Assets> {

    /**
     * 新增
     */
    int insert(Assets assets);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Assets assets);

    /**
     * 根据ID查询
     */
    Assets selectById(Integer id);

    /**
     * 查询所有
     */
    List<AssetsDto> selectAll(Assets assets);

    @Select("select count(*) from assets where category = #{category}")
    int selectCountByCategory(String category);

    List<AssetsExportDto> selectExportData(Assets assets);
}