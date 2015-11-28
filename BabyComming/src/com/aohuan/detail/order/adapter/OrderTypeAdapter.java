package com.aohuan.detail.order.adapter;

import java.util.ArrayList;
import java.util.List;

import com.aohuan.babycomming.R;
import com.aohuan.utils.adapter.CommonAdapter;
import com.aohuan.utils.adapter.ViewHolder;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
/**
 *	对应的order的adapter
 *	有 待付款， 已付款， 已消费， 已评价
 *	1【全部】		用 OrderPageAdapter， 适配4种状态
 *	2【待付款】	待付款
 *	3【已付款】	用 OrderPageAdapter， 		已付款，已消费，已评价
 *	4【待评价】	已消费
 */
public class OrderTypeAdapter extends PagerAdapter {

	ArrayList<String> mStringList;
	List<View> mViews;
	Context context;
	GridView mGridView01;
	GridView mGridView02;
	GridView mGridView03;
	GridView mGridView04;
	TextView mTv01;
	TextView mTv02;
	TextView mTv03;
	TextView mTv04;
	//dingdan_tv
	ArrayList<String> mStrList01 = new ArrayList<String>();
	ArrayList<String> mStrList02 = new ArrayList<String>();
	ArrayList<String> mStrList03 = new ArrayList<String>();
	ArrayList<String> mStrList04 = new ArrayList<String>();
	OrderPageAdapter mCommonAdapter01;
//	CommonAdapter<String> mCommonAdapter01;
	CommonAdapter<String> mCommonAdapter02;
	CommonAdapter<String> mCommonAdapter03;
	CommonAdapter<String> mCommonAdapter04;
	

	public OrderTypeAdapter(List<View> mViews, ArrayList<String> strList,
			Context context, String userid) {
		mStringList = strList;
		this.mViews = mViews;
		this.context = context;
		getView();
		fakeData();
		setAdapter();
	}

	private void setAdapter() {
		setTextViewVis();
		if(mStringList.size()==0){
			mCommonAdapter01 = new OrderPageAdapter(context, 25);
		}else{
			mCommonAdapter01 = new OrderPageAdapter(context, 5);
		}
//		mCommonAdapter01 = new CommonAdapter<String>(context, mStrList01, R.layout.item_order_1_no_pay) {
//			@Override
//			public void convert(ViewHolder helper, String item) {
//				helper.setText(R.id.photo_goods_title, item);
//			}
//		};
		mCommonAdapter02 = new CommonAdapter<String>(context, mStrList02, R.layout.item_order_2_aready_pay) {
			@Override
			public void convert(ViewHolder helper, String item) {
				helper.setText(R.id.photo_goods_title, item);
			}
		};
		mCommonAdapter03 = new CommonAdapter<String>(context, mStrList03, R.layout.item_order_3_aready_consume) {
			@Override
			public void convert(ViewHolder helper, String item) {
				helper.setText(R.id.photo_goods_title, item);
			}
		};
		mCommonAdapter04 = new CommonAdapter<String>(context, mStrList04, R.layout.item_order_4_aready_evaluate) {
			@Override
			public void convert(ViewHolder helper, String item) {
				helper.setText(R.id.photo_goods_title, item);
			}
		};
		mGridView01.setAdapter(mCommonAdapter01);
		mGridView02.setAdapter(mCommonAdapter02);
		mGridView03.setAdapter(mCommonAdapter03);
		mGridView04.setAdapter(mCommonAdapter04);
	}

	private void setTextViewVis() {
		setVis(mStrList01,mTv01);
		setVis(mStrList02,mTv02);
		setVis(mStrList03,mTv03);
		setVis(mStrList04,mTv04);
	}

	private void setVis(ArrayList<String> mStrList, TextView mTv) {
		if(mStrList!=null && mStrList.size()>0){
			mTv.setVisibility(View.GONE);
		}else{
			mTv.setVisibility(View.VISIBLE);
		}
	}

	private void fakeData() {
		//xxx
//		fakeDataIndivdate(mStrList01, 25);
		fakeDataIndivdate(mStrList02, 5);
		fakeDataIndivdate(mStrList03, 11);
		fakeDataIndivdate(mStrList04, 2);
		
	}
	private void fakeDataIndivdate(ArrayList<String> mStrList, int num){
		for(int i=0;i<num;i++){
			mStrList.add("fakedata"+i);
		}
	}

	private void getView() {
		mGridView01 = (GridView) mViews.get(0).findViewById(R.id.dingdan_one_list);
		mGridView02 = (GridView) mViews.get(1).findViewById(R.id.dingdan_two_list);
		mGridView03 = (GridView) mViews.get(2).findViewById(R.id.dingdan_three_list);
		mGridView04 = (GridView) mViews.get(3).findViewById(R.id.dingdan_four_list);
		mTv01 = (TextView) mViews.get(0).findViewById(R.id.dingdan_tv);
		mTv02 = (TextView) mViews.get(1).findViewById(R.id.dingdan_tv);
		mTv03 = (TextView) mViews.get(2).findViewById(R.id.dingdan_tv);
		mTv04 = (TextView) mViews.get(3).findViewById(R.id.dingdan_tv);
	}

//	public void refleshData(ArrayList<String> strList) {
//		fakeDataIndivdate(mStrList01, 2);
//		fakeDataIndivdate(mStrList02, 15);
//		fakeDataIndivdate(mStrList03, 11);
//		fakeDataIndivdate(mStrList04, 5);
//		mCommonAdapter01.refleshNum(25);
////		mCommonAdapter01.notifyDataSetChanged();
//		mCommonAdapter02.notifyDataSetChanged();
//		mCommonAdapter03.notifyDataSetChanged();
//		mCommonAdapter04.notifyDataSetChanged();
//	}

	@Override
	public int getCount() {
		return mViews.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (arg1);
	}
	
	@Override
	public Object instantiateItem(View arg0, int arg1) {
		((ViewPager) arg0).addView(mViews.get(arg1), 0);
		return mViews.get(arg1);
	}
	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView(mViews.get(arg1));
	}
}
