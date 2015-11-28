package com.aohuan.babycomming;


import java.util.ArrayList;
import java.util.List;

import com.aohuan.base.BaseActiviry;
import com.aohuan.bean.UserBean.User;
import com.aohuan.detail.mine.LoginActivity;
import com.aohuan.fragment.FirstPageFragment;
import com.aohuan.fragment.MineFragment;
import com.aohuan.fragment.Morefragment;
import com.aohuan.fragment.OrderFragment;
import com.aohuan.utils.FragmentTabAdapter;
import com.aohuan.utils.FragmentTabAdapter.OnRgsExtraCheckedChangedListener;
import com.aohuan.utils.bitmap.yuanimage.BitmapUtils;
import com.aohuan.utils.propriety.LoginHelper;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActiviry {

	@ViewInject(R.id.radioGroup1)
	private RadioGroup mGroup;
	
	private FragmentTabAdapter tabAdapter;
	
	private List<Fragment> mFragments;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mFragments=new ArrayList<Fragment>();
		mFragments.add(new FirstPageFragment());
		mFragments.add(new OrderFragment());
		mFragments.add(new MineFragment());
		mFragments.add(new Morefragment());
		
		((RadioButton)mGroup.getChildAt(0)).setChecked(true);
		FragmentManager manager=getSupportFragmentManager();
		tabAdapter=new FragmentTabAdapter(this, manager, mFragments, R.id.main_fragment_contain, mGroup);
		tabAdapter.setOnRgsExtraCheckedChangedListener(new OnRgsExtraCheckedChangedListener() {
			@Override
			public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId,
					int index) {
				if(index == 1 && LoginHelper.getUser(getBaseContext()) == null){
					startActivityForResult(new Intent(getBaseContext(), LoginActivity.class), 2);
					
				}
			}
		});
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 如果没有登录， 则显示 【登录】按钮， 否则， 显示用户名：xxx
		if(requestCode==2){
			if(LoginHelper.getUser(getBaseContext()) == null){
				((RadioButton)mGroup.getChildAt(0)).setChecked(true);
			}else{
				
			}
		}
		
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	
	private long exitTime;

	// 点击两次退出（2秒内）
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if (System.currentTimeMillis() - exitTime > 2000) {
				Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
				// ToastUtils.showToast(this, "再按一次退出");
				exitTime = System.currentTimeMillis();
			} else {
//				SharedPreferences spPreferences = getSharedPreferences(
//						"userInfo", MODE_PRIVATE);
//				Editor editor = spPreferences.edit();
//				editor.putString("id", "");
//				editor.commit();
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
