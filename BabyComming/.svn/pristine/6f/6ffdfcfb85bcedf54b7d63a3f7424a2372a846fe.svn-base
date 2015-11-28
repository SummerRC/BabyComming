package com.aohuan.utils.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
/**
 * ʵ������������
 * @author ʷ�»�
 *
 */
public class NetWorkUtil {

	private static final String ATG = NetWorkUtil.class.getSimpleName();
	//�ж���������״̬
	public static boolean isNetAvaiable(Context context){
      	//ͨ��ϵͳ�ṩ��CONNECTIVITY_SERVICE����������õ�ConnectivityManager(������)
		ConnectivityManager  manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if(info != null && info.isAvailable()){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * ��ȡͼƬ
	 */
	public static Bitmap getImageHttpClient(String urlStr){
		Bitmap result = null;
		InputStream is = null;
		//��װHttpGet���� 
		HttpGet get = new HttpGet(urlStr);
		//���Ӳ������ӳ�ʱ����ȡ��ʱ
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 5*1000);
		HttpConnectionParams.setSoTimeout(params, 5*1000);
		//��ɿͻ��˶��󣬲��������Ӳ���?
		HttpClient client = new DefaultHttpClient(params);
		//ִ�����������Ӧ���ж���Ӧ�룬���������Ӧ���?
		HttpResponse response;
		try {
			response = client.execute(get);
			//�ж�һ����Ӧ��
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				HttpEntity entity = response.getEntity();
				is = entity.getContent();
				result = BitmapFactory.decodeStream(is);//һ��ͼƬ����
			}else{
				Log.e(ATG, "图片的接口为:"+urlStr
						);
			}
		} catch (ClientProtocolException e) {
			Log.e(ATG, ""+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Log.e(ATG, ""+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				if(is != null){
					is.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}


}