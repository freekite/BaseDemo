package com.demo.basedemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.demo.basedemo.R;
import com.demo.basedemo.base.BaseActivity;
import com.demo.basedemo.base.Constant;
import com.demo.basedemo.utils.AppUtil;

public class SplashActivity extends BaseActivity {

	private static final int TIME_OUT = 2500;

	private TextView tv_version;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_splash);

		tv_version = (TextView) findViewById(R.id.tv_version);

		init();
	}

	private void init() {
		String version = AppUtil.getVersionName(this);
		tv_version.setText(version);

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(TIME_OUT);
				} catch (InterruptedException e) {
					if (Constant.DEBUG)
						e.printStackTrace();
				}
				startActivity(new Intent(SplashActivity.this, MainActivity.class));
				SplashActivity.this.finish();
			}
		}).start();
	}
}
