package com.temp.service;

import com.temp.bean.User;
import com.temp.dao.Dao;

/**
 * @Auther: YunHai
 * @Date: 2019/7/26 15:04
 * @Description: 业务逻辑
 */
public class Service {
    private Dao dao;
    {dao = new Dao();}

//    直接调用login
    public User login(long qq, String password){
        return dao.getUser(qq,password);
    }


}
