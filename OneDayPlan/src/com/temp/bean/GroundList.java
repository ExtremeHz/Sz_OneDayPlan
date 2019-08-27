package com.temp.bean;

import java.util.List;
import java.util.Map;

/**
 * @Auther: YunHai
 * @Date: 2019/8/18 16:20
 * @Description:
 */
public class GroundList {
//    土地集合
    private List<Ground> grounds;
//    种植数量集合
    private int num;

    public List<Ground> getGrounds() {
        return grounds;
    }

    public void setGrounds(List<Ground> grounds) {
        this.grounds = grounds;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
