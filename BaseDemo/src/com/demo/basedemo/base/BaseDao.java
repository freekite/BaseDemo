package com.demo.basedemo.base;

import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.demo.basedemo.R;
import com.demo.basedemo.utils.XUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class BaseDao {

	private RequestParams generateRequestParams(String headCode, Map<String, Object> paramsMap) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		JSONObject head = new JSONObject();
		head.put("code", headCode);
		jsonObject.put("head", head);
		JSONObject field = new JSONObject();
		if (paramsMap != null) {
			Set<String> keys = paramsMap.keySet();
			for (String key : keys) {
				field.put(key, paramsMap.get(key));
			}
		}
		jsonObject.put("field", field);
		RequestParams params = new RequestParams("utf-8");
		params.addBodyParameter("json", jsonObject.toString());
		return params;
	}

	protected void result(String headCode, Map<String, Object> paramsMap, final DaoResult daoResult) {
		try {
			RequestParams params = generateRequestParams(headCode, paramsMap);
			result(headCode, params, daoResult);
		} catch (JSONException e) {
			if (Constant.DEBUG)
				e.printStackTrace();
		}
	}

	protected void result(String headCode, RequestParams params, final DaoResult daoResult) {
		if (MainApplication.getInstance().isNetworkConnected()) {
			XUtils.getHttpUtils().send(HttpMethod.POST, Constant.SERVER_HOST, new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					try {
						JSONObject jsonObject = new JSONObject(responseInfo.result);
						JSONObject head = jsonObject.getJSONObject("head");
						JSONObject body = jsonObject.getJSONObject("body");
						String resultStr = body.getString("field");
						if (Constant.RESPONSE_SUCCESS.equals(head.getString("res_code")) && resultStr != null) {
							daoResult.onSuccess(resultStr);
						} else {
							daoResult.onFailure(head.getString("res_msg"));
						}
					} catch (Exception e) {
						e.printStackTrace();
						daoResult.onFailure(MainApplication.getInstance().getResources()
								.getString(R.string.unknown_exception));
					}
				}

				@Override
				public void onFailure(HttpException error, String msg) {
					daoResult.onFailure(msg);
				}
			});
		} else {
			daoResult.onFailure(MainApplication.getInstance().getResources().getString(R.string.network_unavailable));
		}
	}

	public interface DaoResult {
		public void onSuccess(String content);

		public void onFailure(String msg);
	}
}
