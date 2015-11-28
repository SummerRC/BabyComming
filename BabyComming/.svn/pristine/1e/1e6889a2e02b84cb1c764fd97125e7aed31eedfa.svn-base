package com.aohuan.utils.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import android.util.Log;

import com.aohuan.utils.common.LogAh;


public class HttpUtil2 {
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/** 带参数的post */
	public static String doPost(String url, List<NameValuePair> paramList) {
		LogAh.i("url:\t" + url);
		Iterator<NameValuePair> iter = paramList.iterator();
		while (iter.hasNext()) {
			NameValuePair np = iter.next();
			LogAh.i("np:\t" + np.getName() + "\t\t\t" + np.getValue());
		}
		try {
			// 创建连接
			HttpPost post = new HttpPost(url);
			post.setEntity(new UrlEncodedFormEntity(paramList, HTTP.UTF_8));
			System.out.println("strPost:\t"+post.getURI()+"\t"+post.getRequestLine());
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
			HttpConnectionParams.setSoTimeout(httpParams, 10000);
			HttpClient httpclient = new DefaultHttpClient(httpParams);
			// HttpClient httpclient = new DefaultHttpClient();
			// 发送HttpPost请求，并返回HttpResponse对象
			HttpResponse httpResponse = httpclient.execute(post);
			// 判断请求响应状态码，状态码为200表示服务端成功响应了客户端的请求
			Log.e("TAG","code:\t" + httpResponse.getStatusLine().getStatusCode());
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// 获取返回结果
				// String result =
				// EntityUtils.toString(httpResponse.getEntity());
//				String result = (String) hp.getParameter("code");
				HttpParams hp = httpResponse.getParams();
//				LogAh.i("hp:\t" + hp.getParameter("code"));
				InputStream in = httpResponse.getEntity().getContent();
				String str =inputStream2String(in);
				LogAh.i("str:\t\t"+decode(str));
				return decode(str);
			}
			return null;
		} catch (Exception e) {
			LogAh.i("error:\t" + e.getMessage());
			return null;
		}
	}

	/** 将InputStream转成String */
	private static String inputStream2String(InputStream in) throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}

	/** unicode 的decode操作 */
	public static String decode(String unicodeStr) {
		if (unicodeStr == null) {
			return null;
		}
		StringBuffer retBuf = new StringBuffer();
		int maxLoop = unicodeStr.length();
		for (int i = 0; i < maxLoop; i++) {
			if (unicodeStr.charAt(i) == '\\') {
				if ((i < maxLoop - 5)
						&& ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr
								.charAt(i + 1) == 'U')))
					try {
						retBuf.append((char) Integer.parseInt(
								unicodeStr.substring(i + 2, i + 6), 16));
						i += 5;
					} catch (NumberFormatException localNumberFormatException) {
						retBuf.append(unicodeStr.charAt(i));
					}
				else
					retBuf.append(unicodeStr.charAt(i));
			} else {
				retBuf.append(unicodeStr.charAt(i));
			}
		}
		return retBuf.toString();
	}

}
