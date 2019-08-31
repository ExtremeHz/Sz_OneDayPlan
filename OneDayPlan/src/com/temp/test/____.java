package com.temp.test;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @Auther: YunHai
 * @Date: 2019/8/11 14:30
 * @Description: 鼠标监听
 */

public class ____ implements MouseListener {

    private __ _;
//构造方法  传入flag
    public ____(__ _){
        this._ = _;
    }


        @Override
        public void mouseClicked(MouseEvent e) {
            //System.out.println("点击事件");
            //System.out.println(e.getClickCount());//和鼠标抬起差不多
        }
        @Override
        public void mousePressed(MouseEvent e) {
            //System.out.println("鼠标按下");
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            //System.out.println("鼠标抬起");
        }
        @Override
        public void mouseEntered(MouseEvent e) {
//            System.out.println("计时开始");
            _.set_(true);
        }
        @Override
        public void mouseExited(MouseEvent e) {
//            System.out.println("计时结束");
            _.set_(false);

        }


}
