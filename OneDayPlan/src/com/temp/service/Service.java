package com.temp.service;


import com.temp.bean.*;
import com.temp.dao.Dao;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public User getUserNoPassword(long qq){
        return null;
    }

    /**
     * 根据qq号更新水滴的数目
     * @param qq
     * @param water
     * @return
     */
    public void UpdateUserWater(Long qq,int water){
        dao.UpdateUserWater(qq,water);
    };

    //    展示记时操作
    public List<UserInfo> ShowUserInfo(long qq) {
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

//    每日登陆更新用户水滴
    public void UpdateUserWater(long qq,int water){
        dao.UpdateUserWater(qq,water);
    }
//    更新用户金币
    public void UpdateUserMoney(long qq,int money){
        dao.UpdateUserMoney(qq,money);
    }
//    商城系统
    public List<Tree> showUserUnlockTree(int money){
        return dao.showUserUnlockTree(money);
    }
//    个人中心
    public List<UserInfo> ShowUserInfo(User user){
        return dao.ShowUserInfo(user.getQq());
    }
//    更新用户课题种植的树
    public void UpdateUserLevel(User user){
        dao.UpdateUserLevel(user.getQq(),"1");
    }

//    插入数据InnsertOneToUserInfo
    public void InnsertOneToUserInfo(User user,String treeName,double moneyGet){
        Date date  = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = sdf.format(date);
        dao.InnsertOneToUserInfo(user.getQq(),treeName,time,moneyGet);
    }



    /**
     * 通过userid获取GroundList
     * @param userid
     * @return
     */
    public GroundList selectGroundListByUserId(int userid){
        return dao.selectGroundListByUserId(userid);
    }

    public int getUserGroundNumByUserId(int userId){
        return dao.getUserGroundNumByUserId(userId);
    }

    public int insertGroundByUserId(Ground newGround){
        return dao.insertGroundByUserId(newGround);
    }

    public int updataGroundById(Ground nowGround){
        return dao.updateGroundById(nowGround);
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