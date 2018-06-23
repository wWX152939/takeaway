package com.onekey.takeaway;

import java.util.ArrayList;
import java.util.List;

import mythware.http.CloudUpdateVersionServer.CloudInterface;
import mythware.http.CloudUpdateVersionServer.CloudResponseStatus;
import android.app.Fragment;
import android.os.Bundle;
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
import com.onekey.takeaway.bean.MsgBean;
import com.onekey.takeaway.bean.OrderBean;
import com.onekey.takeaway.bean.OrderBean.InnerOrderBean;
import com.onekey.takeaway.ux.DataListAdapter;
import com.onekey.takeaway.ux.DataListAdapter.ViewHolder;

public class FragmentOrder extends Fragment {
	GridView mGridView;
	private AbPullToRefreshView mAbPullToRefreshView;
	private TextView mTv1, mTv2;
	
	private DataListAdapter<InnerOrderBean> mDataListAdapter;
	private int mCurrentPage = 1;
	private String mStatus;

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
			InnerOrderBean bean = new InnerOrderBean("张三", "大饭煲", "黄瓜", "西红柿蛋汤", 17, 1, "黄焖鸡米饭", 18,  1111);
			list.add(bean);
		}
		mDataListAdapter.addShowDataList(list);
		
	}
	
	void loadData() {
		CloudManager.getInstance().requestOrderList(new CloudInterface() {
			
			@Override
			public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
				if (arg0 == CloudResponseStatus.Succ) {
					OrderBean bean = (OrderBean) arg1;
					if (bean.getOrderList() != null) {
						mDataListAdapter.addShowDataList(bean.getOrderList());
					}
				} else {
					Toast.makeText(getActivity(), "已经加载到最后", Toast.LENGTH_SHORT).show();
				}
				mAbPullToRefreshView.onFooterLoadFinish();
			}
		}, mCurrentPage++, mStatus);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_order, container, false);
		mGridView = (GridView) view.findViewById(R.id.gridView);
		mAbPullToRefreshView = (AbPullToRefreshView)view.findViewById(R.id.refreshview);
		mDataListAdapter = new DataListAdapter<InnerOrderBean>(getActivity(), new DataListAdapter.ListAdapterInterface<InnerOrderBean>() {

			@Override
			public int getLayoutId() {
				return R.layout.item_fragment_order;
			}

			@Override
			public void regesterListeners(ViewHolder viewHolder, int position) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void initListViewItem(View convertView, ViewHolder holder,
					DataListAdapter<InnerOrderBean> adapter, int position) {
				// TODO Auto-generated method stub
				InnerOrderBean bean = adapter.getItem(position);
				holder.tvs[0].setText(bean.getFood());
				holder.tvs[1].setText(bean.getDeviceType());
				holder.tvs[2].setText(bean.getDeviceId() + "");
				holder.tvs[3].setText(bean.getCustom());
				holder.tvs[4].setText(bean.getPay() + "");
				holder.tvs[5].setText(bean.getDoorId() + "");
				holder.tvs[6].setText(bean.getState());
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
				
			}

			@Override
			public boolean isSameObject(InnerOrderBean t1, InnerOrderBean t2) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		mGridView.setAdapter(mDataListAdapter);
		mTv1 = (TextView) view.findViewById(R.id.tv11);
		mTv2 = (TextView) view.findViewById(R.id.tv12);
		mTv1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mTv1.setBackgroundColor(getResources().getColor(R.color.title));
				mTv2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
				mCurrentPage = 1;
				mStatus = "unfinished";
				CloudManager.getInstance().requestOrderList(new CloudInterface() {
					
					@Override
					public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
						if (arg0 == CloudResponseStatus.Succ) {
							OrderBean bean = (OrderBean) arg1;
							if (bean.getOrderList() != null) {
								mDataListAdapter.setShowDataList(bean.getOrderList());
							}
						}						
					}
				}, mCurrentPage++, mStatus);
			}
		});
		mTv2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mTv2.setBackgroundColor(getResources().getColor(R.color.title));
				mTv1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
				mCurrentPage = 1;
				mStatus = "finished";
				CloudManager.getInstance().requestOrderList(new CloudInterface() {
					
					@Override
					public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
						if (arg0 == CloudResponseStatus.Succ) {
							OrderBean bean = (OrderBean) arg1;
							if (bean.getOrderList() != null) {
								mDataListAdapter.setShowDataList(bean.getOrderList());
							}
						}						
					}
				}, mCurrentPage++, mStatus);				
			}
		});
		
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
				}, mCurrentPage++, mStatus);
			}
		});
		
		mTv1.performClick();
//		loadData();
//		initData();
		return view;
	}
}
