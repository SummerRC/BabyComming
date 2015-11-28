package com.aohuan.detail.order;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

@ContentView(R.layout.activity_order_detail_no_pay)
public class OrderDetailNoPayActivity extends BaseActiviry {

	@ViewInject(R.id.back)
	private ImageButton mImgBtnBack;
	@ViewInject(R.id.title)
	private TextView mTvTitle;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		mTvTitle.setText("订单详情");
	}

	@OnClick({ R.id.back, })
	public void onRlClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		default:
			break;
		}
	}
}
