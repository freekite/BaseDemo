/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.demo.basedemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {

	/**
	 * 保存Preference的name
	 */
	public static final String PREFERENCE_NAME = "saveInfo";
	private static SharedPreferences mSharedPreferences;
	private static PreferenceUtil mPreferenceUtils;
	private static SharedPreferences.Editor editor;

	private PreferenceUtil(Context cxt) {
		mSharedPreferences = cxt.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
	}

	/**
	 * 单例模式，获取instance实例
	 * 
	 * @param cxt
	 * @return
	 */
	public static PreferenceUtil getInstance(Context cxt) {
		if (mPreferenceUtils == null) {
			mPreferenceUtils = new PreferenceUtil(cxt);
		}
		editor = mSharedPreferences.edit();
		return mPreferenceUtils;
	}

	public boolean setString(String key,String value){
		editor.putString(key, value);
		return editor.commit();
	}

	public String getString(String key){
		return mSharedPreferences.getString(key, null);
	}
	
	public boolean setBoolean(String key,boolean value){
		editor.putBoolean(key, value);
		return editor.commit();
	}
	
	public boolean getBoolean(String key){
		return mSharedPreferences.getBoolean(key, false);
	}
	
	public boolean setInt(String key,int value){
		editor.putInt(key, value);
		return editor.commit();
	}
	
	public int getInt(String key){
		return mSharedPreferences.getInt(key, 0);
	}
}
