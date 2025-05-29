package com.sunshinecloud.service;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface SunshineCloudService {

    String sunnyLogin ();

    Map<String, Object> queryPowerStationList(Map<String,Object> params);

    Map<String, Object> getDeviceList(Map<String,Object> params);

    Map<String, Object> getPowerStationDetail(Map<String, Object> params);

    Map<String, Object> getDevicePointsDayMonthYearDataList(Map<String, Object> params);

    Map<String, Object> getDevicePointMinuteDataList(Map<String, Object> params);

    Map<String, Object> getDeviceRealTimeData(Map<String, Object> params);

    Map<String, Object> getDevPropertyPointValue(Map<String, Object> params);

    Map<String, Object> getFaultAlarmInfo(Map<String, Object> params);

    Map<String, Object> getOpenPointInfo(Map<String, Object> params);

    Map<String, Object> getDeviceListByUser(Map<String, Object> params);

    Map<String, Object> getCommunicationDevInfoByDevSn(Map<String, Object> params);

    Map<String, Object> getPVInverterRealTimeData(Map<String, Object> params);

    Map<String, Object> getOpenApiCallInfo(Map<String, Object> params);

    Map<String, Object> getBatchPsDetail(Map<String, Object> params);

    Map<String, Object> getDeviceStringInfo(Map<String, Object> params);

    Map<String, Object> getMlpeDeviceList(Map<String, Object> params);

    Map<String, Object> getMlpeRealTimeData(Map<String, Object> params);

    Map<String, Object> getMlpeMinuteDataList(Map<String, Object> params);

    Map<String, Object> getMlpeDayMonthYearDataList(Map<String, Object> params);
}
