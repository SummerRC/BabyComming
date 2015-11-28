package com.aohuan.base;

import com.lidroid.xutils.ViewUtils;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public class BaseActiviry extends FragmentActivity {

		@Override
		protected void onCreate(Bundle arg0) {
			// TODO Auto-generated method stub
			super.onCreate(arg0);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			ViewUtils.inject(this);
		}
}
