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

    private List<Fruit> fruits;   //背包用

    private UserTree userTree;  //userTree背包

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
                ", fruits=" + fruits +
                ", userTree=" + userTree +
                '}';
    }

    public UserTree getUserTree() {
        return userTree;
    }

    public void setUserTree(UserTree userTree) {
        this.userTree = userTree;
    }

    public List<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(List<Fruit> fruits) {
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
