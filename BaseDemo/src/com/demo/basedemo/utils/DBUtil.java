package com.demo.basedemo.utils;

import java.util.ArrayList;
import java.util.List;

import com.demo.basedemo.base.Constant;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;

public class DBUtil {
	private static DBUtil instance;

	public static DBUtil getInstance() {
		if (instance == null)
			instance = new DBUtil();
		return instance;
	}

	/**
	 * Selector.from(clazz).where("name", "=", "b");
	 * Selector.from(clazz).where(WhereBuilder.b("name", "!=", null));
	 * Selector.from(clazz).where("name", "=", "b").orderBy("a", true);
	 * Selector.from(clazz).where("id", "<", 54).and(WhereBuilder.b("age", ">",
	 * 20).or("age", " < ", 30)).orderBy("id").limit(10).offset(10 * 1);
	 * Selector.from(clazz).select("name");
	 * 
	 * @param clazz
	 * @param whereBuilder
	 * @param orderBy
	 * @param desc
	 * @return
	 */
	public <T> List<T> getDatasFromDb(Selector selector) {
		List<T> ts = new ArrayList<T>();
		try {
			ts = XUtils.getDbUtils().findAll(selector);
		} catch (DbException e) {
			if (Constant.DEBUG)
				e.printStackTrace();
		}
		return ts;
	}

	public <T> void save(T t) {
		try {
			XUtils.getDbUtils().save(t);
		} catch (DbException e) {
			if (Constant.DEBUG)
				e.printStackTrace();
		}
	}

	public <T> void saveAll(List<T> ts) {
		try {
			XUtils.getDbUtils().saveAll(ts);
		} catch (DbException e) {
			if (Constant.DEBUG)
				e.printStackTrace();
		}
	}

	public <T> void update(T t, WhereBuilder whereBuilder) {
		try {
			List<T> ts = XUtils.getDbUtils().findAll(Selector.from(t.getClass()).where(whereBuilder));
			if (ts != null && ts.size() > 0) {
				XUtils.getDbUtils().update(t, whereBuilder);
			}
		} catch (DbException e) {
			if (Constant.DEBUG)
				e.printStackTrace();
		}
	}

	public <T> void clearData(Class<T> clazz) {
		try {
			XUtils.getDbUtils().deleteAll(XUtils.getDbUtils().findAll(clazz));
		} catch (DbException e) {
			if (Constant.DEBUG)
				e.printStackTrace();
		}
	}

}
