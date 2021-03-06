package com.aohuan.detail.mine;

import java.util.ArrayList;
import java.util.List;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.aohuan.utils.SelectPlayingPageAdapter;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_mine_had_yuesao)
public class AlreadyReserveNurseMaidActivity extends BaseActiviry implements OnPageChangeListener {

	@ViewInject(R.id.title)
	private TextView mTitleView;
	@ViewInject(R.id.back)
	private ImageButton mBackButton;

	@ViewInject(R.id.not_paid_text)
	private TextView mNotView;
	@ViewInject(R.id.paid_text)
	private TextView mPaidView;

	@ViewInject(R.id.cursor)
	private ImageView mCursor;
	@ViewInject(R.id.page)
	private ViewPager mViewPager;

	private int mTabNum = 2;
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	private int selectedColor;
	private int unSelectedColor;

	private List<Fragment> mFragments;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		mTitleView.setText("已预约的月嫂");

		

		mBackButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		selectedColor = getResources()
				.getColor(R.color.tab_title_pressed_color);
		unSelectedColor = getResources().getColor(
				R.color.tab_title_normal_color);
		initImageView();
		initTextView();
		initViewPager();
	}

	private void initTextView() {
		setSelectColor(0);
		mNotView.setOnClickListener(new MyOnClickListener(0));
		mPaidView.setOnClickListener(new MyOnClickListener(1));
	}

	private void initImageView() {
		bmpW = BitmapFactory.decodeResource(getResources(),
				R.drawable.bj_pageview).getWidth();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (screenW / mTabNum - bmpW) / 2; // （宽度/数量 - 宽度）/2
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		mCursor.setImageMatrix(matrix);// 设置动画初始位置
		// mCursor.setBackgroundResource(R.drawable.bj_pageview); // 图片
		// mCursorLayout.setPadding(offset, 0, 0, 0);
	}
	
	private SelectPlayingPageAdapter pageAdapter;
	private void initViewPager() {
		mFragments = new ArrayList<Fragment>();
		mFragments.add(new NotPaidFragment());
		mFragments.add(new PaidFragment());
		
		pageAdapter=new SelectPlayingPageAdapter(getSupportFragmentManager(), (ArrayList<Fragment>) mFragments);
		
		mViewPager.setAdapter(pageAdapter);
		
		mViewPager.setOnPageChangeListener(this);
	}
	/**
	 * 头标点击监听 设置 indicator 和 文字颜色
	 */
	private class MyOnClickListener implements OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			setSelectColor(index);
			mViewPager.setCurrentItem(index);
		}
	}

	/** 设置文字的颜色 */
	public void setSelectColor(int index) {
		mNotView.setTextColor(unSelectedColor);
		mPaidView.setTextColor(unSelectedColor);
		switch (index) {
		case 0:
			mNotView.setTextColor(selectedColor);
			break;
		case 1:
			mPaidView.setTextColor(selectedColor);
			break;
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int index) {
		// TODO Auto-generated method stub
		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
		
		Animation animation = new TranslateAnimation(one * currIndex, one
				* index, 0, 0);// 显然这个比较简洁，只有一行代码。
		currIndex = index;
		animation.setFillAfter(true);// True:图片停在动画结束位置
		animation.setDuration(300);
		mCursor.startAnimation(animation);
		setSelectColor(index);
	}

}
