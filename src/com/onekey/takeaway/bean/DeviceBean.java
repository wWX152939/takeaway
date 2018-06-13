package com.onekey.takeaway.bean;

import java.util.List;

public class DeviceBean extends MsgBean{
	
	List<InnerDeviceBean> devicelist;
	

	public static class InnerDeviceBean {
		int deviceID;
		String normal;
		String status;
		public int getDeviceID() {
			return deviceID;
		}
		public void setDeviceID(int deviceID) {
			this.deviceID = deviceID;
		}
		public String getNormal() {
			return normal;
		}
		public void setNormal(String normal) {
			this.normal = normal;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		@Override
		public String toString() {
			return "InnerDeviceBean [deviceID=" + deviceID + ", normal="
					+ normal + ", status=" + status + "]";
		}
		
				
		
	}
}
