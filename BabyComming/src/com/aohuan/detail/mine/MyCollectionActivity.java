package com.aohuan.detail.mine;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aohuan.base.BaseActiviry;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.aohuan.babycomming.R;
import com.aohuan.bean.MycollectBean;
import com.aohuan.bean.MycollectBean.GoodsList2;
import com.aohuan.detail.first.activity.GoodsDetailActivity;
import com.aohuan.detail.first.activity.SelectGoodsActivity;
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

/**
 * 我的套餐收藏
 * @author Administrator
 *
 */
@ContentView(R.layout.activity_mine_collection)
public class MyCollectionActivity extends BaseActiviry{

	@ViewInject(R.id.title)
	private TextView mTvTitle;
	@ViewInject(R.id.back)
	private ImageButton mImgBtnBack;
	
	@ViewInject(R.id.no_data)
	private LinearLayout mRlNoData;
	@ViewInject(R.id.mine_list)
	private GridView mGridView;
	
	private List<GoodsList2> mListString;
	private CommonAdapter<GoodsList2> mCommonAdapter;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		mTvTitle.setText("我的收藏");
		mListString = new ArrayList<GoodsList2>();
		
		//doCommit();
		getData();
	}
	
	
	private void getData() {
		GetDataAsync gs=new GetDataAsync(MyCollectionActivity.this, new IUpdateUI() {
			@Override
			public void updata(Object allData) {
				if (allData !=null && allData instanceof MycollectBean) {
					MycollectBean bean=(MycollectBean) allData;
					if (bean.getCode()==0) {
						mListString=bean.getList();
						doCommit();
					}
				}else if (allData ==null) {
					Toast.makeText(MyCollectionActivity.this, "网络不给力"
							, Toast.LENGTH_SHORT).show();
				}
			}
		}, false, RequestBaseMap.getCollect(LoginHelper.getUser(getApplicationContext()).getId()));
		gs.execute(EFaceType.URL_MY_COLLECT);
	}


	private void setAdapter(){
		mCommonAdapter = new CommonAdapter<GoodsList2>(this, mListString, R.layout.item_mine_shou_cang) {
			public void convert(ViewHolder helper, final GoodsList2 item) {
				helper.setText(R.id.tv_content, item.getProduct());
				helper.setImageByUrl(R.id.img_content, item.getImage(), MyCollectionActivity.this);
				helper.setText(R.id.tv_title, item.getTitle());
				helper.setText(R.id.tv_price_num, item.getTeam_price());
				helper.getView(R.id.tv_delete).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						showDelete(item.getId());
					}
				});
//				helper.getConvertView().setOnClickListener(new OnClickListener() {
//					
//					@Override
//					public void onClick(View v) {
//						Intent intent = new Intent(MyCollectionActivity.this, GoodsDetailActivity.class);
//						intent.putExtra("goodsid", item.getId());
//						startActivity(intent);	
//					}
//				});
			}
		};
		mGridView.setAdapter(mCommonAdapter);
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Log.e("TAG", ""+mListString.get(arg2).getGroup_id());
				if(mListString.get(arg2).getGroup_id().equals("22")){
					Intent intent = new Intent(getApplicationContext(), GoodsDetailActivity.class);
					intent.putExtra("goodsid", mListString.get(arg2).getId());
					startActivity(intent);
				}else{
					Intent intent = new Intent(getApplicationContext(), TaoCanDetailActivity.class);
					intent.putExtra("team_id", mListString.get(arg2).getId());
					startActivity(intent);
				}
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
		tv.setText("确认删除收藏吗？");
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
				deleteDataCollect(LoginHelper.getUser(getApplicationContext()).getId(), id);
				getData();
				dialog.dismiss();
				
			}
		});
	}
	
	
	private void deleteDataCollect(String userid, String team_id){
		IUpdateUI ui = new IUpdateUI() {
			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
//				if(allData == null){
//					Toast.makeText(MyCollectionActivity.this, 
//							"网络不给力", Toast.LENGTH_SHORT).show();
//					return ;
//				}
			}
		};

		GetDataAsyncZGQ asyncZGQ = new GetDataAsyncZGQ(this, ui,
				true, RequestBaseMapZGQ.getGoodsCollect(userid, team_id));
		asyncZGQ.execute(EFaceTypeZGQ.URL_SELCT_GOODS_COLLECT);
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
