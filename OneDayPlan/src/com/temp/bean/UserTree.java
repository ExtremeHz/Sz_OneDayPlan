package com.temp.bean;

import java.util.Date;

/**
 * @Author susuper
 * @Date 2019/8/13 16:07
 * @description:用户已经解锁的树的种类
 */
public class UserTree {
    private Integer id;   //没用，后面删除掉
    private String treeName;
    private Integer userQq;
    private String  price;
    private String time;
    private Integer flag;

    @Override
    public String toString() {
        return "UserTree{" +
                "id=" + id +
                ", treeName='" + treeName + '\'' +
                ", userQq=" + userQq +
                ", price='" + price + '\'' +
                ", time='" + time + '\'' +
                ", flag=" + flag +
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

    public Integer getUserQq() {
        return userQq;
    }

    public void setUserQq(Integer userQq) {
        this.userQq = userQq;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
