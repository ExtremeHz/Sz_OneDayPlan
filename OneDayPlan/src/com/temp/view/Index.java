package com.temp.view;

import java.util.Scanner;


public class Index {
		
	private static String weiChatName = "iieen";
	
	public void showIndex() {
		System.out.println("**��ӭ����һ��֮��**\t\t\t\t\t\t\t�ͷ����ں�:" + weiChatName);
		System.out.println("�������û�ID�����벢��///��Ϊ���");
		Scanner input = new Scanner(System.in);
		input.nextLine();
		System.out.println("**�˺Ż����������**");
	}
	
	public static void main(String[] args) {
		Index i = new Index();
		i.showIndex();
	}
}
