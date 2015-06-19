package com.demo.basedemo.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.demo.basedemo.R;

public class MessageDialog extends Dialog implements android.view.View.OnClickListener {

	private MessageCallBack callBack;
	private TextView tv_message;
	private Button btn_ok, btn_cancel;

	public interface MessageCallBack {
		public void onOkClick();
	}

	public MessageDialog(Context context) {
		super(context, R.style.DialogCenterStyle);
		setContentView(R.layout.dialog_message);
		tv_message = (TextView) findViewById(R.id.tv_message);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		btn_cancel = (Button) findViewById(R.id.btn_cancel);
		btn_ok.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);
	}

	@Override
	public void show() {
	}

	public void setMessage(String msg) {
		tv_message.setText(msg);
	}

	public void show(MessageCallBack callBack) {
		this.callBack = callBack;
		super.show();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_ok:
			callBack.onOkClick();
			cancel();
			break;
		case R.id.btn_cancel:
			cancel();
			break;

		}
	}
}
