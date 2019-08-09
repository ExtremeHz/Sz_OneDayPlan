package com.temp.view;

/*import sun.plugin2.message.WindowActivationEventMessage;

import java.awt.event.*;
*/
public class TimeTask extends  Thread {

    private int time; // 存储用户传入的时间 hao
    private int minute;
    private int second;

    /**
     * 通过构造方法传递秒
     * @param time 秒
     */
    public TimeTask(int time){
        this.time = time;
        minute = time /60;
        second = time %60;
    }

    @Override
    public void run() {


        while (true){ // 外层循环时分钟数
            if(second == 0 && minute >0 ){ // 如果剩余时间大于0分钟，并且当前秒数等于0，则当前分钟减去1
                minute--;
            }

            if(second ==0) { // 如果剩余秒数为0 那么久从 60开始倒计时
                int i = 60; // 固定60秒
                while (i > 0) { // 利用线程沉睡，延迟一秒打印
                    i--;// 秒数减少
                    try {
                        if(i >=10) {
                            System.out.println(minute + ":" + i);
                        }else {
                            System.out.println(minute + ":0" + i);
                        }
                        Thread.sleep(1000); // 程序睡1秒钟
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                while ( second >0){ // 如果剩余秒数大于0 则从当前秒数开始
                    try {
                        second--;
                        if(second >=10){
                        System.out.println(minute + ":" + second);}
                        else {
                            System.out.println(minute + ":0" + second);
                        }
                        Thread.sleep(1000); // 程序睡1秒钟
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            if(minute == 0){
                break;
            }
        }
    }


}
