package com.sunshinecloud.controller;

import com.common.jinnnacommon.common.ResponseResult;
import com.sunshinecloud.service.DataBoardService;
import com.sunshinecloud.vo.BarChart;
import com.sunshinecloud.vo.LineChart;
import com.sunshinecloud.vo.PowerStationDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/data")
public class DataBoardController {
    @Autowired
    private DataBoardService dataBoardService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "/queryPowerStationDetail",method = RequestMethod.GET)
    public ResponseResult queryPowerStationList() {
        PowerStationDetail powerStationDetail = dataBoardService.queryPowerStationDetail();
        return ResponseResult.success(powerStationDetail);
    }

    @RequestMapping(value = "/getDayLineChart",method = RequestMethod.POST)
    public ResponseResult getDayLineChart(@RequestBody Map<String,Object> params) {
        LineChart lineChart = dataBoardService.getDayLineChart(params);
        return ResponseResult.success(lineChart);
    }

    @RequestMapping(value = "/getBarChart",method = RequestMethod.POST)
    public ResponseResult getBarChart(@RequestBody Map<String,Object> params) {
        BarChart barChart = dataBoardService.getBarChart(params);
        return ResponseResult.success(barChart);
    }



}

