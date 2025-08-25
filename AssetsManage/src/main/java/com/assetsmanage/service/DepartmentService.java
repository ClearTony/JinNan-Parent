package com.assetsmanage.service;

import com.assetsmanage.entity.Department;
import com.assetsmanage.mapper.DepartmentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 部门信息业务处理
 **/
public interface DepartmentService {
    /**
     * 新增
     */
    void add(Department department);
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
    void updateById(Department department);
    /**
     * 根据ID查询
     */
    Department selectById(Integer id);
    /**
     * 查询所有
     */
    List<Department> selectAll(Department department);
    /**
     * 分页查询
     */
    PageInfo<Department> selectPage(Department department, Integer pageNum, Integer pageSize);

    List<Department> selectTree();

    List<Department> selectParent();
}