package com.sunshinecloud.Enum;

import com.sunshinecloud.utils.HttpClientUtils;

import java.util.HashMap;
import java.util.Map;

public enum ApiRequestEnum {
    SERVICE_LOGIN("/openapi/login"),
    SERVICE_POWER_STATION_LIST("/openapi/getPowerStationList"),
    SERVICE_POWER_DEVICE_LIST("/openapi/getDeviceList"),
    SERVICE_POWER_DEVICE_REAL_TIME_DATA("/openapi/getDeviceRealTimeData"),
    SERVICE_POWER_DEVICE_POINT_MINUTE_DATA_LIST("/openapi/getDevicePointMinuteDataList"),
    SERVICE_POWER_DEVICE_POINT_DAY_MONTH_YEAR_DATA_LIST("/openapi/getDevicePointsDayMonthYearDataList"),
    SERVICE_POWER_DEV_PROPERTY_POINT_VALUE("/openapi/getDevPropertyPointValue"),
    SERVICE_POWER_FAULT_ALARM_INFO("/openapi/getFaultAlarmInfo"),
    SERVICE_POWER_OPEN_POINT_INFO("/openapi/getOpenPointInfo"),
    SERVICE_POWER_DEVICE_LIST_BY_USER("/openapi/getDeviceListByUser"),
    SERVICE_POWER_COMMUNICATION_DEV_INFO_BY_DEV_SN("/openapi/getCommunicationDevInfoByDevSn"),
    SERVICE_POWER_PV_INVERTER_REAL_TIME_DATA("/openapi/getPVInverterRealTimeData"),
    SERVICE_POWER_OPEN_API_CALL_INFO("/openapi/getOpenApiCallInfo"),
    SERVICE_POWER_BATCH_PS_DETAIL("/openapi/getBatchPsDetail"),
    SERVICE_POWER_DEVICE_STRING_INFO("/openapi/getDeviceStringInfo"),
    SERVICE_POWER_MLPE_DEVICE_LIST("/openapi/getMlpeDeviceList"),
    SERVICE_POWER_MLPERE_REAL_TIME_DATA("/openapi/getMlpeRealTimeData"),
    SERVICE_POWER_MLPE_MINUTE_DATA_LIST("/openapi/getMlpeMinuteDataList"),
    SERVICE_POWER_MLPE_DAY_MONTH_YEAR_DATA_LIST("/openapi/getMlpeDayMonthYearDataList"),
    SERVICE_POWER_STATION_DETAIL("/openapi/getPowerStationDetail");


    private final String url;

    ApiRequestEnum(String url) {
        this.url = url;
    }

    /**
     * 调用 HttpClientUtils 发送 POST 请求
     *
     * @param params 业务参数（动态传入）
     * @return 响应结果
     * @throws Exception 请求失败时抛出异常
     */
    public String sendRequest(Map<String, Object> params) throws Exception {
        Map<String, Object> req = new HashMap<>();
        // 接口标识
        req.put("service", this.name());
        // 合并业务参数
        req.putAll(params);

        return HttpClientUtils.sendPost(this.url, req);
    }
}
