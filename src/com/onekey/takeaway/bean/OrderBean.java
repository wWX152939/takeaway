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
		int devType;
		String xiaocai;
		String tang;
		int doorId;
		int state;
		String foodName;
		String foodId;
		String pay;
		int devId;
		int orderId;
		String genTime;
		
		public String getGenTime() {
			return genTime;
		}

		public void setGenTime(String genTime) {
			this.genTime = genTime;
		}

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
				
		public InnerOrderBean(String custom, int deviceType, String xiaocai,
				String tang, int doorId, int state, String food, String pay,
				int deviceId) {
			super();
			this.custom = custom;
			this.devType = deviceType;
			this.xiaocai = xiaocai;
			this.tang = tang;
			this.doorId = doorId;
			this.state = state;
			this.foodName = food;
			this.pay = pay;
			this.devId = deviceId;
		}

		public String getCustom() {
			return custom;
		}

		public void setCustom(String custom) {
			this.custom = custom;
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


		public String getFoodName() {
			return foodName;
		}

		public void setFoodName(String foodName) {
			this.foodName = foodName;
		}

		public String getFoodId() {
			return foodId;
		}

		public void setFoodId(String foodId) {
			this.foodId = foodId;
		}

		public String getPay() {
			return pay;
		}

		public void setPay(String pay) {
			this.pay = pay;
		}

		public int getDevType() {
			return devType;
		}

		public void setDevType(int devType) {
			this.devType = devType;
		}

		public int getDevId() {
			return devId;
		}

		public void setDevId(int devId) {
			this.devId = devId;
		}

		public int getOrderId() {
			return orderId;
		}

		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}

		@Override
		public String toString() {
			return "InnerOrderBean [custom=" + custom + ", devType=" + devType
					+ ", xiaocai=" + xiaocai + ", tang=" + tang + ", doorId="
					+ doorId + ", state=" + state + ", foodName=" + foodName
					+ ", foodId=" + foodId + ", pay=" + pay + ", devId="
					+ devId + ", orderId=" + orderId + ", genTime=" + genTime
					+ "]";
		}

		
	}
}
