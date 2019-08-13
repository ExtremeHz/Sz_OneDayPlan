package com.temp.view;

import java.util.Scanner;


public class Index {

	private static String weiChatName = "iieen";

	public void showIndex() {
		System.out.println("**欢迎进入一日之计**\t\t\t\t\t\t\t客服公众号:" + weiChatName);
		System.out.println("请输入用户ID和密码并以///作为间隔");
		Scanner input = new Scanner(System.in);
		input.nextLine();
		System.out.println("**账号或者密码错误**");
	}
	
	public static void main(String[] args) {
		Index i = new Index();
		i.showIndex();
	}
}
