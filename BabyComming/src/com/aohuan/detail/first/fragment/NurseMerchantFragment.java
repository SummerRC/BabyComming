package com.aohuan.detail.first.fragment;

import com.aohuan.babycomming.R;
import com.lidroid.xutils.ViewUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NurseMerchantFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view;
		view = inflater.inflate(R.layout.fragment_photo_store, container, false);
		ViewUtils.inject(this, view);
		return view;
	}
}
