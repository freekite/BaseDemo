package com.demo.basedemo.utils;

import android.content.Context;

import com.demo.basedemo.base.Constant;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;

public class XUtils {

	private static final String DB_NAME = "demo.db";
	private static final int TIME_OUT = 30 * 1000;
	private static final int MEMORY_CACHE = 10 * 1024 * 1024;// 10M
	private static final int DISK_CACHE = 10 * 1024 * 1024;

	private static boolean isInitialize;
	private static DbUtils dbUtils;
	private static HttpUtils httpUtils;
	private static BitmapUtils bitmapUtils;

	public static void init(Context context) {
		if (!isInitialize) {
			isInitialize = true;
			dbUtils = DbUtils.create(context, DB_NAME);
			dbUtils.configDebug(Constant.DEBUG);
			httpUtils = new HttpUtils(TIME_OUT);
			bitmapUtils = new BitmapUtils(context, FolderUtil.getImageCacheFolder().getAbsolutePath(), MEMORY_CACHE,
					DISK_CACHE);
		}
	}

	public static DbUtils getDbUtils() {
		return dbUtils;
	}

	public static HttpUtils getHttpUtils() {
		return httpUtils;
	}

	public static BitmapUtils getBitmapUtils() {
		return bitmapUtils;
	}

}
