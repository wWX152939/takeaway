package com.onekey.takeaway;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onekey.takeaway.R;

public class FragmentCount extends Fragment {
	
	public static FragmentCount newInstance(String text) {
		FragmentCount fragmentCommon = new FragmentCount();
		Bundle bundle = new Bundle();
		bundle.putString("text", text);
		fragmentCommon.setArguments(bundle);
		return fragmentCommon;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_signal, container, false);
		return view;
	}
	
	
	
	
	
	
	
}
