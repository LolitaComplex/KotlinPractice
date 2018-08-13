package com.doing.kotlin.baselib.data.image;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

/**
 * Class description here
 *
 * @author doing
 * @version 1.0.0
 * @since 2017-03-13.
 */

public class BaseGlideModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        long availableSize = 1024 * 1024 * 200;
        long memoryCacheSize = Runtime.getRuntime().maxMemory() / 8;
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, (int) availableSize))
                .setMemoryCache(new LruResourceCache((int) memoryCacheSize))
                .setBitmapPool(new LruBitmapPool((int) memoryCacheSize / 2))
                .setDecodeFormat(DecodeFormat.PREFER_RGB_565);

    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
