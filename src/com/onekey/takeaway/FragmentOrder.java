package com.onekey.takeaway;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.onekey.takeaway.bean.OrderBean;
import com.onekey.takeaway.ux.DataListAdapter;
import com.onekey.takeaway.ux.DataListAdapter.ViewHolder;

public class FragmentOrder extends Fragment {
	GridView mGridView;

	public static FragmentOrder newInstance(String text) {
		FragmentOrder fragmentCommon = new FragmentOrder();
		Bundle bundle = new Bundle();
		bundle.putString("text", text);
		fragmentCommon.setArguments(bundle);
		return fragmentCommon;
	}
	
	private DataListAdapter<OrderBean> mDataListAdapter = new DataListAdapter<OrderBean>(getActivity(), new DataListAdapter.ListAdapterInterface<OrderBean>() {

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
				DataListAdapter<OrderBean> adapter, int position) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void initLayout(View convertView, ViewHolder holder) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isSameObject(OrderBean t1, OrderBean t2) {
			// TODO Auto-generated method stub
			return false;
		}
	});

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_order, container, false);
		mGridView = (GridView) view.findViewById(R.id.gridView);
		
		mGridView.setAdapter(mDataListAdapter);
		return view;
	}
}
