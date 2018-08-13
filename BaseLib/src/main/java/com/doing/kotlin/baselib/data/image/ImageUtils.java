package com.doing.kotlin.baselib.data.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;

import java.io.File;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * Class description here
 *
 * @author doing
 * @version 1.0.0
 * @since 2017-03-13.
 */

public class ImageUtils {

    private static MemoryCache sMemoryCache = null;
    private static LruBitmapPool sBitmapPoolCache = null;
    private static GlideCircleTransform sGlideCircleTransform = null;
    private static SparseArray<GlideRoundTransform> sRoundTransforms = new SparseArray<>();

    /*package*/
    static MemoryCache getMemoryCache(Context context) {
        if (sMemoryCache == null) {
            synchronized (ImageUtils.class) {
                if (sMemoryCache == null) {
                    MemorySizeCalculator calculator = new MemorySizeCalculator(context);
                    sMemoryCache = new LruResourceCache(calculator.getMemoryCacheSize());
                }
            }
        }
        return sMemoryCache;
    }

    /*package*/
    static LruBitmapPool getBitmapPoolCache(Context context) {
        if (sBitmapPoolCache == null) {
            synchronized (ImageUtils.class) {
                if (sBitmapPoolCache == null) {
                    MemorySizeCalculator calculator = new MemorySizeCalculator(context);
                    sBitmapPoolCache = new LruBitmapPool(calculator.getMemoryCacheSize());
                }
            }
        }
        return sBitmapPoolCache;
    }

    private static BitmapTransformation getCircleBitmapTransform(Context context) {
        if (sGlideCircleTransform == null) {
            synchronized (ImageUtils.class) {
                if (sGlideCircleTransform == null) {
                    sGlideCircleTransform = new GlideCircleTransform(context);
                }
            }
        }
        return sGlideCircleTransform;
    }

    private static BitmapTransformation getRoundBitmapTransform(Context context, int raduisDp) {
        GlideRoundTransform transformation = sRoundTransforms.get(raduisDp);

        if (transformation == null) {
            synchronized (ImageUtils.class) {
                transformation = sRoundTransforms.get(raduisDp);
                if (transformation == null) {
                    transformation = new GlideRoundTransform(context, raduisDp);
                    sRoundTransforms.put(raduisDp, transformation);
                }
            }
        }
        return transformation;
    }

    public static void setUrl(ImageView imageView, String url) {
        setUrl(imageView, url, 0, 0, null, 0);
    }

    public static void setUrl(ImageView imageView, String url, int placeHolder) {
        setUrl(imageView, url, 0, 0, null, placeHolder);
    }

    public static void setUrl(ImageView imageView, String url, int width, int height) {
        setUrl(imageView, url, width, height, null, 0);
    }

    public static void setRadiusUrl(ImageView imageView, String url, int radiusDp) {
        BitmapTransformation transformation = getRoundBitmapTransform(imageView.getContext(), radiusDp);
        setUrl(imageView, url, 0, 0, transformation, 0);
    }

    public static void setRadiusUrl(ImageView imageView, String url, int width, int height, int radiusDp) {
        BitmapTransformation transformation = getRoundBitmapTransform(imageView.getContext(), radiusDp);
        setUrl(imageView, url, width, height, transformation, 0);
    }

    public static void setCircleUrl(ImageView imageView, String url) {
        BitmapTransformation transformation = getCircleBitmapTransform(imageView.getContext());
        setUrl(imageView, url, 0, 0, transformation, 0);
    }

    public static void setCircleUrl(ImageView imageView, String url, int placeholderImg) {
        BitmapTransformation transformation = getCircleBitmapTransform(imageView.getContext());
        setUrl(imageView, url, 0, 0, transformation, placeholderImg);
    }

    public static void setCircleUrl(ImageView imageView, String url, int width, int height) {
        BitmapTransformation transformation = getCircleBitmapTransform(imageView.getContext());
        setUrl(imageView, url, width, height, transformation, 0);
    }

    private static void setUrl(ImageView imageView, String url, int width, int height, BitmapTransformation transformation
            , int placeholderImg) {
        String sizeUrl = (width > 0 && height > 0) ?
                UrlGenerator.getImageUrl(url, width, height) : url;

        if (!sizeUrl.contains("http:")) {
            sizeUrl = "file://" + sizeUrl;
        }

        DrawableRequestBuilder<String> builder;
        try {
            builder = Glide.with(imageView.getContext())
                    .load(sizeUrl)
                    .animate(android.R.anim.fade_in);
        } catch (Exception e) {
            return;
        }

        if (placeholderImg != 0) {
            builder.placeholder(placeholderImg)
                    .error(placeholderImg);
        }

        if (transformation != null) {
            builder.bitmapTransform(transformation);
        }


        builder.into(imageView);
    }

    public static void setResId(ImageView imageView, int resId) {
        setResId(imageView, resId, null);
    }

    public static void setResId(ImageView imageView, int resId, int radiusDp) {
        setResId(imageView, resId, getRoundBitmapTransform(imageView.getContext(), radiusDp));
    }

    public static void setCircleResId(ImageView imageView, int resId) {
        setResId(imageView, resId, getCircleBitmapTransform(imageView.getContext()));
    }

    private static void setResId(ImageView imageView, int resId, BitmapTransformation transformation) {
        DrawableRequestBuilder<Integer> builder = Glide.with(imageView.getContext())
                .load(resId)
                .animate(android.R.anim.fade_in);

        if (transformation != null) {
            builder.transform(transformation);
        }

        builder.into(imageView);
    }

    public static void setFilePath(ImageView imageView, String filePath) {
        setFilePath(imageView, filePath, null);
    }

    public static void setFilePath(ImageView imageView, String filePath, int radius) {
        setFilePath(imageView, filePath, getRoundBitmapTransform(imageView.getContext(), radius));
    }

    public static void setCircleFilePath(ImageView imageView, String filePath) {
        setFilePath(imageView, filePath, new CropCircleTransformation(imageView.getContext()));
    }

    private static void setFilePath(ImageView imageView, String filepath, Transformation transformation) {
        DrawableRequestBuilder<String> builder = Glide.with(imageView.getContext())
                .load(filepath)
                .animate(android.R.anim.fade_in);

        if (transformation != null) {
            builder.transform(transformation);
        }

        builder.into(imageView);
    }

    //其他
    public static long getDiskCacheSize(Context context) {
        File diskCacheDir = getDiskCacheDir(context);
        return getFolderSize(diskCacheDir);
    }

    public static void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    public static void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    public static void prefetch(Context context, String url, int width, int height) {
        String sizeUrl = (width > 0 && height > 0) ?
                UrlGenerator.getImageUrl(url, width, height) : url;

        Glide.with(context).load(sizeUrl).downloadOnly(width, height);
    }

    public static void prefetch(Context context, String url, int width, int height, ImageLoaderListener listener) {
        String sizeUrl = (width > 0 && height > 0) ?
                UrlGenerator.getImageUrl(url, width, height) : url;

    }

    private static long getFolderSize(File file) {
        long size = 0;
        try {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File subFile : files) {
                    if (subFile.isDirectory()) {
                        size += getFolderSize(subFile);
                    } else {
                        size += subFile.length();
                    }
                }
            } else {
                size += file.length();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 返回Glide本地磁盘缓存路径
     *
     * @param context
     * @return
     */
    private static File getDiskCacheDir(Context context) {
        File cacheDirectory = context.getCacheDir();
        return new File(cacheDirectory, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR);
    }


    private static class DownloadTarget implements Target<File> {

        @Override
        public void onLoadStarted(Drawable placeholder) {

        }

        @Override
        public void onLoadFailed(Exception e, Drawable errorDrawable) {

        }

        @Override
        public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {

        }

        @Override
        public void onLoadCleared(Drawable placeholder) {

        }

        @Override
        public void getSize(SizeReadyCallback cb) {

        }

        @Override
        public void setRequest(Request request) {

        }

        @Override
        public Request getRequest() {
            return null;
        }

        @Override
        public void onStart() {

        }

        @Override
        public void onStop() {

        }

        @Override
        public void onDestroy() {

        }
    }
}
