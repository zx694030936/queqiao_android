package com.queqiaolove.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.queqiaolove.QueQiaoLoveApp;
import com.queqiaolove.global.Constants;

import java.io.File;

public class CommonUtils {
    //dip转px
    public static int dip2px(int dip) {
        //px = dip * 逻辑密度
        float density = QueQiaoLoveApp.getmContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5);
    }

    //px转dip
    public static int px2dip(int px) {
        //px = dip * 逻辑密度
        float density = QueQiaoLoveApp.getmContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

    //运行任务在主线程
    public static void runInMainThread(Runnable task) {

        if (QueQiaoLoveApp.isMainThread(android.os.Process.myTid())) {
            //如果当前线程是主线程，直接调用task.run()
            task.run();
        } else {
            //如果当前线程是子线程，把task交给主线程去执行
            QueQiaoLoveApp.getMainHandler().post(task);
        }

    }

    public static int getColor(int r) {
        return QueQiaoLoveApp.getmContext().getResources().getColor(r);
    }

    public static Drawable getDrawable(int mDrawableForegroudResId) {
        return QueQiaoLoveApp.getmContext().getResources().getDrawable(mDrawableForegroudResId);
    }

    /**
     * 判断当前线程是否是主线程
     *
     * @return
     */
    public static boolean isRunInMainThread() {
        return QueQiaoLoveApp.isMainThread(android.os.Process.myTid());
    }


    /*打电话，到输入号码界面*/
    public static void callPhone(Activity activity, String tel) {
        if (Build.VERSION.SDK_INT >= 23) {
            //权限已经被授予，在这里直接写要执行的相应方法即可
                        /*申请权限*/
            //第二个参数是需要申请的权限
            if (ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                //权限还没有授予，需要在这里写申请权限的代码
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.CALL_PHONE},
                        Constants.MY_PERMISSIONS_REQUEST_CALL_PHONE);

            } else {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                activity.startActivity(intent);
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            activity.startActivity(intent);
        }
    }

    private static ImageLoader imageLoader;
    private static DisplayImageOptions imageOptions;

    /*加载图片*/
    public static void loadImage(int ic, ImageView imageView, String url) {
        /*VolleyLoadPicture loadPicture = new VolleyLoadPicture(activity,imageView);
        loadPicture.getmImageLoader().get(url,loadPicture.getOne_listener());*/
        initImageOption(ic);
        imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(url, imageView, imageOptions);
    }

    /*初始化imageloader*/
    public static void initImageLoader(Context context) {
        //缓存路径
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "imageloader/Cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(100) //缓存的文件数量
                .discCache(new UnlimitedDiskCache(cacheDir))//自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();//开始构建
        ImageLoader.getInstance().init(config);
    }

    /*设置缓存属性*/
    private static void initImageOption(int ic) {
        imageOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(ic) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(ic)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(ic)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                //.decodingOptions(android.graphics.BitmapFactory.Options
                //decodingOptions)//设置图片的解码配置
                //.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
                //设置图片加入缓存前，对bitmap进行设置
                //.preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .build();//构建完成
    }

    private static int totalHeight = 0;

    /*设置listview高度*/
    public static void setListViewHeight(ListView listView) {
        /*得到适配器*/
        Adapter adapter = listView.getAdapter();
		/*遍历控件*/
        for (int i = 0; i < adapter.getCount(); i++) {
            View view = adapter.getView(i, null, listView);
		/*测量一下子控件的高度*/
            view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
		/*控件之间的间隙*/
        totalHeight += listView.getDividerHeight() * (listView.getCount() - 1);
		/*2、赋值给ListView的LayoutParams对象*/
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight;
        listView.setLayoutParams(params);
    }
    public static void setScrollViewHeight(ScrollView scrollView) {
        /*得到适配器*/
        //Adapter adapter = scrollView.getAdapter();

		/*遍历控件*/
        for (int i = 0; i < scrollView.getChildCount(); i++) {
           View view = scrollView.getChildAt(i);
		/*测量一下子控件的高度*/
            view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
		/*控件之间的间隙*/
        //totalHeight += listView.getDividerHeight() * (listView.getCount() - 1);
		/*2、赋值给ListView的LayoutParams对象*/
        ViewGroup.LayoutParams params = scrollView.getLayoutParams();
        params.height = totalHeight;
        scrollView.setLayoutParams(params);
    }
}
