package com.aohuan.detail.first.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.aohuan.bean.CodeMessageBean;
import com.aohuan.bean.NurseDetaisBean;
import com.aohuan.bean.NurseDetaisBean.Pingjia;
import com.aohuan.bean.UserBean.User;
import com.aohuan.bean.YuesaoListBean;
import com.aohuan.bean.YuesaoListBean.YuesaoList;
import com.aohuan.detail.mine.LoginActivity;
import com.aohuan.utils.adapter.CommonAdapter;
import com.aohuan.utils.adapter.ImageLoad;
import com.aohuan.utils.adapter.ViewHolder;
import com.aohuan.utils.common.LogAh;
import com.aohuan.utils.http.EFaceTypeZGQ;
import com.aohuan.utils.http.GetDataAsyncZGQ;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.propriety.LoginHelper;
import com.aohuan.utils.request.RequestBaseMapZGQ;
import com.aohuan.utils.setlistview_height.SetListViewHeightUtil;
import com.aohuan.utils.viewpager_bili.SetWidthAndeHeightUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
@ContentView(R.layout.activity_nurse_details_layout)
public class NurseMaidPersonalTwoActivity extends BaseActiviry implements OnItemClickListener, OnRefreshListener<ScrollView>{
	@ViewInject(R.id.scroll)
	private PullToRefreshScrollView mScrollView;
	
	@ViewInject(R.id.back)
	private ImageButton mBackButton;
	@ViewInject(R.id.title)
	private TextView mTextTitle;
	@ViewInject(R.id.ib_collect)
	private ImageButton mCollectBtn;
	@ViewInject(R.id.details_iv)
	private ImageView mImage;
	@ViewInject(R.id.details_tv_name)
	private TextView mTextName;
	@ViewInject(R.id.details_tv_dengji)
	private TextView mTextDengji;
	@ViewInject(R.id.details_tv_age)
	private TextView mTextAge;
	@ViewInject(R.id.details_tv_jiguan)
	private TextView mTextJiguan;
	@ViewInject(R.id.details_tv_xueli)
	private TextView mTextXueli;
	@ViewInject(R.id.ratingBar)
	private RatingBar mRatingBar;
	@ViewInject(R.id.details_tv_pingfen)
	private TextView mTextPingfen;
	@ViewInject(R.id.details_tv_qw_price)
	private TextView mTextQwPrice;
	@ViewInject(R.id.details_tv_yu_price)
	private TextView mTextYuPrice;
	@ViewInject(R.id.details_tv_danwei)
	private TextView mTextDanwei;
	@ViewInject(R.id.details_tv_jieshao)
	private TextView mTextJieshao;
	@ViewInject(R.id.details_tv_pingjia_num)
	private TextView mTextPingjiaNum;
	
	@ViewInject(R.id.tv_pingjia)
	private TextView mTextPingjia;
	@ViewInject(R.id.lv_pingjia)
	private ListView mLvPingjia;
	@ViewInject(R.id.btn_yuyue)
	private Button mBtnYuyue;
	@ViewInject(R.id.rl_yuesao_gongsi)
	private RelativeLayout mLayoutGongsi;
	
	
	/** 评价列表 */
	private CommonAdapter<Pingjia> mAdapter;
	/** 跳转 */
	private Intent mIntent;
	/** 月嫂id */
	private String mYuesaoId;
	/** 月嫂详情实体类 */
	private NurseDetaisBean mNurseDetaisBean;
	/** 收藏实体类 */
	private CodeMessageBean mCodeMessageBean;
	/** 判断是否要刷新评价---为false时  刷新 */
	private boolean mFlag;
	/** 用户信息实体类 */
	private User mUser;
	
	private List<Pingjia> mList;
	private int page = 1;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		mFlag = true;
		mList = new ArrayList<Pingjia>();
		mScrollView.setMode(Mode.PULL_FROM_END);
		mScrollView.setOnRefreshListener(this);
		mYuesaoId = getIntent().getStringExtra("yuesao_id");
		mUser = LoginHelper.getUser(getApplicationContext());
		initView();
		if(mUser == null){
			initDataYuesaoList("", mYuesaoId);
		}else{
			initDataYuesaoList(mUser.getId(), mYuesaoId);
		}
		
	}
	
	@OnClick({R.id.back, R.id.btn_yuyue, R.id.rl_yuesao_gongsi, R.id.tv_pingjia,
		R.id.ib_collect})
	public void onClick(View view){
		switch (view.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.ib_collect:
			if(mUser == null){
				mIntent = new Intent(NurseMaidPersonalTwoActivity.this, LoginActivity.class);
				startActivity(mIntent);
				return ;
			}
			initDataCollect(mUser.getId(), mYuesaoId);
			break;
		case R.id.btn_yuyue:
			if(mUser == null){
				mIntent = new Intent(NurseMaidPersonalTwoActivity.this, LoginActivity.class);
				startActivity(mIntent);
				return ;
			}
			mIntent = new Intent(NurseMaidPersonalTwoActivity.this, NurseMaidReserveActivity.class);
			mIntent.putExtra("id", mYuesaoId);
			mIntent.putExtra("name", mNurseDetaisBean.getList().get(0).getName());
			mIntent.putExtra("dengji", mNurseDetaisBean.getList().get(0).getGrade());
			mIntent.putExtra("gongsi", mNurseDetaisBean.getList().get(0).getPartner_title());
			mIntent.putExtra("price", mNurseDetaisBean.getList().get(0).getDing_price());
			startActivity(mIntent);
			break;
		case R.id.rl_yuesao_gongsi:
			mIntent = new Intent(NurseMaidPersonalTwoActivity.this, NurseMaidCompanyActivity.class);
			mIntent.putExtra("partner_id", mNurseDetaisBean.getList().get(0).getPartner_id());
			startActivity(mIntent);
			break;
		case R.id.tv_pingjia:
			if(mUser == null){
				mIntent = new Intent(NurseMaidPersonalTwoActivity.this, LoginActivity.class);
				startActivity(mIntent);
				return ;
			}
			mIntent = new Intent(NurseMaidPersonalTwoActivity.this, NurseMaidEvaluateActivity.class);
			mIntent.putExtra("id", mYuesaoId);
			mIntent.putExtra("image", mNurseDetaisBean.getList().get(0).getImage());
			mIntent.putExtra("name", mNurseDetaisBean.getList().get(0).getName());
			mIntent.putExtra("age", mNurseDetaisBean.getList().get(0).getAge());
			mIntent.putExtra("jiguan", mNurseDetaisBean.getList().get(0).getLocation_city());
			mIntent.putExtra("xueli", mNurseDetaisBean.getList().get(0).getEducation());
			mIntent.putExtra("dengji", mNurseDetaisBean.getList().get(0).getGrade());
			mIntent.putExtra("price", mNurseDetaisBean.getList().get(0).getPrice());
			startActivity(mIntent);
			break;

		default:
			break;
		}
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mUser = LoginHelper.getUser(getApplicationContext());
		if(mFlag == true){
			mFlag = false;
		}else{
			mList.clear();
			mAdapter = null;
			page = 1;
			if(mUser == null){
				initDataYuesaoList("", mYuesaoId);
			}else{
				
				initDataYuesaoList(mUser.getId(), mYuesaoId);
			}
		}
	}
	
	private void initView(){
		mTextTitle.setText("月嫂个人页");
		mCollectBtn.setVisibility(View.VISIBLE);
		SetWidthAndeHeightUtils.setImageView(this, mImage, 3, 2);
		mTextPingjia.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
	}
	
	private void initPingjiaList(List<Pingjia> list){
		mAdapter = new CommonAdapter<Pingjia>(this, list,
				R.layout.item_nurse_details_pingjia_layout) {
			
			@Override
			public void convert(ViewHolder helper, Pingjia item) {
				// TODO Auto-generated method stub
				helper.setText(R.id.pingjia_username, item.getUsername());
				helper.setText(R.id.pingjia_time, item.getCreate_time());
				helper.setText(R.id.pingjia_details, item.getDetails());
			}
		};
		mLvPingjia.setAdapter(mAdapter);
		mScrollView.onRefreshComplete();
	}
	
	/**
	 * 月嫂详情网络请求
	 */
	private void initDataYuesaoList(String userid, String yuesao_id){
		IUpdateUI ui = new IUpdateUI() {

			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				if(allData == null){
					Toast.makeText(NurseMaidPersonalTwoActivity.this, "网络不给力", Toast.LENGTH_SHORT).show();
					return ;
				}
				LogAh.e("月嫂详情页：："+allData);
				LogAh.e("月嫂详页：："+1);
				mNurseDetaisBean =(NurseDetaisBean) allData;
				if(mNurseDetaisBean.getCode().equals("0") && 
						mNurseDetaisBean.getList() != null && 
						!mNurseDetaisBean.getList().isEmpty()){
					ImageLoad.loadImage(mImage, mNurseDetaisBean.getList().get(0).getImage(), getApplicationContext());
					mTextName.setText(mNurseDetaisBean.getList().get(0).getName());
					mTextDengji.setText(mNurseDetaisBean.getList().get(0).getGrade());
					mTextAge.setText(mNurseDetaisBean.getList().get(0).getAge());
					mTextJiguan.setText(mNurseDetaisBean.getList().get(0).getLocation_city());
					mTextXueli.setText(mNurseDetaisBean.getList().get(0).getEducation());
					mRatingBar.setRating(Float.parseFloat(mNurseDetaisBean.getList().get(0).getPingfen()));
					mRatingBar.getRating();
					mTextPingfen.setText(mNurseDetaisBean.getList().get(0).getPingfen());
					mTextQwPrice.setText(mNurseDetaisBean.getList().get(0).getPrice());
					mTextYuPrice.setText(mNurseDetaisBean.getList().get(0).getDing_price());
					mTextDanwei.setText("所属单位："+mNurseDetaisBean.getList().get(0).getPartner_title());
					mTextJieshao.setText(mNurseDetaisBean.getList().get(0).getParticulars());
					mTextPingjiaNum.setText("("+mNurseDetaisBean.getList().get(0).getPinglun_tiao()+")");
					
					if(mNurseDetaisBean.getList().get(0).getShoucang().equals("1")){
						mCollectBtn.setImageResource(R.drawable.ic_collect_on);
					}
				}
				if(mNurseDetaisBean.getCode().equals("0") && 
						mNurseDetaisBean.getPinglun() != null && 
						!mNurseDetaisBean.getPinglun().isEmpty()){
					mList.addAll(mNurseDetaisBean.getPinglun());
					initPingjiaList(mList);
				}else{
					if(page == 1){
						initPingjiaList(new ArrayList<Pingjia>());
					}else{
						initPingjiaList(mList);
					}
				}
			}
		};

		GetDataAsyncZGQ asyncZGQ = new GetDataAsyncZGQ(this, ui,
				true, RequestBaseMapZGQ.getYuesaoDetails(userid, yuesao_id, page+""));
		asyncZGQ.execute(EFaceTypeZGQ.URL_YUESAO_DETAILS);
	}
	/**
	 * 收藏网络请求
	 */
	private void initDataCollect(String userid, String yuesao_id){
		IUpdateUI ui = new IUpdateUI() {

			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				if(allData == null){
					Toast.makeText(NurseMaidPersonalTwoActivity.this, 
							"网络不给力", Toast.LENGTH_SHORT).show();
					return ;
				}
				LogAh.e("月嫂收藏：："+allData);
				mCodeMessageBean = (CodeMessageBean) allData;
				if(mCodeMessageBean.getCode().equals("0")){
					mCollectBtn.setImageResource(R.drawable.ic_collect_on);
					Toast.makeText(NurseMaidPersonalTwoActivity.this, 
							"已收藏", Toast.LENGTH_SHORT).show();
				}else if(mCodeMessageBean.getCode().equals("1")){
					mCollectBtn.setImageResource(R.drawable.ic_collect_off);
					Toast.makeText(NurseMaidPersonalTwoActivity.this, 
							"取消收藏", Toast.LENGTH_SHORT).show();
				}
			}
		};

		GetDataAsyncZGQ asyncZGQ = new GetDataAsyncZGQ(this, ui,
				true, RequestBaseMapZGQ.getYuesaoCollect(userid, yuesao_id));
		asyncZGQ.execute(EFaceTypeZGQ.URL_YUESAO_COLLECT);
	}
	
	@Override
	public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
		// TODO Auto-generated method stub
		page++;
		if(mUser == null){
			initDataYuesaoList("", mYuesaoId);
		}else{
			initDataYuesaoList(mUser.getId(), mYuesaoId);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
