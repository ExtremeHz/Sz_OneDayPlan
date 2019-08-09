package com.temp.view;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainPage{

	public void  showMainPage(int waterDrop)  {
		if(-1 != waterDrop) {
			System.out.println("**一日之计（已完成今日打卡你获得 "+ waterDrop +"水滴）**\t\t\t\t\t输入页面前缀进入相应页面");
		}else {
			System.out.println("**一日之计**\t\t\t\t\t输入页面前缀进入相应页面");
		}
		menu();
		SimpleDateFormat sFormat = new SimpleDateFormat("hh:mm");
		System.out.println("\t\t\t\t" + sFormat.format(new Date()));
	}

	public void menu() {
		System.out.println("1、专注模式(预计收入价值\"树对应金币数\"的\"树名\"及\"果实名\")");
		System.out.println("2、功能菜单(请注意分配你的时间和精力)");
	}

	public void status(int status,String coin,int time,String thing,int waterDrop) {
		menu();
		//status 1继续保持 2专注失败 3专注成功
		if(status == 1) {
			System.out.println("请保持关注！");
		}else if(status == 2) {
			System.out.println("专注失败 !");
			System.out.println("请接收：  枯树树干(" + coin + "金币)");
		}else if(status == 3) {
			System.out.println("恭喜你成功专注了" + time + "分钟");
			System.out.println("请接收：  "+ thing +" ("+ coin + "金币)");
			System.out.println("请接收：  水滴(" + waterDrop + ")");
		}else {
			System.out.println("网络异常！！！");
		}

		SimpleDateFormat sFormat = new SimpleDateFormat("hh:mm");
		System.out.println("\t\t\t\t" + sFormat.format(new Date()));
	}


	public static void main(String[] args) {
		MainPage m = new MainPage();
		m.showMainPage(2);
	}
}
