package com.assetsmanage.service;

import com.assetsmanage.entity.Account;
import com.assetsmanage.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 管理员业务处理
 **/

public interface AdminService {

    void add(Admin admin);

    void deleteById(Integer id);

    void deleteBatch(List<Integer> ids);

    void updateById(Admin admin);

    Admin selectById(Integer id);

    List<Admin> selectAll(Admin admin);

    PageInfo<Admin> selectPage(Admin admin, Integer pageNum, Integer pageSize);

    Account login(Account account);

    void register(Account account);

    void updatePassword(Account account);

}