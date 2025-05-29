package com.sunshinecloud.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PowerStationDetail {
    /**
     * 总发电量
     */
    private BigDecimal totalEnergyValue;
    /**
     * 总发电量单位
     */
    private String totalEnergyUnit;
    /**
     * 本月发电量
     */
    private BigDecimal monthEnergyValue;
    /**
     * 本月发电量单位
     */
    private String monthEnergyUnit;

    /**
     * 今日发电量
     */
    private BigDecimal todayEnergyValue;

    /**
     * 今日发电量单位
     */
    private String todayEnergyUnit;

    /**
     * 今日收入
     */
    private BigDecimal todayIncomeValue;
    /**
     * 今日收入单位
     */
    private String todayIncomeUnit;

    /**
     * 本月收入
     */
    private BigDecimal monthIncomeValue;
    /**
     * 本月收入单位
     */
    private String monthIncomeUnit;

    /**
     * 总收入
     */
    private BigDecimal totalIncomeValue;
    /**
     * 总收入单位
     */
    private String totalIncomeUnit;
    /**
     * 当前功率
     */
    private BigDecimal currPowerValue;
    /**
     * 当前功率单位
     */
    private String currPowerUnit;

    /**
     * 装机功率
     */
    private BigDecimal totalCapcityValue;
    /**
     * 装机功率单位
     */
    private String totalCapcityUnit;

    /**
     * CO₂减排量
     */
    private BigDecimal coTwoValue;
    /**
     * CO₂减排量单位
     */
    private String coTwoUnit;

    /**
     * 节约标准煤
     */
    private BigDecimal standardCoalValue;

    /**
     * 节约标准煤单位
     */
    private String standardCoalUnit;

    /**
     * 等效植树
     */
    private BigDecimal standingWoodValue;
    /**
     * 等效植树单位
     */
    private String standingWoodUnit;

}
