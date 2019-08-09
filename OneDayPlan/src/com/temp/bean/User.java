package com.temp.bean;

/**
 * @Auther: YunHai
 * @Date: 2019/7/26 14:32
 * @Description: 用户实体类
 */
public class User {
    private int id;
    private long qq;
    private String password;
    private int waater;
    private int money;

    public User(){};

    public User(int id, long qq, String password, int waater, int money) {
        this.id = id;
        this.qq = qq;
        this.password = password;
        this.waater = waater;
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", qq=" + qq +
                ", password='" + password + '\'' +
                ", waater=" + waater +
                ", money=" + money +
                '}';
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

    public int getWaater() {
        return waater;
    }

    public void setWaater(int waater) {
        this.waater = waater;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
