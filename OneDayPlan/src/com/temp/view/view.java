package com.temp.view;

import com.temp.bean.*;
import com.temp.service.Service;
import org.junit.Test;

import java.util.*;

/**
 * @Auther: YunHai
 * @Date: 2019/7/26 15:05
 * @Description: view层
 */
public class view {
    static Map<Byte, Integer> optionMap;
    static Map<Integer, Tree> treeMap;
    static Map<Integer, Fruit> fruitMap;
    static{
        optionMap = new HashMap<Byte, Integer>();
        treeMap = new HashMap<Integer, Tree>();
        fruitMap = new HashMap<Integer, Fruit>();
        optionMap.put(new Byte((byte)'!'),1);
        optionMap.put(new Byte((byte)'@'),2);
        optionMap.put(new Byte((byte)'#'),3);
        optionMap.put(new Byte((byte)'$'),4);
        optionMap.put(new Byte((byte)'%'),5);
        optionMap.put(new Byte((byte)'^'),6);
        optionMap.put(new Byte((byte)'&'),7);
        optionMap.put(new Byte((byte)'*'),8);
        optionMap.put(new Byte((byte)'('),9);
    }


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
       showMainIndex(user);
    }

//    首页展示
    public  void showMainIndex(User user){
        List<UserTree> list = service.ShowUserlevel(user.getQq());
        System.out.println("输入数字进入对应页面                                当前水滴数: "+user.getWater()+". 当前金币数: "+user.getMoney()+";\n1.专注模式(预计收入价值\"树对应金币数\"的\"树名\"及\"果实名\")" +
                "\n2.功能菜单(请注意分配您的时间和精力)");
        System.out.println("------------------");
        list.stream().forEach(t -> {
            System.out.println(t.getStartTime()+"\""+t.getTreeName());
            System.out.println(1);
        });

        switch (scan.nextInt()){
            case 1:
                break;
            case 2:
                menu(user);
                break;

            default:

        }

    };


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

    public void menu(User user){
        System.out.println("-------------------------------------------------\n" +
                "**一曰之计(当前水滴数)**    //首页->2.功能菜单\n"+
                "1.绿化种植\n" +
                "2.个人中心\n" +
                "3.商城兑换\n" +
                "4.返回上级");
//        接收选择  并调用不同方法
        String s  = scan.next();
        switch (s){
            case "1":
//                种植中心
              plant();
                break;
            case "2":
//                个人中心
                personCenter(user);
                break;
            case "3":
//              商城兑换
                break;
//                返回上级
            case "4":
                showMainIndex(user);
                break;
            case "//":
                showMainIndex(user);
                break;
        }

    }

    /**
     * 种植页面 待完善
     */
    @Test
    public void plant(){
        byte[] options = {'!','@','#','$','%','^','&','*','('};
        updateTreeMap();
        updatefruit();

        System.out.println("--------------------------------------------------\n" +
                "**一曰之计(当前水滴数)**    //首页->2.功能菜单->种植绿化");
        GroundList gList = service.selectGroundListByUserId(user.getId());
        System.out.println("植树统计:   当前植树数量: "+ gList.getNum() + "   可进行的种植操作: 输入<浇水>+树前缀, 如浇水!然后回车");
        System.out.println("种类, 名称, 价值, 阶段, 成长值, 所需成长值, 转化价值");

        List<Ground> grounds = gList.getGrounds();
        for (int i = 0; i < gList.getNum(); i++){
            Ground ground = grounds.get(i);
            Tree tree = treeMap.get(ground.getTreeid());
            Fruit fruit = fruitMap.get(tree.getFruitId());
            double level = 1.0 * ground.getGrowValue() / tree.getGrowValue();
            byte levelS= 'Z';
            if(level < 0.3) levelS = 'A';
            else if(level < 0.5) levelS = 'B';
            else if(level < 0.7) levelS = 'C';
            else levelS = 'D';


            System.out.println("树|  "+options[i]+tree.getName()+"|"+ tree.getPrice()+"|"
            +levelS +"|" + tree.getGrowValue()+"|"+(tree.getGrowValue() - ground.getGrowValue())+"|" +fruit.getPrice());
        }

//        ------------------------



    }

//    个人中心
    public void personCenter(User user){
        List<User> list = service.ShowUserInfo(user.getQq());

        System.out.println("**一曰之计(当前水滴数"+user.getWater()+")**    //首页->2.功能菜单->种植绿化");
//        System.out.println(list.size());
        list.stream().forEach(t ->{

            System.out.println("用户"+t.getQq()+"在"+t.getUserTree().getStartTime()+"\n种植了"+t.getUserTree().getTreeName()+"\n预计收益"+t.getUserTree().getGrowValue()+"\n");
        });
        String str =scan.next();
        if(str.equals("//")){
            showMainIndex(user);
        }

    }






    public static void main(String[] args) {
        view vi = new view();
        vi.start();
    }











    public void updateTreeMap(){
        List<Tree> treeList = service.selectTreeAll();
        treeMap.clear();
        for(Tree tempT : treeList){
            treeMap.put(tempT.getId(), tempT);
        }

    }

    public void updatefruit(){
        List<Fruit> fruitList = service.selectFruitAll();
        fruitMap.clear();
        for(Fruit tempF : fruitList){
            fruitMap.put(tempF.getId(), tempF);
        }
    }











}

























