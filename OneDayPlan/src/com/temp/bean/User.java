package com.temp.bean;

import java.util.List;

/**
 * @Auther: YunHai
 * @Date: 2019/7/26 14:32
 * @Description: 用户实体类
 */
public class User {
    private int id;
    private long qq;
    private String password;
    private int water;
    private int money;

    private List<fruit> fruits;   //背包用

    public User(){};

    public User(int id, long qq, String password, int water, int money) {
        this.id = id;
        this.qq = qq;
        this.password = password;
        this.water = water;
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", qq=" + qq +
                ", password='" + password + '\'' +
                ", water=" + water +
                ", money=" + money +
                '}';
    }

    public List<fruit> getFruits() {
        return fruits;
    }

    public void setFruits(List<fruit> fruits) {
        this.fruits = fruits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getQq() {
        return qq;
    }

    public void setQq(long qq) {
        this.qq = qq;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
