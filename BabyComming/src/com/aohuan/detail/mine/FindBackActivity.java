package com.aohuan.detail.mine;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.aohuan.bean.BaseBean;
import com.aohuan.bean.PhotoGraType;
import com.aohuan.bean.PhotoGraType.PhotoType;
import com.aohuan.utils.FindPwdUtils;
import com.aohuan.utils.http.EFaceType;
import com.aohuan.utils.http.GetDataAsync;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.request.BaseMap;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

@ContentView(R.layout.activity_mine_find_back_pwd)
public class FindBackActivity extends BaseActiviry implements OnItemSelectedListener {
	@ViewInject(R.id.back)
	private ImageButton mImgBtnBack;

	@ViewInject(R.id.et_count)
	private EditText mEtUser;

	@ViewInject(R.id.et_mine_answear)
	private EditText mEtAnswear;
	@ViewInject(R.id.btn_commit)
	private Button mBtnCommit;
	@ViewInject(R.id.mSpinner)
	private Spinner mSpinner;

	private ArrayAdapter<CharSequence> mSpinnerAdapter;

	private List<PhotoType> mList;
	private List<CharSequence> spinnerList;
	private String question_id;
	
	@SuppressLint("HandlerLeak")
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (msg.what==4) {
				if (msg.obj!=null && msg.obj instanceof BaseBean) {
					BaseBean bean=(BaseBean) msg.obj;
					if (bean.getCode()==0) {
						Toast.makeText(FindBackActivity.this,
								bean.getMessage(), Toast.LENGTH_SHORT).show();
						Intent intent=new Intent(FindBackActivity.this, FindPwdActivity.class);
						intent.putExtra("username", username);
						startActivity(intent);
						finish();
					}else if (bean.getCode()==1) {
						Toast.makeText(FindBackActivity.this,
								bean.getMessage(), Toast.LENGTH_SHORT).show();
					}
				}else if (msg.obj==null) {
					Toast.makeText(FindBackActivity.this,
							"网络不给力", Toast.LENGTH_SHORT).show();
				}
			}
		}
	};

	private String username;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		spinnerList=new ArrayList<CharSequence>();
		GetDataAsync gs = new GetDataAsync(this, new IUpdateUI() {

			@Override
			public void updata(Object allData) {
				// TODO Auto-generated method stub
				if (allData != null && allData instanceof PhotoGraType) {
					PhotoGraType bean = (PhotoGraType) allData;
					if (bean.getCode() == 0) {
						mList = bean.getList();
						for (int i = 0; i < mList.size(); i++) {
							spinnerList.add(mList.get(i).getName());
							// map.put(mList.get(i).getName(),
							// mList.get(i).getId());
						}
						mSpinnerAdapter = new ArrayAdapter<CharSequence>(
								FindBackActivity.this,
								android.R.layout.simple_spinner_dropdown_item,
								spinnerList);
						mSpinner.setPrompt("选择你喜欢的问题:");
						mSpinner.setAdapter(mSpinnerAdapter);
					}
				}else if (allData ==null) {
					Toast.makeText(FindBackActivity.this, "网络不给力",
							Toast.LENGTH_SHORT).show();
				}
			}
		}, false, new BaseMap());

		gs.execute(EFaceType.URL_PWD_QUESTION);
		mSpinner.setOnItemSelectedListener(this);
	}

	@OnClick({ R.id.back, R.id.btn_commit })
	public void onRlClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.btn_commit:
			commitFindBack();
			break;
		default:
			break;
		}
	}

	private void commitFindBack() {
		if (!showToastEditTextNotNull(mEtUser, "用户名")
				&& !showToastEditTextNotNull(mEtAnswear, "答案")) {
			username = mEtUser.getText().toString();
			String result=mEtAnswear.getText().toString();
			FindPwdUtils.findPwd(FindBackActivity.this, username, result, question_id, handler);
		}
	}

	private boolean showToastEditTextNotNull(EditText editText, String name) {
		if (editText == null || editText.getText().toString().trim().equals("")
				|| name == null) {
			Toast.makeText(this, name + " 不能为空！", Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		question_id=mList.get(position).getId();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}
