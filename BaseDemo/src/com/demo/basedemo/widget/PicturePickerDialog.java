package com.demo.basedemo.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.demo.basedemo.R;

public class PicturePickerDialog extends Dialog implements android.view.View.OnClickListener {

	private Button btn_photograph, btn_from_album, btn_cancel;

	private PicturePickerCallBack callBack;

	public interface PicturePickerCallBack {
		public void onPhotoClick();

		public void onAlbumClick();
	}

	public PicturePickerDialog(Context context) {
		super(context, R.style.DialogBottomStyle);
		setContentView(R.layout.dialog_picture_picker);
		btn_photograph = (Button) findViewById(R.id.btn_photograph);
		btn_from_album = (Button) findViewById(R.id.btn_from_album);
		btn_cancel = (Button) findViewById(R.id.btn_cancel);
		btn_photograph.setOnClickListener(this);
		btn_from_album.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);
	}

	@Override
	public void show() {
	}

	public void show(PicturePickerCallBack callBack) {
		this.callBack = callBack;
		super.show();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		cancel();
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_photograph:
			cancel();
			callBack.onPhotoClick();
			break;
		case R.id.btn_from_album:
			cancel();
			callBack.onAlbumClick();
			break;
		case R.id.btn_cancel:
			cancel();
			break;
		}
	}

}
