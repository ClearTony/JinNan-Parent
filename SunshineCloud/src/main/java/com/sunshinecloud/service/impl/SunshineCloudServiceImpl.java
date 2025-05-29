package com.sunshinecloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sunshinecloud.Enum.ApiRequestEnum;
import com.sunshinecloud.service.SunshineCloudService;
import com.sunshinecloud.utils.ParamsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class SunshineCloudServiceImpl implements SunshineCloudService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(SunshineCloudServiceImpl.class);
    @Override
    public String sunnyLogin() {
        String token = "";
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("user_account", "13434361011");
            params.put("user_password", "gold1234");
            String response = ApiRequestEnum.SERVICE_LOGIN.sendRequest(params);
            Map<String, Object> resultMap = (Map) JSONObject.parse(response);
            if (resultMap.get("result_code").equals("1")) {
                Object resultData = resultMap.get("result_data");
                Map<String, Object> resultMap2 = (Map) JSONObject.parse(resultData.toString());
                token = (String) resultMap2.get("token");
                redisTemplate.opsForValue().set(ParamsUtils.SunnyToken, token, 24, TimeUnit.HOURS);
            } else {
                return resultMap.get("result_msg").toString();
            }
        } catch (Exception e) {
            logger.error("Exception occurred in sunnyLogin: ", e);
        }
        return token;
    }

    @Override
    public Map<String, Object> queryPowerStationList(Map<String,Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = ApiRequestCommon(params,ApiRequestEnum.SERVICE_POWER_STATION_LIST);
            return resultMap;
        } catch (Exception e) {
            logger.error("Exception occurred in queryPowerStationList: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getDeviceList(Map<String,Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = ApiRequestCommon(params,ApiRequestEnum.SERVICE_POWER_DEVICE_LIST);
            return resultMap;
        } catch (Exception e) {
            logger.error("Exception occurred in getDeviceList: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getPowerStationDetail(Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = ApiRequestCommon(params,ApiRequestEnum.SERVICE_POWER_STATION_DETAIL);
        } catch (Exception e) {
            logger.error("Exception occurred in getPowerStationDetail: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getDevicePointMinuteDataList(Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = ApiRequestCommon(params,ApiRequestEnum.SERVICE_POWER_DEVICE_POINT_MINUTE_DATA_LIST);
        } catch (Exception e) {
            logger.error("Exception occurred in getDevicePointMinuteDataList: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getDeviceRealTimeData(Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = ApiRequestCommon(params,ApiRequestEnum.SERVICE_POWER_DEVICE_REAL_TIME_DATA);
        } catch (Exception e) {
            logger.error("Exception occurred in getDeviceRealTimeData: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getDevPropertyPointValue(Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = ApiRequestCommon(params,ApiRequestEnum.SERVICE_POWER_DEV_PROPERTY_POINT_VALUE);
        } catch (Exception e) {
            logger.error("Exception occurred in getDevPropertyPointValue: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getFaultAlarmInfo(Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = ApiRequestCommon(params,ApiRequestEnum.SERVICE_POWER_FAULT_ALARM_INFO);
        } catch (Exception e) {
            logger.error("Exception occurred in getFaultAlarmInfo: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getOpenPointInfo(Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = ApiRequestCommon(params,ApiRequestEnum.SERVICE_POWER_OPEN_POINT_INFO);
        } catch (Exception e) {
            logger.error("Exception occurred in getOpenPointInfo: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getDeviceListByUser(Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = ApiRequestCommon(params,ApiRequestEnum.SERVICE_POWER_DEVICE_LIST_BY_USER);
        } catch (Exception e) {
            logger.error("Exception occurred in getDeviceListByUser: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getCommunicationDevInfoByDevSn(Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = ApiRequestCommon(params,ApiRequestEnum.SERVICE_POWER_COMMUNICATION_DEV_INFO_BY_DEV_SN);
        } catch (Exception e) {
            logger.error("Exception occurred in getCommunicationDevInfoByDevSn: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getPVInverterRealTimeData(Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = ApiRequestCommon(params,ApiRequestEnum.SERVICE_POWER_PV_INVERTER_REAL_TIME_DATA);
        } catch (Exception e) {
            logger.error("Exception occurred in getPVInverterRealTimeData: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getOpenApiCallInfo(Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = ApiRequestCommon(params,ApiRequestEnum.SERVICE_POWER_OPEN_API_CALL_INFO);
        } catch (Exception e) {
            logger.error("Exception occurred in getOpenApiCallInfo: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getBatchPsDetail(Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = ApiRequestCommon(params,ApiRequestEnum.SERVICE_POWER_BATCH_PS_DETAIL);
        } catch (Exception e) {
            logger.error("Exception occurred in getBatchPsDetail: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getDeviceStringInfo(Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = ApiRequestCommon(params,ApiRequestEnum.SERVICE_POWER_DEVICE_STRING_INFO);
        } catch (Exception e) {
            logger.error("Exception occurred in getDeviceStringInfo: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getMlpeDeviceList(Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = ApiRequestCommon(params,ApiRequestEnum.SERVICE_POWER_MLPE_DEVICE_LIST);
        } catch (Exception e) {
            logger.error("Exception occurred in getMlpeDeviceList: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getMlpeRealTimeData(Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = ApiRequestCommon(params,ApiRequestEnum.SERVICE_POWER_MLPERE_REAL_TIME_DATA);
        } catch (Exception e) {
            logger.error("Exception occurred in getMlpeRealTimeData: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getMlpeMinuteDataList(Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = ApiRequestCommon(params,ApiRequestEnum.SERVICE_POWER_MLPE_MINUTE_DATA_LIST);
        } catch (Exception e) {
            logger.error("Exception occurred in getMlpeMinuteDataList: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getMlpeDayMonthYearDataList(Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = ApiRequestCommon(params,ApiRequestEnum.SERVICE_POWER_MLPE_DAY_MONTH_YEAR_DATA_LIST);
        } catch (Exception e) {
            logger.error("Exception occurred in getMlpeDayMonthYearDataList: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getDevicePointsDayMonthYearDataList(Map<String, Object> params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = ApiRequestCommon(params,ApiRequestEnum.SERVICE_POWER_DEVICE_POINT_DAY_MONTH_YEAR_DATA_LIST);
        } catch (Exception e) {
            logger.error("Exception occurred in getDevicePointsDayMonthYearDataList: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }

    private Map<String, Object> ApiRequestCommon(Map<String, Object> params,ApiRequestEnum apiRequestEnum){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            String token = redisTemplate.opsForValue().get(ParamsUtils.SunnyToken);
            if (token == null){
                token = this.sunnyLogin();
            }
            params.put("token",token);
            String response = apiRequestEnum.sendRequest(params);
            resultMap = (Map) JSONObject.parse(response);
            return resultMap;
        } catch (Exception e) {
            logger.error("Exception occurred in ApiRequestCommon: ", e);
            resultMap.put("error", "Internal server error");
        }
        return resultMap;
    }
}
