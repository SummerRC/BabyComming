package com.aohuan.detail.first.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.aohuan.bean.CommentBean;
import com.aohuan.bean.CommentBean.Content;
import com.aohuan.utils.adapter.CommonAdapter;
import com.aohuan.utils.adapter.ViewHolder;
import com.aohuan.utils.http.EFaceType;
import com.aohuan.utils.http.GetDataAsync;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.request.RequestBaseMap;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 评论列表没写
 * 
 * @author Administrator
 * 
 */
@ContentView(R.layout.activity_customer_comment)
public class CommentActivity extends BaseActiviry implements
		OnRefreshListener<ListView> {
	@ViewInject(R.id.comment_list)
	private PullToRefreshListView mPull;
	@ViewInject(R.id.back)
	private ImageButton mBackButton;
	@ViewInject(R.id.title)
	private TextView mTextView;

	private ListView mListView;
	private List<Content> mList;

	private CommonAdapter<Content> mAdapter;

	private String parent_id;
	private TextView titleView;
	private TextView countView;
	private RatingBar bar;
	private TextView scoreView;

	private int page = 1;
	private View headerView;
	private boolean hasHeader=false;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		mTextView.setText("顾客评论");
		parent_id = getIntent().getStringExtra("id");
		System.out.println("-----"+parent_id);
		mListView = mPull.getRefreshableView();
		mPull.setMode(Mode.PULL_FROM_END);
		mPull.setOnRefreshListener(this);

		headerView = LayoutInflater.from(this).inflate(R.layout.header,
				mListView, false);

		titleView = (TextView) headerView.findViewById(R.id.store_name);
		countView = (TextView) headerView.findViewById(R.id.comment_num);
		bar = (RatingBar) headerView.findViewById(R.id.store_bar);
		scoreView = (TextView) headerView.findViewById(R.id.store_score);

		mList = new ArrayList<Content>();

		loadData();

		mBackButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	private void loadData() {
		GetDataAsync gs = new GetDataAsync(this, new IUpdateUI() {

			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				if (allData != null && allData instanceof CommentBean) {
					CommentBean bean = (CommentBean) allData;
					titleView.setText(bean.getTitle());
					scoreView.setText(bean.getPingfen() + "分");
					countView.setText("总评价数   " + bean.getCount());
					bar.setRating(Float.parseFloat(bean.getPingfen()));
					mList.addAll(bean.getList());

					setAdapter();
				} else {
					Toast.makeText(CommentActivity.this, "网络异常",
							Toast.LENGTH_SHORT).show();
				}
			}
		}, false, RequestBaseMap.getStoreDetail(parent_id, page + ""));
		gs.execute(EFaceType.URL_CUSTOMER_COMMENT);
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		String label = DateUtils.formatDateTime(this,
				System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
						| DateUtils.FORMAT_SHOW_DATE
						| DateUtils.FORMAT_ABBREV_ALL);
		refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
		page++;
		loadData();
	}

	private void setAdapter() {
		mAdapter = new CommonAdapter<Content>(CommentActivity.this, mList,
				R.layout.item_commemt) {

			@Override
			public void convert(ViewHolder helper, Content item) {
				// TODO Auto-generated method stub
				helper.setText(R.id.username, item.getUsername());
				helper.setText(R.id.content, item.getDetails());
				helper.setText(R.id.date, item.getCreate_time());
			}
		};
		if (!hasHeader) {
			mListView.addHeaderView(headerView);
			hasHeader=true;
		}	
		mListView.setAdapter(mAdapter);
		mPull.onRefreshComplete();
	}
}
