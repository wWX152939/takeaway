package com.onekey.takeaway.bean;

import java.util.List;

public class FoodBean extends MsgBean{
	
	List<InnerFoodBean> foodlist;
	

	public static class InnerFoodBean {
		int foodID;
		int total;
		String name;
		String pay;
		String picURL;
		
				
		
	}
}
