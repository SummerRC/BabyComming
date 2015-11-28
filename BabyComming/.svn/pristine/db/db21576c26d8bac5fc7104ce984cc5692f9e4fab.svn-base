package com.aohuan.utils.bitmap.netdownlodeyuanimage;

import java.io.File;

import com.aohuan.babycomming.R;
import com.aohuan.utils.propriety.AppConfigure;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;


public class ImageLoad {

	
	/** 为头像设置的universalimageloader的方法， 默认为R.drawable.ic_launcher图片 */
	public static void loadImageHead(ImageView imageView, String url,Context context){
		loadImageByDefaultImage(imageView, url, context, R.drawable.moren_heade);
	}
	/** 为头像设置的universalimageloader的方法， 需要传递默认图片的R.drawable图片地址 */
	public static void loadImageHead(ImageView imageView, String url,Context context, int drawId ){
		loadImageByDefaultImage(imageView, url, context, drawId);
	}
	
	/** 之前的 */
	public  static  void loadImageByDefaultImage(ImageView imageView, String url,Context context,int drawId){
//		loadImageByDefaultImage(imageView, url, context, drawId, new FadeInBitmapDisplayer(200));
		loadImageByDefaultImage(imageView, url, context, drawId, new CircleBitmapDisplayer());
	}
	
	
	public  static  void loadImageByDefaultImage(ImageView imageView, String url,Context context,int drawId, BitmapDisplayer displayer){
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
