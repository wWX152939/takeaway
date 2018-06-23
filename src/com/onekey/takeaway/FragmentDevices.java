package com.onekey.takeaway;

import mythware.http.CloudUpdateVersionServer.CloudInterface;
import mythware.http.CloudUpdateVersionServer.CloudResponseStatus;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.ab.fragment.AbAlertDialogFragment.AbDialogOnClickListener;
import com.ab.util.AbDialogUtil;
import com.ab.util.AbToastUtil;
import com.onekey.common.LogUtils;
import com.onekey.http.CloudManager;
import com.onekey.takeaway.bean.DeviceBean;
import com.onekey.takeaway.bean.DeviceBean.InnerDeviceBean;
import com.onekey.takeaway.ux.DataListAdapter;
import com.onekey.takeaway.ux.DataListAdapter.ViewHolder;

public class FragmentDevices extends Fragment {
	GridView mGridView;
	private DataListAdapter<InnerDeviceBean> mDataListAdapter;

	public static FragmentDevices newInstance(String text) {
		FragmentDevices fragmentCommon = new FragmentDevices();
		Bundle bundle = new Bundle();
		bundle.putString("text", text);
		fragmentCommon.setArguments(bundle);
		return fragmentCommon;
	}

    public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		LogUtils.d("ll1 hidden=" + hidden);
		if (!hidden) {
			initData();
		}
	}
	
	void initData() {
//		List<InnerDeviceBean> list = new ArrayList<InnerDeviceBean>();
//		for (int i = 0; i < 10; i++) {
//			InnerDeviceBean bean = new InnerDeviceBean("张三", "大饭煲", "黄瓜", "西红柿蛋汤", 17, "running", 100, "黄焖鸡米饭", 18);
//			list.add(bean);
//		}
//		mDataListAdapter.setShowDataList(list);
		CloudManager.getInstance().requestDevList(new CloudInterface() {
			
			@Override
			public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
				if (arg0 == CloudResponseStatus.Succ) {
					DeviceBean DeviceBean = (com.onekey.takeaway.bean.DeviceBean) arg1;
					LogUtils.d("ll1 size=" + DeviceBean.getDevList().size());
					mDataListAdapter.setShowDataList(DeviceBean.getDevList());

				}
			}
		});
	}
	
	@Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_device, container, false);
		mGridView = (GridView) view.findViewById(R.id.gridView);
		mDataListAdapter = new DataListAdapter<InnerDeviceBean>(getActivity(), new DataListAdapter.ListAdapterInterface<InnerDeviceBean>() {

			@Override
			public int getLayoutId() {
				return R.layout.item_fragment_devices;
			}

			@Override
			public void regesterListeners(ViewHolder viewHolder, int position) {
				// TODO Auto-generated method stub
				
			}

			@SuppressLint("ResourceAsColor") @Override
			public void initListViewItem(View convertView, final ViewHolder holder,
					DataListAdapter<InnerDeviceBean> adapter, int position) {
				// TODO Auto-generated method stub
				final InnerDeviceBean bean = adapter.getItem(position);
				
				holder.tvs[0].setText(bean.getName());
				holder.tvs[1].setText(bean.getDeviceID());
				holder.tvs[2].setText(bean.getStatus());
				if (bean.getStatusInt() == 3) {
					holder.tvs[2].setTextColor(getResources().getColor(android.R.color.holo_red_light));
				} else {
					holder.tvs[2].setTextColor(getResources().getColor(android.R.color.white));
				}
				holder.tvs[2].setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if (bean.getStatusInt() == 3) {
							AbDialogUtil.showAlertDialog(getActivity(), "修改状态", "确认要上架该设备吗？", new AbDialogOnClickListener() {
								
								@Override
								public void onPositiveClick() {
									CloudManager.getInstance().ModifyDevStatus(new CloudInterface() {
										
										@Override
										public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
											if (arg0 == CloudResponseStatus.Succ) {
												bean.setStatus(0);
												holder.tvs[2].setText("空闲");
												holder.tvs[2].setTextColor(getResources().getColor(android.R.color.white));
											}
										}
									}, bean.getDeviceID(), 0);
								}
								
								@Override
								public void onNegativeClick() {
									AbDialogUtil.removeDialog(getActivity());
								}
							});
						} else {
							AbDialogUtil.showAlertDialog(getActivity(), "修改状态", "确认要下架该设备吗？", new AbDialogOnClickListener() {
								
								@Override
								public void onPositiveClick() {
									CloudManager.getInstance().ModifyDevStatus(new CloudInterface() {
										
										@Override
										public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
											if (arg0 == CloudResponseStatus.Succ) {
												bean.setStatus(3);
												holder.tvs[2].setText("故障");
												holder.tvs[2].setTextColor(getResources().getColor(android.R.color.holo_red_light));
											}
										}
									}, bean.getDeviceID(), 3);
								}
								
								@Override
								public void onNegativeClick() {
									AbDialogUtil.removeDialog(getActivity());
								}
							});
						}
						
					}
				});
			}

			@SuppressLint("ResourceAsColor") @Override
			public void initLayout(View convertView, ViewHolder holder) {
				// TODO Auto-generated method stub
				convertView.setBackgroundColor(getResources().getColor(R.color.title));
				holder.tvs = new TextView[3];
				holder.tvs[0] = (TextView)convertView.findViewById(R.id.tv1);
				holder.tvs[1] = (TextView)convertView.findViewById(R.id.tv2);
				holder.tvs[2] = (TextView)convertView.findViewById(R.id.tv3);
				
			}

			@Override
			public boolean isSameObject(InnerDeviceBean t1, InnerDeviceBean t2) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		mGridView.setAdapter(mDataListAdapter);
		
		initData();
		return view;
	}
}
