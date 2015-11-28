package com.aohuan.detail.mine;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.aohuan.utils.adapter.CommonAdapter;
import com.aohuan.utils.adapter.ViewHolder;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_my_collect)
public class MyCollectGoodsActivity extends BaseActiviry {

	@ViewInject(R.id.goods_grid)
	private GridView mGridView;
	@ViewInject(R.id.back)
	private ImageButton mBackButton;
	@ViewInject(R.id.title)
	private TextView mTextView;
	
	private List<String> mList;
	
	private CommonAdapter<String> mAdapter;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		
		mTextView.setText("我的商品收藏");
		mBackButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		mList=new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			mList.add(""+i);
		}
		
		mAdapter=new CommonAdapter<String>(this, mList, R.layout.item_mine_shou_cang) {

			@Override
			public void convert(ViewHolder helper, String item) {
				// TODO Auto-generated method stub
				
			}
		};
		mGridView.setAdapter(mAdapter);
	}
}
