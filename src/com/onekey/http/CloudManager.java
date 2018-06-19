package com.onekey.http;

import mythware.common.LogUtils;
import mythware.http.CloudUpdateVersionServer.CloudInterface;
import mythware.http.CloudUpdateVersionServer.CloudResponseStatus;
import mythware.http.DataManagerInterface;
import mythware.http.DataUtils;
import mythware.http.NetworkAsyncTask;

import com.onekey.takeaway.bean.DeviceBean;
import com.onekey.takeaway.bean.FoodBean;
import com.onekey.takeaway.bean.LoginBean;
import com.onekey.takeaway.bean.MsgBean;
import com.onekey.takeaway.bean.RequestDevBean;
import com.onekey.takeaway.bean.ShopBean;
import com.onekey.takeaway.bean.TokenBean;

public class CloudManager {
	private final String URL_CLOUD = "http://203.195.244.21:8080/auto-sell-web";
	private final String URL_CLOUD_PAD = "http://203.195.244.21:8080/auto-sell-web/pad";
	private final String URL_login = URL_CLOUD_PAD + "/login/login.do";
	private final String URL_request_info = URL_CLOUD_PAD + "";
	private final String URL_request_foodlist = URL_CLOUD_PAD + "/shop/foodList.do";
	private final String URL_modify_food = URL_CLOUD_PAD + "";
	private final String URL_request_shop_devices = URL_CLOUD_PAD + "/dev/list.do";
	private final String URL_modify_device_status = URL_CLOUD_PAD + "";
	private final String URL_request_unfinished_order = URL_CLOUD_PAD + "";
	private final String URL_request_finished_order = URL_CLOUD_PAD + "";
	private final String URL_logout = URL_CLOUD_PAD + "/logout/logout.do";
	
	private ShopBean mShopBean;
	
	public void generateShopBean() {
		mShopBean = new ShopBean();
		mShopBean.setShopID("6fdf23bd-9172-4775-b5ac-8b5efd6c677f");
		mShopBean.setTitle("1号店");
		mShopBean.setInfo("卖菜");
		mShopBean.setToken("eddc225a-12c4-4911-936a-42c3e663bb64");
	}
	
	private static CloudManager mClassCloudManager;
	public static synchronized CloudManager getInstance() {
		if (mClassCloudManager == null) {
			mClassCloudManager = new CloudManager();
		}
		return mClassCloudManager;
	}
	
	public void login(final CloudInterface cloudInterface, String account, String password) {
		NetworkAsyncTask networkAsyncTask = new NetworkAsyncTask(new DataManagerInterface() {
			
			@Override
			public void getDataOnResult(String responseBody) {
				LogUtils.i("wzw responseBody:" + responseBody);
				if (responseBody == null) {
					cloudInterface.cloudCallback(CloudResponseStatus.ErrorNetwork, null);
					return;
				}
				ShopBean info = (ShopBean) DataUtils.getObject(ShopBean.class, responseBody);
				if (info.getErrorcode() == 0) {
					mShopBean = info;
					cloudInterface.cloudCallback(CloudResponseStatus.Succ, info);
				} else {
					cloudInterface.cloudCallback(CloudResponseStatus.Failed, info);
				}
			}
		});
		LoginBean loginBean = new LoginBean();
		loginBean.setAccount(account);
		loginBean.setPassword(password);
		String requestBody = DataUtils.beanToJson(loginBean);
		networkAsyncTask.setUrl(URL_login).setRequestType(NetworkAsyncTask.TYPE_POST).setRequestBody(requestBody).execute();
	}
	
	public void logout(final CloudInterface cloudInterface) {
		if (mShopBean == null) {
			cloudInterface.cloudCallback(CloudResponseStatus.Failed, null);
			return;
		}
		NetworkAsyncTask networkAsyncTask = new NetworkAsyncTask(new DataManagerInterface() {
			
			@Override
			public void getDataOnResult(String responseBody) {
				LogUtils.i("wzw responseBody:" + responseBody);
				if (responseBody == null) {
					cloudInterface.cloudCallback(CloudResponseStatus.ErrorNetwork, null);
					return;
				}
				MsgBean info = (MsgBean) DataUtils.getObject(MsgBean.class, responseBody);
				if (info.getErrorcode() == 0) {
					cloudInterface.cloudCallback(CloudResponseStatus.Succ, info);
				} else {
					cloudInterface.cloudCallback(CloudResponseStatus.Failed, info);
				}
			}
		});
		networkAsyncTask.setUrl(URL_logout).setRequestType(NetworkAsyncTask.TYPE_POST).setRequestBody(getToken()).execute();
	}
	
	public String getToken() {
		if (mShopBean == null) {
			return null;
		}
		TokenBean bean = new TokenBean();
		bean.setToken(mShopBean.getToken());
		return DataUtils.beanToJson(bean);
	}
	
	public void requestFoodList(final CloudInterface cloudInterface) {
		if (mShopBean == null) {
			cloudInterface.cloudCallback(CloudResponseStatus.Failed, null);
			return;
		}
		NetworkAsyncTask networkAsyncTask = new NetworkAsyncTask(new DataManagerInterface() {
			
			@Override
			public void getDataOnResult(String responseBody) {
				LogUtils.i("wzw responseBody:" + responseBody);
				if (responseBody == null) {
					cloudInterface.cloudCallback(CloudResponseStatus.ErrorNetwork, null);
					return;
				}
				FoodBean info = (FoodBean) DataUtils.getObject(FoodBean.class, responseBody);
				if (info.getErrorcode() == 0) {
					cloudInterface.cloudCallback(CloudResponseStatus.Succ, info);
				} else {
					cloudInterface.cloudCallback(CloudResponseStatus.Failed, info);
				}
			}
		});
		networkAsyncTask.setUrl(URL_request_foodlist).setRequestType(NetworkAsyncTask.TYPE_POST).setRequestBody(getToken()).execute();
	}
	
	public void requestDevList(final CloudInterface cloudInterface) {
		if (mShopBean == null) {
			cloudInterface.cloudCallback(CloudResponseStatus.Failed, null);
			return;
		}
		NetworkAsyncTask networkAsyncTask = new NetworkAsyncTask(new DataManagerInterface() {
			
			@Override
			public void getDataOnResult(String responseBody) {
				LogUtils.i("wzw responseBody:" + responseBody);
				if (responseBody == null) {
					cloudInterface.cloudCallback(CloudResponseStatus.ErrorNetwork, null);
					return;
				}
				DeviceBean info = (DeviceBean) DataUtils.getObject(DeviceBean.class, responseBody);
				if (info.getErrorcode() == 0) {
					cloudInterface.cloudCallback(CloudResponseStatus.Succ, info);
				} else {
					cloudInterface.cloudCallback(CloudResponseStatus.Failed, info);
				}
			}
		});
		RequestDevBean bean = new RequestDevBean();
		bean.setToken(mShopBean.getToken());
		bean.setShopID(mShopBean.getShopID());
		String requestString = DataUtils.beanToJson(bean);
		networkAsyncTask.setUrl(URL_request_shop_devices).setRequestType(NetworkAsyncTask.TYPE_POST).setRequestBody(requestString).execute();
	}
}
