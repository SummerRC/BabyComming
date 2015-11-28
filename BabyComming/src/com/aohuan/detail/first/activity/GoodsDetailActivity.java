package com.aohuan.detail.first.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.SyncStateContract.Helpers;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.aohuan.bean.CodeMessageBean;
import com.aohuan.bean.GoodsDetailsBean;
import com.aohuan.bean.UserBean.User;
import com.aohuan.detail.first.activity.TaoCanDetailActivity.MyWebClient;
import com.aohuan.detail.mine.LoginActivity;
import com.aohuan.utils.adapter.ImageLoad;
import com.aohuan.utils.common.LogAh;
import com.aohuan.utils.http.EFaceTypeZGQ;
import com.aohuan.utils.http.GetDataAsyncZGQ;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.http.UrlConstants;
import com.aohuan.utils.propriety.LoginHelper;
import com.aohuan.utils.request.RequestBaseMapZGQ;
import com.aohuan.utils.viewpager_bili.SetWidthAndeHeightUtils;
import com.aohuan.view.MyWebview;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

@ContentView(R.layout.activity_goods_details_layout)
public class GoodsDetailActivity extends BaseActiviry{
	@ViewInject(R.id.back)
	private ImageButton mBackButton;
	@ViewInject(R.id.title)
	private TextView mTextTitle;
	@ViewInject(R.id.ib_collect)
	private ImageButton mBtnCollect;
	@ViewInject(R.id.goods_iv)
	private ImageView mImage;
	@ViewInject(R.id.goods_tv_title)
	private TextView mTextStitle;
	@ViewInject(R.id.goods_tv_w_price)
	private TextView mTextWprice;
	@ViewInject(R.id.goods_tv_y_price)
	private TextView mTextYprice;
	@ViewInject(R.id.goods_tv_jieshao)
	private TextView mTextJieshao;
	@ViewInject(R.id.goods_tv_canshu)
	private TextView mTextCanshu;
	@ViewInject(R.id.btn_collect)
	private Button mCollect;
	
	
	/** 跳转 */
	private Intent mIntent;
	/** 商品Id */
	private String mGoodsId;
	/** 商品详情实体类 */
	private GoodsDetailsBean mGoodsDetailsBean;
	/** 收藏实体类 */
	private CodeMessageBean mCodeMessageBean;
	/** 用户信息实体类 */
	private User mUser;
	
	@ViewInject(R.id.web)
	private WebView mWebView;
	@ViewInject(R.id.popup_sf_event_scroll_01)
	private ScrollView sv01;
	@ViewInject(R.id.popup_sf_event_scroll_02)
	private ScrollView sv02;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		mUser = LoginHelper.getUser(getApplicationContext());
		initView();
		if(mUser == null){
			initDataDetails("", mGoodsId);
		}else{
			initDataDetails(mUser.getId(), mGoodsId);
		}
		
		
		
	}

	private void initView(){
		mGoodsId = getIntent().getStringExtra("goodsid");
		mTextTitle.setText("精选商品详情页");
		mBtnCollect.setVisibility(View.VISIBLE);
		SetWidthAndeHeightUtils.setImageView(this, mImage, 3, 2);
		
		mWebView.setWebViewClient(new MyWebClient());
		mWebView.loadUrl(UrlConstants.URL+"App/webapp.php?action=web_show&team_id="+mGoodsId);
//		mWebView.setOnTouchListener(new OnTouchListener() {
//
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				// TODO Auto-generated method stub
//				if (event.getAction() == MotionEvent.ACTION_UP)
//					sv02.requestDisallowInterceptTouchEvent(true);
//				else
//					sv02.requestDisallowInterceptTouchEvent(false);
//
//				return false;
//			}
//
//		});
//		LinearLayout.LayoutParams params=(LinearLayout.LayoutParams) mWebView.getLayoutParams();
//		
//		params.height=getWindowsWidth(this);
//		params.width=getWindowsWidth2(this);
//		
//		mWebView.setLayoutParams(params);
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mUser = LoginHelper.getUser(getApplicationContext());
	}
	
	@OnClick({R.id.back,  R.id.ib_collect, R.id.btn_collect})
	public void onClick(View view){
		switch (view.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.ib_collect:
			if(mUser == null){
				mIntent = new Intent(GoodsDetailActivity.this, LoginActivity.class);
				startActivity(mIntent);
				return ;
			}
			initDataCollect(mUser.getId(), mGoodsId);
			break;
		case R.id.btn_collect:
			if(mUser == null){
				mIntent = new Intent(GoodsDetailActivity.this, LoginActivity.class);
				startActivity(mIntent);
				return ;
			}
			initDataCollect(mUser.getId(), mGoodsId);
			break;
		
		default:
			break;
		}
	}
	/**
	 * 商品详情网络请求
	 */
	private void initDataDetails(String userid, String goods_id){
		IUpdateUI ui = new IUpdateUI() {

			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				if(allData == null){
					Toast.makeText(GoodsDetailActivity.this, 
							"网络不给力", Toast.LENGTH_SHORT).show();
					return ;
				}
				LogAh.e("商品详情：："+allData);
				mGoodsDetailsBean = (GoodsDetailsBean) allData;
				if(mGoodsDetailsBean.getCode().equals("0")){
					if(mGoodsDetailsBean.getImage().length > 0){
						ImageLoad.loadImage(mImage, mGoodsDetailsBean.getImage()[0], getApplicationContext());
					}
					mTextStitle.setText(mGoodsDetailsBean.getTitle());
					mTextWprice.setText("￥"+mGoodsDetailsBean.getTeam_price());
					mTextYprice.setText("原价："+mGoodsDetailsBean.getMarket_price());
					mTextYprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); 
					mTextJieshao.setText(mGoodsDetailsBean.getPresentation());
					mTextCanshu.setText(mGoodsDetailsBean.getSummary());
					if(mGoodsDetailsBean.getShoucang().equals("1")){
						mBtnCollect.setImageResource(R.drawable.ic_collect_on);
					}
				}
			}
		};

		GetDataAsyncZGQ asyncZGQ = new GetDataAsyncZGQ(this, ui,
				true, RequestBaseMapZGQ.getGoodsDetails(userid, goods_id));
		asyncZGQ.execute(EFaceTypeZGQ.URL_SELCT_GOODS_DETAILS);
	}
	/**
	 * 收藏网络请求
	 */
	private void initDataCollect(String userid, String team_id){
		IUpdateUI ui = new IUpdateUI() {

			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				if(allData == null){
					Toast.makeText(GoodsDetailActivity.this, 
							"网络不给力", Toast.LENGTH_SHORT).show();
					return ;
				}
				LogAh.e("商品收藏：："+allData);
				mCodeMessageBean = (CodeMessageBean) allData;
				if(mCodeMessageBean.getCode().equals("0")){
					mBtnCollect.setImageResource(R.drawable.ic_collect_on);
					Toast.makeText(GoodsDetailActivity.this, 
							"已收藏", Toast.LENGTH_SHORT).show();
				}else if(mCodeMessageBean.getCode().equals("1")){
					mBtnCollect.setImageResource(R.drawable.ic_collect_off);
					Toast.makeText(GoodsDetailActivity.this, 
							"取消收藏", Toast.LENGTH_SHORT).show();
				}
			}
		};

		GetDataAsyncZGQ asyncZGQ = new GetDataAsyncZGQ(this, ui,
				true, RequestBaseMapZGQ.getGoodsCollect(userid, team_id));
		asyncZGQ.execute(EFaceTypeZGQ.URL_SELCT_GOODS_COLLECT);
	}
	
	class MyWebClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub

			view.loadUrl(url);
			return true;
		}
	}
	
	/** 获取屏幕的宽度 */
	public final static int getWindowsWidth(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}
	/** 获取屏幕的宽度 */
	public final static int getWindowsWidth2(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}
}
