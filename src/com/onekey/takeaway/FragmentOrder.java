package com.onekey.takeaway;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.onekey.takeaway.bean.OrderBean.InnerOrderBean;
import com.onekey.takeaway.ux.DataListAdapter;
import com.onekey.takeaway.ux.DataListAdapter.ViewHolder;

public class FragmentOrder extends Fragment {
	GridView mGridView;
	private DataListAdapter<InnerOrderBean> mDataListAdapter;

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
			InnerOrderBean bean = new InnerOrderBean("张三", "大饭煲", "黄瓜", "西红柿蛋汤", 17, "running", 100, "黄焖鸡米饭", 18);
			list.add(bean);
		}
		mDataListAdapter.setShowDataList(list);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_order, container, false);
		mGridView = (GridView) view.findViewById(R.id.gridView);
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
				holder.tvs[0].setText(bean.getCustom());
				holder.tvs[1].setText(bean.getFood());
				holder.tvs[2].setText(bean.getPeicangui() + "");
				holder.tvs[3].setText(bean.getChaofanji());
				holder.tvs[4].setText(bean.getXiaocai());
				holder.tvs[5].setText(bean.getTang());
				holder.tvs[6].setText(bean.getWaitTime() + "");
				holder.tvs[7].setText(bean.getPay() + "");
				holder.tvs[8].setText(bean.getStatus());
			}

			@Override
			public void initLayout(View convertView, ViewHolder holder) {
				// TODO Auto-generated method stub
				holder.tvs = new TextView[9];
				holder.tvs[0] = (TextView)convertView.findViewById(R.id.tv1);
				holder.tvs[1] = (TextView)convertView.findViewById(R.id.tv2);
				holder.tvs[2] = (TextView)convertView.findViewById(R.id.tv3);
				holder.tvs[3] = (TextView)convertView.findViewById(R.id.tv4);
				holder.tvs[4] = (TextView)convertView.findViewById(R.id.tv5);
				holder.tvs[5] = (TextView)convertView.findViewById(R.id.tv6);
				holder.tvs[6] = (TextView)convertView.findViewById(R.id.tv7);
				holder.tvs[7] = (TextView)convertView.findViewById(R.id.tv8);
				holder.tvs[8] = (TextView)convertView.findViewById(R.id.tv9);
				
			}

			@Override
			public boolean isSameObject(InnerOrderBean t1, InnerOrderBean t2) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		mGridView.setAdapter(mDataListAdapter);
		
		initData();
		return view;
	}
}
