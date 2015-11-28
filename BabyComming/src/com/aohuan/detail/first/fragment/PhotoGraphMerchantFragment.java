package com.aohuan.detail.first.fragment;

import java.util.ArrayList;
import java.util.List;

import com.aohuan.babycomming.R;
import com.aohuan.bean.PhotoGraStoreBean;
import com.aohuan.bean.PhotoGraType;
import com.aohuan.bean.PhotoGraStoreBean.StoreList;
import com.aohuan.bean.PhotoGraType.PhotoType;
import com.aohuan.detail.first.activity.StoreIntroActivity;
import com.aohuan.utils.adapter.CommonAdapter;
import com.aohuan.utils.adapter.ViewHolder;
import com.aohuan.utils.http.EFaceType;
import com.aohuan.utils.http.GetDataAsync;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.http.UrlConstants;
import com.aohuan.utils.request.BaseMap;
import com.aohuan.utils.request.RequestBaseMap;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * 摄影商家
 * @author Administrator
 *
 */
public class PhotoGraphMerchantFragment extends Fragment implements OnItemClickListener, OnRefreshListener<ListView> {

	@ViewInject(R.id.store_list)
	private  PullToRefreshListView Mpull;
	@ViewInject(R.id.manyi_digree)
	private  ToggleButton mTbDengji;
	@ViewInject(R.id.area)
	private  ToggleButton mTbQuyu;
	@ViewInject(R.id.price_order)
	private  ToggleButton mTbPrice;
	
	@ViewInject(R.id.lv_dengji)
	private ListView mLvDengji;
	@ViewInject(R.id.lv_quyu)
	private ListView mLvQuyu;
	@ViewInject(R.id.lv_price)
	private ListView mLvPrice;
	@ViewInject(R.id.view2)
	private View mView;
	
	private ListView mListView;
	
	/** 满意度适配器 */
	private CommonAdapter<String> mDengjiAdapter;
	/** 区域适配器 */
	private CommonAdapter<PhotoType> mQuyuAdapter;
	/** 价格适配器 */
	private CommonAdapter<String> mPriceAdapter;
	
	/** 判断是否已经加载过数据 */
	private boolean quyuBoo = true;
	
	/**满意度id**/
	private String agreeID="";
	/**区域id**/
	private String quyuID="";
	/**价格id**/
	private String PriceID="";
	
		
	private List<StoreList> mList;
	
	private CommonAdapter<StoreList> mAdapter;
	
	private int type;
	
	private int page=1; 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Bundle bundle = getArguments();
		type=bundle.getInt("type", 0);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view;
		view = inflater.inflate(R.layout.fragment_photo_store, container, false);
		ViewUtils.inject(this, view);
		
		mListView=Mpull.getRefreshableView();
		Mpull.setMode(Mode.PULL_FROM_END);
		Mpull.setOnRefreshListener(this);
		
//		Mpull.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {
//
//			@Override
//			public void onLastItemVisible() {
//				// TODO Auto-generated method stub
//				Mpull.getLoadingLayoutProxy().setRefreshingLabel(
//						"加载更多...");
//			}
//		});
		
		mList=new ArrayList<StoreList>();
			
		loadStoreList("","","");
		
		mListView.setOnItemClickListener(this);
		
		toggleButton();
		initDengjiData();		
		initPriceData();
		
		return view;
	}

	/**
	 * 商家列表页
	 */
	private void loadStoreList(String price,String city_id,String pingfen) {
		GetDataAsync gs=new GetDataAsync(getActivity(), new IUpdateUI() {
			
			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				if (allData !=null && allData instanceof PhotoGraStoreBean) {
					PhotoGraStoreBean bean=(PhotoGraStoreBean) allData;
					if (bean.getCode()==0) {
						mList.addAll(bean.getList());
							mAdapter=new CommonAdapter<StoreList>(getActivity(), mList, R.layout.item_photo_store) {

								@Override
								public void convert(ViewHolder helper, StoreList item) {
									helper.setText(R.id.store_title, item.getTitle());
									helper.setText(R.id.store_person_num, item.getCount());
									helper.setText(R.id.price, item.getPrice());
									helper.setImageByUrl(R.id.store_image, item.getImage(), getActivity());
									RatingBar bar=helper.getView(R.id.store_bar);
									bar.setRating(Float.parseFloat(item.getPingfen()));
								}
							};						
						mListView.setAdapter(mAdapter);
						Mpull.onRefreshComplete();
					}
				}
			}
		}, false, RequestBaseMap.getPhotoStore(price, city_id, pingfen,page+""));
		gs.execute(UrlConstants.getStoreEFaceType(type));
	}
	

	@OnClick({ R.id.manyi_digree, R.id.area, R.id.price_order })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.manyi_digree:
			break;

		case R.id.area:
			break;

		case R.id.price_order:
			
			break;
		}
	}
	/**
	 * 月嫂等级、区域、价格排序的选择事件
	 */
	private void toggleButton(){
		mView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mView.setVisibility(View.GONE);
				mLvDengji.setVisibility(View.GONE);
				mLvQuyu.setVisibility(View.GONE);
				mLvPrice.setVisibility(View.GONE);
				mTbDengji.setChecked(false);
				mTbQuyu.setChecked(false);
				mTbPrice.setChecked(false);
			}
		});
		/** 满意度 */
		mTbDengji.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked == true){
					mView.setVisibility(View.VISIBLE);
					mLvDengji.setVisibility(View.VISIBLE);
					
					mLvQuyu.setVisibility(View.GONE);
					mLvPrice.setVisibility(View.GONE);
					mTbQuyu.setChecked(false);
					mTbPrice.setChecked(false);
				}else if(isChecked == false){
					if(mTbQuyu.isChecked() == true || mTbPrice.isChecked() == true){
						mView.setVisibility(View.VISIBLE);
						return;
					}
					mView.setVisibility(View.GONE);
					mLvDengji.setVisibility(View.GONE);
				}
			}
		});
		mTbQuyu.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked == true){
					if (quyuBoo) {
						GetDataAsync gs=new GetDataAsync(getActivity(), new IUpdateUI() {
							
							@Override
							public void updata(Object allData) {
								// TODO Auto-generated method stub
								if (allData !=null && allData instanceof PhotoGraType) {
									PhotoGraType bean=(PhotoGraType) allData;
									
									PhotoType type=bean.new PhotoType();
									type.setId("0");
									type.setName("全部城区");
									bean.getList().add(0, type);
									initQuyuData(bean.getList());
								}else if (allData ==null) {
									Toast.makeText(getActivity(), "网络不给力", Toast.LENGTH_SHORT).show();
									return;
								}
							}
						}, false, new BaseMap());
						gs.execute(EFaceType.URL_QUYU_CITY);
						quyuBoo=false;
					}
					mView.setVisibility(View.VISIBLE);
					mLvQuyu.setVisibility(View.VISIBLE);
					
					mLvDengji.setVisibility(View.GONE);
					mLvPrice.setVisibility(View.GONE);
					mTbDengji.setChecked(false);
					mTbPrice.setChecked(false);
				}else if(isChecked == false){
					if(mTbDengji.isChecked() == true || mTbPrice.isChecked() == true){
						mView.setVisibility(View.VISIBLE);
						return;
					}
					mView.setVisibility(View.GONE);
					mLvQuyu.setVisibility(View.GONE);
					
				}
			}
		});
		mTbPrice.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked == true){
					mView.setVisibility(View.VISIBLE);
					mLvPrice.setVisibility(View.VISIBLE);
					
					mLvQuyu.setVisibility(View.GONE);
					mLvDengji.setVisibility(View.GONE);
					mTbQuyu.setChecked(false);
					mTbDengji.setChecked(false);
				}else if(isChecked == false){
					if(mTbQuyu.isChecked() == true || mTbDengji.isChecked() == true){
						mView.setVisibility(View.VISIBLE);
						return;
					}
					mView.setVisibility(View.GONE);
					mLvPrice.setVisibility(View.GONE);
				}
			}
		});
	}
	/**
	 * 满意度的数据
	 */
	private void initDengjiData(){
		final List<String> list = new ArrayList<String>();
		list.add("全部");
		list.add("升序");
		list.add("降序");
		mDengjiAdapter = new CommonAdapter<String>(getActivity(),list,
				R.layout.popupwindow_fangyuan_item_layout) {

			@Override
			public void convert(ViewHolder helper, String item) {
				// TODO Auto-generated method stub
				helper.setText(R.id.tv_list_item_city, item);
			}
		};
		mLvDengji.setAdapter(mDengjiAdapter);
		mLvDengji.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(list.get(arg2).equals("升序")){
					agreeID = "2";
				}else if(list.get(arg2).equals("降序")){
					agreeID = "1";
				}else if (list.get(arg2).equals("全部")) {
					agreeID = "0";
				}
				page=1;
				mList.clear();
				loadStoreList(PriceID, quyuID, agreeID);
				mTbDengji.setTextOff(list.get(arg2));
				mTbDengji.setTextOn(list.get(arg2));
				mTbDengji.setChecked(false);
			}
		});
	}
	/**
	 * 区域的数据
	 */
	private void initQuyuData(final List<PhotoType> list){
		
		mQuyuAdapter = new CommonAdapter<PhotoType>(getActivity(),list,
				R.layout.popupwindow_fangyuan_item_layout) {
			
			@Override
			public void convert(ViewHolder helper, PhotoType item) {
				// TODO Auto-generated method stub
				helper.setText(R.id.tv_list_item_city, item.getName());
			}
		};
		mLvQuyu.setAdapter(mQuyuAdapter);
		mLvQuyu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				quyuID=list.get(arg2).getId().toString();
				page=1;
				mList.clear();
				loadStoreList(PriceID, quyuID, agreeID);
				mTbQuyu.setTextOff(list.get(arg2).getName());
				mTbQuyu.setTextOn(list.get(arg2).getName());
				mTbQuyu.setChecked(false);
			}
		});
	}
	/**
	 * 价格排序的数据
	 */
	private void initPriceData(){
		final List<String> list = new ArrayList<String>();
		list.add("全部");
		list.add("升序");
		list.add("降序");
		mPriceAdapter = new CommonAdapter<String>(getActivity(),list,
				R.layout.popupwindow_fangyuan_item_layout) {
			
			@Override
			public void convert(ViewHolder helper, String item) {
				// TODO Auto-generated method stub
				helper.setText(R.id.tv_list_item_city, item);
			}
		};
		mLvPrice.setAdapter(mPriceAdapter);
		mLvPrice.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(list.get(arg2).equals("升序")){
					PriceID = "2";
				}else if(list.get(arg2).equals("降序")){
					PriceID = "1";
				}else if (list.get(arg2).equals("全部")) {
					PriceID = "0";
				}
				loadStoreList(PriceID, quyuID, agreeID);
				page=1;
				mList.clear();
				mTbPrice.setTextOff(list.get(arg2));
				mTbPrice.setTextOn(list.get(arg2));
				mTbPrice.setChecked(false);
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(getActivity(), StoreIntroActivity.class);
		intent.putExtra("id", mList.get(arg2-1).getId());
		startActivity(intent);
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		 String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),  
                 DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);  
         refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);  
		
         page++;
         loadStoreList(PriceID, quyuID, agreeID);
	}
//	@Override
//	public void onResume() {
//		// TODO Auto-generated method stub
//		super.onResume();
//		System.out.println("--onResume--"+page);
//	}
//	@Override
//	public void onStop() {
//		// TODO Auto-generated method stub
//		super.onStop();
//		System.out.println("--onStop--");
//		onDetach();
//	}
//	@Override
//	public void onDestroy() {
//		// TODO Auto-generated method stub
//		super.onDestroy();
//	}
//	@Override
//	public void onDestroyView() {
//		// TODO Auto-generated method stub
//		super.onDestroyView();
//		System.out.println("--onDestroyView--");
//	}
//	@Override
//	public void onDetach() {
//		// TODO Auto-generated method stub
//		super.onDetach();
//		System.out.println("--onDetach--");
//	}
}
