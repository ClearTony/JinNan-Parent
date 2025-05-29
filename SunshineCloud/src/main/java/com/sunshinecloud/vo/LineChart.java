package com.sunshinecloud.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class LineChart {

    private String type;

    private List<String> xAxisDatas;

    private List<BigDecimal> yAxisNowDatas;

    private List<String> xAxisYesterDayDatas;

    private List<BigDecimal> yAxisYesterDayDatas;
}
