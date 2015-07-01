package com.demo.basedemo.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.demo.basedemo.utils.DialogUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class BaseActivity extends FragmentActivity {

	public DialogUtil dialogUtil;

	protected SystemBarTintManager tintManager;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		dialogUtil = new DialogUtil(this);
	}

	public void back(View v) {
		finish();
	}

	/**
	 * 通知栏颜色
	 * @param resId
	 */
	public void initSystemBar(int resId) {
		/*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(true);
		}
		tintManager = new SystemBarTintManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintResource(resId);*/
	}

	@TargetApi(19)
	private void setTranslucentStatus(boolean on) {
		Window win = getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}

}
