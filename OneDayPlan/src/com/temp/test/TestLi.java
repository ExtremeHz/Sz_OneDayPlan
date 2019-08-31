package com.temp.test;

import com.temp.bean.*;
import com.temp.dao.Dao;
import com.temp.service.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @Auther: YunHai
 * @Date: 2019/8/20 14:46
 * @Description:
 */
public class TestLi {
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
    private User user = new User();
    //    service层实现类
    private Service service = new Service();


    public void before(){

        user.setId(10086);
        user.setWater(100);
        user.setMoney(20);
        user.setQq(10086);
        user.setPassword("10086");
    }


        /**
         * 黎  种植页面--------------------------------------------------------
         */

    public void plant(){



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
                    System.out.println("请输入操作(由于Test没有输入, 所以用字符串代替");
                    String input = "%浇水1"/*scan.nextLine()*/;
                    try{Thread.sleep(5000);}catch (Exception e){}
                    //        如果数字开头 则是跳转到对应页面
                    if (input.matches("^\\d")) {
                        //                这里是跳转用代码块


                    } else if (input.matches("\\W[\\u4e00-\\u9fa5]{2}\\d+$")) {
                        //            则是种植操作选项
                        userin = getUserOptionTree(input);
                        break;
                    } else {
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


    public void testString(){

        System.out.println("请输入用户ID和密码并以//作为间隔");
//            获取输入  并通过//进行分割
        String input = "123//";
        String[] inputs = input.split("//");
        long qq = 0;
        String password;
//            如果输入格式错误   会抛出异常
        try {
            qq = Long.valueOf(inputs[0]);
            password = inputs[1];


        } catch(Exception e){
            System.out.println("账号或密码格式错误, 请重新输入");
        }
    }


    public void testGetGroundNum(){
        Dao dao = new Dao();
        System.out.println(dao.getUserGroundNumByUserId(10086));

    }


}
