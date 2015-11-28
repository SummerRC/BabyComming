package com.aohuan.detail.order;

import java.util.ArrayList;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RatingBar.OnRatingBarChangeListener;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.aohuan.bean.CodeMessageBean;
import com.aohuan.bean.OrderDetailsBean;
import com.aohuan.bean.OrderDetailsBean.OrderDetails;
import com.aohuan.bean.UserBean.User;
import com.aohuan.detail.first.activity.NurseMaidEvaluateActivity;
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

//activity_order_no_evaluate
@ContentView(R.layout.activity_order_evaluate)
public class OrderDetailEvaluateActivity extends BaseActiviry {

	@ViewInject(R.id.back)
	private ImageButton mImgBtnBack;
	@ViewInject(R.id.title)
	private TextView mTvTitle;
	@ViewInject(R.id.order_iv)
	private ImageView mImage;
	@ViewInject(R.id.order_tv_name)
	private TextView mTextName;
	@ViewInject(R.id.order_tv_num)
	private TextView mTextNum;
	@ViewInject(R.id.order_tv_price)
	private TextView mTextPrice;
	@ViewInject(R.id.ratingBar)
	private RatingBar mRatingBar;
	@ViewInject(R.id.order_tv_pingfen)
	private TextView mTextPingfen;
	@ViewInject(R.id.order_et_content)
	private EditText mEditText;
	@ViewInject(R.id.btn_send)
	private Button mBtnSend;


	/** 月嫂id */
	private String mId;
	/** 月嫂图片 */
	private String mImageStr;
	/** 月嫂名字 */
	private String mName;
	/** 数量 */
	private String mNum;
	/** 总价 */
	private String mPrice;
	
	/** 评价实体类 */
	private CodeMessageBean mCodeMessageBean;
	/** 用户信息实体类 */
	private User mUser;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		mUser = LoginHelper.getUser(getApplicationContext());
		initView();
	}
	private void initView(){
		mTvTitle.setText("评价");
		mId = getIntent().getStringExtra("id");
		mImageStr = getIntent().getStringExtra("image");
		mName = getIntent().getStringExtra("title");
		mNum = getIntent().getStringExtra("num");
		mPrice = getIntent().getStringExtra("price");

		ImageLoad.loadImage(mImage, mImageStr, getApplicationContext());
		
		mTextName.setText(mName);
		mTextNum.setText(mNum);
		mTextPrice.setText(mPrice);
		mRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				// TODO Auto-generated method stub
				mTextPingfen.setText(String.valueOf(rating));
			}
		});
	}

	@OnClick({ R.id.back, R.id.btn_send})
	public void onRlClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.btn_send:
			if(mTextPingfen.getText().toString().equals("0")){
				Toast.makeText(OrderDetailEvaluateActivity.this, 
						"满意度不能为0", Toast.LENGTH_SHORT).show();
				return ;
			}
			if(TextUtils.isEmpty(mEditText.getText().toString())){
				Toast.makeText(OrderDetailEvaluateActivity.this, 
						"评价内容不能为空", Toast.LENGTH_SHORT).show();
				return ;
			}
			initData(mEditText.getText().toString(), 
					mTextPingfen.getText().toString(), mId);
			break;
		default:
			break;
		}
	}

	/**
	 * 订单评价网络请求
	 */
	private void initData(String details, String score, String order_id){
		IUpdateUI ui = new IUpdateUI() {

			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				if(allData == null){
					Toast.makeText(OrderDetailEvaluateActivity.this, "网络不给力", Toast.LENGTH_SHORT).show();
					return ;
				}
				LogAh.e("评价：："+allData.toString());
				mCodeMessageBean = (CodeMessageBean) allData;
				if(mCodeMessageBean.getCode().equals("0")){
					Toast.makeText(OrderDetailEvaluateActivity.this, 
							mCodeMessageBean.getMessage(), Toast.LENGTH_SHORT).show();
					finish();
				}else if(mCodeMessageBean.getCode().equals("1")){
					Toast.makeText(OrderDetailEvaluateActivity.this, 
							mCodeMessageBean.getMessage(), Toast.LENGTH_SHORT).show();
				}
			}
		};

		GetDataAsyncZGQ asyncZGQ = new GetDataAsyncZGQ(OrderDetailEvaluateActivity.this, ui, true,
				RequestBaseMapZGQ.getMyOrderPingjia(mUser.getId(), details, score, order_id));
		asyncZGQ.execute(EFaceTypeZGQ.URL_MY_ORDER_PINGJIA);
	}
}
