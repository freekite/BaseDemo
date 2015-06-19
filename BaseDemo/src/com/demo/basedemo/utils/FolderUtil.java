package com.demo.basedemo.utils;

import android.os.Environment;

import java.io.File;

public class FolderUtil {

	private static final String APP_ROOT = "eapp";
	private static final String DOWNLOAD_CACHE = "download";
	private static final String IMAGE_CACHE = "images";
	private static final String TEMP_CACHE = "temp";

	private static File appRootFile;
	private static File downloadCacheFile;
	private static File imageCacheFile;
	private static File tempCacheFile;

	public static void init() {
		if (SDCardUtil.isSDCardEnable()) {
			appRootFile = new File(Environment.getExternalStorageDirectory(), APP_ROOT);
			downloadCacheFile = new File(appRootFile, DOWNLOAD_CACHE);
			imageCacheFile = new File(appRootFile, IMAGE_CACHE);
			tempCacheFile = new File(appRootFile, TEMP_CACHE);
		}
	}

	public static File getAppRootFolder() {
		if (!appRootFile.exists()) {
			appRootFile.mkdirs();
		}
		return appRootFile;
	}

	public static File getDownloadCacheFolder() {
		if (!downloadCacheFile.exists()) {
			downloadCacheFile.mkdirs();
		}
		return downloadCacheFile;
	}

	public static File getImageCacheFolder() {
		if (!imageCacheFile.exists()) {
			imageCacheFile.mkdirs();
		}
		return imageCacheFile;
	}

	public static File getTempCacheFolder() {
		if (!tempCacheFile.exists()) {
			tempCacheFile.mkdirs();
		}
		return tempCacheFile;
	}
}
