package com.aohuan.detail.first.activity;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.aohuan.bean.BaseBean;
import com.aohuan.bean.TaoCanDetailBean;
import com.aohuan.bean.TaoCanDetailBean.Partner;
import com.aohuan.bean.UserBean.User;
import com.aohuan.detail.mine.LoginActivity;
import com.aohuan.utils.CollectUtils;
import com.aohuan.utils.adapter.ImageLoad;
import com.aohuan.utils.http.EFaceType;
import com.aohuan.utils.http.GetDataAsync;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.http.UrlConstants;
import com.aohuan.utils.propriety.LoginHelper;
import com.aohuan.utils.request.RequestBaseMap;
import com.aohuan.utils.viewpager_bili.SetWidthAndeHeightUtils;
import com.aohuan.view.FoundWebView;
import com.aohuan.view.MyLayout;
import com.aohuan.view.ScrollViewContainer;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 套餐详情
 *
 * @author Administrator
 */
@ContentView(R.layout.activity_taocan_detail)
public class TaoCanDetailActivity extends BaseActiviry {
	@ViewInject(R.id.store_name_detail)
	private RelativeLayout mNameLayout;
	@ViewInject(R.id.customer_comment)
	private RelativeLayout mCommentLayout;
	@ViewInject(R.id.photo_text_detail)
	private RelativeLayout mPhotoLayout;
	@ViewInject(R.id.back)
	private ImageButton mBackButton;
	@ViewInject(R.id.tao_can_get)
	private Button mGetButton;
	
	private String team_id;
	
	@ViewInject(R.id.collect)
	private ImageButton mCollectButton;
	
	@ViewInject(R.id.tao_can_image)
	private ImageView mImageView;
	@ViewInject(R.id.tao_can_name)
	private TextView mTextView;
	@ViewInject(R.id.tao_can_price)
	private TextView mNowPriceView;
	@ViewInject(R.id.tao_can_old_price2)
	private TextView mOldPriceView;
	@ViewInject(R.id.tao_can_person)
	private TextView mSaleCountView;
	@ViewInject(R.id.tao_can_intro_detail)
	private TextView mIntroView;
	@ViewInject(R.id.store_name)
	private TextView mTextView2;
	@ViewInject(R.id.sotre_address_content)
	private TextView mAddressView;
	@ViewInject(R.id.comment_num)
	private TextView mCommentView;
	@ViewInject(R.id.store_bar)
	private RatingBar mBar;
	private Partner partner;
	
	private String user_id;
	
	@ViewInject(R.id.web)
	private WebView mWebView;
	@ViewInject(R.id.popup_sf_event_scroll_01)
	private ScrollView sv01;
	@ViewInject(R.id.popup_sf_event_scroll_02)
	private ScrollView sv02;
	
	@ViewInject(R.id.lay)
	private MyLayout mLayout;
	@ViewInject(R.id.con)
	private ScrollViewContainer mViewContainer;
	
	private TaoCanDetailBean bean;
	
	@SuppressLint("HandlerLeak")
	private Handler handler=new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			if (msg.what==1) {
				if (msg.obj !=null && msg.obj instanceof BaseBean) {
					BaseBean bean=(BaseBean) msg.obj;
					if (bean!=null && bean.getCode()==0) {
						Toast.makeText(TaoCanDetailActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();	
						mCollectButton.setImageResource(R.drawable.ic_collect_on);
					}else if (bean!=null && bean.getCode()==1) {
						Toast.makeText(TaoCanDetailActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
						mCollectButton.setImageResource(R.drawable.ic_collect_off);
					}
				}else {
					Toast.makeText(TaoCanDetailActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
				}
			}
		}
	};
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);	
		
		SetWidthAndeHeightUtils.setImageView(this, mImageView, 3, 2);
		team_id=getIntent().getStringExtra("team_id");
	
		
		mWebView.setWebViewClient(new MyWebClient());
		mWebView.setWebChromeClient(new MyCh());
//		mWebView.getSettings().setUseWideViewPort(true);
//		mWebView.getSettings().setLoadWithOverviewMode(true);
//		mWebView.getSettings().setJavaScriptEnabled(true);
//		mWebView.getSettings().setSupportZoom(true);
//		mWebView.getSettings().setDomStorageEnabled(true);
//		mWebView.getSettings().setBuiltInZoomControls(true);
//		mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//		
		
		mWebView.loadUrl(UrlConstants.PATH_STRING+team_id);
		
		
		
		//mWebView.loadUrl(UrlConstants.URL+"App/webapp.php?action=web_show&team_id="+5);	
//		LinearLayout.LayoutParams params=(LinearLayout.LayoutParams) mWebView.getLayoutParams();		
//		params.height=getWindowsWidth(this);
//		params.width=getWindowsWidth2(this);		
//		mWebView.setLayoutParams(params);
			
	}
	private void loadData() {
		GetDataAsync gs=new GetDataAsync(this, new IUpdateUI() {
			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				if (allData !=null && allData instanceof TaoCanDetailBean) {
					bean = (TaoCanDetailBean) allData;
					if (bean.getCode()==0) {
						initData();					
					}
				}
			}
		}, false, RequestBaseMap.getTaoCanDetail(team_id,user_id));
		
		gs.execute(EFaceType.URL_TAOCAN_DETAIL);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();	
		User user=LoginHelper.getUser(this);
		if (user==null) {
			user_id="";
		}else {
			user_id=user.getId();
		}
		loadData();
		
	}
	@OnClick({ R.id.store_name_detail, R.id.customer_comment,
			 R.id.back,R.id.tao_can_get,
			R.id.collect})
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.store_name_detail:
			 intent=new Intent(this, StoreIntroActivity.class);
			 intent.putExtra("id", partner.getId());
			 startActivity(intent);
			break;

		case R.id.customer_comment:
			intent=new Intent(this, CommentActivity.class);
			intent.putExtra("id", partner.getId());
			startActivity(intent);			
			break;
			
		case R.id.photo_text_detail:
			intent=new Intent(this, PhotoTextDetailActivity.class);
			intent.putExtra("id", team_id);
			startActivity(intent);
			break;
			
		case R.id.back:
			finish();
			break;
		case R.id.tao_can_get:
			if (LoginHelper.getUser(TaoCanDetailActivity.this)!=null) {
				intent=new Intent(this, SubmitOrderActivity.class);
				intent.putExtra("single", bean.getTeam_price());
				intent.putExtra("title", bean.getTitle());
				intent.putExtra("team_id", bean.getId());
				intent.putExtra("summary", bean.getSummary());
				startActivity(intent);
			}else {
				intent=new Intent(TaoCanDetailActivity.this, LoginActivity.class);
				startActivity(intent);
			}		
			break;
		case R.id.collect:			
			if (LoginHelper.getUser(TaoCanDetailActivity.this)!=null) {
				//String user_id=LoginHelper.getUser(TaoCanDetailActivity.this).getId();					
				CollectUtils.collectOrCancel(this, handler, user_id, team_id);
			}else {
				intent=new Intent(TaoCanDetailActivity.this, LoginActivity.class);
				startActivity(intent);
			}
			
			break;
		}
	}


	private void initData() {
		mCollectButton.setVisibility(View.VISIBLE);
		if (bean.getShoucang().equals("0")) {
			mCollectButton.setImageResource(R.drawable.ic_collect_off);
		}else if (bean.getShoucang().equals("1")) {
			mCollectButton.setImageResource(R.drawable.ic_collect_on);
		}
		mTextView.setText(bean.getTitle());
		mNowPriceView.setText("￥"+bean.getTeam_price());
		mOldPriceView.setText("￥"+bean.getMarket_price());
		mSaleCountView.setText(bean.getCount()+"人已约");
		mIntroView.setText(bean.getPresentation());
		List<String> s=bean.getImage();
		if (s.size()>0) {
			ImageLoad.loadImage(mImageView, s.get(0), TaoCanDetailActivity.this);
		}	
		partner = bean.getPartner();
		mTextView2.setText(partner.getTitle());
		mAddressView.setText(partner.getAddress());
		mCommentView.setText("("+partner.getPinglun_number()+")");
		mBar.setRating(Float.parseFloat(partner.getPingfen()));
	}
	
	class MyWebClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub

			view.loadUrl(url);
			return true;
		}
		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);
			
//			int w=View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.EXACTLY);
//			int h=View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//			mLayout.measure(w, h);
			int hs=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.AT_MOST);
			
			mLayout.measure(hs, hs);
			System.out.println("----"+mLayout.getMeasuredHeight());
			System.out.println("----"+mLayout.getHeight());
			System.out.println("----"+mLayout.getBottom());
			System.out.println("----"+mWebView.getContentHeight());
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
	class MyCh extends WebChromeClient{
		
		
		@Override
		public void onRequestFocus(WebView view) {
			// TODO Auto-generated method stub
			super.onRequestFocus(view);
			System.out.println("------1");
		}
		@Override
		public void onHideCustomView() {
			// TODO Auto-generated method stub
			super.onHideCustomView();
			System.out.println("------2");
		}
	
	}
	
}
