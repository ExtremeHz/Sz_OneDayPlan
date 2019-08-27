package com.temp.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import com.temp.bean.*;
import org.junit.Test;


import javax.management.relation.RoleUnresolved;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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



    public List<Fruit> selectFruitAll() {
        List<Fruit> fruitList = new ArrayList<>();
        Connection con = null;
        try {
            con = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "select * from fruit";
        PreparedStatement ps = null;
        ResultSet re = null;

        try {
            ps = con.prepareStatement(sql);

            re = ps.executeQuery();

            while (re.next()) {
                Fruit fruitT = new Fruit();

                fruitT.setId(re.getInt("id"));
                fruitT.setName(re.getString("name"));
                fruitT.setPrice(re.getInt("price"));
                fruitList.add(fruitT);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fruitList;
    }

    public List<Tree> selectTreeAll(){
        List<Tree> treeList = new ArrayList<>();
        Connection con = null;
        try {
            con = dataSource.getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }

        String sql = "select * from tree";
        PreparedStatement ps = null;
        ResultSet re = null;

        try {
            ps = con.prepareStatement(sql);

            re = ps.executeQuery();

            while(re.next()){
                Tree tempT = new Tree();

                tempT.setId(re.getInt("id"));
                tempT.setName(re.getString("name"));
                tempT.setTime(re.getInt("time"));
                tempT.setUnlockTree(re.getInt("unlockTree"));
                tempT.setFruitId(re.getInt("fruitId"));
                tempT.setPrice(re.getInt("price"));
                tempT.setGrowValue(re.getInt("growValue"));
                tempT.setType(re.getInt("type"));

                treeList.add(tempT);
            }

        }catch(Exception e){
            e.printStackTrace();
        }





        return treeList;
    }


    public int deleteGroundById(int id){
        Connection con = null;
        int result = -1;
        try {
            con = dataSource.getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }

        String sql = "delete from ground where id = ?";
        PreparedStatement ps = null;
        ResultSet re = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1,id);

            result = ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }


        return result;
    }

    public int insertGroundByUserId(Ground newGround){
        Connection con = null;
        int result = -1;
        try {
            con = dataSource.getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }

        String sql = "insert into ground(userid, treeid, growValue, startTime) value(?,?,?,?);";
        PreparedStatement ps = null;
        ResultSet re = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, newGround.getUserid());
            ps.setObject(2, newGround.getTreeid());
            ps.setObject(3, newGround.getGrowValue());
            ps.setObject(4, newGround.getStartTime());


            result = ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }


        return result;
    }

    public int updateGroundById(Ground nowGround){
        Connection con = null;
        int result = -1;
        try {
            con = dataSource.getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
//        通过userid获取
        String sql = "update ground set treeid = ?, growValue = ?, startTime = ? where id = ?;";
        PreparedStatement ps = null;
        ResultSet re = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, nowGround.getTreeid());
            ps.setObject(2, nowGround.getGrowValue());
            ps.setObject(3, nowGround.getStartTime());
            ps.setObject(4, nowGround.getId());

            result = ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }


            return result;
    }

    /**
     * 通过userid获取groundList对象
     * @param userId
     * @return
     */
    public GroundList selectGroundListByUserId(int userId){
        GroundList result = new GroundList();
        List<Ground> gList = new ArrayList<Ground>(9);
        Connection con = null;
        try {
            con = dataSource.getConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
//        通过userid获取
        String sql = "select *, (select count(*) from ground where userid=?) as num from ground where userid = ?";
        PreparedStatement ps = null;
        ResultSet re = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, userId);
            ps.setObject(2, userId);

            re = ps.executeQuery();

//            如果没有获取到信息
            if(!re.next()){
                result.setNum(0);

                return result;
            }

            result.setNum(re.getInt("num"));
//            循环获取
            do{
                Ground tempG = new Ground();
                tempG.setId(re.getInt("id"));
                tempG.setUserid(userId);
                tempG.setTreeid(re.getInt("treeid"));
                tempG.setGrowValue(re.getInt("growValue"));
                tempG.setStartTime(re.getDate("startTime"));
                gList.add(tempG);
            }while(re.next());

            ps.close();

            con.close();


        }catch(Exception e){
            e.printStackTrace();
        }

        result.setGrounds(gList);

        return result;
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
            if(!re.next()) return null;

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

//                不知为何报错  dao没有对应方法   先注释    8.19 黎
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
     * 个人中心
     * @param qq
     * @return
     */
    public List<UserInfo> ShowUserInfo(Long qq){
        List<UserInfo> userInfos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        String sql="select * from userinfo where Userqq=?";
        try{
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,qq);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
               UserInfo userInfo = new UserInfo();
               userInfo.setUserQq(qq);
               userInfo.setTreeName(rs.getString("treeName"));
               userInfo.setTimer(rs.getString("Timer"));
               userInfo.setMoneyGet(rs.getDouble("moneyGet"));
                userInfos.add(userInfo);
            }
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return userInfos;

    }

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


    /**
     * 插入一条数据到userinfo表中
     * @param qq
     * @param treeName
     * @param time
     * @param moneyGet
     */
    public  void InnsertOneToUserInfo(Long qq,String treeName,String time,Double moneyGet){
        String sql = "insert into userinfo(userQq,treeName,Timer,moneyGet) values(?,?,?,?)";
        Connection con = null;
        PreparedStatement pre = null;
        try{
            con = dataSource.getConnection();
            pre = con.prepareStatement(sql);
            pre.setObject(1,qq);
            pre.setObject(2,treeName);
            pre.setObject(3,time);
            pre.setObject(4,moneyGet);
            pre.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 解锁树
     * @param userQq
     * @param treeName
     */
    public void updateUserTreeFlag(Integer userQq,String treeName){

        String sql = "update usertree set flag=1 where userQq=? and treeName=?";
        Connection connection = null;
        PreparedStatement pre = null;
        try {
            connection = dataSource.getConnection();
            pre = connection.prepareStatement(sql);
            pre.setObject(1,userQq);
            pre.setObject(2,treeName);
            pre.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
