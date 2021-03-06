package com.aohuan.detail.mine;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.aohuan.babycomming.R;
import com.aohuan.base.BaseActiviry;
import com.aohuan.bean.BaseBean;
import com.aohuan.utils.FindPwdUtils2;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

@ContentView(R.layout.activity_find_pwd)
public class FindPwdActivity extends BaseActiviry {
    
	@ViewInject(R.id.back)
	private ImageButton mImgBtnBack;
	@ViewInject(R.id.et_input_username)
	private EditText mUsernameText;
	@ViewInject(R.id.et_input_answer)
	private EditText mNewPwdText;
	@ViewInject(R.id.et_input_answer_again)
	private EditText magainPwdText;
	@ViewInject(R.id.btn_commit)
	private Button mSubmitButton;

	private String username;
	
	@SuppressLint("HandlerLeak")
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (msg.what==5) {
				if (msg.obj!=null && msg.obj instanceof BaseBean) {
					BaseBean bean=(BaseBean) msg.obj;
					if (bean.getCode()==0) {
						Toast.makeText(FindPwdActivity.this,
								bean.getMessage(), Toast.LENGTH_SHORT).show();
						finish();
					}else if (bean.getCode()==1) {
						Toast.makeText(FindPwdActivity.this,
								bean.getMessage(), Toast.LENGTH_SHORT).show();
					}
				}else if (msg.obj==null) {
					Toast.makeText(FindPwdActivity.this,
							"网络不给力", Toast.LENGTH_SHORT).show();
				}
			}
		}
	};
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);		
		username=getIntent().getStringExtra("username");
		mUsernameText.setText(username);
	}
	@OnClick({R.id.back,R.id.btn_commit})
	public void onRlClick(View v) {
		
		switch (v.getId()) {
		case R.id.back:		
			finish();
			break;			
		case R.id.btn_commit:
			
			String pwd=mNewPwdText.getText().toString();
			String againPwd=magainPwdText.getText().toString();
			if (pwd.equals("")|| againPwd.equals("")|| pwd.length()<6||againPwd.length()<6) {				
				Toast.makeText(FindPwdActivity.this, "密码为空或密码长度小于6",
						Toast.LENGTH_SHORT).show();
				return;
			}else if (!pwd.equals(againPwd)) {
				Toast.makeText(FindPwdActivity.this, "两次密码不一致",
						Toast.LENGTH_SHORT).show();
				return;
			}			
			FindPwdUtils2.find(this, username, pwd, handler);
			break;
		}
	}
}
