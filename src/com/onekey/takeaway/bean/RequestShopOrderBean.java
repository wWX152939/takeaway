package com.onekey.takeaway.bean;

public class RequestShopOrderBean extends TokenBean {
	String shopID;
	int pageIndex;

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

	@Override
	public String toString() {
		return "RequestShopOrderBean [shopID=" + shopID + ", pageIndex="
				+ pageIndex + "]";
	}

}
