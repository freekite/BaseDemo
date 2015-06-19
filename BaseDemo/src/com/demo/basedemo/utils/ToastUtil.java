package com.demo.basedemo.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

	private ToastUtil() {
		throw new AssertionError();
	}

	public static void toast(Context context, int resId, int duration) {
		Toast.makeText(context, resId, duration).show();
	}

	public static void toast(Context context, String content, int duration) {
		Toast.makeText(context, content, duration).show();
	}

	public static void toast(Context context, String content) {
		toast(context, content, Toast.LENGTH_SHORT);
	}

	public static void toast(Context context, int resId) {
		toast(context, resId, Toast.LENGTH_SHORT);
	}

}
