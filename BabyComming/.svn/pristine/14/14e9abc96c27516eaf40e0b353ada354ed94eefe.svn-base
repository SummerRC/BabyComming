package com.aohuan.utils.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class GuideViewPagerAdapter extends FragmentPagerAdapter{
	private FragmentManager manager;
	private List<Fragment> list;
	private Context context;

	public GuideViewPagerAdapter(FragmentManager fm) {
		super(fm);
		this.manager = fm;
	}
	
	public void initData(Context context,List<Fragment> list){
		this.list = list;
		this.context = context;
	}

	@Override
	public Fragment getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public int getCount() {
		return list.size();
	}

}
