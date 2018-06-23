package com.onekey.takeaway.bean;

public class RequestOrderListBean extends TokenBean {
	String shopID;
	int pageIndex;
	String orderState;

	public String getShopID() {
		return shopID;
	}

	public void setShopID(String shopID) {
		this.shopID = shopID;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	@Override
	public String toString() {
		return "RequestOrderListBean [shopID=" + shopID + ", pageIndex="
				+ pageIndex + ", orderState=" + orderState + "]";
	}

}
