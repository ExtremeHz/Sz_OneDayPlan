package com.temp.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import com.temp.bean.Tree;
import com.temp.bean.User;
import com.temp.bean.UserInfo;
import com.temp.bean.UserTree;



import javax.management.relation.RoleUnresolved;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
            user.setWater(re.getInt("water"));
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
                user.setWater(rs.getInt("water"));
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

    /**
     * 返回用户已经解锁的树信息，方便用户选择进入专注界面多久
     * @return 解锁的树信息
     */
    public List<UserTree> ShowUserlevel(long qq){
        List<UserTree> userTreeList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
//        String sql = "select * from usertree where userQq=?";
        //查询 usertree表，通过和tree表连接来获取名字
        String sql="select * from usertree where Userqq=?";
        try{
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,qq);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                UserTree userTree = new UserTree();
//                userTree.setId(rs.getInt("id"));
                userTree.setTreeName(rs.getString("treeName"));
                userTree.setPrice(rs.getString("price"));
                userTree.setFlag(rs.getInt("flag"));
                userTree.setTime(rs.getString("time"));
                userTreeList.add(userTree);
            }
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return userTreeList;
    }

    /**
     * 更新用户可以种植的树
     * @param qq
     * @param flag
     * @return
     */
    public boolean UpdateUserLevel(long qq,String flag){
        String sql = "insert into usertree(treename,userQq,startTime) values(?,?,?)";
        Connection con = null;
        PreparedStatement pre = null;
        try{
            con = dataSource.getConnection();
            pre = con.prepareStatement(sql);
            pre.setObject(1,flag);
            pre.setObject(2,qq);
            pre.setObject(3,new Date());
            if (pre.executeUpdate()>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 展示用户所有的计时操作
     * @param qq
     * @return
     */
    /*public List<User> ShowUserInfo(Long qq){
//        List<UserInfo> userInfoList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
//        String sql = "select * from userinfo where userQq=?";
        //查询tree usertree  szuser三个表来获取用户信息
        String sql = "select sz.*,tree.name,ut.startTime,tree.growValue from szuser sz,usertree ut,tree where sz.qq=ut.userQq and ut.treeId=tree.id and sz.qq=?";
        try{
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,qq);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setQq(rs.getLong("qq"));
                user.setWater(rs.getInt("water"));
                user.setMoney(rs.getInt("money"));

                //user表中的usertree属性
                UserTree userTree = new UserTree();
                userTree.setTreeName(rs.getString("name"));
                userTree.setStartTime(rs.getTimestamp("startTime"));
                userTree.setGrowValue(rs.getInt("growValue"));
                user.setUserTree(userTree);
//                UserInfo userInfo = new UserInfo();
//                userInfo.setId(rs.getInt("id"));
//                userInfo.setUserQq(rs.getLong("userQq"));
//                userInfo.setTreeName(rs.getString("treeName"));
//                userInfo.setStartTime(rs.getTimestamp("startTime"));
//                userInfo.setEndTime(rs.getTimestamp("endTime"));
//                userInfo.setMoneyGet(rs.getDouble("moneyGet"));
//                userInfoList.add(userInfo);
                userList.add(user);
            }
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
//        return userInfoList;
        return userList;
    }
*/
    /**
     *更新用户的拥有的果子
     * @return
     */
    public boolean UpdateUserFruit(){
        return true;
    }

    /**
     * 展示用户可以解锁的树根据moeny
     * @param moeny
     * @return
     */
    public List<Tree> showUserUnlockTree(Integer moeny){
        List<Tree> trees= new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql="select * from tree  where unlockTree < ?";
        try{
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,moeny);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Tree tree =new Tree();
                tree.setName(resultSet.getString("name"));
                tree.setTime(resultSet.getInt("time"));
                tree.setPrice(resultSet.getDouble("price"));
                trees.add(tree);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return trees;

    }

    /**
     * 展示用户金币和水滴，通过qq查询user
     * @param qq
     * @return 用户
     */
    public User ShowUserMoenyAndWater(Long qq){
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
                user.setWater(rs.getInt("water"));
                user.setMoney(rs.getInt("money"));
                return user;
            }
            rs.close();
            ps.close();
            conn.close();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException("查询失败");
        }
    }




}
