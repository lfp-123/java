package com.newland.boss.test;

import com.newland.boss.bbf.config.ConfigCenter;

public class App {
	public static void main(String[] args) throws Exception {
		ConfigCenter.init();
		System.out.println( ConfigCenter.get("product_name") );
	}
}
