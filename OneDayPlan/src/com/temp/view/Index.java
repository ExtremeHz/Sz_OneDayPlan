package com.temp.view;

import com.temp.bean.User;
import com.temp.service.Service;

import java.util.Scanner;


public class Index {

	private static String weiChatName = "iieen";
	private static Service service = new Service();

	public void showIndex() {
		System.out.println("**欢迎进入一日之计**\t\t\t\t\t\t\t客服公众号:" + weiChatName);
		System.out.println("请输入用户ID和密码并以//作为间隔");
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		while(!str.contains("//")){
			System.out.println("请按指定形式输入");
			str = input.nextLine();
		}
		String []strs =  str.split("//");
//		User user = service.login(Long.parseLong(strs[0]),strs[1]);
		if(/*(user.getId()+"")*/"123".equals(strs[0])){
				Menu menu = new Menu();
				menu.showSwitch(123,2345);
//				menu.showBag(user.getWater(),user.getMoney());
		}else{
			System.out.println("**账号或者密码错误**");
		}

	}
	
	public static void main(String[] args) {
		Index i = new Index();
		i.showIndex();
	}
}
