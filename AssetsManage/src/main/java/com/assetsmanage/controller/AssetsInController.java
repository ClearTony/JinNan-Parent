package com.assetsmanage.controller;

import com.assetsmanage.common.Result;
import com.assetsmanage.entity.AssetsIn;
import com.assetsmanage.service.AssetsInService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资产入库前端操作接口
 **/
@RestController
@RequestMapping("/assetsIn")
public class AssetsInController {

    @Autowired
    private AssetsInService assetsInService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody AssetsIn assetsIn) {
        assetsInService.add(assetsIn);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        assetsInService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        assetsInService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody AssetsIn assetsIn) {
        assetsInService.updateById(assetsIn);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        AssetsIn assetsIn = assetsInService.selectById(id);
        return Result.success(assetsIn);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(AssetsIn assetsIn) {
        List<AssetsIn> list = assetsInService.selectAll(assetsIn);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(AssetsIn assetsIn,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<AssetsIn> page = assetsInService.selectPage(assetsIn, pageNum, pageSize);
        return Result.success(page);
    }

}