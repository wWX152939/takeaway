package com.onekey.takeaway.bean;

import java.util.List;

public class OrderBean extends MsgBean{
	
	List<InnerOrderBean> orderlist;
	
	public List<InnerOrderBean> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(List<InnerOrderBean> orderlist) {
		this.orderlist = orderlist;
	}

	public static class InnerOrderBean {
		String custom;
		String chaofanji;
		String xiaocai;
		String tang;
		int peicangui;
		String status;
		int waitTime;
		String food;
		int pay;
		
		public InnerOrderBean() {
			
		}
				
		public InnerOrderBean(String custom, String chaofanji, String xiaocai,
				String tang, int peicangui, String status, int waitTime,
				String food, int pay) {
			super();
			this.custom = custom;
			this.chaofanji = chaofanji;
			this.xiaocai = xiaocai;
			this.tang = tang;
			this.peicangui = peicangui;
			this.status = status;
			this.waitTime = waitTime;
			this.food = food;
			this.pay = pay;
		}
		public String getCustom() {
			return custom;
		}
		public void setCustom(String custom) {
			this.custom = custom;
		}
		public String getChaofanji() {
			return chaofanji;
		}
		public void setChaofanji(String chaofanji) {
			this.chaofanji = chaofanji;
		}
		public String getXiaocai() {
			return xiaocai;
		}
		public void setXiaocai(String xiaocai) {
			this.xiaocai = xiaocai;
		}
		public String getTang() {
			return tang;
		}
		public void setTang(String tang) {
			this.tang = tang;
		}
		public int getPeicangui() {
			return peicangui;
		}
		public void setPeicangui(int peicangui) {
			this.peicangui = peicangui;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getWaitTime() {
			return waitTime;
		}
		public void setWaitTime(int waitTime) {
			this.waitTime = waitTime;
		}
		public String getFood() {
			return food;
		}
		public void setFood(String food) {
			this.food = food;
		}
		public int getPay() {
			return pay;
		}
		public void setPay(int pay) {
			this.pay = pay;
		}
		@Override
		public String toString() {
			return "InnerOrderBean [custom=" + custom + ", chaofanji="
					+ chaofanji + ", xiaocai=" + xiaocai + ", tang=" + tang
					+ ", peicangui=" + peicangui + ", status=" + status
					+ ", waitTime=" + waitTime + ", food=" + food + ", pay="
					+ pay + "]";
		}
		
	}
}
