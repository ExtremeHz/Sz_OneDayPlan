package com.temp.bean;

/**
 * @Auther: YunHai
 * @Date: 2019/8/2 14:55
 * @Description: 树类型
 */
public class Tree {
    private int id;
    private String name;
    private int time;
    private int unlockTree;
    private int fruitId;
    private double price;
    private int growValue;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getUnlockTree() {
        return unlockTree;
    }

    public void setUnlockTree(int unlockTree) {
        this.unlockTree = unlockTree;
    }

    public int getFruitId() {
        return fruitId;
    }

    public void setFruitId(int fruitId) {
        this.fruitId = fruitId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getGrowValue() {
        return growValue;
    }

    public void setGrowValue(int growValue) {
        this.growValue = growValue;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
