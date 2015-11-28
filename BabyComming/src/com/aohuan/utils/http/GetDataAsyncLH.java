package com.aohuan.utils.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.aohuan.bean.BaseBean;
import com.aohuan.bean.UserBean;
import com.aohuan.utils.common.JsonUtil;
import com.aohuan.utils.common.LogAh;
import com.aohuan.utils.common.NetUtils;
import com.aohuan.utils.loding.ProgressUtils;
import com.aohuan.utils.request.BaseMap;


public class GetDataAsyncLH extends AsyncTask<EFaceTypeLH, Integer, Object> {
	private Activity activity;
	private IUpdateUI callback;
	/** 是否显示加载框 */
	private boolean isShowProgress = false;
	/** 是否显示toast */
	private boolean isShowToast = true;// false
	private BaseMap requestMap;

	String returnString;

	 ProgressUtils pu = null;

	public GetDataAsyncLH(Activity activity, IUpdateUI callback,
			boolean isShowProgress, BaseMap requestMap) {
		this.activity = activity;
		this.callback = callback;
		this.isShowProgress = isShowProgress;
		this.requestMap = requestMap;
		 pu = new ProgressUtils();
	}

	@Override
	protected void onPreExecute() {
		if (isShowProgress) {
			// Intent intent = new Intent();
			// intent.setClass(activity, LoadingActivity.class);
			// activity.startActivity(intent);

			// WaitingDialog.CreatWaitingDialog(activity);
		}
		 pu.showDialog(activity);
	}

	@Override
	protected void onPostExecute(Object result) {
		 pu.dismissDialog();
		if (!(result instanceof ExceptionType) && callback != null) {
			callback.updata(result);

		} else {
			if (isShowToast) {
				// ExceptionType et = (ExceptionType) result;
				// switch (et) {
				// case Exception:
				// Toast.makeText(activity, "连网失败,请重试！E", Toast.LENGTH_SHORT)
				// .show();
				// break;
				// case IOException:
				// Toast.makeText(activity, "连网失败,请重试！I", Toast.LENGTH_SHORT)
				// .show();
				// break;
				// case JsonMappingException:
				// Toast.makeText(activity, "连网失败,请重试！JM", Toast.LENGTH_SHORT)
				// .show();
				// break;
				// case JsonParseException:
				// Toast.makeText(activity, "连网失败,请重试！JP", Toast.LENGTH_SHORT)
				// .show();
				// break;
				// case NoNetworkException:
				// Toast.makeText(activity, "连网失败,请重试！NE", Toast.LENGTH_SHORT)
				// .show();
				// break;
				// default:
				// break;
			}
			// }
		}
		// WaitingDialog.dismiss();
	}

	@Override
	protected Object doInBackground(EFaceTypeLH... params) {
		// ProgressUtils.dismissDialog();
		if (!NetUtils.isConnected(activity))
			return ExceptionType.NoNetworkException;

		returnString = HttpUtil2.doPost(params[0].getUrlAll(),
				getPairList(requestMap));
		LogAh.i("---returnString-->", "----->" + returnString);
		// Log.e("TAG", returnString.toString());
		Object obj = null;
		switch (params[0]) {
		case URL_1_1_ACCOUNT_REG:
			if (isShowProgress) {

			}
			// obj = parserClass(returnString, UserInfo.class);
		case URL_MINE_REGISTER:
			 obj = parserClass(returnString, UserBean.class);
			break;
		case URL_MINE_LOGIN:
			obj = parserClass(returnString, UserBean.class);
			break;
		case URL_MINE_DELETE_YUESAO:
			obj = parserClass(returnString, BaseBean.class);
			break;
		default:
			break;
		}
		return obj;
	}

	private static Object parserClass(String returnString, Class<?> classIn) {
		try {
			Object obj = JsonUtil.toObjectByJson(returnString, classIn);
			return obj;
		} catch (JsonParseException e) {
			// e.printStackTrace();
			return ExceptionType.JsonParseException;
		} catch (JsonMappingException e) {
			// e.printStackTrace();
			return ExceptionType.JsonMappingException;
		} catch (IOException e) {
			// e.printStackTrace();
			return ExceptionType.IOException;
		} catch (Exception e) {
			return ExceptionType.Exception;
		}
	}

	private List<NameValuePair> getPairList(BaseMap bm) {
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		if (null != bm) {
			Iterator<String> iter = bm.getMap().keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				BasicNameValuePair param = new BasicNameValuePair(key,
						bm.get(key));
				paramList.add(param);
			}
		}
		return paramList;
	}

	enum ExceptionType {
		JsonParseException, JsonMappingException, IOException, Exception, NoNetworkException;
	}
}
