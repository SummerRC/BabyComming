package com.aohuan.detail.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.aohuan.bean.UserBean;
import com.aohuan.bean.UserBean.User;
import com.aohuan.utils.common.LogAh;
import com.aohuan.utils.http.EFaceTypeLH;
import com.aohuan.utils.http.GetDataAsyncLH;
import com.aohuan.utils.http.IUpdateUI;
import com.aohuan.utils.propriety.LoginHelper;
import com.aohuan.utils.request.RequestBaseMapLH;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

@ContentView(R.layout.activity_mine_login)
public class LoginActivity extends BaseActiviry {

	@ViewInject(R.id.back)
	private ImageButton mImgBtnBack;
	@ViewInject(R.id.title)
	private TextView mView;
	@ViewInject(R.id.register)
	private Button mBtnRegister;
	@ViewInject(R.id.login)
	private Button mBtnLogin;
	@ViewInject(R.id.forget_pwd)
	private TextView mTvForgetPwd;
	
	@ViewInject(R.id.et_username)
	private EditText mEtUsername;
	@ViewInject(R.id.et_password)
	private EditText mEtPassword;
	
	/** mIntent */
	private Intent mIntent;
	
	public static final int RESULT = 1;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		mView.setText("登录");
	}

	@OnClick({ R.id.back, R.id.register, R.id.forget_pwd, R.id.login })
	public void onRlClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.login:
			commitLogin();
			break;
		case R.id.register:
			mIntent = new Intent(this, RegisterActivity.class);
			startActivity(mIntent);
			break;
		case R.id.forget_pwd:
			mIntent = new Intent(this, FindBackActivity.class);
			startActivity(mIntent);
			break;
		default:
			break;
		}
	}
	
	
	private void commitLogin() {
		if(!showToastEditTextNotNull(mEtUsername, "用户名") && !showToastEditTextNotNull(mEtPassword, "密码")){
//			Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
			httpLogin();
		}
	}
	
	private void httpLogin(){
		GetDataAsyncLH gs = new GetDataAsyncLH(this, new IUpdateUI() {
			@Override
			public void updata(Object allData) {
				if(allData != null && allData instanceof UserBean){
					LogAh.i("reg", allData.toString());
					UserBean user = (UserBean) allData;
					
					Toast.makeText(getApplication(), user.getMessage(), Toast.LENGTH_SHORT).show();
					if(user.getCode().equals("0")){
						setResult(RESULT);
						User userbean = user.getUser();
						LoginHelper.saveLogin(getApplicationContext(), userbean.getId(), userbean.getUsername(), userbean.getImage());
						finish();
//						Toast.makeText(getApplication(), user.getUser().getUsername()+":"+user.getUser().getId(), Toast.LENGTH_SHORT).show();
					}else{
					}
				}
			}
		}, false, RequestBaseMapLH.getLogin(mEtUsername.getText().toString(), mEtPassword.getText().toString()));
		gs.execute(EFaceTypeLH.URL_MINE_LOGIN);
	}
	
	
	private boolean showToastEditTextNotNull(EditText editText,String name){
		if(editText == null || editText.getText().toString().trim().equals("") || name == null){
			Toast.makeText(this, name+" 不能为空！", Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}

}
