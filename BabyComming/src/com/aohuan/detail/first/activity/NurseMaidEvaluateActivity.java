package com.aohuan.detail.first.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.aohuan.bean.CodeMessageBean;
import com.aohuan.bean.UserBean.User;
import com.aohuan.utils.adapter.ImageLoad;
import com.aohuan.utils.common.LogAh;
import com.aohuan.utils.http.EFaceTypeZGQ;
import com.aohuan.utils.http.GetDataAsyncZGQ;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.propriety.LoginHelper;
import com.aohuan.utils.request.RequestBaseMapZGQ;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

@ContentView(R.layout.activity_nurse_maid_evaluate_layout)
public class NurseMaidEvaluateActivity extends BaseActiviry{
	@ViewInject(R.id.back)
	private ImageButton mBackButton;
	@ViewInject(R.id.title)
	private TextView mTextTitle;
	@ViewInject(R.id.btn_send)
	private Button mBtnSend;
	
	@ViewInject(R.id.pingjia_iv)
	private ImageView mImage;
	@ViewInject(R.id.pingjia_tv_name)
	private TextView mTextName;
	@ViewInject(R.id.pingjia_tv_age)
	private TextView mTextAge;
	@ViewInject(R.id.pingjia_tv_jiguan)
	private TextView mTextJiguan;
	@ViewInject(R.id.pingjia_tv_xueli)
	private TextView mTextXueli;
	@ViewInject(R.id.pingjia_tv_dengji)
	private TextView mTextDengji;
	@ViewInject(R.id.pingjia_tv_price)
	private TextView mTextPrice;
	@ViewInject(R.id.pingjia_tv_pingfen)
	private TextView mTextNum;
	@ViewInject(R.id.ratingBar)
	private RatingBar mRatingBar;
	@ViewInject(R.id.pingjia_et_content)
	private EditText mEditContent;
	
	/** 月嫂id */
	private String mYid;
	/** 月嫂图片 */
	private String mYimage;
	/** 月嫂名字 */
	private String mYname;
	/** 月嫂年龄 */
	private String mYage;
	/** 月嫂籍贯 */
	private String mYjiguan;
	/** 月嫂学历 */
	private String mYxueli;
	/** 月嫂等级 */
	private String mYdengji;
	/** 月嫂工资 */
	private String mYprice;
	/** 月嫂评价实体类 */
	private CodeMessageBean mCodeMessageBean;
	/** 用户信息实体类 */
	private User mUser;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		initData();
		initView();
	}
	private void initData(){
		mUser = LoginHelper.getUser(getApplicationContext());
		mYid = getIntent().getStringExtra("id");
		mYimage = getIntent().getStringExtra("image");
		mYname = getIntent().getStringExtra("name");
		mYage = getIntent().getStringExtra("age");
		mYjiguan = getIntent().getStringExtra("jiguan");
		mYxueli = getIntent().getStringExtra("xueli");
		mYdengji = getIntent().getStringExtra("dengji");
		mYprice = getIntent().getStringExtra("price");
	}
	private void initView(){
		mTextTitle.setText("评价");
		ImageLoad.loadImage(mImage, mYimage, getApplicationContext());
		mTextName.setText(mYname);
		mTextAge.setText(mYage);
		mTextJiguan.setText(mYjiguan);
		mTextXueli.setText(mYxueli);
		mTextDengji.setText(mYdengji);
		mTextPrice.setText(mYprice);
		mRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				// TODO Auto-generated method stub
				mTextNum.setText(String.valueOf(rating));
			}
		});
	}

	@OnClick({R.id.back, R.id.btn_send})
	public void onClick(View view){
		switch (view.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.btn_send:
			if(mTextNum.getText().toString().equals("0")){
				Toast.makeText(NurseMaidEvaluateActivity.this, 
						"满意度不能为0", Toast.LENGTH_SHORT).show();
				return ;
			}
			if(TextUtils.isEmpty(mEditContent.getText().toString())){
				Toast.makeText(NurseMaidEvaluateActivity.this, 
						"评价内容不能为空", Toast.LENGTH_SHORT).show();
				return ;
			}
			initDataCollect(mUser.getId(), mYid, mTextNum.getText().toString(),
					mEditContent.getText().toString());
			break;
		default:
			break;
		}
	}
	
	/**
	 * 收藏网络请求
	 */
	private void initDataCollect(String userid, String yuesao_id, String score, String details){
		IUpdateUI ui = new IUpdateUI() {

			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				if(allData == null){
					Toast.makeText(NurseMaidEvaluateActivity.this, 
							"网络不给力", Toast.LENGTH_SHORT).show();
					return ;
				}
				LogAh.e("月嫂评价：："+allData);
				mCodeMessageBean = (CodeMessageBean) allData;
				if(mCodeMessageBean.getCode().equals("0")){
					Toast.makeText(NurseMaidEvaluateActivity.this, 
							mCodeMessageBean.getMessage(), Toast.LENGTH_SHORT).show();
					finish();
				}else if(mCodeMessageBean.getCode().equals("1")){
					Toast.makeText(NurseMaidEvaluateActivity.this, 
							mCodeMessageBean.getMessage(), Toast.LENGTH_SHORT).show();
				}
			}
		};

		GetDataAsyncZGQ asyncZGQ = new GetDataAsyncZGQ(this, ui,
				true, RequestBaseMapZGQ.getYuesaoPingjia(userid, yuesao_id, score, details));
		asyncZGQ.execute(EFaceTypeZGQ.URL_YUESAO_PINGJIA);
	}

}
