package com.temp.service;

import com.temp.bean.*;
import com.temp.dao.Dao;

import java.util.List;

/**
 * @Auther: YunHai
 * @Date: 2019/7/26 15:04
 * @Description: 业务逻辑
 */
public class Service {
    private Dao dao;

    {
        dao = new Dao();
    }

    //    直接调用login
    public User login(long qq, String password) {
        return dao.getUser(qq, password);
    }

    //    展示记时操作
    public List<User> ShowUserInfo(long qq) {
        return dao.ShowUserInfo(qq);
    }

    /**
     * 返回用户已经解锁的树信息，方便用户选择进入专注界面多久
     *
     * @param qq
     * @return 解锁的树信息
     */
    public List<UserTree> ShowUserlevel(long qq) {
        return dao.ShowUserlevel(qq);
    }


    /**
     * 通过userid获取GroundList
     * @param userid
     * @return
     */
    public GroundList selectGroundListByUserId(int userid){
        return dao.selectGroundListByUserId(userid);
    }

    public int insertGroundByUserId(Ground newGround){
        return dao.insertGroundByUserId(newGround);
    }

    public int updataGroundByUserId(Ground nowGround){
        return dao.updateGroundByUserId(nowGround);
    }

    public int deleteGroundById(int id){
        return dao.deleteGroundById(id);
    }

    public List<Tree> selectTreeAll(){
        return dao.selectTreeAll();
    }

    public List<Fruit> selectFruitAll(){
        return dao.selectFruitAll();
    }



}