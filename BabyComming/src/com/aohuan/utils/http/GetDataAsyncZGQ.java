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

import com.aohuan.bean.CodeMessageBean;
import com.aohuan.bean.GoodsDetailsBean;
import com.aohuan.bean.GoodsListBean;
import com.aohuan.bean.GuangGaoLunboBean;
import com.aohuan.bean.MyOrderAllBean;
import com.aohuan.bean.NurseCompanyBean;
import com.aohuan.bean.NurseDetaisBean;
import com.aohuan.bean.NurseYuYueBean;
import com.aohuan.bean.OrderDetailsBean;
import com.aohuan.bean.RefreshUserInfoBean;
import com.aohuan.bean.UserBean;
import com.aohuan.bean.VersionBean;
import com.aohuan.bean.YuesaoDengjiBean;
import com.aohuan.bean.YuesaoListBean;
import com.aohuan.utils.common.JsonUtil;
import com.aohuan.utils.common.NetUtils;
import com.aohuan.utils.loding.ProgressUtils;
import com.aohuan.utils.request.BaseMap;


public class GetDataAsyncZGQ extends AsyncTask<EFaceTypeZGQ, Integer, Object> {
	private Activity activity;
	private IUpdateUI callback;
	/** 是否显示加载框 */
	private boolean isShowProgress = false;
	/** 是否显示toast */
	private boolean isShowToast = true;// false
	private BaseMap requestMap;

	String returnString;

	 ProgressUtils pu = null;

	public GetDataAsyncZGQ(Activity activity, IUpdateUI callback,
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
	protected Object doInBackground(EFaceTypeZGQ... params) {
		// ProgressUtils.dismissDialog();
		if (!NetUtils.isConnected(activity))
			return ExceptionType.NoNetworkException;

		returnString = HttpUtil2.doPost(params[0].getUrlAll(),
				getPairList(requestMap));

		Log.e("---returnString-->", "----->" + returnString);

		// Log.e("TAG", returnString.toString());
		Object obj = null;
		switch (params[0]) {
		case URL_GUANGGAO:
			obj = parserClass(returnString, GuangGaoLunboBean.class);
			break;
		case URL_KEFU_PHONE:
			obj = returnString;
			break;
		case URL_VERSION:
			obj = parserClass(returnString, VersionBean.class);
			break;
		case URL_GRADE_LIST:
			obj = parserClass(returnString, YuesaoDengjiBean.class);
			break;
		case URL_QUYU_LIST:
			obj = parserClass(returnString, YuesaoDengjiBean.class);
			break;
		case URL_YUESAO_LIST:
			obj = parserClass(returnString, YuesaoListBean.class);
			break;
		case URL_YUESAO_DETAILS:
			obj = parserClass(returnString, NurseDetaisBean.class);
			break;
		case URL_YUESAO_COLLECT:
			obj = parserClass(returnString, CodeMessageBean.class);
			break;
		case URL_YUESAO_PINGJIA:
			obj = parserClass(returnString, CodeMessageBean.class);
			break;
		case URL_YUESAO_ORDER:
			obj = parserClass(returnString, NurseYuYueBean.class);
			break;
		case URL_YUESAO_PARENTS:
			obj = parserClass(returnString, NurseCompanyBean.class);
			break;
		case URL_SELCT_GOODS:
			obj = parserClass(returnString, GoodsListBean.class);
			break;
		case URL_SELCT_GOODS_DETAILS:
			obj = parserClass(returnString, GoodsDetailsBean.class);
			break;
		case URL_SELCT_GOODS_COLLECT:
			obj = parserClass(returnString, CodeMessageBean.class);
			break;
		case URL_MY_ORDER_ALL:
			obj = parserClass(returnString, MyOrderAllBean.class);
			break;
		case URL_MY_ORDER_NO_FUKUAN:
			obj = parserClass(returnString, MyOrderAllBean.class);
			break;
		case URL_MY_ORDER_NO_XIAOFEI:
			obj = parserClass(returnString, MyOrderAllBean.class);
			break;
		case URL_MY_ORDER_NO_PINGJIA:
			obj = parserClass(returnString, MyOrderAllBean.class);
			break;
		case URL_MY_ORDER_DETAILS:
			obj = parserClass(returnString, OrderDetailsBean.class);
			break;
		case URL_MY_ORDER_PINGJIA:
			obj = parserClass(returnString, CodeMessageBean.class);
			break;
		case URL_DELETE_MY_ORDER:
			obj = parserClass(returnString, CodeMessageBean.class);
			break;
		case URL_UPDATE_IMAGE:
			obj = parserClass(returnString, CodeMessageBean.class);
			break;
		case URL_REFRESH_USERINFO:
			obj = parserClass(returnString, RefreshUserInfoBean.class);
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
