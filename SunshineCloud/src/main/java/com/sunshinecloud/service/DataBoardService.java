package com.sunshinecloud.service;

import com.sunshinecloud.vo.BarChart;
import com.sunshinecloud.vo.LineChart;
import com.sunshinecloud.vo.PowerStationDetail;

import java.util.Map;

public interface DataBoardService {
    PowerStationDetail queryPowerStationDetail();

    LineChart getDayLineChart(Map<String,Object> params);

    BarChart getBarChart(Map<String, Object> params);
}
