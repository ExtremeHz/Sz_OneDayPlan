package com.temp.view;

import com.temp.bean.*;


import com.temp.service.Service;

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
//        new TimeTask(5).startTime();
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
            }else{
                service.UpdateUserWater(user.getQq(),user.getWater()+10);
                break;
            }
        }
       showMainIndex(user);
    }

//    首页展示
    public  void showMainIndex(User user){
        List<UserTree> list = service.ShowUserlevel(user.getQq());
        System.out.println("输入数字进入对应页面                                当前水滴数: "+user.getWater()+". 当前金币数: "+user.getMoney()+";\n1.专注模式(预计收入价值\"树对应金币数\"的\"树名\"及\"果实名\")" +
                "\n2.功能菜单(请注意分配您的时间和精力)");
        System.out.println("------------------");
        System.out.println("名称\t筹赏\t耗时");
        list.stream().forEach(t -> {
            if(t.getFlag()==1){
                System.out.println(t.getTreeName()+"\t"+t.getPrice()+"\t"+t.getTime());
            }
        });
        String choice = scan.next();
        switch (choice){
            case "2":
                menu(user);
                break;
            case "榕树":
                isFocus(20,user,"榕树",60);
                break;
            case "20" :
                isFocus(20,user,"榕树",60);
                break;
            case "松树":
                isFocus(30,user,"松树",80);
                break;
            case "30":
                isFocus(30,user,"松树",80);
                break;
            case "枫叶树":
                isFocus(80,user,"枫叶树",240);
                break;
            case "80":
                isFocus(80,user,"枫叶树",240);
                break;
                default:
                    showMainIndex(user);

        }
    };
//  专注页面
    public boolean focus(int num){
         boolean flag   = TimeUtils.timekeeper(num);
         return flag;
    }
//    isfocus
    public void isFocus(int num ,User user,String treeName,double money){
        boolean flag=  focus(num);
        if(flag){
            System.out.println("请选择种植或者卖出\n");
            System.out.println("1.立即种植\n");
            System.out.println("2.立即卖出\n");
            String str = scan.next();
            switch (str){
                case "1":
                    plant(null);
                    break;
                case "2":
                    service.UpdateUserMoney(user.getQq(), user.getMoney()+(int) money);
                    service.InnsertOneToUserInfo(user,"榕树",money);
                    showSuperMarket(user);
                    break;
            }
        }else{
            System.out.println("专注失败，没有获得任何东西");
            showMainIndex(user);
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
                showSuperMarket(user);
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
        List<UserInfo> list = service.ShowUserInfo(user);

        System.out.println("**一曰之计(当前水滴数"+user.getWater()+")**    //首页->2.功能菜单->种植绿化");
//        System.out.println(list.size());
        list.stream().forEach(t ->{
            System.out.println("用户"+user.getQq()+"在"+t.getTimer()+"\t种植了"+t.getTreeName()+"\t预计收益"+t.getMoneyGet()+"\n");
        });
        String str =scan.next();
        if(str.equals("//")){
            showMainIndex(user);
        }

    }
//    商城
    public void showSuperMarket(User user){
        System.out.println("欢迎"+user.getQq()+"进入商城系统，当前你的金币为"+user.getMoney()+"");
        List<Tree> list = service.showUserUnlockTree(user.getMoney());
        System.out.println("————————————————————");
        System.out.println("名称\t价格\t时间");
        list.stream().forEach(t ->{
            System.out.println(t.getName()+"\t"+t.getPrice()+"\t"+t.getTime());
        });
        System.out.println("选择你要购买的树");
        String choice  = scan.next();

       switch (choice){
           case "榕树":
               if(user.getMoney()>60){
                   System.out.println("购买成功");
                   service.UpdateUserMoney(user.getQq(),user.getMoney()-60);
                   showSuperMarket(user);
               }else{
                   System.out.println("赶紧去挣钱吧");
                   showSuperMarket(user);
               }
               break;
           case "松树":
               if(user.getMoney()>80){
                   System.out.println("购买成功");
                   service.UpdateUserMoney(user.getQq(),user.getMoney()-80);
                   showSuperMarket(user);
               }else{
                   System.out.println("赶紧去挣钱吧");
                   showSuperMarket(user);
               }
               break;
           case "枫叶树":
               if(user.getMoney()>240){
                   System.out.println("购买成功");
                   service.UpdateUserMoney(user.getQq(),user.getMoney()-240);
                   showSuperMarket(user);
               }else{
                   System.out.println("赶紧去挣钱吧");
                   showSuperMarket(user);
               }
               break;
           case "苹果树":
               if(user.getMoney()>300){
                   System.out.println("购买成功");
                   service.UpdateUserMoney(user.getQq(),user.getMoney()-300);
                   showSuperMarket(user);
               }else{
                   System.out.println("赶紧去挣钱吧");
                   showSuperMarket(user);
               }
               break;
           case "桃树":
               if(user.getMoney()>240){
                   System.out.println("购买成功");
                   service.UpdateUserMoney(user.getQq(),user.getMoney()-340);
                   showSuperMarket(user);
               }else{
                   System.out.println("赶紧去挣钱吧");
                   showSuperMarket(user);
               }
               break;
           case "//":
               showMainIndex(user);
               break;
               default:
                   System.out.println("当前输入商城未有");
                   showSuperMarket(user);
                   break;
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

























