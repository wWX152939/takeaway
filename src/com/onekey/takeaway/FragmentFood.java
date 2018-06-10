package com.onekey.takeaway;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onekey.takeaway.R;

public class FragmentFood extends Fragment {
	
	public static FragmentFood newInstance(String text) {
		FragmentFood fragmentCommon = new FragmentFood();
		Bundle bundle = new Bundle();
		bundle.putString("text", text);
		fragmentCommon.setArguments(bundle);
		return fragmentCommon;
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_net, container, false);
		return view;
	}
	
}
