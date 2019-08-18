package com.temp.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @Auther: YunHai
 * @Date: 2019/8/18 16:13
 * @Description: 周迅的常用工具类
 */
public class Utils {

    /**
     * 获取数字  含头含尾
     * @param min:最小值
     * @param max:最大值
     * @param scan:scanner对象
     * @return: 用户输入的正确值
     */
    public static int getInt(int min, int max, Scanner scan) {
        int Num = 0;

        while (true) {

            try {
                Num = scan.nextInt();
//                如果输入的不是数字 则抛异常
            } catch (InputMismatchException exception) {
                System.out.println("错误:" + exception + "\n请不要输入数字以外的字符,请重新输入");
                scan.next();
                continue;
            }

            if(Num >= min && Num <= max)
                return Num;
            else
                System.out.println("输入值错误,请重新输入.(要求值在"+min+"-"+max+"之间");
        }

    }

}
