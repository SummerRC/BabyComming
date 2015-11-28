package com.aohuan.detail.order;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.aohuan.bean.MyOrderAllBean;
import com.aohuan.bean.MyOrderAllBean.MyOrderAll;
import com.aohuan.bean.OrderDetailsBean;
import com.aohuan.bean.OrderDetailsBean.OrderDetails;
import com.aohuan.bean.OrderDetailsBean.Pinglun;
import com.aohuan.bean.UserBean.User;
import com.aohuan.detail.order.adapter.ConsumeDetailAdapter;
import com.aohuan.utils.adapter.ImageLoad;
import com.aohuan.utils.common.LogAh;
import com.aohuan.utils.http.EFaceTypeZGQ;
import com.aohuan.utils.http.GetDataAsyncZGQ;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.propriety.LoginHelper;
import com.aohuan.utils.request.RequestBaseMapZGQ;
import com.aohuan.utils.setlistview_height.SetListViewHeightUtil;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

@ContentView(R.layout.activity_order_detail_go_evaluate)
public class OrderDetailNoEvaluateActivity extends BaseActiviry {

	@ViewInject(R.id.back)
	private ImageButton mImgBtnBack;
	@ViewInject(R.id.title)
	private TextView mTvTitle;
	@ViewInject(R.id.btn_send)
	private Button mBtnCommit;
	@ViewInject(R.id.btn_refresh)
	private Button mBtnRefresh;
	@ViewInject(R.id.tv_order_hao)
	private TextView mTextHao;
	@ViewInject(R.id.tv_order_time)
	private TextView mTextTime;
	@ViewInject(R.id.tv_order_image)
	private ImageView mImage;
	@ViewInject(R.id.tv_order_title)
	private TextView mTextTitle;
	@ViewInject(R.id.tv_order_num)
	private TextView mTextNum;
	@ViewInject(R.id.tv_order_price)
	private TextView mTextPrice;
	@ViewInject(R.id.list)
	private ListView mLv;
	@ViewInject(R.id.ratingBar)
	private RatingBar mRatingBar;
	@ViewInject(R.id.order_tv_pingfen)
	private TextView mTextPingfen;
	@ViewInject(R.id.order_tv_pingjia_content)
	private TextView mTextPingjia;
	@ViewInject(R.id.ll01)
	private LinearLayout mLayout;
	/** 订单id */
	private String mPayId;
	/** 订单状态 */
	private String mPayType;
	/** 订单详情实体类 */
	private OrderDetailsBean mOrderDetailsBean;
	/** 用户信息实体类 */
	private User mUser;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setSth();
	}

	private void setSth() {
		mUser = LoginHelper.getUser(getApplicationContext());
		mTvTitle.setText("订单详情");
		mPayId = getIntent().getStringExtra("pay_id");
		mPayType = getIntent().getStringExtra("type");
		mBtnRefresh.setVisibility(View.VISIBLE);
		initData();

		if(mPayType.equals("已评价")){
			mBtnCommit.setVisibility(View.GONE);
		}else if(mPayType.equals("已付款")){
			mBtnCommit.setVisibility(View.GONE);
			mTextPingjia.setVisibility(View.GONE);
			mLayout.setVisibility(View.GONE);
		}else{
			mBtnCommit.setVisibility(View.VISIBLE);
			mTextPingjia.setVisibility(View.GONE);
			mLayout.setVisibility(View.GONE);
		}

	}

	@OnClick({ R.id.back, R.id.btn_refresh, R.id.btn_send})
	public void onRlClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.btn_refresh:
			initData();
			break;
		case R.id.btn_send:
			Intent intent = new Intent(this, OrderDetailEvaluateActivity.class);
			intent.putExtra("id", mOrderDetailsBean.getId());
			intent.putExtra("image", mOrderDetailsBean.getImage());
			intent.putExtra("title", mOrderDetailsBean.getTitle());
			intent.putExtra("num", mOrderDetailsBean.getQuantity());
			intent.putExtra("price", mOrderDetailsBean.getOrigin());
			startActivity(intent);
			this.finish();
			break;
		default:
			break;
		}
	}

	/**
	 * 订单详情网络请求
	 */
	private void initData(){
		IUpdateUI ui = new IUpdateUI() {

			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				if(allData == null){
					Toast.makeText(OrderDetailNoEvaluateActivity.this, "网络不给力", Toast.LENGTH_SHORT).show();
					return ;
				}
				LogAh.e("订单详情：："+allData.toString());
				mOrderDetailsBean = (OrderDetailsBean) allData;
				if(mOrderDetailsBean.getCode().equals("0")){
					mTextHao.setText(mOrderDetailsBean.getSnid());
					mTextTime.setText(mOrderDetailsBean.getCreate_time());
					ImageLoad.loadImage(mImage, mOrderDetailsBean.getImage(), getApplicationContext());
					mTextTitle.setText(mOrderDetailsBean.getTitle());
					mTextNum.setText(mOrderDetailsBean.getQuantity());
					mTextPrice.setText(mOrderDetailsBean.getOrigin());

					if(mOrderDetailsBean.getList() != null && !mOrderDetailsBean.getList().isEmpty()){
						mLv.setAdapter(new ConsumeDetailAdapter(OrderDetailNoEvaluateActivity.this, mOrderDetailsBean.getList()));
						SetListViewHeightUtil.setListViewHeightBasedOnChildren(mLv);
					}else{
						mLv.setAdapter(new ConsumeDetailAdapter(OrderDetailNoEvaluateActivity.this, new ArrayList<OrderDetails>()));
					}
					if(!TextUtils.isEmpty(mOrderDetailsBean.getPinglun().getScore())){
						mRatingBar.setRating(Float.parseFloat(mOrderDetailsBean.getPinglun().getScore()));
						mRatingBar.getRating();
						mTextPingfen.setText(mOrderDetailsBean.getPinglun().getScore());
					}
					if(!TextUtils.isEmpty(mOrderDetailsBean.getPinglun().getDetails())){
						mTextPingjia.setText(mOrderDetailsBean.getPinglun().getDetails());
					}
				}
			}
		};

		GetDataAsyncZGQ asyncZGQ = new GetDataAsyncZGQ(OrderDetailNoEvaluateActivity.this, ui, true,
				RequestBaseMapZGQ.getMyOrderDetails(mUser.getId(), mPayId));
		asyncZGQ.execute(EFaceTypeZGQ.URL_MY_ORDER_DETAILS);
	}
}
