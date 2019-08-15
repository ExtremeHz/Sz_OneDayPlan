package com.temp.view;

import com.temp.bean.User;
import com.temp.service.Service;
import org.junit.Test;

import java.util.Scanner;

/**
 * @Auther: YunHai
 * @Date: 2019/7/26 15:05
 * @Description: view层
 */
public class view {




    public Scanner scan = new Scanner(System.in);
    private User user = null;
//    service层实现类
    private Service service = new Service();

//    public static void main(String[] args){
//
//        new TimeTask(5).start();
//    }

//    启动- 登录
    public void start(){


        System.out.println("**欢迎进入一日之计**");

//        循环登录   如果错值则continue, 否则breck
        while(true) {
            System.out.println("请输入用户ID和密码并以//作为间隔");
//            获取输入  并通过//进行分割
            String input = scan.nextLine();
            String[] inputs = input.split("//");


            long qq = 0;
            String password;
//            如果输入格式错误   会抛出异常
            try {
                qq = Long.valueOf(inputs[0]);
                password = inputs[1];


            } catch(NumberFormatException e){
                System.out.println("账号或密码格式错误, 请重新输入");
                continue;
            }

//            查询
            user = service.login(qq,password);

            if(user == null){
                System.out.println("账号或密码错误, 请重新登陆");
            }else break;
        }

        System.out.println("输入数字进入对应页面                                当前水滴数: "+user.getWater()+". 当前金币数: "+user.getMoney()+";\n1.专注模式(预计收入价值\"树对应金币数\"的\"树名\"及\"果实名\")" +
                "\n2.功能菜单(请注意分配您的时间和精力)");

        switch (scan.nextInt()){
            default:

        }

    }


    /**
     * 计时  参数为秒    如果完成返回true   失败返回false
     */
    public boolean countTime(int time){
//        循环计时
        for(int i = time; i > 0; i--){
            System.out.println(String.format("%02d",i/60)+ ":" + String.format("%02d",i%60) );
            try{
                Thread.sleep(1000);
            }catch(Exception e){

            }

//            判断  如果判断失败返回false  成功则继续循环
//          判断逻辑待完善
            if(1==2) return false;

        }

//        如果完成计时 返回true
        return true;
    }


    /**
     * 菜单打印
     */
    @Test
    public void menu(){
        System.out.println("-------------------------------------------------\n" +
                "**一曰之计(当前水滴数)**    //首页->2.功能菜单"+
                "1.绿化种植\n" +
                "2.个人中心\n" +
                "3.商城兑换\n" +
                "4.返回上级");
//        接收选择  并调用不同方法
        switch (scan.nextInt()){
            case 1:
                System.out.println(1);
                break;

            default:
                System.out.println(4);
        }

    }

    /**
     * 种植页面 待完善
     */
    public void plant(){
        System.out.println("--------------------------------------------------\n" +
                "**一曰之计(当前水滴数)**    //首页->2.功能菜单->种植绿化"+
                "背包: " +"苹果树1" +
                "种植需求"+" 10滴水"+
                "种植收益"+ " 苹果"+ "*"+ "1"+"(" +"50"+"金币)"
        );
    }


}

























