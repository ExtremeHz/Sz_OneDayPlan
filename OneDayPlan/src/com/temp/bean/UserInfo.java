package com.temp.bean;

import java.util.Date;

/**
 * @Author susuper
 * @Date 2019/8/13 16:47
 * @description:
 */
public class UserInfo {
    private Integer id;
    private Long userQq;
    private String treeName;
    private Date startTime;
    private Date endTime;
    private Double moneyGet;

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userQq=" + userQq +
                ", treeName='" + treeName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", moneyGet=" + moneyGet +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserQq() {
        return userQq;
    }

    public void setUserQq(Long userQq) {
        this.userQq = userQq;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getMoneyGet() {
        return moneyGet;
    }

    public void setMoneyGet(Double moneyGet) {
        this.moneyGet = moneyGet;
    }
}
