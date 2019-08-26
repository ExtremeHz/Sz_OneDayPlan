package com.temp.view;

import com.temp.bean.*;


import com.temp.service.Service;
import com.temp.test.TimeUtils;


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
        optionMap.put(new Byte((byte)'!'),0);
        optionMap.put(new Byte((byte)'@'),1);
        optionMap.put(new Byte((byte)'#'),2);
        optionMap.put(new Byte((byte)'$'),3);
        optionMap.put(new Byte((byte)'%'),4);
        optionMap.put(new Byte((byte)'^'),5);
        optionMap.put(new Byte((byte)'&'),6);
        optionMap.put(new Byte((byte)'*'),7);
        optionMap.put(new Byte((byte)'('),8);
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
            if(inputs.length>1){
                //            如果输入格式错误   会抛出异常
                try {
                    qq = Long.valueOf(inputs[0]);
                    password = inputs[1];

                    user = service.login(qq,password);
                } catch(NumberFormatException e){
                    System.out.println("账号或密码格式错误, 请重新输入");
                    continue;
                }

            }

//            查询


            if(user==null){
                System.out.println("账号或密码错误, 请重新登陆");
                continue;
            }else{
                service.UpdateUserWater(user.getQq(),user.getWater()+10);
                showMainIndex(user);
                break;
            }
        }

    }

//    首页展示
    public  void showMainIndex(User user){
        List<UserTree> list = service.ShowUserlevel(user.getQq());
        System.out.println("输入数字进入对应页面                                当前水滴数: "+user.getWater()+". 当前金币数: "+user.getMoney()+";\n1.专注模式(预计收入价值\"树对应金币数\"的\"树名\"及\"果实名\")输入树名或相应时间可进入专注页面" +
                "\n2.功能菜单(请注意分配您的时间和精力)");
        System.out.println("------------------");
        System.out.println("名称\t筹赏\t耗时");
        list.stream().forEach(t -> {
//            不知为何报错  先注释了  8.19 黎
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
                isFocus(5,user,"榕树",60);
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
                    plant(user);
                    break;
                case "2":
                    service.UpdateUserMoney(user.getQq(), user.getMoney()+(int) money);
                    service.InnsertOneToUserInfo(user,treeName,money);
                    user = service.getUserNoPassword(user.getQq());
                    showSuperMarket(user);
                    break;
                case "//":
                    showMainIndex(user);
                    default:
                        showMainIndex(user);
            }
        }else{
            System.out.println("专注失败，没有获得任何东西");
            showMainIndex(user);
        }
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
              plant(user);
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
                default:
                    System.out.println("请按规定输入");
                    menu(user);
                    break;
        }

    }

    /**
     * 黎  种植页面--------------------------------------------------------
     */
//    @Test
    public void plant(User user){


        byte[] options = {'!','@','#','$','%','^','&','*','('};
        updateTreeMap();
        updatefruit();
        while(true) {

            System.out.println("--------------------------------------------------\n" +
                    "**一曰之计(当前水滴数)**    //首页->2.功能菜单->种植绿化");

            GroundList gList = service.selectGroundListByUserId(user.getId());
            System.out.println("植树统计:   当前植树数量: " + gList.getNum() + "   可进行的种植操作: 输入树前缀+<浇水>+水滴值, 如 !浇水100 然后回车");
            System.out.println("种类| 名称| 价值| 阶段| 成长值| 所需成长值| 转化价值");

//        打印种植的树
            List<Ground> grounds = gList.getGrounds();
            for (int i = 0; i < gList.getNum(); i++) {
                Ground ground = grounds.get(i);

                Tree tree = treeMap.get(ground.getTreeid());
                Fruit fruit = fruitMap.get(tree.getFruitId());
                double level = 1.0 * ground.getGrowValue() / tree.getGrowValue();

                byte levelS = 'Z';
                if (level < 0.3) levelS = 'A';
                else if (level < 0.5) levelS = 'B';
                else if (level < 0.7) levelS = 'C';
                else levelS = 'D';


                System.out.println("树|  " + (char)options[i] + tree.getName() + "| " + tree.getPrice() + "| "
                        + (char)levelS + "| " + tree.getGrowValue() + "   | " + (tree.getGrowValue() - ground.getGrowValue()) + "     | " + tree.getPrice());
            }

            int userin = -1;

//        种植判定模块
            while (true) {


                //        输入模块
                while (true) {
//                    System.out.println("请输入操作(由于Test没有输入, 所以用字符串代替");
                    String input = /*"%浇水1"*/scan.nextLine();
                    try{Thread.sleep(5000);}catch (Exception e){}
                    //        如果数字开头 则是跳转到对应页面
                    if (input.matches("^\\d")) {
                        //                这里是跳转用代码块


                    } else if (input.matches("\\W[\\u4e00-\\u9fa5]{2}\\d+$")) {
                        //            则是种植操作选项
                        userin = getUserOptionTree(input);
                        break;
                    } else if(input.equals("//")){
                        showMainIndex(user);
                        break;
                    }else{
                        continue;
                    }
                }

                int treeIndex = userin % 10;
                int handle = (userin % 100) / 10;
                int waterValue = userin / 100;

//            如果操作为1  则是浇水
                if (handle == 1) {
                    Ground groundT = grounds.get(treeIndex);
                    Tree treeT = treeMap.get(groundT.getTreeid());

//                判定1 : 用户的water够不够用
//                判定2 : 是否小于等于植物所需的water
                    if (user.getWater() < waterValue) {
//                    判定1 : 用户的water是否够用
                        System.out.println("当前水滴数不足, 请重新选中操作");
                        continue;
                    } else if (treeT.getGrowValue() - groundT.getGrowValue() < waterValue) {
//                    判定2 : 植物所需water是否小于输入water
                        System.out.println("这样浇水太浪费了, 请重新选择操作");
                        continue;
                    }

//                设置当前树的状态
                    groundT.setGrowValue(groundT.getGrowValue() + waterValue);
                    service.updataGroundById(groundT);
//                设置用户的水滴数
                    service.UpdateUserWater(user.getQq(), user.getWater() - waterValue);
                    user.setWater(user.getWater() - waterValue);


//                待完成:   如果树的能量值满了
//                可以获得对应的果实price  但目前还没有果实数据  所以用tree的price
//                同理   因为用户只有water和money   没有第三个数值   所以给他算money里面去
                    if (treeT.getGrowValue() == groundT.getGrowValue()) {
                        service.UpdateUserMoney(user.getQq(), (int) (user.getMoney() + treeT.getPrice()));
                        user.setMoney((int) (user.getMoney() + treeT.getPrice()));
                        System.out.println("你的<"+treeT.getName()+">能量值已满, 作为奖励, 你获得了:"+treeT.getPrice()+"金币");
                        System.out.println("当前金币:"+user.getMoney());
                    }

                }
                System.out.println("浇水成功, 当前水滴数: "+ user.getWater());
                break;

            }
        }

    }

//    个人中心
    public void personCenter(User user){
        List<UserInfo> list = service.ShowUserInfo(user);

        System.out.println("**一曰之计(当前水滴数"+user.getWater()+")**    //首页->2.功能菜单->种植绿化");
//        System.out.println(list.size());
        list.stream().forEach(t ->{
//            不知为何报错. 先注释了    8.19 黎
            System.out.println("用户"+user.getQq()+"在"+/*t.getTime()+*/"\t种植了"+t.getTreeName()+"\t预计收益"+t.getMoneyGet()+"\n");
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
        System.out.println("名称\t\t\t价格\t\t\t时间");
        list.stream().forEach(t ->{
            System.out.println(t.getName()+"\t\t\t"+t.getPrice()+"\t\t\t"+t.getTime());
        });
        System.out.println("选择你要购买的树");
        String choice  = scan.next();

       switch (choice){
           case "榕树":
               if(user.getMoney()>60){
                   System.out.println("购买成功");
                   service.UpdateUserMoney(user.getQq(),user.getMoney()-60);
                   user = service.getUserNoPassword(user.getQq());
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
                   user = service.getUserNoPassword(user.getQq());
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
                   user = service.getUserNoPassword(user.getQq());
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
                   user = service.getUserNoPassword(user.getQq());
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
                   user = service.getUserNoPassword(user.getQq());
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


    /**
     * 黎  刷新tree字典表映射  id为key
     */
    public void updateTreeMap(){
        List<Tree> treeList = service.selectTreeAll();
        treeMap.clear();
        for(Tree tempT : treeList){
            treeMap.put(tempT.getId(), tempT);
        }

    }

    /**
     * 黎  刷新fruit字典表映射  id为key
     */
    public void updatefruit(){
        List<Fruit> fruitList = service.selectFruitAll();
        fruitMap.clear();
        for(Fruit tempF : fruitList){
            fruitMap.put(tempF.getId(), tempF);
        }
    }

    /**
     * 将用户输入 转化为指令编号
     * 规则:  个位是树的编号, 十位是指令  百位往后是浇水的值
     * 如 !浇水100   转化为10010
     * 或 %浇水213   转化为21314
     *
     * @return
     */
    public int getUserOptionTree(String input){

            Integer input1 = optionMap.get((byte) input.charAt(0));
            Integer input2 = -1;
            Integer input3 = -1;

            String input2S =input.substring(1,3);
            if("浇水".equals(input2S)) input2 = 10;

            String input3S = input.substring(3);
            input3 = Integer.valueOf(input3S) * 100;

            return input1 + input2 + input3;



    }











}

























