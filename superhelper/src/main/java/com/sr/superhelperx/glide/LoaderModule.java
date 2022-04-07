package com.sr.superhelperx.glide;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.sr.superhelperx.app.AppApplication;
import com.sr.superhelperx.glide.okhttpglide.OkHttpUrlLoader;
import com.sr.superhelperx.util.UtilFile;
import com.sr.superhelperx.util.UtilLog;

import java.io.File;
import java.io.InputStream;


/**
 * Created by Hang.Yang on 2018/8/17 15:52.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */
@GlideModule
public class LoaderModule extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        //设置内存大小
        builder.setMemoryCache(new LruResourceCache((int) (Runtime.getRuntime().maxMemory() / 8)));
        //设置磁盘缓存大小
        File imageCacheFile = new File(AppApplication.INSTANCE.getImageCacheFolder());
        if (imageCacheFile.exists() && imageCacheFile.isDirectory()){
            builder.setDiskCache(new DiskLruCacheFactory(AppApplication.INSTANCE.getImageCacheFolder(), DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE * 2));
        }else {
            builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context));
        }
        super.applyOptions(context, builder);
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
//        super.registerComponents(context, glide, registry);
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}