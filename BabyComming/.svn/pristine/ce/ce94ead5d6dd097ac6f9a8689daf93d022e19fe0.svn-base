package com.aohuan.utils;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SelectPlayingPageAdapter extends FragmentPagerAdapter {
	private ArrayList<Fragment> fragments;

	public SelectPlayingPageAdapter(FragmentManager fm,ArrayList<Fragment> fragments) {
		super(fm);
		this.fragments=fragments;
	}



	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return fragments.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragments.size();
	}

}
