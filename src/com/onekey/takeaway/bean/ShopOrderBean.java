package com.onekey.takeaway.bean;

import java.util.List;

public class ShopOrderBean extends MsgBean{
	
	List<InnerShopOrderBean> orderList;
	String totalPage;
	int payTotal;
	

	public List<InnerShopOrderBean> getOrderList() {
		return orderList;
	}


	public void setOrderList(List<InnerShopOrderBean> orderList) {
		this.orderList = orderList;
	}


	public String getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}


	public int getPayTotal() {
		return payTotal;
	}


	public void setPayTotal(int payTotal) {
		this.payTotal = payTotal;
	}


	@Override
	public String toString() {
		return "ShopOrderBean [orderList=" + orderList + ", totalPage="
				+ totalPage + ", payTotal=" + payTotal + "]";
	}


	public static class InnerShopOrderBean {
		String number;
		String custom;
		int pay;
		String food;
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		public String getCustom() {
			return custom;
		}
		public void setCustom(String custom) {
			this.custom = custom;
		}
		public int getPay() {
			return pay;
		}
		public void setPay(int pay) {
			this.pay = pay;
		}
		public String getFood() {
			return food;
		}
		public void setFood(String food) {
			this.food = food;
		}
		@Override
		public String toString() {
			return "InnerShopOrderBean [number=" + number + ", custom=" + custom
					+ ", pay=" + pay + ", food=" + food + "]";
		}
		
		
	}
}
