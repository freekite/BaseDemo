package com.demo.basedemo.utils;

import android.content.Context;

import com.demo.basedemo.widget.MessageDialog;
import com.demo.basedemo.widget.MessageDialog.MessageCallBack;
import com.demo.basedemo.widget.PicturePickerDialog;
import com.demo.basedemo.widget.PicturePickerDialog.PicturePickerCallBack;
import com.demo.basedemo.widget.ProgressDialog;

public class DialogUtil {

	private MessageDialog messageDialog;
	private ProgressDialog progressDialog;
	private PicturePickerDialog picturePickerDialog;

	private Context context;

	public DialogUtil(Context context) {
		super();
		this.context = context;
		init();
	}

	public void init() {
		messageDialog = new MessageDialog(context);
		progressDialog = new ProgressDialog(context);
		picturePickerDialog = new PicturePickerDialog(context);
	}

	public void showMessageDialog(String message, MessageCallBack callBack) {
		messageDialog.setMessage(message);
		messageDialog.show(callBack);
	}

	public void showPicturePickerDialog(PicturePickerCallBack callBack) {
		picturePickerDialog.show(callBack);
	}

	public void showProgressDialog() {
		progressDialog.show();
	}

	public void dismissProgressDialog() {
		progressDialog.dismiss();
	}
}
