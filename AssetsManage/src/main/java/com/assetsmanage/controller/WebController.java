package com.assetsmanage.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.assetsmanage.common.Result;
import com.assetsmanage.common.enums.ResultCodeEnum;
import com.assetsmanage.common.enums.RoleEnum;
import com.assetsmanage.entity.Account;
import com.assetsmanage.entity.Category;
import com.assetsmanage.service.AdminService;
import com.assetsmanage.service.AssetsService;
import com.assetsmanage.service.CategoryService;
import com.assetsmanage.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础前端接口
 */
@RestController
public class WebController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AssetsService assetsService;
    @GetMapping("/")
    public Result hello() {
        return Result.success("访问成功");
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        if (ObjectUtil.isEmpty(account.getUsername()) || ObjectUtil.isEmpty(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            account = adminService.login(account);
        } else if (RoleEnum.STAFF.name().equals(account.getRole())) {
            account = staffService.login(account);
        }
        return Result.success(account);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.STAFF.name().equals(account.getRole())) {
            staffService.register(account);
        } else {
            return Result.error("500", "只允许注册员工");
        }
        return Result.success();
    }


    /**
     * 修改密码
     */
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getNewPassword())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            adminService.updatePassword(account);
        }else if (RoleEnum.STAFF.name().equals(account.getRole())) {
            staffService.updatePassword(account);
        }
        return Result.success();
    }

    /**
     * 分类资产的数据统计
     */
    @GetMapping("/selectPie")
    public Result selectPie() {
        List<Category> categoryList = categoryService.selectAll(null);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Category category : categoryList) {
            int count = assetsService.selectCountByCategory(category.getName());
            if(count==0){
                continue;
            }
            Map<String, Object> map = new HashMap<>();
            map.put("name", category.getName());
            map.put("value", count);
            list.add(map);
        }
        return Result.success(list);
    }

}
