package com.aohuan.detail.first.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.aohuan.utils.MyActivityManager;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
@ContentView(R.layout.activity_nurse_maid_payment_layout)
public class NurseMaidPaymentActivity extends BaseActiviry{
	@ViewInject(R.id.back)
	private ImageButton mBackButton;
	@ViewInject(R.id.title)
	private TextView mTextTitle;
	@ViewInject(R.id.rb_zhifubao)
	private RadioButton mRadioZfb;
	@ViewInject(R.id.rb_yinlian)
	private RadioButton mRadioYl;
	@ViewInject(R.id.btn_submit)
	private Button mBtnSubmit;
	
	/** 订单id */
	private String mPayId;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		initView();
	}
	private void initView(){
		mTextTitle.setText("支付定金");
		mPayId = getIntent().getStringExtra("pay_id");
	}
	
	@OnClick({R.id.back, R.id.btn_submit})
	public void onClick(View view){
		switch (view.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.btn_submit:
//			MyActivityManager.getInstance().exit();
//			finish();
			break;

		default:
			break;
		}
	}

}
