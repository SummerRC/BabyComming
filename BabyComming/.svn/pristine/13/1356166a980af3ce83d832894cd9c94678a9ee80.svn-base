package com.aohuan.detail.mine;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.aohuan.bean.YuesaoListBean;
import com.aohuan.bean.YuesaoListBean.YuesaoList;
import com.aohuan.detail.first.activity.NurseMaidPersonalActivity;
import com.aohuan.detail.first.activity.TaoCanDetailActivity;
import com.aohuan.utils.adapter.CommonAdapter;
import com.aohuan.utils.adapter.ViewHolder;
import com.aohuan.utils.http.EFaceType;
import com.aohuan.utils.http.EFaceTypeZGQ;
import com.aohuan.utils.http.GetDataAsync;
import com.aohuan.utils.http.GetDataAsyncZGQ;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.propriety.LoginHelper;
import com.aohuan.utils.request.RequestBaseMap;
import com.aohuan.utils.request.RequestBaseMapZGQ;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

@ContentView(R.layout.activity_mine_collection)
public class MyFocusNurseMaidActivity extends BaseActiviry {

	@ViewInject(R.id.title)
	private TextView mTvTitle;
	@ViewInject(R.id.back)
	private ImageButton mImgBtnBack;
	
	@ViewInject(R.id.no_data)
	private LinearLayout mRlNoData;
	@ViewInject(R.id.mine_list)
	private GridView mGridView;
	
	private List<YuesaoList> mListString;
	private CommonAdapter<YuesaoList> mCommonAdapter;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		mTvTitle.setText("我关注的月嫂");
		mListString = new ArrayList<YuesaoList>();
		
		getData();
		
	}
	
	
	private void getData() {
		GetDataAsync gs=new GetDataAsync(this, new IUpdateUI() {
			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				if (allData !=null && allData instanceof YuesaoListBean) {
					YuesaoListBean bean=(YuesaoListBean) allData;
					if (bean.getCode().equals("0")) {
						mListString=bean.getList();
						doCommit();
					}
				}
			}
		}, false, RequestBaseMap.getCollect(LoginHelper.getUser(getApplicationContext()).getId()));
		gs.execute(EFaceType.URL_MY_FOCUS_YUESAO);
	}


	private void setAdapter(){
		mCommonAdapter = new CommonAdapter<YuesaoList>(this, mListString, R.layout.item_has_paid_yuesao) {
			public void convert(ViewHolder helper, final YuesaoList item) {
				helper.setImageByUrl(R.id.yuesao_iv, item.getImage(), mContext);
				helper.setText(R.id.yuesao_name, item.getName());
				helper.setText(R.id.yuesao_dengji, item.getGrade());
				helper.setText(R.id.yuesao_age, item.getAge());
				helper.setText(R.id.yuesao_quyu, item.getLocation_city());
				helper.setText(R.id.yuesao_xueli, item.getEducation());
				helper.setText(R.id.yuesao_price, item.getPrice()+"元/月");
				RatingBar rb = helper.getView(R.id.ratingBar);
				rb.setRating(Float.parseFloat(item.getPingfen()));
				helper.getView(R.id.delete).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						showDelete(item.getId());
					}
				});
			}
		};
		mGridView.setAdapter(mCommonAdapter);
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), NurseMaidPersonalActivity.class);
				intent.putExtra("yuesao_id", mListString.get(arg2).getId());
				startActivity(intent);
			}
		});
	}
	
	/**
	 * 删除的Dialog
	 */
	public void showDelete(final String id){
		final Dialog dialog = new Dialog(this);
		View view = LayoutInflater.from(this).inflate(R.layout.dialog_delete_order_layout, null);
		Button cencal = (Button) view.findViewById(R.id.btn_cencal);
		Button sure = (Button) view.findViewById(R.id.btn_sure);
		TextView tv = (TextView) view.findViewById(R.id.tv_delete);
		tv.setText("确认删除关注的月嫂吗？");
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(view);
		dialog.setCanceledOnTouchOutside(false);//点击空白不会消失
		dialog.show();
		cencal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				
			}
		});
		sure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				deleteCollect(LoginHelper.getUser(getApplicationContext()).getId(), id);
				getData();
				dialog.dismiss();
				
			}
		});
	}
	
	private void deleteCollect(String userid, String yuesao_id){
		IUpdateUI ui = new IUpdateUI() {
			@Override
			public void updata(Object allData) {
				
			}
		};

		GetDataAsyncZGQ asyncZGQ = new GetDataAsyncZGQ(this, ui,
				true, RequestBaseMapZGQ.getYuesaoCollect(userid, yuesao_id));
		asyncZGQ.execute(EFaceTypeZGQ.URL_YUESAO_COLLECT);
	}
	
	
	private void doCommit(){
		if(mListString!=null && mListString.size()>0){
			mRlNoData.setVisibility(View.GONE);
			mGridView.setVisibility(View.VISIBLE);
			setAdapter();
		}else{
			mRlNoData.setVisibility(View.VISIBLE);
			mGridView.setVisibility(View.GONE);
		}
	}
	
	
	@OnClick({ R.id.back })
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
