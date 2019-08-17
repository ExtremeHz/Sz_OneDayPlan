package com.temp.service;

import com.temp.bean.User;
import com.temp.bean.UserTree;
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
   /* public List<User> ShowUserInfo(long qq) {
        return dao.ShowUserInfo(qq);
    }
*/
    /**
     * 返回用户已经解锁的树信息，方便用户选择进入专注界面多久
     *
     * @param qq
     * @return 解锁的树信息
     */
    public List<UserTree> ShowUserlevel(long qq) {
        return dao.ShowUserlevel(qq);
    }
}