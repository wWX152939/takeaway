package com.onekey.takeaway.bean;

import java.util.List;

public class OrderBean extends MsgBean{
	String totalPage;
	List<InnerOrderBean> orderList;
	

	public List<InnerOrderBean> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<InnerOrderBean> orderList) {
		this.orderList = orderList;
	}

	public String getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	public String toString() {
		return "OrderBean [totalPage=" + totalPage + ", orderList=" + orderList
				+ "]";
	}
	
	public static class InnerOrderBean {
		String custom;
		String deviceType;
		String xiaocai;
		String tang;
		int doorId;
		int state;
		String food;
		int pay;
		int deviceId;
		
		public String getStatus() {
			String ret = "";
			switch (state) {
			case 1:
				ret = "未支付";
				break;
			case 2:
				ret = "已支付";
				break;
			case 3:
				ret = "制作中";
				break;
			case 4:
				ret = "制作完成";
				break;
			case 5:
				ret = "已放入箱柜，未取走";
				break;
			case 6:
				ret = "已取走，订单完成结束";
				break;
				
			}
			return ret;
		}
		
		public InnerOrderBean() {
			
		}
				
		public InnerOrderBean(String custom, String deviceType, String xiaocai,
				String tang, int doorId, int state, String food, int pay,
				int deviceId) {
			super();
			this.custom = custom;
			this.deviceType = deviceType;
			this.xiaocai = xiaocai;
			this.tang = tang;
			this.doorId = doorId;
			this.state = state;
			this.food = food;
			this.pay = pay;
			this.deviceId = deviceId;
		}

		public String getCustom() {
			return custom;
		}

		public void setCustom(String custom) {
			this.custom = custom;
		}

		public String getDeviceType() {
			return deviceType;
		}

		public void setDeviceType(String deviceType) {
			this.deviceType = deviceType;
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

		public int getDoorId() {
			return doorId;
		}

		public void setDoorId(int doorId) {
			this.doorId = doorId;
		}

		public String getState() {
			String ret = "";
			switch (state) {
			case 1:
				ret = "未支付";
				break;
			case 2:
				ret = "已支付";
				break;
			case 3:
				ret = "制作中";
				break;
			case 4:
				ret = "制作完成";
				break;
			case 5:
				ret = "已放入箱柜，未取走";
				break;
			case 6:
				ret = "已取走，订单完成结束";
				break;
				
			}
			return ret;
		}

		public void setState(int state) {
			this.state = state;
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

		public int getDeviceId() {
			return deviceId;
		}

		public void setDeviceId(int deviceId) {
			this.deviceId = deviceId;
		}

		@Override
		public String toString() {
			return "InnerOrderBean [custom=" + custom + ", deviceType="
					+ deviceType + ", xiaocai=" + xiaocai + ", tang=" + tang
					+ ", doorId=" + doorId + ", state=" + state + ", food="
					+ food + ", pay=" + pay + ", deviceId=" + deviceId + "]";
		}

		
	}
}
