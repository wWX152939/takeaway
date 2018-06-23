package com.onekey.takeaway;

import mythware.http.CloudUpdateVersionServer.CloudInterface;
import mythware.http.CloudUpdateVersionServer.CloudResponseStatus;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.ab.view.pullview.AbPullToRefreshView;
import com.ab.view.pullview.AbPullToRefreshView.OnFooterLoadListener;
import com.ab.view.pullview.AbPullToRefreshView.OnHeaderRefreshListener;
import com.onekey.common.LogUtils;
import com.onekey.http.CloudManager;
import com.onekey.takeaway.bean.ShopBean;
import com.onekey.takeaway.bean.ShopOrderBean;
import com.onekey.takeaway.bean.ShopOrderBean.InnerShopOrderBean;
import com.onekey.takeaway.ux.DataListAdapter;
import com.onekey.takeaway.ux.DataListAdapter.ViewHolder;

public class FragmentCount extends Fragment {
	GridView mGridView;
	private AbPullToRefreshView mAbPullToRefreshView;
	private TextView mTv1, mTv2, mTv3;
	
	private DataListAdapter<InnerShopOrderBean> mDataListAdapter;
	private int mCurrentPage = 1;

	public static FragmentCount newInstance(String text) {
		FragmentCount fragmentCommon = new FragmentCount();
		Bundle bundle = new Bundle();
		bundle.putString("text", text);
		fragmentCommon.setArguments(bundle);
		return fragmentCommon;
	}
	
	void loadData() {
		CloudManager.getInstance().requestShopOrderList(new CloudInterface() {
			
			@Override
			public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
				if (arg0 == CloudResponseStatus.Succ) {
					ShopOrderBean bean = (ShopOrderBean) arg1;
					if (bean.getOrderList() != null) {
						mDataListAdapter.addShowDataList(bean.getOrderList());
					}
					mTv3.setText("订单总额：" + bean.getPayTotal() + "");
				}
				mAbPullToRefreshView.onFooterLoadFinish();
			}
		}, mCurrentPage++);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_count, container, false);
		mGridView = (GridView) view.findViewById(R.id.gridView);
		mAbPullToRefreshView = (AbPullToRefreshView)view.findViewById(R.id.refreshview);
		mDataListAdapter = new DataListAdapter<InnerShopOrderBean>(getActivity(), new DataListAdapter.ListAdapterInterface<InnerShopOrderBean>() {

			@Override
			public int getLayoutId() {
				return R.layout.item_fragment_shop_order;
			}

			@Override
			public void regesterListeners(ViewHolder viewHolder, int position) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void initListViewItem(View convertView, ViewHolder holder,
					DataListAdapter<InnerShopOrderBean> adapter, int position) {
				// TODO Auto-generated method stub
				InnerShopOrderBean bean = adapter.getItem(position);
				holder.tvs[0].setText(bean.getNumber());
				holder.tvs[1].setText(bean.getCustom());
				holder.tvs[2].setText(bean.getFood());
				holder.tvs[3].setText(bean.getPay() + "");
			}

			@Override
			public void initLayout(View convertView, ViewHolder holder) {
				// TODO Auto-generated method stub
				convertView.setBackgroundColor(getResources().getColor(R.color.title));
				holder.tvs = new TextView[4];
				holder.tvs[0] = (TextView)convertView.findViewById(R.id.tv1);
				holder.tvs[1] = (TextView)convertView.findViewById(R.id.tv2);
				holder.tvs[2] = (TextView)convertView.findViewById(R.id.tv3);
				holder.tvs[3] = (TextView)convertView.findViewById(R.id.tv4);
				
			}

			@Override
			public boolean isSameObject(InnerShopOrderBean t1, InnerShopOrderBean t2) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		mGridView.setAdapter(mDataListAdapter);
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
				CloudManager.getInstance().requestShopOrderList(new CloudInterface() {
					
					@Override
					public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
						if (arg0 == CloudResponseStatus.Succ) {
							ShopOrderBean bean = (ShopOrderBean) arg1;
							if (bean.getOrderList() != null) {
								mDataListAdapter.setShowDataList(bean.getOrderList());
							}
							mTv3.setText("订单总额：" + bean.getPayTotal() + "");
						}
						mAbPullToRefreshView.onHeaderRefreshFinish();
					}
				}, mCurrentPage++);
			}
		});
		
		mTv1 = (TextView) view.findViewById(R.id.tv11);
		mTv2 = (TextView) view.findViewById(R.id.tv12);
		mTv3 = (TextView) view.findViewById(R.id.tv13);
		
		ShopBean ShopBean = CloudManager.getInstance().getShopBean();
		LogUtils.d("ll1 shopBean=" + ShopBean);
		if (ShopBean != null) {
			mTv1.setText("店名：" + ShopBean.getTitle());
			mTv2.setText(ShopBean.getInfo());
		}
		
		loadData();
		return view;
	}
}
