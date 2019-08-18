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
    private String Timer;
    private String treeName;
    private Double moneyGet;

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userQq=" + userQq +
                ", Timer='" + Timer + '\'' +
                ", treeName='" + treeName + '\'' +
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

    public String getTimer() {
        return Timer;
    }

    public void setTimer(String timer) {
        Timer = timer;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public Double getMoneyGet() {
        return moneyGet;
    }

    public void setMoneyGet(Double moneyGet) {
        this.moneyGet = moneyGet;
    }
}
