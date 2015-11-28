package com.aohuan.detail.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.aohuan.babycomming.R;
import com.aohuan.detail.order.OrderDetailEvaluateActivity;
import com.aohuan.detail.order.OrderDetailEvaluateDoneActivity;
import com.aohuan.detail.order.OrderDetailNoEvaluateActivity;
import com.aohuan.detail.order.OrderDetailNoPayActivity;
import com.aohuan.utils.adapter.ViewHolder;

public class OrderPageAdapter extends BaseAdapter {
	private int num;
	private Context mContext;

	public OrderPageAdapter(Context mContext, int num) {
		this.num = num;
		this.mContext = mContext;
	}

	public void refleshNum(int num) {
		this.num = num;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return num;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = null;
//		final ViewHolder viewHolder01 = ViewHolder.get(mContext,
//				convertView, parent, R.layout.item_order_1_no_pay,
//				position);
//		final ViewHolder viewHolder02 = ViewHolder.get(mContext,
//				convertView, parent, R.layout.item_order_2_aready_pay,
//				position);
//		final ViewHolder viewHolder03 = ViewHolder.get(mContext,
//				convertView, parent, R.layout.item_order_3_aready_consume,
//				position);
//		final ViewHolder viewHolder04 = ViewHolder.get(mContext,
//				convertView, parent, R.layout.item_order_4_aready_evaluate,
//				position);
		if (position % 4 == 1) {
			final ViewHolder viewHolder01 = ViewHolder.get(mContext,
					convertView, parent, R.layout.item_order_1_no_pay,
					position);
			viewHolder01.setText(R.id.photo_goods_title, "test"
					+ position+"wfk");
			viewHolder01.getConvertView().setOnClickListener(new MyClick(position, 1+"wfk"));
			
			return viewHolder01.getConvertView();
		} else if (position % 4 == 2) {
			final ViewHolder viewHolder02 = ViewHolder.get(mContext,
					convertView, parent, R.layout.item_order_2_aready_pay,
					position);
			viewHolder02.setText(R.id.photo_goods_title, "test"
					+ position+"yfk");
			viewHolder02.getConvertView().setOnClickListener(new MyClick(position, 2+"yfk"));
			return viewHolder02.getConvertView();
		} else if (position % 4 == 3) {
			final ViewHolder viewHolder03 = ViewHolder.get(mContext,
					convertView, parent, R.layout.item_order_3_aready_consume,
					position);
			viewHolder03.setText(R.id.photo_goods_title, "test"
					+ position+"yxf");
			viewHolder03.getConvertView().setOnClickListener(new MyClick(position, 3+"yxf"));
			return viewHolder03.getConvertView();
		} else if (position % 4 == 0) {
			final ViewHolder viewHolder04 = ViewHolder.get(mContext,
					convertView, parent, R.layout.item_order_4_aready_evaluate,
					position);
			viewHolder04.setText(R.id.photo_goods_title, "test"
					+ position+"ypj");
			viewHolder04.getConvertView().setOnClickListener(new MyClick(position, 4+"ypj"));
			return viewHolder04.getConvertView();
		}else{
			final ViewHolder viewHolder04 = ViewHolder.get(mContext,
					convertView, parent, R.layout.item_order_4_aready_evaluate,
					position);
			viewHolder04.setText(R.id.photo_goods_title, "test"
					+ position+"ypj");
			viewHolder04.getConvertView().setOnClickListener(new MyClick(position, 4+"ypj"));
			return viewHolder04.getConvertView();
		}
	}
	
	class MyClick implements View.OnClickListener{
		int index = 0;
		String what = 0+"";
		public MyClick(int index, String what) {
			this.index = index;
			this.what = what;
		}
		@Override
		public void onClick(View v) {
			Toast.makeText(mContext, index + "  \t  "+what, Toast.LENGTH_SHORT).show();
			if(index%4==0){
				mContext.startActivity(new Intent(mContext, OrderDetailEvaluateActivity.class));
			}else if(index%4 == 1){
				mContext.startActivity(new Intent(mContext, OrderDetailNoPayActivity.class));
			}else if(index%4 == 2){
				mContext.startActivity(new Intent(mContext, OrderDetailNoEvaluateActivity.class));
			}else if(index%4 == 3){
				mContext.startActivity(new Intent(mContext, OrderDetailEvaluateDoneActivity.class));
			}
		}
		
	}

}
