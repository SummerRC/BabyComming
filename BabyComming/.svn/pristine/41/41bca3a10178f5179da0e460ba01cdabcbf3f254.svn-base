package com.aohuan.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aohuan.babycomming.MainActivity;
import com.aohuan.babycomming.R;
import com.aohuan.babycomming.ViewPagerActivity;
import com.aohuan.bean.CodeMessageBean;
import com.aohuan.bean.GuangGaoLunboBean;
import com.aohuan.bean.MyOrderAllBean;
import com.aohuan.bean.GuangGaoLunboBean.GuangGaoLunbo;
import com.aohuan.bean.MyOrderAllBean.MyOrderAll;
import com.aohuan.bean.UserBean.User;
import com.aohuan.detail.first.activity.PayOrderActivity;
import com.aohuan.detail.first.activity.SubmitOrderActivity;
import com.aohuan.detail.order.OrderDetailNoEvaluateActivity;
import com.aohuan.detail.order.adapter.OrderTypeAdapter;
import com.aohuan.utils.adapter.CommonAdapter;
import com.aohuan.utils.adapter.ViewHolder;
import com.aohuan.utils.common.LogAh;
import com.aohuan.utils.dialog.CallPhoneUtils;
import com.aohuan.utils.http.EFaceTypeZGQ;
import com.aohuan.utils.http.GetDataAsyncZGQ;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.propriety.LoginHelper;
import com.aohuan.utils.request.RequestBaseMapZGQ;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class OrderFragment extends Fragment {
	@ViewInject(R.id.tab1)
	private TextView mTv01All;
	@ViewInject(R.id.tab2)
	private TextView mTv02NoPay;
	@ViewInject(R.id.tab3)
	private TextView mTv03NoConsume;
	@ViewInject(R.id.tab4)
	private TextView mTv04NoEvaluate;
	// @ViewInject(R.id.layout)
	// private RelativeLayout mCursorLayout;
	@ViewInject(R.id.cursor)
	private ImageView mCursor;
	@ViewInject(R.id.dingdan_one_grid_list)
	private GridView mGridView;
	@ViewInject(R.id.no_data)
	private LinearLayout mNoDataLayout;

	private int mTabNum = 4;
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	private int selectedColor;
	private int unSelectedColor;
	/** 全部的实体类 */
	private MyOrderAllBean myOrderAllBean;
	/** 删除待付款订单的实体类 */
	private CodeMessageBean mCodeMessageBean;
	/** 全部的适配器 */
	private CommonAdapter<MyOrderAll> mAdapterAll;
	/** 待付款的适配器 */
	private CommonAdapter<MyOrderAll> mAdapterNoFukuan;
	/** 未消费的适配器 */
	private CommonAdapter<MyOrderAll> mAdapterNoXiaofei;
	/** 待评价的适配器 */
	private CommonAdapter<MyOrderAll> mAdapterNoPingjia;
	/** 用户信息实体类 */
	private User mUser;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = null;
		view = inflater.inflate(R.layout.fragment_order, container, false);
		ViewUtils.inject(this, view);
		initView();
		return view;
	}

	private void initView() {
		mUser = LoginHelper.getUser(getActivity());
		selectedColor = getResources()
				.getColor(R.color.tab_title_pressed_color);
		unSelectedColor = getResources().getColor(
				R.color.tab_title_normal_color);
		initImageView();
		initTextView();
	}

	private void initTextView() {
		setSelectColor(0);
		mTv01All.setOnClickListener(new MyOnClickListener(0));
		mTv02NoPay.setOnClickListener(new MyOnClickListener(1));
		mTv03NoConsume.setOnClickListener(new MyOnClickListener(2));
		mTv04NoEvaluate.setOnClickListener(new MyOnClickListener(3));
	}
	/**
	 * 设置下标图片的移动的距离
	 */
	private void initImageView() {
		bmpW = BitmapFactory.decodeResource(getResources(),
				R.drawable.bj_pageview).getWidth();
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (screenW / mTabNum - bmpW) / 2; // （宽度/数量 - 宽度）/2
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		mCursor.setImageMatrix(matrix);// 设置动画初始位置
		// mCursor.setBackgroundResource(R.drawable.bj_pageview); // 图片
		// mCursorLayout.setPadding(offset, 0, 0, 0);
	}

	/**
	 * 头标点击监听 设置 indicator 和 文字颜色
	 */
	private class MyOnClickListener implements OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		public void onClick(View v) {
			setSelectColor(index);
			onImage(index);
		}
	}


	/** 设置文字的颜色 */
	public void setSelectColor(int index) {
		mTv01All.setTextColor(unSelectedColor);
		mTv02NoPay.setTextColor(unSelectedColor);
		mTv03NoConsume.setTextColor(unSelectedColor);
		mTv04NoEvaluate.setTextColor(unSelectedColor);
		switch (index) {
		case 0:
			initData(0);
			mTv01All.setTextColor(selectedColor);
			break;
		case 1:
			initData(1);
			mTv02NoPay.setTextColor(selectedColor);
			break;
		case 2:
			initData(2);
			mTv03NoConsume.setTextColor(selectedColor);
			break;
		case 3:
			initData(3);
			mTv04NoEvaluate.setTextColor(selectedColor);
			break;
		default:
			break;
		}
	}
	/**
	 * 下标图片的移动
	 * @param index
	 */
	public void onImage(int index) {
		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量

		Animation animation = new TranslateAnimation(one * currIndex, one
				* index, 0, 0);// 显然这个比较简洁，只有一行代码。
		currIndex = index;
		animation.setFillAfter(true);// True:图片停在动画结束位置
		animation.setDuration(300);
		mCursor.startAnimation(animation);
		setSelectColor(index);
	}

	private void initData(final int index){
		IUpdateUI ui = new IUpdateUI() {

			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				if(allData == null){
					Toast.makeText(getActivity(), "网络不给力", Toast.LENGTH_SHORT).show();
					return ;
				}
				LogAh.e(allData.toString());
				myOrderAllBean = (MyOrderAllBean) allData;
				if(myOrderAllBean.getCode().equals("0")){
					if(myOrderAllBean.getList() != null && !myOrderAllBean.getList().isEmpty()){
						if(index == 0){
							initAllData(myOrderAllBean.getList());
							mNoDataLayout.setVisibility(View.GONE);
						}else if(index == 1){
							initNoFukuanData(myOrderAllBean.getList());
							mNoDataLayout.setVisibility(View.GONE);
						}else if(index == 2){
							initNoXiaofeiData(myOrderAllBean.getList());
							mNoDataLayout.setVisibility(View.GONE);
						}else if(index == 3){
							initNoPingjiaData(myOrderAllBean.getList());
							mNoDataLayout.setVisibility(View.GONE);
						}
					}else{
						if(index == 0){
							initAllData(new ArrayList<MyOrderAll>());
							mNoDataLayout.setVisibility(View.VISIBLE);
						}else if(index == 1){
							initNoFukuanData(new ArrayList<MyOrderAll>());
							mNoDataLayout.setVisibility(View.VISIBLE);
						}else if(index == 2){
							initNoXiaofeiData(new ArrayList<MyOrderAll>());
							mNoDataLayout.setVisibility(View.VISIBLE);
						}else if(index == 3){
							initNoPingjiaData(new ArrayList<MyOrderAll>());
							mNoDataLayout.setVisibility(View.VISIBLE);
						}
					}
				}
			}
		};
		if(index == 0){
			GetDataAsyncZGQ asyncZGQ = new GetDataAsyncZGQ(getActivity(), ui, true,
					RequestBaseMapZGQ.getMyOrder(mUser.getId()));
			asyncZGQ.execute(EFaceTypeZGQ.URL_MY_ORDER_ALL);
		}else if(index == 1){
			GetDataAsyncZGQ asyncZGQ = new GetDataAsyncZGQ(getActivity(), ui, true,
					RequestBaseMapZGQ.getMyOrder(mUser.getId()));
			asyncZGQ.execute(EFaceTypeZGQ.URL_MY_ORDER_NO_FUKUAN);
		}else if(index == 2){
			GetDataAsyncZGQ asyncZGQ = new GetDataAsyncZGQ(getActivity(), ui, true,
					RequestBaseMapZGQ.getMyOrder(mUser.getId()));
			asyncZGQ.execute(EFaceTypeZGQ.URL_MY_ORDER_NO_XIAOFEI);
		}else if(index == 3){
			GetDataAsyncZGQ asyncZGQ = new GetDataAsyncZGQ(getActivity(), ui, true,
					RequestBaseMapZGQ.getMyOrder(mUser.getId()));
			asyncZGQ.execute(EFaceTypeZGQ.URL_MY_ORDER_NO_PINGJIA);
		}
	}

	/**
	 * 全部的数据
	 */
	private void initAllData(final List<MyOrderAll> list){
		mAdapterAll = new CommonAdapter<MyOrderAll>(getActivity(),list,
				R.layout.item_order_1_no_pay) {

			@Override
			public void convert(final ViewHolder helper, final MyOrderAll item) {
				// TODO Auto-generated method stub
				helper.setImageByUrl(R.id.photo_goods_image, item.getImage(), getActivity());
				helper.setText(R.id.photo_goods_title, item.getTitle());
				helper.setText(R.id.photo_goods_num, item.getQuantity());
				helper.setText(R.id.photo_goods_price, item.getOrigin());
				helper.setText(R.id.photo_goods_type, item.getType());
				if(item.getType().equals("待付款")){
					helper.getView(R.id.photo_goods_fukuan).setVisibility(View.VISIBLE);
				}else{
					helper.getView(R.id.photo_goods_fukuan).setVisibility(View.GONE);
				}
				helper.getView(R.id.photo_goods_fukuan).setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getActivity(), PayOrderActivity.class);
						double price = Double.parseDouble(item.getOrigin());
						int num = Integer.parseInt(item.getQuantity());
						double singlePrice = price/num;
						intent.putExtra("single", String.valueOf(singlePrice));
						intent.putExtra("title", item.getTitle());
						intent.putExtra("num", Integer.parseInt(item.getQuantity()));
						intent.putExtra("total", item.getOrigin());
						startActivity(intent);
					}
				});
			}
		};
		mGridView.setAdapter(mAdapterAll);
		onItemGridView(list);
		onLongItemNoGridView(list, mGridView, false);

	}
	/**
	 * 待付款的数据
	 */
	private void initNoFukuanData(List<MyOrderAll> list){
		mAdapterNoFukuan = new CommonAdapter<MyOrderAll>(getActivity(),list,
				R.layout.item_order_1_no_pay) {

			@Override
			public void convert(final ViewHolder helper, final MyOrderAll item) {
				// TODO Auto-generated method stub
				helper.setImageByUrl(R.id.photo_goods_image, item.getImage(), getActivity());
				helper.setText(R.id.photo_goods_title, item.getTitle());
				helper.setText(R.id.photo_goods_num, item.getQuantity());
				helper.setText(R.id.photo_goods_price, item.getOrigin());
				helper.setText(R.id.photo_goods_type, item.getType());
				if(item.getType().equals("待付款")){
					helper.getView(R.id.photo_goods_fukuan).setVisibility(View.VISIBLE);
				}else{
					helper.getView(R.id.photo_goods_fukuan).setVisibility(View.GONE);
				}
				helper.getView(R.id.photo_goods_fukuan).setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getActivity(), PayOrderActivity.class);
						double price = Double.parseDouble(item.getOrigin());
						int num = Integer.parseInt(item.getQuantity());
						double singlePrice = price/num;
						intent.putExtra("single", String.valueOf(singlePrice));
						intent.putExtra("title", item.getTitle());
						intent.putExtra("num", Integer.parseInt(item.getQuantity()));
						intent.putExtra("total", item.getOrigin());
						startActivity(intent);
					}
				});
			}
		};
		mGridView.setAdapter(mAdapterNoFukuan);
		onItemNoGridView(list);
		onLongItemNoGridView(list, mGridView, true);
	}
	/**
	 * 已付款的数据
	 */
	private void initNoXiaofeiData(List<MyOrderAll> list){
		mAdapterNoXiaofei = new CommonAdapter<MyOrderAll>(getActivity(),list,
				R.layout.item_order_1_no_pay) {

			@Override
			public void convert(final ViewHolder helper, final MyOrderAll item) {
				// TODO Auto-generated method stub
				helper.setImageByUrl(R.id.photo_goods_image, item.getImage(), getActivity());
				helper.setText(R.id.photo_goods_title, item.getTitle());
				helper.setText(R.id.photo_goods_num, item.getQuantity());
				helper.setText(R.id.photo_goods_price, item.getOrigin());
				helper.setText(R.id.photo_goods_type, item.getType());
				if(item.getType().equals("待付款")){
					helper.getView(R.id.photo_goods_fukuan).setVisibility(View.VISIBLE);
				}else{
					helper.getView(R.id.photo_goods_fukuan).setVisibility(View.GONE);
				}
				helper.getView(R.id.photo_goods_fukuan).setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getActivity(), PayOrderActivity.class);
						double price = Double.parseDouble(item.getOrigin());
						int num = Integer.parseInt(item.getQuantity());
						double singlePrice = price/num;
						intent.putExtra("single", String.valueOf(singlePrice));
						intent.putExtra("title", item.getTitle());
						intent.putExtra("num", Integer.parseInt(item.getQuantity()));
						intent.putExtra("total", item.getOrigin());
						startActivity(intent);
					}
				});
			}
		};
		mGridView.setAdapter(mAdapterNoXiaofei);
		onItemGridView(list);
		onLongItemNoGridView(list, mGridView, false);
	}
	/**
	 * 待评价的数据
	 */
	private void initNoPingjiaData(List<MyOrderAll> list){
		mAdapterNoPingjia = new CommonAdapter<MyOrderAll>(getActivity(),list,
				R.layout.item_order_1_no_pay) {

			@Override
			public void convert(final ViewHolder helper, final MyOrderAll item) {
				// TODO Auto-generated method stub
				helper.setImageByUrl(R.id.photo_goods_image, item.getImage(), getActivity());
				helper.setText(R.id.photo_goods_title, item.getTitle());
				helper.setText(R.id.photo_goods_num, item.getQuantity());
				helper.setText(R.id.photo_goods_price, item.getOrigin());
				helper.setText(R.id.photo_goods_type, item.getType());
				if(item.getType().equals("待付款")){
					helper.getView(R.id.photo_goods_fukuan).setVisibility(View.VISIBLE);
				}else{
					helper.getView(R.id.photo_goods_fukuan).setVisibility(View.GONE);
				}
				helper.getView(R.id.photo_goods_fukuan).setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getActivity(), PayOrderActivity.class);
						double price = Double.parseDouble(item.getOrigin());
						int num = Integer.parseInt(item.getQuantity());
						double singlePrice = price/num;
						intent.putExtra("single", String.valueOf(singlePrice));
						intent.putExtra("title", item.getTitle());
						intent.putExtra("num", Integer.parseInt(item.getQuantity()));
						intent.putExtra("total", item.getOrigin());
						startActivity(intent);
					}
				});
			}
		};
		mGridView.setAdapter(mAdapterNoPingjia);
		onItemGridView(list);
		onLongItemNoGridView(list, mGridView, false);
	}

	private void onItemGridView(final List<MyOrderAll> list){
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(list.get(arg2).getType().equals("待付款")){
					Intent intent = new Intent(getActivity(), PayOrderActivity.class);
					double price = Double.parseDouble(list.get(arg2).getOrigin());
					int num = Integer.parseInt(list.get(arg2).getQuantity());
					double singlePrice = price/num;
					intent.putExtra("single", String.valueOf(singlePrice));
					intent.putExtra("title", list.get(arg2).getTitle());
					intent.putExtra("num", Integer.parseInt(list.get(arg2).getQuantity()));
					intent.putExtra("total", list.get(arg2).getOrigin());
					startActivity(intent);
				}else{
					Intent intent = new Intent(getActivity(),OrderDetailNoEvaluateActivity.class);
					intent.putExtra("pay_id", list.get(arg2).getId());
					intent.putExtra("type", list.get(arg2).getType());
					startActivity(intent);
				}

			}
		});
	}
	/**
	 * 待付款点击事件
	 * @param list
	 */
	private void onItemNoGridView(final List<MyOrderAll> list){
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), PayOrderActivity.class);
				double price = Double.parseDouble(list.get(arg2).getOrigin());
				int num = Integer.parseInt(list.get(arg2).getQuantity());
				double singlePrice = price/num;
				intent.putExtra("single", String.valueOf(singlePrice));
				intent.putExtra("title", list.get(arg2).getTitle());
				intent.putExtra("num", Integer.parseInt(list.get(arg2).getQuantity()));
				intent.putExtra("total", list.get(arg2).getOrigin());
				intent.putExtra("summary", "");
				intent.putExtra("pay_id", list.get(arg2).getPay_id());
				startActivity(intent);

			}
		});
	}
	/**
	 * 待付款长按点击事件
	 * @param list
	 */
	private void onLongItemNoGridView(final List<MyOrderAll> list, GridView mGridView, final boolean boo){
		mGridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if(boo || list.get(arg2).getType().equals("待付款")){
//					Toast.makeText(getActivity(), list.get(arg2).getTitle(), Toast.LENGTH_SHORT).show();
					showDeleteOrder(list.get(arg2).getId(), boo);
					
				}else{

				}
				return true;
			}
		});
	}
	/**
	 * 待付款订单删除
	 */
	private void deleteNoFukuan(String order_id, final boolean boo){
		

		IUpdateUI ui = new IUpdateUI() {

			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				if(allData == null){
					Toast.makeText(getActivity(), "网络不给力", Toast.LENGTH_SHORT).show();
					return ;
				}
				LogAh.e("待付款订单删除：："+allData.toString());
				mCodeMessageBean = (CodeMessageBean) allData;
				if(mCodeMessageBean.getCode().equals("0")){
					Toast.makeText(getActivity(), 
							mCodeMessageBean.getMessage(), Toast.LENGTH_SHORT).show();
					if(boo){
						initData(1);
					}else{
						initData(0);
					}
				}else{
					Toast.makeText(getActivity(), 
							mCodeMessageBean.getMessage(), Toast.LENGTH_SHORT).show();
				}
			}
		};
		GetDataAsyncZGQ asyncZGQ = new GetDataAsyncZGQ(getActivity(), ui, true,
				RequestBaseMapZGQ.getDeleteMyOrder(mUser.getId(), order_id));
		asyncZGQ.execute(EFaceTypeZGQ.URL_DELETE_MY_ORDER);
	}
	/**
	 * 删除的Dialog
	 */
	public void showDeleteOrder(final String order_id, final boolean boo){
		final Dialog dialog = new Dialog(getActivity());
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_delete_order_layout, null);
		Button cencal = (Button) view.findViewById(R.id.btn_cencal);
		Button sure = (Button) view.findViewById(R.id.btn_sure);
		TextView tv = (TextView) view.findViewById(R.id.tv_delete);
		tv.setText("确认删除订单吗？");
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(view);
		dialog.setCanceledOnTouchOutside(false);//点击空白不会消失
		dialog.show();
		cencal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				
			}
		});
		sure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				deleteNoFukuan(order_id, boo);
				dialog.dismiss();
				
			}
		});
		CallPhoneUtils.dialogOperation(dialog , getActivity());
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.e("TAG", "onStart（）");
		mUser = LoginHelper.getUser(getActivity());
		initData(0);
		onImage(0);
	}

}
