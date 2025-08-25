package com.assetsmanage.controller;

import com.assetsmanage.common.Result;
import com.assetsmanage.entity.Department;
import com.assetsmanage.service.DepartmentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门信息前端操作接口
 **/
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Department department) {
        departmentService.add(department);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        departmentService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        departmentService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Department department) {
        departmentService.updateById(department);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Department department = departmentService.selectById(id);
        return Result.success(department);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Department department) {
        List<Department> list = departmentService.selectAll(department);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Department department,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Department> page = departmentService.selectPage(department, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 查询树状表格数据
     */
    @GetMapping("/selectTree")
    public Result selectTree() {
        List<Department> tree = departmentService.selectTree();
        return Result.success(tree);
    }

    /**
     * 查询树状表格数据
     */
    @GetMapping("/selectParent")
    public Result selectParent() {
        List<Department> list = departmentService.selectParent();
        return Result.success(list);
    }
}