package com.onekey.takeaway.bean;

import java.util.List;

public class FoodBean extends MsgBean{
	
	List<InnerFoodBean> foodlist;
	

	public List<InnerFoodBean> getFoodlist() {
		return foodlist;
	}


	public void setFoodlist(List<InnerFoodBean> foodlist) {
		this.foodlist = foodlist;
	}


	@Override
	public String toString() {
		return "FoodBean [foodlist=" + foodlist + "]";
	}


	public static class InnerFoodBean {
		String foodID;
		int total;
		String name;
		String pay;
		String picURL;
		public String getFoodID() {
			return foodID;
		}
		public void setFoodID(String foodID) {
			this.foodID = foodID;
		}
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPay() {
			return pay;
		}
		public void setPay(String pay) {
			this.pay = pay;
		}
		public String getPicURL() {
			return picURL;
		}
		public void setPicURL(String picURL) {
			this.picURL = picURL;
		}
		@Override
		public String toString() {
			return "InnerFoodBean [foodID=" + foodID + ", total=" + total
					+ ", name=" + name + ", pay=" + pay + ", picURL=" + picURL
					+ "]";
		}
		
		
	}
}
