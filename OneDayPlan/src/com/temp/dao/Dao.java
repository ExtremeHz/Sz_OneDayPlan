package com.temp.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import com.temp.bean.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Auther: YunHai
 * @Date: 2019/7/26 14:09
 * @Description: 数据库操作
 */
public class Dao {
    ComboPooledDataSource dataSource;
    {
         dataSource = new ComboPooledDataSource();
    }


//    登录方法 -  通过账号密码进行登录  返回对应的user
//    代码块不解释.... 普通的c3p0
    public User getUser(long qq, String password){
        User user= new User();
        Connection con = null;
        try {
             con = dataSource.getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
        String sql = "select * from szuser where qq = ? And password = ?";
        PreparedStatement ps = null;
        ResultSet re = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, 369360377);
            ps.setObject(2, "password");

            re = ps.executeQuery();
            re.next();

            user.setId(re.getInt("id"));
            user.setQq(re.getLong("qq"));
            user.setWaater(re.getInt("water"));
            user.setMoney(re.getInt("money"));
            ps.close();

            con.close();


        }catch(Exception e){
            e.printStackTrace();
        }


        return user;


    }
}
