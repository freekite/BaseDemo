package com.demo.basedemo.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.renderscript.RenderScript.Priority;
import android.view.View;
import android.view.View.OnClickListener;

import com.demo.basedemo.R;
import com.demo.basedemo.base.BaseActivity;
import com.demo.basedemo.receiver.MainReceiver;
import com.demo.basedemo.utils.DialogUtil;
import com.demo.basedemo.utils.ToastUtil;
import com.demo.basedemo.widget.MessageDialog.MessageCallBack;
import com.demo.basedemo.widget.PicturePickerDialog.PicturePickerCallBack;

public class MainActivity extends BaseActivity implements OnClickListener {

	private MainReceiver mainReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		registerMainReceiver();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_show:
			dialogUtil.showPicturePickerDialog(new PicturePickerCallBack() {

				@Override
				public void onPhotoClick() {
					ToastUtil.toast(MainActivity.this, "拍照");
				}

				@Override
				public void onAlbumClick() {
					ToastUtil.toast(MainActivity.this, "从相册选");
				}
			});
			break;
		case R.id.btn_message:
			dialogUtil.showMessageDialog("确定要退出应用吗？", new MessageCallBack() {

				@Override
				public void onOkClick() {
				}
			});
			break;
		case R.id.btn_progress:
			dialogUtil.showProgressDialog();
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							dialogUtil.dismissProgressDialog();
						}
					});
				}
			}).start();
			break;
		}
	}

	public void registerMainReceiver() {
		mainReceiver = new MainReceiver();
		IntentFilter filter = new IntentFilter();
		filter.setPriority(1000);
		filter.addAction(Intent.ACTION_HEADSET_PLUG);
		filter.addAction(Intent.ACTION_MEDIA_BAD_REMOVAL);
		filter.addAction(Intent.ACTION_MEDIA_EJECT);
		filter.addAction(Intent.ACTION_MEDIA_MOUNTED);
		filter.addAction(Intent.ACTION_MEDIA_REMOVED);
		filter.addAction(Intent.ACTION_MEDIA_SHARED);
		filter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
		filter.addAction(Intent.ACTION_POWER_CONNECTED);
		filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
		filter.addAction(Intent.ACTION_BATTERY_CHANGED);
		filter.addAction(Intent.ACTION_BATTERY_LOW);
		filter.addAction(Intent.ACTION_BATTERY_OKAY);
		filter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
		filter.addAction(MainReceiver.SMS_RECEIVED_ACTION);
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(mainReceiver, filter);
	}

	private void unregisterReceiver() {
		unregisterReceiver(mainReceiver);
	}

}
