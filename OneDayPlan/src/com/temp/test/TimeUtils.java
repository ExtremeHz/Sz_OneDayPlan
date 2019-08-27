package com.temp.test;



public class TimeUtils {
    public static boolean timekeeper(int num){
        boolean flag;
        System.out.println("将鼠标移入白框开始计时, "+num+"秒后结束计时");
//        创建监听对象, 并传入flag
        __ _ = new __(false);
        ___ __ = new ___(_);
        __.start();

        a: while (true) {

//            如果flag为true  表示鼠标已经移入白框  开始计时
            if (_.is_()) {
                System.out.println("计时开始");
                for (int i = 0; i < num; i++) {

//                    判断flag  如果为false  则已经移出, 计时失败
//                    跳出a循环
                    if (!_.is_()) break a;
                    System.out.println(num - i);
                    try { Thread.sleep(1000); } catch (Exception e) { e.printStackTrace(); }
                }
                break a;
            } else {
                try { Thread.sleep(100); } catch (Exception e) { e.printStackTrace(); }
            }

        }

        if(_.is_()){
//            System.exit(0);
            flag = true;
        }else {
//            System.exit(0);
            flag = false;
        }

        __.interrupt();

        return flag;

    }


    public void show(){
        boolean flag = timekeeper(5);
        if (flag){}else{
            System.out.println("123");
        }
    }
}
