package com.onekey.takeaway.bean;

public class ModifyFoodBean extends TokenBean {
	String shopID;
	String foodID;
	String number;

	public String getShopID() {
		return shopID;
	}

	public void setShopID(String shopID) {
		this.shopID = shopID;
	}

	public String getFoodID() {
		return foodID;
	}

	public void setFoodID(String foodID) {
		this.foodID = foodID;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "ModifyFoodBean [shopID=" + shopID + ", foodID=" + foodID
				+ ", number=" + number + "]";
	}

}
