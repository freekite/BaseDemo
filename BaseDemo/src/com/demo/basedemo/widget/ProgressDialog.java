package com.demo.basedemo.widget;

import android.app.Dialog;
import android.content.Context;

import com.demo.basedemo.R;

public class ProgressDialog extends Dialog {

	public ProgressDialog(Context context) {
		super(context, R.style.DialogCenterStyle);
		setContentView(R.layout.dialog_progress);
	}

}
