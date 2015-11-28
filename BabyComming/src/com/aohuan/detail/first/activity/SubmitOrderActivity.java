package com.aohuan.detail.first.activity;

import java.text.DecimalFormat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.aohuan.bean.OrderBean;
import com.aohuan.bean.UserBean.User;
import com.aohuan.utils.http.EFaceType;
import com.aohuan.utils.http.GetDataAsync;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.propriety.LoginHelper;
import com.aohuan.utils.request.RequestBaseMap;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

@ContentView(R.layout.activity_submit_order)
public class SubmitOrderActivity extends BaseActiviry implements TextWatcher {

	@ViewInject(R.id.num_edit)
	private EditText mNumText;
	@ViewInject(R.id.submit_order)
	private Button mSubmitButton;
	@ViewInject(R.id.back)
	private ImageButton mBackButton;
	@ViewInject(R.id.title)
	private TextView mTitleView;
	
	@ViewInject(R.id.order_name)
	private TextView mTitleView2;
	@ViewInject(R.id.single_price2)
	private TextView mSinglePrice;
	@ViewInject(R.id.delete)
	private Button mDeleteButton;
	@ViewInject(R.id.add)
	private Button mAddButton;
	@ViewInject(R.id.order_total2)
	private TextView mTotalView;
	
	private String singlePrice;
	private String title;
	private String team_id;
	private int count;
	private String summary;
	
	private String user_id="";
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		mTitleView.setText("提交订单");
//		String t = mNumText.getText().toString().trim();
//		mNumText.setSelection(t.length());
		mNumText.addTextChangedListener(this);
		team_id=getIntent().getStringExtra("team_id");
		System.out.println("---team_id-"+team_id);
		title=getIntent().getStringExtra("title");
		summary=getIntent().getStringExtra("summary");
		singlePrice=getIntent().getStringExtra("single");
		mTitleView2.setText(title);
		mSinglePrice.setText("￥"+singlePrice);
		
		User user=LoginHelper.getUser(this);
		if (user!=null) {
			user_id=user.getId();
		}
	}

	private  Intent intent;
	@OnClick({ R.id.submit_order, R.id.back ,R.id.add,R.id.delete})
	public void onClick(View v) {
		
		switch (v.getId()) {
		
		case R.id.submit_order:
			if (count==0) {
				Toast.makeText(SubmitOrderActivity.this, 
						"您还没有购买任何商品", Toast.LENGTH_SHORT).show();
			}else {
				submitOrder();	
			}
			
			
			break;		
		case R.id.back:
			finish();
			break;
			
		case R.id.delete:
			if (count==0) {
				break;
			}else {
				count--;
				mNumText.setText(String.valueOf(count));
				String t = mNumText.getText().toString().trim();
				mNumText.setSelection(t.length());
				
				double total=Double.parseDouble(singlePrice)*count;
				mTotalView.setText("￥"+formatDouble(total));
			}
			break;
		case R.id.add:
			count++;
			mNumText.setText(String.valueOf(count));
			String t = mNumText.getText().toString().trim();
			mNumText.setSelection(t.length());
			
			double total=Double.parseDouble(singlePrice)*count;
			mTotalView.setText("￥"+formatDouble(total));
			
			break;
		}
	}
	private String pay_id;
	private void submitOrder() {
		GetDataAsync gs=new GetDataAsync(this, new IUpdateUI() {
			
			

			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				if (allData !=null && allData instanceof OrderBean) {
					OrderBean bean=(OrderBean) allData;
					if (bean.getCode()==0) {
						pay_id = bean.getPay_id();
						intent = new Intent(SubmitOrderActivity.this, PayOrderActivity.class);
						intent.putExtra("single", singlePrice);
						intent.putExtra("title", title);
						intent.putExtra("num", count);
						intent.putExtra("summary", summary);
						intent.putExtra("total", mTotalView.getText().toString());		
						intent.putExtra("pay_id", pay_id);
						startActivity(intent);
					}
				}else {
					Toast.makeText(SubmitOrderActivity.this, "网络异常",
							Toast.LENGTH_SHORT).show();
				}
			}
		}, false, RequestBaseMap.getOrderId(user_id, pay_id, singlePrice, "", "", "", "", "", team_id, count+""));		
		gs.execute(EFaceType.URL_GET_ORDER);
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		String str = s.toString();
		if (str.equals("")) {
			mTotalView.setText("￥0.00");
			this.count=0;
		} else {
			double total = Double.parseDouble(s.toString())
					* Double.parseDouble(singlePrice);
			this.count=Integer.parseInt(str);
			mTotalView.setText("￥"+formatDouble(total));
		}
	}
	public String formatDouble(double d){		
		DecimalFormat  dFormat=new DecimalFormat("######0.00");
		return dFormat.format(d);
	}
}
