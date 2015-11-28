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
import com.aohuan.utils.modifyPwdUtils;
import com.aohuan.utils.propriety.LoginHelper;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 修改密码
 * @author Administrator
 */
@ContentView(R.layout.activity_mine_change_pwd)
public class ChangePasswordActivity extends BaseActiviry {

	@ViewInject(R.id.back)
	private ImageButton mImgBtnBack;
	@ViewInject(R.id.et_input_username)
	private EditText mUsernameText;
	@ViewInject(R.id.et_input_quest)
	private EditText mOldPwdText;
	@ViewInject(R.id.et_input_answer)
	private EditText mNewPwdText;
	@ViewInject(R.id.et_input_answer_again)
	private EditText magainPwdText;
	@ViewInject(R.id.btn_commit)
	private Button mSubmitButton;

	
	@SuppressLint("HandlerLeak")
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what==2){
				if (msg.obj !=null && msg.obj instanceof BaseBean) {
					BaseBean bean=(BaseBean) msg.obj;
					if (bean.getCode()==0) {
						Toast.makeText(ChangePasswordActivity.this,
								bean.getMessage(), Toast.LENGTH_SHORT).show();
					}else if (bean.getCode()==1) {
						Toast.makeText(ChangePasswordActivity.this,
								bean.getMessage(), Toast.LENGTH_SHORT).show();
					}
				}else if (msg.obj==null) {
					Toast.makeText(ChangePasswordActivity.this,
							"网络不给力", Toast.LENGTH_SHORT).show();
				}
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		mUsernameText.setText(LoginHelper.getUser(this).getUsername());
	}

	@OnClick({ R.id.back,R.id.btn_commit })
	public void onRlClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;

		case R.id.btn_commit:	
			String pwd=mOldPwdText.getText().toString();
			String new_pwd=mNewPwdText.getText().toString();
			String again_pwd=magainPwdText.getText().toString();
			//原密码是否相等			
			if (!new_pwd.equals(again_pwd)) {
				Toast.makeText(this, "两次新密码不一致", Toast.LENGTH_SHORT).show();
				break;
			}

			if (new_pwd.length() < 6 || again_pwd.length() < 6) {
				Toast.makeText(this, "密码长度不能小于6", Toast.LENGTH_SHORT).show();
				break;
			}
			modifyPwdUtils.modifyPwd(this, LoginHelper.getUser(this).getId()
					, new_pwd, pwd, handler);
			break;
		}
	}

}
