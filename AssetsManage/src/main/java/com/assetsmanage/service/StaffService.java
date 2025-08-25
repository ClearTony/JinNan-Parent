package com.assetsmanage.service;

import com.assetsmanage.entity.Account;
import com.assetsmanage.entity.Staff;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 员工员业务处理
 **/
public interface StaffService {

    /**
     * 新增
     */

    void add(Staff staff);

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

    void updateById(Staff staff);

    /**
     * 根据ID查询
     */

    Staff selectById(Integer id);

    /**
     * 查询所有
     */

    List<Staff> selectAll(Staff staff);

    /**
     * 分页查询
     */

    PageInfo<Staff> selectPage(Staff staff, Integer pageNum, Integer pageSize);

    /**
     * 登录
     */

    Account login(Account account);

    /**
     * 注册
     */

    void register(Account account);

    /**
     * 修改密码
     */

    void updatePassword(Account account);
}