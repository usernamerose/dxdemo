package com.dx.dxdemo.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author tWX579831
 * @date 2020/3/21 20:25
 */
@Entity
public class Wage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer year;
    private Integer month;
    // 基本工资
    private Integer baseWage;
    // 其他，包含绩效，奖金等
    private Integer otherWage;
    // 社保税收
    private Integer deduction;
    // 是否发放
    private boolean isProvide;
    // 反馈意见
    private String feedBackInfo;
    // 审批意见
    private String approvalInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getBaseWage() {
        return baseWage;
    }

    public void setBaseWage(Integer baseWage) {
        this.baseWage = baseWage;
    }

    public Integer getOtherWage() {
        return otherWage;
    }

    public void setOtherWage(Integer otherWage) {
        this.otherWage = otherWage;
    }

    public Integer getDeduction() {
        return deduction;
    }

    public void setDeduction(Integer deduction) {
        this.deduction = deduction;
    }

    public boolean isProvide() {
        return isProvide;
    }

    public void setProvide(boolean provide) {
        isProvide = provide;
    }

    public String getFeedBackInfo() {
        return feedBackInfo;
    }

    public void setFeedBackInfo(String feedBackInfo) {
        this.feedBackInfo = feedBackInfo;
    }

    public String getApprovalInfo() {
        return approvalInfo;
    }

    public void setApprovalInfo(String approvalInfo) {
        this.approvalInfo = approvalInfo;
    }
}
