package com.temp.test;




/**
 * @Auther: YunHai
 * @Date: 2019/8/11 14:43
 * @Description:
 */
public class start {


    public static void main(String[] args) {
        System.out.println("将鼠标移入白框开始计时, 10秒后结束计时");
//        创建监听对象, 并传入flag
        __ _ = new __(false);
        ___ __ = new ___(_);
        __.start();

        a: while (true) {

//            如果flag为true  表示鼠标已经移入白框  开始计时
            if (_.is_()) {
                System.out.println("计时开始");
                for (int i = 0; i < 10; i++) {

//                    判断flag  如果为false  则已经移出, 计时失败
//                    跳出a循环
                    if (!_.is_()) break a;
                    System.out.println(10 - i);
                    try { Thread.sleep(1000); } catch (Exception e) { e.printStackTrace(); }
                }
                break a;
            } else {
                try { Thread.sleep(100); } catch (Exception e) { e.printStackTrace(); }
            }

        }

//        如果flag为true  表示计时成功
        if(_.is_())System.out.println("计时成功");
        else System.out.println("计时失败");




    }




    public void test1(){
        System.out.println("将鼠标移入白框开始计时, 10秒后结束计时");
//        创建监听对象, 并传入flag
        __ _ = new __(false);
        ___ __ = new ___(_);
        __.start();

        a: while (true) {

//            如果flag为true  表示鼠标已经移入白框  开始计时
            if (_.is_()) {
                System.out.println("计时开始");
                for (int i = 0; i < 10; i++) {

//                    判断flag  如果为false  则已经移出, 计时失败
//                    跳出a循环
                    if (!_.is_()) break a;
                    System.out.println(10 - i);
                    try { Thread.sleep(1000); } catch (Exception e) { e.printStackTrace(); }
                }
                break a;
            } else {
                try { Thread.sleep(100); } catch (Exception e) { e.printStackTrace(); }
            }

        }

//        如果flag为true  表示计时成功
        if(_.is_())System.out.println("计时成功");
        else System.out.println("计时失败");

        System.exit(0);

    }
}
