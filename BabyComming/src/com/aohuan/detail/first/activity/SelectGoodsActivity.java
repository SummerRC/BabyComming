package com.aohuan.detail.first.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.aohuan.bean.GoodsListBean;
import com.aohuan.bean.GoodsListBean.GoodsList;
import com.aohuan.bean.YuesaoListBean;
import com.aohuan.bean.YuesaoListBean.YuesaoList;
import com.aohuan.utils.adapter.CommonAdapter;
import com.aohuan.utils.adapter.ViewHolder;
import com.aohuan.utils.common.LogAh;
import com.aohuan.utils.http.EFaceTypeZGQ;
import com.aohuan.utils.http.GetDataAsyncZGQ;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.request.RequestBaseMapZGQ;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
@ContentView(R.layout.activity_select_goods_layout)
public class SelectGoodsActivity extends BaseActiviry implements OnRefreshListener<GridView>{
	@ViewInject(R.id.back)
	private ImageButton mBackButton;
	@ViewInject(R.id.title)
	private TextView mTextTitle;
	@ViewInject(R.id.gv_goods_list)
	private PullToRefreshGridView mPullGridView;
	private GridView mGridView;
	private int page = 1;
	private List<GoodsList> mList;
	
	/** 商品适配器 */
	private CommonAdapter<GoodsList> mAdapter;
	/** 商品列表实体类 */
	private GoodsListBean mGoodsListBean;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		mList = new ArrayList<GoodsList>();
		mGridView = mPullGridView.getRefreshableView();
		mPullGridView.setMode(Mode.PULL_FROM_END);
		mPullGridView.setOnRefreshListener(this);
		initView();
		initDataGoodsList();
	}
	private void initView(){
		mTextTitle.setText("精选商品");
	}
	
	@OnClick({R.id.back})
	public void onClick(View view){
		switch (view.getId()) {
		case R.id.back:
			finish();
			break;
		default:
			break;
		}
	}
	
	private void initGoodsData(final List<GoodsList> list){
		mAdapter = new CommonAdapter<GoodsList>(this, list,
				R.layout.item_goods_gridview_layout) {
			
			@Override
			public void convert(ViewHolder helper, GoodsList item) {
				// TODO Auto-generated method stub
				helper.setImageByUrl(R.id.goods_iv, item.getImage(), SelectGoodsActivity.this);
					helper.setText(R.id.goods_tv_title, item.getTitle());
					helper.setText(R.id.goods_tv_content, item.getProduct());
				helper.setText(R.id.goods_tv_price, "￥"+item.getTeam_price());
			}
		};
		mGridView.setAdapter(mAdapter);
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SelectGoodsActivity.this, GoodsDetailActivity.class);
				intent.putExtra("goodsid", list.get(arg2).getId());
				startActivity(intent);
			}
		});
	}
	
	/**
	 * 商品列表网络请求
	 */
	private void initDataGoodsList(){
		IUpdateUI ui = new IUpdateUI() {

			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				if(allData == null){
					Toast.makeText(SelectGoodsActivity.this, "网络不给力", Toast.LENGTH_SHORT).show();
					return ;
				}
				LogAh.e("商品列表：："+allData);
				mGoodsListBean =(GoodsListBean) allData;
				if(mGoodsListBean.getCode().equals("0") && 
						mGoodsListBean.getList() != null && 
						!mGoodsListBean.getList().isEmpty()){
					mList.addAll(mGoodsListBean.getList());
					if(mAdapter == null){
						initGoodsData(mList);
						mPullGridView.onRefreshComplete();
					}else{
						mAdapter.notifyDataSetChanged();
						mPullGridView.onRefreshComplete();
					}
				}else{
					initGoodsData(new ArrayList<GoodsList>());
					if(page == 1){
						initGoodsData(new ArrayList<GoodsList>());
					}else{
						initGoodsData(mList);
						
					}
					mAdapter.notifyDataSetChanged();
					mPullGridView.onRefreshComplete();
				}
			}
		};

		GetDataAsyncZGQ asyncZGQ = new GetDataAsyncZGQ(this, ui,
				true, RequestBaseMapZGQ.getGoodsList(page+""));
		asyncZGQ.execute(EFaceTypeZGQ.URL_SELCT_GOODS);
	}
	@Override
	public void onRefresh(PullToRefreshBase<GridView> refreshView) {
		// TODO Auto-generated method stub
		page++;
		initDataGoodsList();
	}

}
