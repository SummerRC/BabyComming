package com.aohuan.utils.adapter;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.aohuan.babycomming.R;
import com.aohuan.utils.bitmap.netdownlodeyuanimage.CircleBitmapDisplayer;
import com.aohuan.utils.propriety.AppConfigure;
import com.lidroid.xutils.BitmapUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class ImageLoad {

	public static BitmapUtils bitmapUtils;
	public  static  void loadImage(ImageView imageView, String url,Context context){
		loadImage(imageView, url, context, R.drawable.ic_default);
		
		//loadImageByDefaultImage(imageView, url, context, R.drawable.ic_launcher);
	}
	
	/** xutils的图片*/
	public  static  void loadImage(ImageView imageView, String url,Context context, int drawId ){
//		if(null == bitmapUtils){
//			bitmapUtils = new BitmapUtils(context);
//	        bitmapUtils.configDefaultLoadingImage(drawId);
//	        bitmapUtils.configDefaultLoadFailedImage(drawId);
//	        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
//		}
//		bitmapUtils.display(imageView, url);
		
		loadImageByDefaultImage(imageView, url, context, drawId);
	}
	
	/** 为头像设置的universalimageloader的方法， 默认为R.drawable.ic_launcher图片 */
	public static void loadImageHead(ImageView imageView, String url,Context context){
		loadImageHead(imageView, url, context, R.drawable.ic_default);
	}
	/** 为头像设置的universalimageloader的方法， �?��传�?默认图片的R.drawable图片地址 */
	public static void loadImageHead(ImageView imageView, String url,Context context, int drawId ){
		loadImageByDefaultImage(imageView, url, context, drawId, new CircleBitmapDisplayer());
	}
	
	/** 之前*/
	public  static  void loadImageByDefaultImage(ImageView imageView, String url,Context context,int drawId){
		loadImageByDefaultImage(imageView, url, context, drawId, new FadeInBitmapDisplayer(200));
//		loadImageByDefaultImage(imageView, url, context, drawId, new CircleBitmapDisplayer());
	}
	
	
	public  static  void loadImageByDefaultImage(ImageView imageView, String url,Context context,int drawId, BitmapDisplayer displayer){
		
		StorageUtils.getOwnCacheDirectory(context, AppConfigure.UNIVERSAL_IMAGE_LOADER_DIR);
		
		File cacheDir = StorageUtils.getOwnCacheDirectory(context, AppConfigure.UNIVERSAL_IMAGE_LOADER_DIR);
		
		DisplayImageOptions options;
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(drawId)
		.showImageForEmptyUri(drawId)
		.showImageOnFail(drawId)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.displayer(displayer)
		.resetViewBeforeLoading(true)
		.build();
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				.memoryCacheExtraOptions(480, 800)
				.threadPoolSize(3)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
				.memoryCacheSize(2 * 1024 * 1024)
				.discCacheSize(50 * 1024 * 1024)
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.discCacheFileCount(100)//缓存文件数量
				.discCache(new UnlimitedDiscCache(cacheDir))
				.defaultDisplayImageOptions(options)
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.imageDownloader(
						new BaseImageDownloader(context, 5 * 1000, 30 * 1000))
				.writeDebugLogs().build();
		
		ImageLoader.getInstance().init(config);
		ImageLoader imageLoader=ImageLoader.getInstance();
		
		if(url == null){
			imageLoader.displayImage("", imageView, options);
		}else{
			imageLoader.displayImage(url, imageView, options);
		}
	}
	
	
}
