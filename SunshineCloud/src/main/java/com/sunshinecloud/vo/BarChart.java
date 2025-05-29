package com.sunshinecloud.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BarChart {

    private String type;

    private List<String> weekXAxis;
    private List<BigDecimal> weekYAxis;

    private List<String> monthXAxis;
    private List<BigDecimal> monthYAxis;

    private List<String> yearXAxis;
    private List<BigDecimal> yearYAxis;
}
