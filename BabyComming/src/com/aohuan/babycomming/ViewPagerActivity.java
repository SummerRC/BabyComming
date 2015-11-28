package com.aohuan.babycomming;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

public class ViewPagerActivity extends Activity{
	private ViewPager mPager;
	private List<View> mViewList;
	
	private View view1;
	private View view2;
	private View view3;
	private Button mBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_viewpager_layout);
		mPager = (ViewPager) findViewById(R.id.viewpager);
		
		LayoutInflater lf = getLayoutInflater().from(this);
		view1 = lf.inflate(R.layout.viewpager_layout1, null);
		view2 = lf.inflate(R.layout.viewpager_layout2, null);
		
		view3 = lf.inflate(R.layout.viewpager_layout3, null);
		mBtn = (Button) view3.findViewById(R.id.viewpager_btn);
		mBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ViewPagerActivity.this, MainActivity.class));
				finish();
			}
		});

		mViewList = new ArrayList<View>();// 将要分页显示的View装入数组中
		mViewList.add(view1);
		mViewList.add(view2);
		mViewList.add(view3);
		mPager.setAdapter(new MyViewPagerAdapter(mViewList));
	}
	
	public class MyViewPagerAdapter extends PagerAdapter{
		private List<View> mListViews;
		
		public MyViewPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;//构造方法，参数是我们的页卡，这样比较方便。
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) 	{	
			container.removeView(mListViews.get(position));//删除页卡
		}


		@Override
		public Object instantiateItem(ViewGroup container, int position) {	//这个方法用来实例化页卡		
			 container.addView(mListViews.get(position), 0);//添加页卡
			 return mListViews.get(position);
		}

		@Override
		public int getCount() {			
			return  mListViews.size();//返回页卡的数量
		}
		
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {			
			return arg0==arg1;//官方提示这样写
		}
	}
	

}
