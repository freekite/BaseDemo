package com.demo.basedemo.base;

import java.util.Iterator;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;

import com.demo.basedemo.utils.DialogUtil;
import com.demo.basedemo.utils.FolderUtil;
import com.demo.basedemo.utils.NetUtil;
import com.demo.basedemo.utils.PreferenceUtil;
import com.demo.basedemo.utils.XUtils;

public class MainApplication extends Application {
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";

	private String userName;
	private String passWord;

	private static MainApplication instance;

	public static MainApplication getInstance() {
		return instance;
	}

	public void setUserName(String userName) {
		if (userName != null) {
			if (PreferenceUtil.getInstance(this).setString(USERNAME, userName))
				this.userName = userName;
		}
	}

	public String getUserName() {
		if (this.userName == null)
			this.userName = PreferenceUtil.getInstance(this).getString(USERNAME);
		return this.userName;
	}

	public void setPassWord(String passWord) {
		if (passWord != null) {
			if (PreferenceUtil.getInstance(this).setString(PASSWORD, passWord))
				this.passWord = passWord;
		}
	}

	public String getPassWord() {
		if (this.passWord == null)
			this.passWord = PreferenceUtil.getInstance(this).getString(PASSWORD);
		return this.passWord;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		if (isRemote()) {
			return;
		}
		instance = this;
		FolderUtil.init();
		XUtils.init(this);
	}

	public boolean isRemote() {
		int pid = android.os.Process.myPid();
		String processAppName = getAppName(pid);
		if (processAppName == null || !getPackageName().equals(processAppName)) {
			return true;
		}
		return false;
	}

	protected String getAppName(int pID) {
		String processName = null;
		ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> l = am.getRunningAppProcesses();
		Iterator<RunningAppProcessInfo> i = l.iterator();
		while (i.hasNext()) {
			ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
			try {
				if (info.pid == pID) {
					processName = info.processName;
					return processName;
				}
			} catch (Exception e) {
				if (Constant.DEBUG)
					e.printStackTrace();
			}
		}
		return processName;
	}

	public boolean isNetworkConnected() {
		return NetUtil.isConnected(getApplicationContext());
	}

}
