package com.onekey.takeaway;

import com.onekey.takeaway.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

public class FragmentDevices extends Fragment {
	GridView mGridView;

	public static FragmentDevices newInstance(String text) {
		FragmentDevices fragmentCommon = new FragmentDevices();
		Bundle bundle = new Bundle();
		bundle.putString("text", text);
		fragmentCommon.setArguments(bundle);
		return fragmentCommon;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_common, container, false);
		mGridView = (GridView) view.findViewById(R.id.gridView);
		return view;
	}
}
