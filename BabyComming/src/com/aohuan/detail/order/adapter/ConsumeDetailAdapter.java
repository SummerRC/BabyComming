package com.aohuan.detail.order.adapter;

import java.util.ArrayList;
import java.util.List;

import com.aohuan.babycomming.R;
import com.aohuan.bean.OrderDetailsBean.OrderDetails;
import com.aohuan.utils.adapter.ViewHolder;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

public class ConsumeDetailAdapter extends BaseAdapter {
	private Context mContext;
	private List<OrderDetails> mlist;
	
	public ConsumeDetailAdapter(Context mContext, List<OrderDetails> mDetailBeanList) {
		super();
		this.mContext = mContext;
		this.mlist = mDetailBeanList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mlist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder = ViewHolder.get(mContext,
				convertView, parent, R.layout.item_order_consume_num,
				position);
		viewHolder.setText(R.id.tv_consume_num, mlist.get(position).getId());
		viewHolder.setText(R.id.tv_consume_type, mlist.get(position).getConsume());
		viewHolder.getView(R.id.tv_consume_mima).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(mContext, mlist.get(position).getSecret(), Toast.LENGTH_LONG).show();
			}
		});
		return viewHolder.getConvertView();
	}
	
	
	class DetailBean{
		String mType;
		String mString;
	}

}
