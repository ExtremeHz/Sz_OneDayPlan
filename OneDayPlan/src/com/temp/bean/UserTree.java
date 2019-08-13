package com.temp.bean;

import java.util.Date;

/**
 * @Author susuper
 * @Date 2019/8/13 16:07
 * @description:用户已经解锁的树的种类
 */
public class UserTree {
    private Integer id;
    private String treeName;
    private Long userQq;
    private Date startTime;

    @Override
    public String toString() {
        return "UserTree{" +
                "id=" + id +
                ", treeName='" + treeName + '\'' +
                ", userQq=" + userQq +
                ", startTime=" + startTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public Long getUserQq() {
        return userQq;
    }

    public void setUserQq(Long userQq) {
        this.userQq = userQq;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
