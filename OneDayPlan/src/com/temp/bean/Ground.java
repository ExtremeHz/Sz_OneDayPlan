package com.temp.bean;

import java.util.Date;

/**
 * @Auther: YunHai
 * @Date: 2019/8/18 16:18
 * @Description:bean
 */
public class Ground {
    private int id;
//    对应的用户id
    private int userid;
//    对应的树id
    private int treeid;
//    当前成长值
    private int growValue;
//    开始种植的时间
    private Date startTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getTreeid() {
        return treeid;
    }

    public void setTreeid(int treeid) {
        this.treeid = treeid;
    }

    public int getGrowValue() {
        return growValue;
    }

    public void setGrowValue(int growValue) {
        this.growValue = growValue;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
