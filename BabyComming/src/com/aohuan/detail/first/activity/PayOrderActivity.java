package com.aohuan.detail.first.activity;

import java.net.URLEncoder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.android.app.sdk.AliPay;
import com.alipay.android.msp.demo.Keys;
import com.alipay.android.msp.demo.Result;
import com.alipay.android.msp.demo.Rsa;
import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 支付订单
 * @author Administrator
 */
@ContentView(R.layout.activity_pay_order)
public class PayOrderActivity extends BaseActiviry {
	@ViewInject(R.id.back)
	private ImageButton mBackButton;
	@ViewInject(R.id.title)
	private TextView mTitleView;
	@ViewInject(R.id.order_name)
	private TextView mTitleView2;
	@ViewInject(R.id.single_price2)
	private TextView mSingleView;
	@ViewInject(R.id.order_num2)
	private TextView mCountTextView;
	@ViewInject(R.id.order_total2)
	private TextView mTotalView;
	@ViewInject(R.id.btn_submit)
	private Button mSubmitButton;
	
	private int count;
	private String singlePrice;
	private String totalPrice;
	private String title;
	private String pay_id;
	private String summary;
	
	private Context mContext;
	private static final int RQF_PAY = 1;
	private static final int RQF_LOGIN = 2;
	
	@SuppressLint("HandlerLeak")
	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Result result = new Result((String) msg.obj);
			Log.e("TAG", "444444444444");
			switch (msg.what) {
			case RQF_PAY:
			case RQF_LOGIN: {
				Toast.makeText(mContext, result.getResult(),
						Toast.LENGTH_LONG).show();
			}
			break;
			default:
				break;
			}
		};
	};
	
	@SuppressLint("HandlerLeak")
	private Handler handler=new Handler(){

		public void handleMessage(android.os.Message msg) {

			if (msg.what==3) {
				
			}	else if(msg.what == 1111){
				if (msg.obj != null) {
					String str = (String) msg.obj;
					Result result = new Result(str);
					String src = str.replace("{", "");
					src = src.replace("}", "");
					String startTag = "resultStatus=";
					String endTag = ";memo";
					String content = src;
					int start = src.indexOf(startTag);
					start += startTag.length();
					if (endTag != null) {
						int end = src.indexOf(endTag);
						content = src.substring(start, end);
					} else {
						content = src.substring(start);
					}
					if ("9000".equals(content)) {
						Toast.makeText(mContext, "支付宝支付成功",
								0).show();												
					}
				} else {
					Toast.makeText(mContext, "支付失败",
							Toast.LENGTH_LONG).show();
//					int paySuccess = Integer.parseInt(sp.getString("nupayordernumber", ""))+1;
//					Editor editor = sp.edit();
//					editor.putString("nupayordernumber", paySuccess+"");
//					editor.commit();
				}
			}		
		};
	};
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		mTitleView.setText("支付订单");
		mContext=this;
		count=getIntent().getIntExtra("num", 0);
		singlePrice=getIntent().getStringExtra("single");
		totalPrice=getIntent().getStringExtra("total");
		title=getIntent().getStringExtra("title");
		pay_id=getIntent().getStringExtra("pay_id");
		summary=getIntent().getStringExtra("summary");
		
		mTotalView.setText(totalPrice);
		mCountTextView.setText(count+"");
		mTitleView2.setText(title);
		mSingleView.setText("￥"+singlePrice);
		
	}
	
	private AliPay alipay;
	/**
	 * 开始支付
	 */
	private void getZhiFuBao(){
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.e("TAG", "进来了");
				String result = null;
				String info = getNewOrderInfo();
				String sign = Rsa.sign(info, Keys.PRIVATE);
				sign = URLEncoder.encode(sign);
				info += "&sign=\"" + sign + "\"&" + getSignType();
				String orderInfo = info;
				Log.e("TAG", "11111111orderInfo：：："+orderInfo);
				alipay = new AliPay(PayOrderActivity.this, mHandler);
				Log.e("TAG", "222222222");
				result = alipay.pay(orderInfo);
				Log.e("TAG", "3333333333");
				Message msg = new Message();
				msg.what = 1111;
				msg.obj = result;
				handler.sendMessage(msg);
				Log.e("TAG", "到Handle了");
			}
		}).start();
	}
	
	/** 提交订单信息 **/
	private String getNewOrderInfo() {
		// ORDERNOM = getOrderNumberInfo();
		StringBuilder sb = new StringBuilder();
		sb.append("partner=\"");
		sb.append(Keys.DEFAULT_PARTNER);
		sb.append("\"&out_trade_no=\"");
		sb.append(pay_id);
		sb.append("\"&subject=\"");
		sb.append(title);
		sb.append("\"&body=\"");
		sb.append(summary);
		sb.append("\"&total_fee=\"");
		sb.append(totalPrice.substring(1));
		sb.append("\"&notify_url=\"");


		// 网址需要做URL编码
		// sb.append(URLEncoder.encode(MyURLUtil.MY_URL +
		// "app/alikuaipay/notify_url.php"));
		sb.append(URLEncoder.encode("http://chunjing.com.h001.360sheji.cn/app/alikuaipay/notify_url.php"));
		sb.append("\"&service=\"mobile.securitypay.pay");
		sb.append("\"&_input_charset=\"UTF-8");
		sb.append("\"&return_url=\"");
		sb.append(URLEncoder.encode("http://m.alipay.com"));
		sb.append("\"&payment_type=\"1");
		sb.append("\"&seller_id=\"");
		sb.append(Keys.DEFAULT_SELLER);
		// 如果show_url值为空，可不传
		// sb.append("\"&show_url=\"");
		sb.append("\"&it_b_pay=\"1m");
		sb.append("\"");
		return new String(sb);
	}
	
	/**
	 * 类型
	 * @return
	 */
	private String getSignType() {
		return "sign_type=\"RSA\"";
	}
	@OnClick({R.id.back,R.id.btn_submit})
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;

		case R.id.btn_submit:
			getZhiFuBao();
			break;
		}
	}
}
