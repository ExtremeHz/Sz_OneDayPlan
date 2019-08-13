package com.temp.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import com.temp.bean.User;
import org.junit.Test;


import javax.management.relation.RoleUnresolved;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
            ps.setObject(1, qq);
            ps.setObject(2, password);

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

    /**
     * 用户登录的方法，通过qq查询user
     * @param qq
     * @return 用户
     */
    public User UserLogin(Long qq){
        String sql = "select * from szuser where qq=?";
        User user = new User();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1,qq);
            rs = ps.executeQuery();
            while (rs.next()){
                //将查询到的bean保存到user中
                user.setId(rs.getInt("id"));
                user.setQq(rs.getInt("qq"));
                user.setPassword(rs.getString("password"));
                user.setWaater(rs.getInt("water"));
                user.setMoney(rs.getInt("money"));
                return user;
            }
            rs.close();
            ps.close();
            conn.close();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException("用户登录查询失败");
        }
    }

    /**
     * 根据qq号更新水滴的数目
     * @param qq
     * @param water
     * @return
     */
    public boolean UpdateUserWater(Long qq,int water){
        String sql = "update szuser set water=? where qq=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn  = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1,water);
            ps.setObject(2,qq);
            if(ps.executeUpdate()>0){
                return true;
            }
            ps.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 更新用户金币
     * @param qq
     * @param money
     * @return
     */
    public boolean UpdateUserMoney(Long qq,int money){
        String sql = "update szuser set money=? where qq=?";
        Connection connection = null;
        PreparedStatement pre = null;
        try {
            connection = dataSource.getConnection();
            pre = connection.prepareStatement(sql);
            pre.setObject(1,money);
            pre.setObject(2,qq);
            if (pre.executeUpdate()>0){
                return true;
            }
            pre.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Test
    public void run1(){
        Dao dao = new Dao();
        dao.UpdateUserWater((long)123,100);
        dao.UpdateUserMoney((long)123,50);
        System.out.println(dao.UserLogin((long)123));
    }


}
