package com.sunshinecloud.service.impl;

import cn.hutool.core.date.DateUtil;
import com.sunshinecloud.service.DataBoardService;
import com.sunshinecloud.service.SunshineCloudService;
import com.sunshinecloud.vo.BarChart;
import com.sunshinecloud.vo.LineChart;
import com.sunshinecloud.vo.PowerStationDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class DataBoardServiceImpl implements DataBoardService {

    @Autowired
    private SunshineCloudService sunshineCloudService;

    @Override
    public PowerStationDetail queryPowerStationDetail() {
        Date nowDate = new Date();
        String month = DateUtil.format(nowDate, "yyyyMM");
        PowerStationDetail powerStationDetail = new PowerStationDetail();
        Map<String,Object> params = new HashMap<>();
        params.put("curPage", 1);
        params.put("size", 10);
        Map<String, Object> stringObjectMap = sunshineCloudService.queryPowerStationList(params);
        Map<String,Object> paramsMonth = new HashMap<>();
        paramsMonth.put("data_point", "p83022");
        paramsMonth.put("end_time", month);
        paramsMonth.put("query_type", "2");
        paramsMonth.put("start_time", month);
        paramsMonth.put("ps_key_list",List.of("1502586_11_0_0"));
        paramsMonth.put("data_type", "4");
        paramsMonth.put("order", 0);
        Map<String, Object> monthYearDataMap = sunshineCloudService.getDevicePointsDayMonthYearDataList(paramsMonth);
        if (stringObjectMap != null){
            Map<String,Object> resultData = (Map<String, Object>) stringObjectMap.get("result_data");
            List<Map<String,Object>> list = (List<Map<String, Object>>) resultData.get("pageList");
            Map<String, Object> pageList = list.get(0);
            Map<String,Object> totalEnergy = (Map<String, Object>) pageList.get("total_energy");
            powerStationDetail.setTotalEnergyValue(new BigDecimal(totalEnergy.get("value").toString()));
            powerStationDetail.setTotalEnergyUnit(totalEnergy.get("unit").toString());
            Map<String,Object> todayEnergy = (Map<String, Object>) pageList.get("today_energy");
            powerStationDetail.setTodayEnergyValue(new BigDecimal(todayEnergy.get("value").toString()));
            powerStationDetail.setTodayEnergyUnit(todayEnergy.get("unit").toString());
            Map<String,Object> todayIncome = (Map<String, Object>) pageList.get("today_income");
            powerStationDetail.setTodayIncomeValue(new BigDecimal(todayIncome.get("value").toString()));
            powerStationDetail.setTodayIncomeUnit(todayIncome.get("unit").toString());
            Map<String,Object> monthIncome = (Map<String, Object>) pageList.get("month_income");
            powerStationDetail.setMonthIncomeValue(new BigDecimal(monthIncome.get("value").toString()));
            powerStationDetail.setMonthIncomeUnit(monthIncome.get("unit").toString());
            Map<String,Object> totalIncome = (Map<String, Object>) pageList.get("total_income");
            powerStationDetail.setTotalIncomeValue(new BigDecimal(totalIncome.get("value").toString()));
            powerStationDetail.setTotalIncomeUnit(totalIncome.get("unit").toString());
            Map<String,Object> currPower = (Map<String, Object>) pageList.get("curr_power");
            powerStationDetail.setCurrPowerValue(new BigDecimal(currPower.get("value").toString()));
            powerStationDetail.setCurrPowerUnit(currPower.get("unit").toString());
            Map<String,Object> totalCapcity = (Map<String, Object>) pageList.get("total_capcity");
            powerStationDetail.setTotalCapcityValue(new BigDecimal(totalCapcity.get("value").toString()));
            powerStationDetail.setTotalCapcityUnit(totalCapcity.get("unit").toString());
            BigDecimal coTwoValue = powerStationDetail.getTotalEnergyValue().multiply(new BigDecimal("0.997")).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
            powerStationDetail.setCoTwoValue(coTwoValue);
            powerStationDetail.setCoTwoUnit("千吨");
            BigDecimal standardCoalValue = powerStationDetail.getTotalEnergyValue().multiply(new BigDecimal("4.04")).setScale(2, BigDecimal.ROUND_HALF_UP);
            powerStationDetail.setStandardCoalValue(standardCoalValue);
            powerStationDetail.setStandardCoalUnit("吨");
            BigDecimal standingWoodValue = powerStationDetail.getTotalEnergyValue().multiply(new BigDecimal("0.054")).setScale(2, BigDecimal.ROUND_HALF_UP);
            powerStationDetail.setStandingWoodValue(standingWoodValue);
            powerStationDetail.setStandingWoodUnit("万棵");
        }
        if (monthYearDataMap != null){
            Map<String,Object> resultData = (Map<String, Object>) monthYearDataMap.get("result_data");
            Map<String,Object> dataMap = (Map<String, Object>) resultData.get("1502586_11_0_0");
            List<Map<String,Object>> list  = (List<Map<String, Object>>) dataMap.get("p83022");
            Map<String, Object> objectMap = list.get(0);
            String value  = objectMap.get("4").toString();
            BigDecimal monthTotal = new BigDecimal(value);
            powerStationDetail.setMonthEnergyValue( monthTotal.divide(new BigDecimal("10000"), 2, BigDecimal.ROUND_HALF_UP));
            powerStationDetail.setMonthEnergyUnit("万度");
        }
        return powerStationDetail;
    }

    @Override
    public LineChart getDayLineChart(Map<String,Object> params) {
        LineChart lineChart = new LineChart();
        List<String> xAxisYesterDayDatas = new ArrayList<>();
        List<BigDecimal> yAxisYesterDayDatas = new ArrayList<>();
        List<BigDecimal> yAxisDatas = new ArrayList<>();
        Map<String,Object> yesterdayParams = new HashMap<>();
        yesterdayParams.put("points", "p83033");
        yesterdayParams.put("minute_interval", 5);
        yesterdayParams.put("is_get_data_acquisition_time", "1");
        yesterdayParams.put("ps_key_list",List.of("1502586_11_0_0"));
        LocalDateTime nowStartTime = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime nowEndTime = LocalDateTime.now();
        // 获取昨天的00:00
        LocalDateTime startTime = LocalDateTime.now().minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        // 获取第二天的00:00
        LocalDateTime endTime = startTime.plusDays(1);
        // 使用 for 循环，每次加3小时
        for (LocalDateTime currentTime = startTime; currentTime.isBefore(endTime); currentTime = currentTime.plusHours(3)) {
            yesterdayParams.put("start_time_stamp",DateUtil.format(currentTime,  "yyyyMMddHHmmss"));
            yesterdayParams.put("end_time_stamp",DateUtil.format(currentTime.plusHours(3),  "yyyyMMddHHmmss"));
            Map<String, Object> devicePointMinuteDataList = sunshineCloudService.getDevicePointMinuteDataList(yesterdayParams);
            Map<String, Object> resultData = (Map<String, Object>) devicePointMinuteDataList.get("result_data");
            List<Map<String,Object>> resultList  = (List<Map<String, Object>>) resultData.get("1502586_11_0_0");
            for (Map<String, Object> objectMap : resultList) {
                String timeStamp = (String) objectMap.get("time_stamp");
                String value = (String) objectMap.get("p83033");
                // 定义输入格式
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
                // 解析字符串为 LocalDateTime
                LocalDateTime dateTime = LocalDateTime.parse(timeStamp, inputFormatter);
                // 提取时间部分并格式化为 HH:mm
                String timeOnly = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                xAxisYesterDayDatas.add(timeOnly);
                yAxisYesterDayDatas.add(new BigDecimal(value));
            }
        }
        for (LocalDateTime currentTime = nowStartTime; currentTime.isBefore(nowEndTime); currentTime = currentTime.plusHours(3)){
            yesterdayParams.put("start_time_stamp",DateUtil.format(currentTime,  "yyyyMMddHHmmss"));
            yesterdayParams.put("end_time_stamp",DateUtil.format(currentTime.plusHours(3),  "yyyyMMddHHmmss"));
            Map<String, Object> devicePointMinuteDataList = sunshineCloudService.getDevicePointMinuteDataList(yesterdayParams);
            Map<String, Object> resultData = (Map<String, Object>) devicePointMinuteDataList.get("result_data");
            List<Map<String,Object>> resultList  = (List<Map<String, Object>>) resultData.get("1502586_11_0_0");
            for (Map<String, Object> objectMap : resultList) {
                String value = (String) objectMap.get("p83033");
                yAxisDatas.add(new BigDecimal(value));
            }
        }
        lineChart.setXAxisYesterDayDatas(xAxisYesterDayDatas);
        lineChart.setYAxisYesterDayDatas(yAxisYesterDayDatas);
        lineChart.setYAxisNowDatas(yAxisDatas);
        return lineChart;
    }

    @Override
    public BarChart getBarChart(Map<String, Object> params) {
        Map<String,Object> dayParams = new HashMap<>();
        dayParams.put("point_id_list", List.of("83022"));
        dayParams.put("device_type", "11");
        dayParams.put("ps_key_list",List.of("1502586_11_0_0"));
        Map<String, Object> deviceRealTimeData = sunshineCloudService.getDeviceRealTimeData(dayParams);
        Map<String, Object> deviceResultData = (Map<String, Object>) deviceRealTimeData.get("result_data");
        List<Map<String, Object>> devicePointList= (List<Map<String, Object>>) deviceResultData.get("device_point_list");
        Map<String, Object> map = devicePointList.get(0);
        Map<String, Object> devicePointMap = (Map<String, Object>) map.get("device_point");
        String dayValueStr = (String) devicePointMap.get("p83022");
        BigDecimal dayValue = new BigDecimal(dayValueStr).divide(new BigDecimal("1000"),1, BigDecimal.ROUND_HALF_UP);

        BarChart barChart = new BarChart();
        List<String> weekXAxis = new ArrayList<>();
        List<BigDecimal> weekYAxis = new ArrayList<>();
        List<String> monthXAxis = new ArrayList<>();
        List<BigDecimal> monthYAxis = new ArrayList<>();
        List<String> yearXAxis = new ArrayList<>();
        List<BigDecimal> yearYAxis = new ArrayList<>();

        Map<String,Object> weekParams = new HashMap<>();
        weekParams.put("data_point", "p83022");
        weekParams.put("query_type", "1");
        weekParams.put("data_type", "2");
        weekParams.put("order", "0");
        weekParams.put("ps_key_list",List.of("1502586_11_0_0"));
        LocalDateTime nowWeekDate = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime lastWeekDate = LocalDateTime.now().minusDays(6).withHour(0).withMinute(0).withSecond(0).withNano(0);
        String lastWeekDateStr = DateUtil.format(lastWeekDate, "yyyyMMdd");
        String nowWeekDateStr = DateUtil.format(nowWeekDate, "yyyyMMdd");
        weekParams.put("start_time", lastWeekDateStr);
        weekParams.put("end_time",nowWeekDateStr);
        Map<String, Object> devicePointsWeekList = sunshineCloudService.getDevicePointsDayMonthYearDataList(weekParams);
        Map<String, Object> resultWeekData = (Map<String, Object>) devicePointsWeekList.get("result_data");
        Map<String, Object> objectWeekMap  = (Map<String, Object>)resultWeekData.get("1502586_11_0_0");
        List<Map<String, Object>> resultWeekList = (List<Map<String, Object>>) objectWeekMap.get("p83022");
        for (Map<String, Object> stringObjectMap : resultWeekList) {
            weekXAxis.add(stringObjectMap.get("time_stamp").toString());
            BigDecimal value = new BigDecimal(stringObjectMap.get("2").toString()).divide(new BigDecimal("1000"),1,BigDecimal.ROUND_HALF_UP);
            weekYAxis.add(value);
        }
        weekXAxis.add(nowWeekDateStr);
        weekYAxis.add(dayValue);
        barChart.setWeekXAxis(weekXAxis);
        barChart.setWeekYAxis(weekYAxis);

        Map<String,Object> monthParams = new HashMap<>();
        monthParams.put("data_point", "p83022");
        monthParams.put("query_type", "1");
        monthParams.put("data_type", "2");
        monthParams.put("order", "0");
        monthParams.put("ps_key_list",List.of("1502586_11_0_0"));
        LocalDateTime firstDayOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        String firstDayOfMonthStr = DateUtil.format(firstDayOfMonth, "yyyyMMdd");
        monthParams.put("start_time", firstDayOfMonthStr);
        monthParams.put("end_time",nowWeekDateStr);
        Map<String, Object> devicePointsMonthList = sunshineCloudService.getDevicePointsDayMonthYearDataList(monthParams);
        Map<String, Object> resultDataMonth = (Map<String, Object>) devicePointsMonthList.get("result_data");
        Map<String, Object> resultMonthData = (Map<String, Object>) resultDataMonth.get("1502586_11_0_0");
        List<Map<String, Object>> resultMonthList = (List<Map<String, Object>>) resultMonthData.get("p83022");
        for (Map<String, Object> stringObjectMap : resultMonthList) {
            monthXAxis.add(stringObjectMap.get("time_stamp").toString());
            BigDecimal value = new BigDecimal(stringObjectMap.get("2").toString()).divide(new BigDecimal("1000"),1,BigDecimal.ROUND_HALF_UP);
            monthYAxis.add(value);
        }
        monthXAxis.add(nowWeekDateStr);
        monthYAxis.add(dayValue);
        barChart.setMonthXAxis(monthXAxis);
        barChart.setMonthYAxis(monthYAxis);

        Map<String,Object> yearParams = new HashMap<>();
        yearParams.put("data_point", "p83022");
        yearParams.put("query_type", "2");
        yearParams.put("data_type", "4");
        yearParams.put("order", "0");
        yearParams.put("ps_key_list",List.of("1502586_11_0_0"));
        LocalDateTime firstDayOfYear = LocalDateTime.now().withDayOfYear(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        String firstDayOfYearStr = DateUtil.format(firstDayOfYear, "yyyyMM");
        String nowDayOfYearStr = DateUtil.format(nowWeekDate, "yyyyMM");
        yearParams.put("start_time", firstDayOfYearStr);
        yearParams.put("end_time",nowDayOfYearStr);
        Map<String, Object> devicePointsYearList = sunshineCloudService.getDevicePointsDayMonthYearDataList(yearParams);

        Map<String, Object> resultDataYear = (Map<String, Object>) devicePointsYearList.get("result_data");
        Map<String, Object> objectYearMap  = (Map<String, Object>)resultDataYear.get("1502586_11_0_0");
        List<Map<String, Object>> resultYearList = (List<Map<String, Object>>) objectYearMap.get("p83022");
        for (Map<String, Object> stringObjectMap : resultYearList) {
            yearXAxis.add(stringObjectMap.get("time_stamp").toString());
            BigDecimal value = new BigDecimal(stringObjectMap.get("4").toString()).divide(new BigDecimal("10000000"),1,BigDecimal.ROUND_HALF_UP);
            if (stringObjectMap.get("time_stamp").toString().equals(nowDayOfYearStr)){
                BigDecimal bigDecimal = new BigDecimal(stringObjectMap.get("4").toString()).add(new BigDecimal(dayValueStr));
                value = bigDecimal.divide(new BigDecimal("10000000"),1,BigDecimal.ROUND_HALF_UP);
            }
            yearYAxis.add(value);
        }
        barChart.setYearXAxis(yearXAxis);
        barChart.setYearYAxis(yearYAxis);
        return barChart;
    }
}
