package com.demo.basedemo.base;

import com.demo.basedemo.utils.DialogUtil;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class BaseActivity extends FragmentActivity {

	public DialogUtil dialogUtil;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		dialogUtil = new DialogUtil(this);
	}

	public void back(View v) {
		finish();
	}

}
