package com.temp.test;

import javax.swing.*;
import java.awt.*;

/**
 * @Auther: YunHai
 * @Date: 2019/8/11 14:25
 * @Description:监听线程, 构造方法传入flag  为true表示鼠标移入  为false表示鼠标移出
 */
public class ___  extends Thread{
    private __ _;

    public ___(__ _){
        this._ = _;
    }

    @Override
    public void run() {
//        创建监听对象, 并开始监听
        setName("___");
        JFrame __ = new JFrame();
        __.setBounds(200, 200, 200, 200);//设置窗口的大小 位置
        __.addMouseListener(new ____(_));
        __.setVisible(true);//让窗口显示出来

//        开启关闭
        __.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }


}
