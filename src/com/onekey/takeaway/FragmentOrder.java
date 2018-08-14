package com.onekey.takeaway;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mythware.common.LogUtils;
import mythware.http.CloudUpdateVersionServer.CloudInterface;
import mythware.http.CloudUpdateVersionServer.CloudResponseStatus;
import android.app.Fragment;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.view.pullview.AbPullToRefreshView;
import com.ab.view.pullview.AbPullToRefreshView.OnFooterLoadListener;
import com.ab.view.pullview.AbPullToRefreshView.OnHeaderRefreshListener;
import com.onekey.http.CloudManager;
import com.onekey.takeaway.bean.DeviceFoodBean;
import com.onekey.takeaway.bean.DoorBean;
import com.onekey.takeaway.bean.OrderBean;
import com.onekey.takeaway.bean.DeviceFoodBean.InnerDeviceBean;
import com.onekey.takeaway.bean.DeviceFoodBean.InnerDeviceFoodBean;
import com.onekey.takeaway.bean.OrderBean.InnerOrderBean;
import com.onekey.takeaway.bean.ResponseFoodStoreBean;
import com.onekey.takeaway.ux.DataListAdapter;
import com.onekey.takeaway.ux.DataListAdapter.ViewHolder;

public class FragmentOrder extends Fragment {
	GridView mGridView;
	private AbPullToRefreshView mAbPullToRefreshView;
//	private TextView mTv1, mTv2;
	
	private DataListAdapter<InnerOrderBean> mDataListAdapter;
	private int mCurrentPage = 1;
	private String mStatus;
	
	private String mCurrentType = FragmentOperation.tab1;
	private int mCurrentDevType = 1;

	public static FragmentOrder newInstance(String text) {
		FragmentOrder fragmentCommon = new FragmentOrder();
		Bundle bundle = new Bundle();
		bundle.putString("text", text);
		fragmentCommon.setArguments(bundle);
		return fragmentCommon;
	}
	
	void initData() {
		List<InnerOrderBean> list = new ArrayList<InnerOrderBean>();
		for (int i = 0; i < 10; i++) {
			InnerOrderBean bean = new InnerOrderBean("张三", 1, "黄瓜", "西红柿蛋汤", 17, 1, "黄焖鸡米饭", "18",  1111);
			list.add(bean);
		}
		mDataListAdapter.addShowDataList(list);
		
	}
	
	void loadData() {

		if (mCurrentType.equals(FragmentOperation.tab1)) {
			mCurrentDevType = 1;
		} else if (mCurrentType.equals(FragmentOperation.tab2)){
			mCurrentDevType = 2;
		} else {
			mCurrentDevType = 17;
		}
		String lastOrderId = "";
		if (mDataListAdapter.getDataShowList().size() != 0) {
			lastOrderId = mDataListAdapter.getDataShowList().get(mDataListAdapter.getDataShowList().size() -1).getOrderId() + "";
		}
		CloudManager.getInstance().requestOrderList(new CloudInterface() {
			
			@Override
			public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
				if (arg0 == CloudResponseStatus.Succ) {
					LogUtils.i("ll1 mCurrentType:" + mCurrentType);
					OrderBean bean = (OrderBean) arg1;
					List<InnerOrderBean> cList = new ArrayList<InnerOrderBean>();
					for (InnerOrderBean item : bean.getOrderList()) {
						if (mCurrentType.equals(FragmentOperation.tab1)) {
							if (item.getDevType() == 1) {
								// caofanji
								cList.add(item);
							}
						} else if (mCurrentType.equals(FragmentOperation.tab2)) {
							if (item.getDevType() == 2) {
								// soutangji 
								cList.add(item);
							}
						} else if (mCurrentType.equals(FragmentOperation.tab4)) {
							if (item.getDevType() == 17) {
								// soutangji 
								cList.add(item);
							}
						}
						
					}
					LogUtils.i("ll1 cList:" + cList);
					if (bean.getOrderList() != null) {
						mDataListAdapter.addShowDataList(cList);
					}
				} else {
					Toast.makeText(getActivity(), "已经加载到最后", Toast.LENGTH_SHORT).show();
				}
				mAbPullToRefreshView.onFooterLoadFinish();
			}
		}, mCurrentDevType, lastOrderId, mStatus);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mCurrentType = getArguments().getString("text");
		View view;
		if (mCurrentType.equals(FragmentOperation.tab4)) {
			view = inflater
					.inflate(R.layout.fragment_order_cabinet, container, false);
		} else {
			view = inflater
					.inflate(R.layout.fragment_order, container, false);
		}
		mGridView = (GridView) view.findViewById(R.id.gridView);
		mAbPullToRefreshView = (AbPullToRefreshView)view.findViewById(R.id.refreshview);
		mDataListAdapter = new DataListAdapter<InnerOrderBean>(getActivity(), new DataListAdapter.ListAdapterInterface<InnerOrderBean>() {

			@Override
			public int getLayoutId() {

				if (mCurrentType.equals(FragmentOperation.tab4)) {
					return R.layout.item_fragment_order_cabinet;
				} else {
					return R.layout.item_fragment_order;
				}
			}

			@Override
			public void regesterListeners(final ViewHolder viewHolder, final int position) {
				// TODO Auto-generated method stub
				viewHolder.tvs[5].setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						CloudManager.getInstance().requestUnusedCabinet(new CloudInterface() {
							
							@Override
							public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
								LogUtils.i("ll1 responseBody:" + arg0);
								DoorBean DoorBean = (com.onekey.takeaway.bean.DoorBean) arg1;
								if (arg0 == CloudResponseStatus.Succ) {
//									LogUtils.i("ll1 responseBody:" + DoorBean.getDoorCode());
									viewHolder.tvs[5].setText(DoorBean.getDoor().getDoorCode());
								} else {
									Toast.makeText(getActivity(), DoorBean.getErrorInfo(), Toast.LENGTH_SHORT).show();
								}
							}
						}, mDataListAdapter.getItem(position).getOrderId());
					}
				});
				viewHolder.tvs[0].setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						CloudManager.getInstance().requestFoodStore(new CloudInterface() {
							
							@Override
							public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
								if (arg0 == CloudResponseStatus.Succ) {
									ResponseFoodStoreBean DoorBean = (com.onekey.takeaway.bean.ResponseFoodStoreBean) arg1;
									int total = 0;
									for (int i = 0; i < DoorBean.getStockList().size(); i++) {
										LogUtils.i("ll1 DoorBean.getStockList().get(i).getStock():" + DoorBean.getStockList().get(i).getStock());
										if (DoorBean.getStockList().get(i).getStock() == null) {
											continue;
										}
										total += Integer.parseInt(DoorBean.getStockList().get(i).getStock());
									}
									Toast.makeText(getActivity(), "剩余" + total + "个", Toast.LENGTH_SHORT).show();
								}
							}
						}, mDataListAdapter.getItem(position).getFoodId());						
					}
				});
			}

			@Override
			public void initListViewItem(View convertView, ViewHolder holder,
					DataListAdapter<InnerOrderBean> adapter, int position) {
				// TODO Auto-generated method stub
				InnerOrderBean bean = adapter.getItem(position);
				holder.tvs[0].setText(bean.getFoodName());
				holder.tvs[1].setText(bean.getDevType() + "");
				holder.tvs[2].setText(bean.getDevId() + "");
				holder.tvs[3].setText(bean.getCustom());
				holder.tvs[4].setText(bean.getPay() + "");
				holder.tvs[5].setText(bean.getDoorId() + "");
				holder.tvs[6].setText(bean.getState());
				holder.tvs[7].setText(bean.getGenTime());
			}

			@Override
			public void initLayout(View convertView, ViewHolder holder) {
				// TODO Auto-generated method stub
				convertView.setBackgroundColor(getResources().getColor(R.color.title));
				holder.tvs = new TextView[9];
				holder.tvs[0] = (TextView)convertView.findViewById(R.id.tv1);
				holder.tvs[1] = (TextView)convertView.findViewById(R.id.tv2);
				holder.tvs[2] = (TextView)convertView.findViewById(R.id.tv3);
				holder.tvs[3] = (TextView)convertView.findViewById(R.id.tv4);
				holder.tvs[4] = (TextView)convertView.findViewById(R.id.tv5);
				holder.tvs[5] = (TextView)convertView.findViewById(R.id.tv6);
				holder.tvs[6] = (TextView)convertView.findViewById(R.id.tv7);
				holder.tvs[7] = (TextView)convertView.findViewById(R.id.tv41);
				
			}

			@Override
			public boolean isSameObject(InnerOrderBean t1, InnerOrderBean t2) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		mGridView.setAdapter(mDataListAdapter);
//		mTv1 = (TextView) view.findViewById(R.id.tv11);
//		mTv2 = (TextView) view.findViewById(R.id.tv12);
//		mTv1.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				mTv1.setBackgroundColor(getResources().getColor(R.color.title));
//				mTv2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
//				mCurrentPage = 1;
//				mStatus = "unfinished";
//				CloudManager.getInstance().requestOrderList(new CloudInterface() {
//					
//					@Override
//					public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
//						if (arg0 == CloudResponseStatus.Succ) {
//							OrderBean bean = (OrderBean) arg1;
//							if (bean.getOrderList() != null) {
//								mDataListAdapter.setShowDataList(bean.getOrderList());
//							}
//						}						
//					}
//				}, mCurrentPage++, mStatus);
//			}
//		});
//		mTv2.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				mTv2.setBackgroundColor(getResources().getColor(R.color.title));
//				mTv1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
//				mCurrentPage = 1;
//				mStatus = "finished";
//				CloudManager.getInstance().requestOrderList(new CloudInterface() {
//					
//					@Override
//					public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
//						if (arg0 == CloudResponseStatus.Succ) {
//							OrderBean bean = (OrderBean) arg1;
//							if (bean.getOrderList() != null) {
//								mDataListAdapter.setShowDataList(bean.getOrderList());
//							}
//						}						
//					}
//				}, mCurrentPage++, mStatus);				
//			}
//		});
//		
		mAbPullToRefreshView.setOnFooterLoadListener(new OnFooterLoadListener() {
			
			@Override
			public void onFooterLoad(AbPullToRefreshView arg0) {
				loadData();
			}
		});
		mAbPullToRefreshView.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
			
			@Override
			public void onHeaderRefresh(AbPullToRefreshView arg0) {
				mCurrentPage = 1;
				CloudManager.getInstance().requestOrderList(new CloudInterface() {
					
					@Override
					public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
						if (arg0 == CloudResponseStatus.Succ) {
							OrderBean bean = (OrderBean) arg1;
							if (bean.getOrderList() != null) {
								mDataListAdapter.setShowDataList(bean.getOrderList());
							}
						} 
						mAbPullToRefreshView.onHeaderRefreshFinish();
					}
				}, mCurrentDevType, "", mStatus);
			}
		});
		
//		mTv1.performClick();
		loadData();
//		initData();
		return view;
	}
}
