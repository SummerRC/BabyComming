package com.aohuan.detail.first.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.aohuan.bean.CodeMessageBean;
import com.aohuan.bean.NurseCompanyBean;
import com.aohuan.bean.NurseCompanyBean.NurseCompany;
import com.aohuan.utils.adapter.CommonAdapter;
import com.aohuan.utils.adapter.ImageLoad;
import com.aohuan.utils.adapter.ViewHolder;
import com.aohuan.utils.common.LogAh;
import com.aohuan.utils.http.EFaceTypeZGQ;
import com.aohuan.utils.http.GetDataAsyncZGQ;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.request.RequestBaseMapZGQ;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
@ContentView(R.layout.activity_nurse_maid_company_layout)
public class NurseMaidCompanyActivity extends BaseActiviry implements OnItemClickListener, OnRefreshListener<ScrollView>{
	@ViewInject(R.id.scroll)
	private PullToRefreshScrollView mScrollView;
	
	@ViewInject(R.id.back)
	private ImageButton mBackButton;
	@ViewInject(R.id.title)
	private TextView mTextTitle;
	@ViewInject(R.id.gv_gongsi_list)
	private GridView mGridView;
	
	@ViewInject(R.id.gongsi_iv)
	private ImageView mImage;
	@ViewInject(R.id.gongsi_tv_title)
	private TextView mTitle;
	@ViewInject(R.id.gongsi_tv_num)
	private TextView mNum;
	@ViewInject(R.id.gongsi_ib_phone)
	private ImageButton mPhoneNumber;
	@ViewInject(R.id.gongsi_tv_zhizhao)
	private TextView mZhizhao;
	@ViewInject(R.id.gongsi_tv_shangjia_jieshao)
	private TextView mJieshao;
	@ViewInject(R.id.gongsi_tv_chengnuo)
	private TextView mChengnuo;
	@ViewInject(R.id.gongsi_tv_address)
	private TextView mAddress;
	
	/** 月嫂列表适配器 */
	private CommonAdapter<NurseCompany> mAdapter;
	/** 月嫂商家id */
	private String mParmentId;
	/** 月嫂公司实体类 */
	private NurseCompanyBean mNurseCompanyBean;
	
	private List<NurseCompany> mList;
	private int page = 1;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		mList = new ArrayList<NurseCompany>();
		mScrollView.setMode(Mode.PULL_FROM_END);
		mScrollView.setOnRefreshListener(this);
		initView();
	}
	private void initView(){
		mParmentId = getIntent().getStringExtra("partner_id");
		mTextTitle.setText("月嫂公司");
		initDataCollect(mParmentId);
	}
	
	@OnClick({R.id.back, R.id.gongsi_ib_phone})
	public void onClick(View view){
		switch (view.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.gongsi_ib_phone:
			Intent intent = new Intent(Intent.ACTION_DIAL);
			Uri data = Uri.parse("tel:" + mNurseCompanyBean.getPhone());
			intent.setData(data);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	
	private void initData(final List<NurseCompany> list){
		mAdapter = new CommonAdapter<NurseCompany>(this, list,
				R.layout.item_gongsi_gridview_layout) {
			
			@Override
			public void convert(ViewHolder helper, NurseCompany item) {
				// TODO Auto-generated method stub
				helper.setImageByUrl(R.id.item_gongsi_iv, item.getImage(), getApplicationContext());
				helper.setText(R.id.item_gongsi_tv_name, item.getName());
				helper.setText(R.id.item_gongsi_tv_dengji, item.getGrade());
				helper.setText(R.id.item_gongsi_tv_age, item.getAge());
				helper.setText(R.id.item_gongsi_tv_jiguan, item.getLocation_city());
				helper.setText(R.id.item_gongsi_tv_xueli, item.getEducation());
				helper.setText(R.id.item_gongsi_tv_price, item.getPrice()+"元/月");
				RatingBar ratingBar = helper.getView(R.id.ratingBar);
				ratingBar.setRating(Float.parseFloat(item.getPingfen()));
				ratingBar.getRating();
			}
		};
		mGridView.setAdapter(mAdapter);
		mScrollView.onRefreshComplete();
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(NurseMaidCompanyActivity.this, NurseMaidPersonalTwoActivity.class);
				intent.putExtra("yuesao_id", list.get(arg2).getId());
				startActivity(intent);
			}
		});
	}
	
	/**
	 * 网络请求
	 */
	private void initDataCollect(String id){
		IUpdateUI ui = new IUpdateUI() {

			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				if(allData == null){
					Toast.makeText(NurseMaidCompanyActivity.this, "网络不给力", Toast.LENGTH_SHORT).show();
					return ;
				}
				LogAh.e("月嫂公司：："+allData);
				mNurseCompanyBean = (NurseCompanyBean) allData;
				if(mNurseCompanyBean.getCode().equals("0")){
					ImageLoad.loadImage(mImage, mNurseCompanyBean.getImage(), getApplicationContext());
					mTitle.setText(mNurseCompanyBean.getTitle());
					mNum.setText(mNurseCompanyBean.getCount()+"人已约");
					mZhizhao.setText(mNurseCompanyBean.getLicense());
					mJieshao.setText(mNurseCompanyBean.getOther());
					mChengnuo.setText(mNurseCompanyBean.getPromise());
					mAddress.setText(mNurseCompanyBean.getAddress());
					if(mNurseCompanyBean.getTeam() != null && !mNurseCompanyBean.getTeam().isEmpty()){
						mList.addAll(mNurseCompanyBean.getTeam());
						initData(mList);
					}else{
						if(page == 1){
							initData(new ArrayList<NurseCompany>());
						}else{
							initData(mList);
						}
						
					}
				}
			}
		};

		GetDataAsyncZGQ asyncZGQ = new GetDataAsyncZGQ(this, ui,
				true, RequestBaseMapZGQ.getYuesaoParents(id, page+""));
		asyncZGQ.execute(EFaceTypeZGQ.URL_YUESAO_PARENTS);
	}
	@Override
	public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
		// TODO Auto-generated method stub
		page++;
		initDataCollect(mParmentId);
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
