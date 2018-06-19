package com.onekey.takeaway;

import mythware.http.CloudUpdateVersionServer.CloudInterface;
import mythware.http.CloudUpdateVersionServer.CloudResponseStatus;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.onekey.common.LogUtils;
import com.onekey.http.CloudManager;
import com.onekey.takeaway.bean.FoodBean;
import com.onekey.takeaway.bean.FoodBean.InnerFoodBean;
import com.onekey.takeaway.ux.DataListAdapter;
import com.onekey.takeaway.ux.DataListAdapter.ViewHolder;

public class FragmentFood extends Fragment {
	GridView mGridView;
	private DataListAdapter<InnerFoodBean> mDataListAdapter;

	public static FragmentFood newInstance(String text) {
		FragmentFood fragmentCommon = new FragmentFood();
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
//		List<InnerFoodBean> list = new ArrayList<InnerFoodBean>();
//		for (int i = 0; i < 10; i++) {
//			InnerFoodBean bean = new InnerFoodBean("张三", "大饭煲", "黄瓜", "西红柿蛋汤", 17, "running", 100, "黄焖鸡米饭", 18);
//			list.add(bean);
//		}
//		mDataListAdapter.setShowDataList(list);
		CloudManager.getInstance().requestFoodList(new CloudInterface() {
			
			@Override
			public void cloudCallback(CloudResponseStatus arg0, Object arg1) {
				if (arg0 == CloudResponseStatus.Succ) {
					FoodBean FoodBean = (com.onekey.takeaway.bean.FoodBean) arg1;
					LogUtils.d("ll1 size=" + FoodBean.getFoodlist().size());
					mDataListAdapter.setShowDataList(FoodBean.getFoodlist());
				}
			}
		});
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_order, container, false);
		mGridView = (GridView) view.findViewById(R.id.gridView);
		mDataListAdapter = new DataListAdapter<InnerFoodBean>(getActivity(), new DataListAdapter.ListAdapterInterface<InnerFoodBean>() {

			@Override
			public int getLayoutId() {
				return R.layout.item_fragment_food;
			}

			@Override
			public void regesterListeners(ViewHolder viewHolder, int position) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void initListViewItem(View convertView, ViewHolder holder,
					DataListAdapter<InnerFoodBean> adapter, int position) {
				// TODO Auto-generated method stub
				InnerFoodBean bean = adapter.getItem(position);
				
				holder.tvs[0].setText(bean.getName());
				holder.tvs[1].setText(bean.getPay());
				holder.tvs[2].setText(bean.getTotal() + "");
			}

			@Override
			public void initLayout(View convertView, ViewHolder holder) {
				// TODO Auto-generated method stub
				holder.tvs = new TextView[3];
				holder.tvs[0] = (TextView)convertView.findViewById(R.id.tv1);
				holder.tvs[1] = (TextView)convertView.findViewById(R.id.tv2);
				holder.tvs[2] = (TextView)convertView.findViewById(R.id.tv3);
				holder.ivs = new ImageView[1];
				holder.ivs[0] = (ImageView)convertView.findViewById(R.id.iv);
				
			}

			@Override
			public boolean isSameObject(InnerFoodBean t1, InnerFoodBean t2) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		mGridView.setAdapter(mDataListAdapter);
		
		initData();
		return view;
	}
}
