package com.aohuan.detail.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.aohuan.bean.CodeMessageBean;
import com.aohuan.bean.NurseYuYueBean;
import com.aohuan.bean.UserBean.User;
import com.aohuan.utils.MyActivityManager;
import com.aohuan.utils.common.LogAh;
import com.aohuan.utils.http.EFaceTypeZGQ;
import com.aohuan.utils.http.GetDataAsyncZGQ;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.propriety.LoginHelper;
import com.aohuan.utils.request.RequestBaseMapZGQ;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
@ContentView(R.layout.activity_nurse_maid_reserver_layout)
public class NurseMaidReserveActivity extends BaseActiviry{
	@ViewInject(R.id.back)
	private ImageButton mBackButton;
	@ViewInject(R.id.title)
	private TextView mTextTitle;
	@ViewInject(R.id.btn_next)
	private Button mBtnNext;
	@ViewInject(R.id.tv_name)
	private TextView mTextName;
	@ViewInject(R.id.tv_dengji)
	private TextView mTextDengji;
	@ViewInject(R.id.tv_danwei)
	private TextView mTextDanwei;
	@ViewInject(R.id.tv_dingjin)
	private TextView mTextDingjin;
	@ViewInject(R.id.et_name)
	private EditText mEditName;
	@ViewInject(R.id.et_phone_number)
	private EditText mEditPhoneNumber;
	@ViewInject(R.id.et_address)
	private EditText mEditAddress;

	/** 跳转 */
	private Intent mIntent;

	/** 月嫂id */
	private String mYid;
	/** 月嫂名字 */
	private String mYname;
	/** 月嫂等级 */
	private String mYdengji;
	/** 月嫂单位 */
	private String mYdanwei;
	/** 预付定金 */
	private String mYdingjin;
	/** 预约月嫂实体类 */
	private NurseYuYueBean mNurseYuYueBean;
	/** 用户信息实体类 */
	private User mUser;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
//		MyActivityManager.getInstance().addActivity(NurseMaidReserveActivity.this);
		initData();
		initView();
	}
	private void initData(){
		mUser = LoginHelper.getUser(getApplicationContext());
		mYid = getIntent().getStringExtra("id");
		mYname = getIntent().getStringExtra("name");
		mYdengji = getIntent().getStringExtra("dengji");
		mYdanwei = getIntent().getStringExtra("gongsi");
		mYdingjin = getIntent().getStringExtra("price");
	}

	private void initView(){
		mTextTitle.setText("预约月嫂");
		mTextName.setText(mYname);
		mTextDengji.setText(mYdengji);
		mTextDanwei.setText(mYdanwei);
		mTextDingjin.setText(mYdingjin);
	}
	@OnClick({R.id.back, R.id.btn_next})
	public void onClick(View view){
		switch (view.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.btn_next:
			if(isNull()){
				initDataOrder(mUser.getId(), mYid, mEditName.getText().toString(),
						mEditPhoneNumber.getText().toString(),
						mEditAddress.getText().toString(), mYdingjin);
			}
			break;

		default:
			break;
		}
	}
	/**
	 * 预约月嫂网络请求
	 */
	private void initDataOrder(String userid, String yuesao_id, 
			String name, String phone, String address, String dingjin){
		IUpdateUI ui = new IUpdateUI() {

			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				if(allData == null){
					Toast.makeText(NurseMaidReserveActivity.this, 
							"网络不给力", Toast.LENGTH_SHORT).show();
					return ;
				}
				LogAh.e("预约月嫂：："+allData);
				mNurseYuYueBean = (NurseYuYueBean) allData;
				if(mNurseYuYueBean.getCode().equals("0")){
					mIntent = new Intent(NurseMaidReserveActivity.this, 
							NurseMaidPaymentActivity.class);
					mIntent.putExtra("pay_id", mNurseYuYueBean.getPay_id());
					startActivity(mIntent);
				}else{
					Toast.makeText(getApplicationContext(),
							"预约失败", Toast.LENGTH_SHORT).show();
				}
			}
		};

		GetDataAsyncZGQ asyncZGQ = new GetDataAsyncZGQ(this, ui,
				true, RequestBaseMapZGQ.getYuesaoOrder(userid, yuesao_id, "", dingjin,
						phone, address, name));
		asyncZGQ.execute(EFaceTypeZGQ.URL_YUESAO_ORDER);
	}
	/**
	 * 判断是否为空
	 * @return
	 */
	private boolean isNull(){
		if(TextUtils.isEmpty(mEditName.getText().toString())){
			Toast.makeText(NurseMaidReserveActivity.this, 
					"姓名不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(TextUtils.isEmpty(mEditPhoneNumber.getText().toString())){
			Toast.makeText(NurseMaidReserveActivity.this, 
					"手机号不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(TextUtils.isEmpty(mEditAddress.getText().toString())){
			Toast.makeText(NurseMaidReserveActivity.this, 
					"地址不能为空", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
		
	}

}
