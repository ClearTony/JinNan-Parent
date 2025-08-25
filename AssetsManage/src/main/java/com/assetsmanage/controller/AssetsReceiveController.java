package com.assetsmanage.controller;

import com.assetsmanage.common.Result;
import com.assetsmanage.entity.AssetsReceive;
import com.assetsmanage.mapper.AssetsReceiveMapper;
import com.assetsmanage.service.AssetsReceiveService;
import com.assetsmanage.service.AssetsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资产领用前端操作接口
 **/
@RestController
@RequestMapping("/assetsReceive")
public class AssetsReceiveController {

    @Autowired
    private AssetsReceiveService assetsReceiveService;
    @Autowired
    private AssetsService assetsService;
    @Autowired
    private AssetsReceiveMapper assetsReceiveMapper;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody AssetsReceive assetsReceive) {

        assetsReceiveService.add(assetsReceive);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        assetsReceiveService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        assetsReceiveService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody AssetsReceive assetsReceive) {
        assetsReceiveService.updateById(assetsReceive);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        AssetsReceive assetsReceive = assetsReceiveService.selectById(id);
        return Result.success(assetsReceive);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(AssetsReceive assetsReceive) {
        List<AssetsReceive> list = assetsReceiveService.selectAll(assetsReceive);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(AssetsReceive assetsReceive,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<AssetsReceive> page = assetsReceiveService.selectPage(assetsReceive, pageNum, pageSize);
        return Result.success(page);
    }

}