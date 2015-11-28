package com.aohuan.detail.mine;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aohuan.babycomming.R;
import com.aohuan.bean.BaseBean;
import com.aohuan.bean.MyYueYueSaoBean;
import com.aohuan.bean.MyYueYueSaoBean.YueSao;
import com.aohuan.detail.first.activity.NurseMaidPaymentActivity;
import com.aohuan.detail.first.activity.PayOrderActivity;
import com.aohuan.detail.first.activity.SubmitOrderActivity;
import com.aohuan.utils.adapter.CommonAdapter;
import com.aohuan.utils.adapter.ViewHolder;
import com.aohuan.utils.http.EFaceType;
import com.aohuan.utils.http.EFaceTypeLH;
import com.aohuan.utils.http.GetDataAsync;
import com.aohuan.utils.http.GetDataAsyncLH;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.propriety.LoginHelper;
import com.aohuan.utils.request.RequestBaseMap;
import com.aohuan.utils.request.RequestBaseMapLH;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class NotPaidFragment extends Fragment {

	@ViewInject(R.id.paid_grid)
	private GridView mGridView;
	@ViewInject(R.id.no_data)
	private LinearLayout mRlNoData;
	
	private CommonAdapter<YueSao> mAdapter;
	private List<YueSao> mList;

	private List<YueSao> mUnPayList;
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = null;
		view = inflater.inflate(R.layout.fragment_not_paid, container, false);
		ViewUtils.inject(this, view);

		mList = new ArrayList<YueSao>();
		mUnPayList = new ArrayList<YueSao>();
//		mPayList = new ArrayList<YueSao>();
		getData();
		return view;
	}

	private void getData() {
		GetDataAsync gs = new GetDataAsync(getActivity(), new IUpdateUI() {
			@Override
			public void updata(Object allData) {
				mUnPayList.clear();
				if (allData != null && allData instanceof MyYueYueSaoBean) {
					MyYueYueSaoBean bean = (MyYueYueSaoBean) allData;
					if (bean.getCode() == 0 && bean.getList()!=null) {
						mList = bean.getList();
						for (int i = 0; i < mList.size(); i++) {
							if (mList.get(i).getState().equals("unpay")) {
								mUnPayList.add(mList.get(i));
							} 
						}
						System.out.println(">>>unpayList:"+mUnPayList.size());
					}
				}
				doCommit();
			}
		}, false, RequestBaseMap.getCollect(LoginHelper.getUser(getActivity()).getId()));
		gs.execute(EFaceType.URL_MY_YUE_YUESAO);
	}

	private void initAdapter() {
		mAdapter = new CommonAdapter<YueSao>(getActivity(), mUnPayList,
				R.layout.item_has_paid_yuesao) {

			@Override
			public void convert(ViewHolder helper, final YueSao item) {
				helper.setText(R.id.yuesao_name, item.getName());
				helper.setText(R.id.yuesao_dengji, item.getGrade());
				helper.setText(R.id.yuesao_age, item.getAge());
				helper.setText(R.id.yuesao_quyu, item.getLocation_city());
				helper.setText(R.id.yuesao_xueli, item.getEducation());
				helper.setText(R.id.yuesao_price, item.getPrice() + "元/月");
				helper.setImageByUrl(R.id.yuesao_iv, item.getImage(),
						getActivity());
				RatingBar bar = helper.getView(R.id.ratingBar);
				bar.setRating(Float.parseFloat(item.getPingfen()));
				helper.getView(R.id.delete).setOnClickListener(
						new OnClickListener() {
							@Override
							public void onClick(View v) {
								if(LoginHelper.getUser(getActivity())==null){
									Toast.makeText(getActivity(), "没有登录", Toast.LENGTH_SHORT).show();
									return;
								}
								showDelete(item.getId());
							}
						});
			}
		};
		mGridView.setAdapter(mAdapter);
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), NurseMaidPaymentActivity.class);
				intent.putExtra("pay_id", mUnPayList.get(arg2).getPay_id());
				startActivity(intent);
			}
		});
	}
	
	
	private void doCommit(){
		if(mUnPayList!=null && mUnPayList.size()>0){
			System.out.println(">>>unpayListNOOO:"+mUnPayList.size());
			mRlNoData.setVisibility(View.GONE);
			mGridView.setVisibility(View.VISIBLE);
			initAdapter();
		}else{
			System.out.println(">>>unpayListVVVV:"+mUnPayList.size());
			mRlNoData.setVisibility(View.VISIBLE);
			mGridView.setVisibility(View.GONE);
		}
	}
	
	
	
	/**
	 * 删除的Dialog
	 */
	public void showDelete(final String id){
		final Dialog dialog = new Dialog(getActivity());
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_delete_order_layout, null);
		Button cencal = (Button) view.findViewById(R.id.btn_cencal);
		Button sure = (Button) view.findViewById(R.id.btn_sure);
		TextView tv = (TextView) view.findViewById(R.id.tv_delete);
		tv.setText("确认删除预约的月嫂吗？");
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
				deleteDataCollect(id);
				dialog.dismiss();
				
			}
		});
	}
	
	
	public void deleteDataCollect(String id){
		GetDataAsyncLH gs = new GetDataAsyncLH(
				getActivity(), new IUpdateUI() {
					@Override
					public void updata(Object allData) {
						// TODO Auto-generated method
						// stub
						if (allData != null
								&& allData instanceof BaseBean) {
							Toast.makeText(
									getActivity(),
									((BaseBean) allData)
											.getMessage(),
									Toast.LENGTH_SHORT)
									.show();
						}else{
							
						}
						getData();
					}
				}, false, RequestBaseMapLH.getId(id,
						LoginHelper.getUser(getActivity()).getId()));
		gs.execute(EFaceTypeLH.URL_MINE_DELETE_YUESAO);
	}
	
}
