/*
 * Copyright (C) 2011 Patrik �kerfeldt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aohuan.utils.lunbo;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aohuan.babycomming.R;
import com.aohuan.bean.GuangGaoLunboBean.GuangGaoLunbo;
import com.aohuan.detail.first.activity.TaoCanDetailActivity;
import com.aohuan.utils.adapter.ImageLoad;
import com.aohuan.utils.viewpager_bili.SetWidthAndeHeightUtils;

public class ImageAdapter extends BaseAdapter {

	private Activity mContext;
	private LayoutInflater mInflater;
	private String[] mStr;
	private List<GuangGaoLunbo> mList;
	private static final int[] ids = {R.drawable.lunbo_default };

	public ImageAdapter(Activity context, List<GuangGaoLunbo> list) {
		mContext = context;
		mList = list;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;   //返回很大的值使得getView中的position不断增大来实现循环
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	
	ViewHolder mViewHolder;
	ImageView image;
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			mViewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.image_item, null);
			mViewHolder.img = (ImageView) convertView.findViewById(R.id.imgView);
			convertView.setTag(mViewHolder);
		}else{
			mViewHolder = (ViewHolder) convertView.getTag();  
		}
//		ImageView image =  (ImageView) convertView.findViewById(R.id.imgView);
		image = mViewHolder.img;
		SetWidthAndeHeightUtils.setImageView(mContext, image, 32, 17);
//		image.setImageResource(ids[position%ids.length]);
		if(mList == null && mList.isEmpty()){
			image.setImageResource(ids[position%ids.length]);
		}else{
			ImageLoad.loadImage(image, mList.get(position%mList.size()).getImages(), mContext);
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
//					Toast.makeText(mContext, "点击了"+mList.get(position%mList.size()).getTeam_id(), Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(mContext,TaoCanDetailActivity.class);
					intent.putExtra("team_id", mList.get(position%mList.size()).getTeam_id());
					mContext.startActivity(intent);
				}
			});
		}
		
		return convertView;
	}
	
	public final class ViewHolder {  
		public ImageView img;  
	}  

}
