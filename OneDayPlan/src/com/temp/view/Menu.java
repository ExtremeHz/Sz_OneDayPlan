package com.temp.view;

import java.util.Scanner;

public class Menu {

	public void showSwitch(int waterDrop, int coin) {
		System.out.println("**一日之计\t水滴：" + waterDrop + "金币：" + coin);
		System.out.println("2.1绿化种植\t\t\t\t\t输入页面前缀进入页面");
		System.out.println("2.2个人中心");
		System.out.println("2.3商城兑换");
		System.out.println("2.4返回上级");
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		switch (str){
			case "2.1":
				break;
			case "2.2":
				break;
			case "2.3":
				break;
			case "2.4":
				break;
		}
	}

	public void showBag(int waterDrop, int coin) {
		System.out.println("**一日之计\t水滴：" + waterDrop + "金币：" + coin);

	}
}
