package com.sunshinecloud.controller;

import com.common.jinnnacommon.common.ResponseResult;
import com.sunshinecloud.service.SunshineCloudService;
import com.sunshinecloud.utils.ParamsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sunny")
public class SunshineCloudController {
    @Autowired
    private SunshineCloudService sunshineCloudService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseResult login() {
        Map<String,Object> map = new HashMap<>();
        redisTemplate.delete(ParamsUtils.SunnyToken);
        String token = sunshineCloudService.sunnyLogin();
        map.put("token",token);
        return ResponseResult.success(map);
    }

    @RequestMapping(value = "/queryPowerStationList",method = RequestMethod.POST)
    public ResponseResult queryPowerStationList(@RequestBody Map<String,Object> params) {
        Map<String,Object> map = sunshineCloudService.queryPowerStationList(params);
        return ResponseResult.success(map);
    }

    @RequestMapping(value = "/getDeviceList",method = RequestMethod.POST)
    public ResponseResult getDeviceList(@RequestBody Map<String,Object> params) {
        Map<String,Object> map = sunshineCloudService.getDeviceList(params);
        return ResponseResult.success(map);
    }


    @RequestMapping(value = "/getPowerStationDetail",method = RequestMethod.POST)
    public ResponseResult getPowerStationDetail(@RequestBody Map<String,Object> params) {
        Map<String,Object> map = sunshineCloudService.getPowerStationDetail(params);
        return ResponseResult.success(map);
    }

    @RequestMapping(value = "/getDevicePointMinuteDataList",method = RequestMethod.POST)
    public ResponseResult getDevicePointMinuteDataList(@RequestBody Map<String,Object> params) {
        Map<String,Object> map = sunshineCloudService.getDevicePointMinuteDataList(params);
        return ResponseResult.success(map);
    }

    @RequestMapping(value = "/getDevicePointsDayMonthYearDataList",method = RequestMethod.POST)
    public ResponseResult getDevicePointsDayMonthYearDataList(@RequestBody Map<String,Object> params) {
        Map<String,Object> map = sunshineCloudService.getDevicePointsDayMonthYearDataList(params);
        return ResponseResult.success(map);
    }

    @RequestMapping(value = "/getDeviceRealTimeData",method = RequestMethod.POST)
    public ResponseResult getDeviceRealTimeData(@RequestBody Map<String,Object> params) {
        Map<String,Object> map = sunshineCloudService.getDeviceRealTimeData(params);
        return ResponseResult.success(map);
    }

    @RequestMapping(value = "/getDevPropertyPointValue",method = RequestMethod.POST)
    public ResponseResult getDevPropertyPointValue(@RequestBody Map<String,Object> params) {
        Map<String,Object> map = sunshineCloudService.getDevPropertyPointValue(params);
        return ResponseResult.success(map);
    }

    @RequestMapping(value = "/getFaultAlarmInfo",method = RequestMethod.POST)
    public ResponseResult getFaultAlarmInfo(@RequestBody Map<String,Object> params) {
        Map<String,Object> map = sunshineCloudService.getFaultAlarmInfo(params);
        return ResponseResult.success(map);
    }

    @RequestMapping(value = "/getOpenPointInfo",method = RequestMethod.POST)
    public ResponseResult getOpenPointInfo(@RequestBody Map<String,Object> params) {
        Map<String,Object> map = sunshineCloudService.getOpenPointInfo(params);
        return ResponseResult.success(map);
    }

    @RequestMapping(value = "/getDeviceListByUser",method = RequestMethod.POST)
    public ResponseResult getDeviceListByUser(@RequestBody Map<String,Object> params) {
        Map<String,Object> map = sunshineCloudService.getDeviceListByUser(params);
        return ResponseResult.success(map);
    }

    @RequestMapping(value = "/getCommunicationDevInfoByDevSn",method = RequestMethod.POST)
    public ResponseResult getCommunicationDevInfoByDevSn(@RequestBody Map<String,Object> params) {
        Map<String,Object> map = sunshineCloudService.getCommunicationDevInfoByDevSn(params);
        return ResponseResult.success(map);
    }

    @RequestMapping(value = "/getPVInverterRealTimeData",method = RequestMethod.POST)
    public ResponseResult getPVInverterRealTimeData(@RequestBody Map<String,Object> params) {
        Map<String,Object> map = sunshineCloudService.getPVInverterRealTimeData(params);
        return ResponseResult.success(map);
    }

    @RequestMapping(value = "/getOpenApiCallInfo",method = RequestMethod.POST)
    public ResponseResult getOpenApiCallInfo(@RequestBody Map<String,Object> params) {
        Map<String,Object> map = sunshineCloudService.getOpenApiCallInfo(params);
        return ResponseResult.success(map);
    }

    @RequestMapping(value = "/getBatchPsDetail",method = RequestMethod.POST)
    public ResponseResult getBatchPsDetail(@RequestBody Map<String,Object> params) {
        Map<String,Object> map = sunshineCloudService.getBatchPsDetail(params);
        return ResponseResult.success(map);
    }

    @RequestMapping(value = "/getDeviceStringInfo",method = RequestMethod.POST)
    public ResponseResult getDeviceStringInfo(@RequestBody Map<String,Object> params) {
        Map<String,Object> map = sunshineCloudService.getDeviceStringInfo(params);
        return ResponseResult.success(map);
    }

    @RequestMapping(value = "/getMlpeDeviceList",method = RequestMethod.POST)
    public ResponseResult getMlpeDeviceList(@RequestBody Map<String,Object> params) {
        Map<String,Object> map = sunshineCloudService.getMlpeDeviceList(params);
        return ResponseResult.success(map);
    }

    @RequestMapping(value = "/getMlpeRealTimeData",method = RequestMethod.POST)
    public ResponseResult getMlpeRealTimeData(@RequestBody Map<String,Object> params) {
        Map<String,Object> map = sunshineCloudService.getMlpeRealTimeData(params);
        return ResponseResult.success(map);
    }

    @RequestMapping(value = "/getMlpeMinuteDataList",method = RequestMethod.POST)
    public ResponseResult getMlpeMinuteDataList(@RequestBody Map<String,Object> params) {
        Map<String,Object> map = sunshineCloudService.getMlpeMinuteDataList(params);
        return ResponseResult.success(map);
    }


    @RequestMapping(value = "/getMlpeDayMonthYearDataList",method = RequestMethod.POST)
    public ResponseResult getMlpeDayMonthYearDataList(@RequestBody Map<String,Object> params) {
        Map<String,Object> map = sunshineCloudService.getMlpeDayMonthYearDataList(params);
        return ResponseResult.success(map);
    }





}
