package com.aohuan.detail.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.aohuan.bean.UserBean;
import com.aohuan.utils.common.LogAh;
import com.aohuan.utils.http.EFaceTypeLH;
import com.aohuan.utils.http.GetDataAsyncLH;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.request.RequestBaseMapLH;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

@ContentView(R.layout.activity_mine_register)
public class RegisterActivity extends BaseActiviry {

	@ViewInject(R.id.back)
	private ImageButton mImgBtnBack;
	@ViewInject(R.id.tv_register)
	private TextView mTvRegister;

	@ViewInject(R.id.et_quest)
	private EditText mEtUser;
	@ViewInject(R.id.et_answer)
	private EditText mEtAnswear;
	@ViewInject(R.id.et_answer_again)
	private EditText mEtAnswearAgain;
	@ViewInject(R.id.btn_commit)
	private Button mBtnCommit;
	@ViewInject(R.id.checkBox)
	private CheckBox mCheckBox;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

	}

	@OnClick({ R.id.back, R.id.tv_register, R.id.btn_commit })
	public void onRlClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.tv_register:
//			Toast.makeText(this, "aa", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(RegisterActivity.this, RegistreXieyiActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_commit:
			commitRegister();
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 */
	private void commitRegister() {
		if (showToastEditTextNotNull(mEtUser, "用户名")
				|| showToastEditTextNotNull(mEtAnswear, "密码")
				|| showToastEditTextNotNull(mEtAnswearAgain, "再次输入的密码")) {
			return;
		}
		if (!mCheckBox.isChecked()) {
			Toast.makeText(this, "请同意对应的条款", Toast.LENGTH_SHORT).show();
			return;
		}
		if(!mEtAnswear.getText().toString().equals(mEtAnswearAgain.getText().toString())){
			Toast.makeText(this, "2次密码不一样", Toast.LENGTH_SHORT).show();
			return;
		}
		httpRegister();
	}

	private void httpRegister() {
		GetDataAsyncLH gs = new GetDataAsyncLH(this, new IUpdateUI() {
			@Override
			public void updata(Object allData) {
				if(allData != null && allData instanceof UserBean){
					LogAh.i("reg", allData.toString());
					UserBean user = (UserBean) allData;
					Toast.makeText(getApplication(), user.getMessage(), Toast.LENGTH_SHORT).show();
					if(user.getCode().equals("0")){
						Toast.makeText(getApplication(), "注册成功，请登录", Toast.LENGTH_SHORT).show();
						finish();
					}else{
					}
				}
			}
		}, false, RequestBaseMapLH.getRegister(mEtUser.getText().toString(), mEtAnswear.getText().toString()));
		gs.execute(EFaceTypeLH.URL_MINE_REGISTER);
	}

	
	private boolean showToastEditTextNotNull(EditText editText, String name) {
		if (editText == null || editText.getText().toString().trim().equals("")
				|| name == null) {
			Toast.makeText(this, name + " 不能为空！", Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}

}
