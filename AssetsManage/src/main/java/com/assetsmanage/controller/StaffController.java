package com.assetsmanage.controller;

import com.assetsmanage.common.Result;
import com.assetsmanage.entity.Staff;
import com.assetsmanage.service.StaffService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工前端操作接口
 **/
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Staff staff) {
        staffService.add(staff);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        staffService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        staffService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Staff staff) {
        staffService.updateById(staff);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Staff staff = staffService.selectById(id);
        return Result.success(staff);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Staff staff ) {
        List<Staff> list = staffService.selectAll(staff);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Staff staff,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Staff> page = staffService.selectPage(staff, pageNum, pageSize);
        return Result.success(page);
    }

}