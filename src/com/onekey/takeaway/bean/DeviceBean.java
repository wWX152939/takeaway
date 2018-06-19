package com.onekey.takeaway.bean;

import java.util.List;

public class DeviceBean extends MsgBean{
	
	List<InnerDeviceBean> devList;

	public List<InnerDeviceBean> getDevList() {
		return devList;
	}


	public void setDevList(List<InnerDeviceBean> devList) {
		this.devList = devList;
	}


	@Override
	public String toString() {
		return "DeviceBean [devList=" + devList + "]";
	}


	public static class InnerDeviceBean {
		String deviceID;
		String name;
		String status;
		public String getDeviceID() {
			return deviceID;
		}
		public void setDeviceID(String deviceID) {
			this.deviceID = deviceID;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		@Override
		public String toString() {
			return "InnerDeviceBean [deviceID=" + deviceID + ", name="
					+ name + ", status=" + status + "]";
		}
		
				
		
	}
}
