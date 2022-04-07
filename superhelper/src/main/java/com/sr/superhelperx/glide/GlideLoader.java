package com.sr.superhelperx.glide;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.sr.superhelperx.app.AppApplication;
import com.sr.superhelperx.http.Http;
import com.sr.superhelperx.scale.ScaleScreenHelperFactory;
import com.sr.superhelperx.sign.g;
import com.sr.superhelperx.util.UtilFile;
import com.sr.superhelperx.util.UtilLog;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Hang.Yang on 2018/8/17 15:51.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */
/*@f
@z*/
@TargetApi(11)
public final class GlideLoader {
    private List<OnDownloadCallBack> l;
    private int p;
    private int e;

    private GlideLoader() {
        this.l = new ArrayList();
    }

    public void init(int placeholder_id) {
        this.init(placeholder_id, 0);
    }

    public void init(int placeholder_id, int error_id) {
        this.p = placeholder_id;
        this.e = error_id;
    }

    public void get(String url, ImageView imageView) {
        this.get((Object)Object.class, url, imageView, this.p, this.e, (Transformation)null);
    }

    public void get(Object object, String url, ImageView imageView) {
        this.get((Object)object, url, imageView, this.p, this.e, (Transformation)null);
    }

    public void get(String url, ImageView imageView, int placeholder_id) {
        this.get((Object)Object.class, url, imageView, placeholder_id, this.e, (Transformation)null);
    }

    public void get(String url, ImageView imageView, int placeholder_id, int error_id) {
        this.get((Object)Object.class, url, imageView, placeholder_id, error_id, (Transformation)null);
    }

    public void get(Object object, String url, ImageView imageView, int placeholder_id) {
        this.get((Object)object, url, imageView, placeholder_id, this.e, (Transformation)null);
    }

    public void get(Object object, String url, ImageView imageView, int placeholder_id, int error_id) {
        this.get((Object)object, url, imageView, placeholder_id, error_id, (Transformation)null);
    }

    public void get(Object object, String url, ImageView imageView, Drawable placeholder_drawable) {
        this.get((Object)object, url, imageView, placeholder_drawable, null, (Transformation)null);
    }

    public void get(Object object, String url, ImageView imageView, Drawable placeholder_drawable, Drawable error_drawable) {
        this.get((Object)object, url, imageView, placeholder_drawable, error_drawable, (Transformation)null);
    }

    public void get(String url, ImageView imageView, Transformation transformation) {
        this.get((Object)Object.class, url, imageView, this.p, this.e, transformation);
    }

    public void get(Object object, String url, ImageView imageView, Transformation transformation) {
        this.get(object, url, imageView, this.p, this.e, transformation);
    }

    public void get(String url, ImageView imageView, int placeholder_id, Transformation transformation) {
        this.get((Object)Object.class, url, imageView, placeholder_id, this.e, transformation);
    }

    public void get(Object object, String url, ImageView imageView, int placeholder_id, int error_id, Transformation transformation) {
        if(object instanceof Context) {
            this.get(Glide.with((Context)object), url, imageView, placeholder_id, error_id, transformation);
        } else if(object instanceof Activity) {
            this.get(Glide.with((Activity)object), url, imageView, placeholder_id, error_id, transformation);
        } else if(object instanceof Fragment) {
            this.get(Glide.with((Fragment) object), url, imageView, placeholder_id, error_id, transformation);
        } else if(object instanceof FragmentActivity) {
            this.get(Glide.with((FragmentActivity)object), url, imageView, placeholder_id, error_id, transformation);
        } else {
            this.get(Glide.with(AppApplication.AINSTANCE), url, imageView, placeholder_id, error_id, transformation);
        }
    }

    public void get(Object object, String url, ImageView imageView, Drawable placeholder_drawable, Drawable error_drawable, Transformation transformation) {
        if(object instanceof Context) {
            this.get(Glide.with((Context)object), url, imageView, placeholder_drawable, error_drawable, transformation);
        } else if(object instanceof Activity) {
            this.get(Glide.with((Activity)object), url, imageView, placeholder_drawable, error_drawable, transformation);
        } else if (object instanceof Fragment) {
            this.get(Glide.with((Fragment) object), url, imageView, placeholder_drawable, error_drawable, transformation);
        } else if(object instanceof FragmentActivity) {
            this.get(Glide.with((FragmentActivity)object), url, imageView, placeholder_drawable, error_drawable, transformation);
        } else {
            this.get(Glide.with(AppApplication.AINSTANCE), url, imageView, placeholder_drawable, error_drawable, transformation);
        }
    }

    public void get(Object object, int resourceId, ImageView imageView) {
        this.get(object,resourceId,imageView,null);
    }

    public void get(Object object, int resourceId, ImageView imageView, Transformation transformation) {
        if(object instanceof Context) {
            this.get(Glide.with((Context)object), resourceId, imageView, this.p, this.e, transformation);
        } else if(object instanceof Activity) {
            this.get(Glide.with((Activity)object), resourceId, imageView, this.p, this.e, transformation);
        } else if(object instanceof Fragment) {
            this.get(Glide.with((Fragment) object), resourceId, imageView, this.p, this.e, transformation);
        } else if(object instanceof FragmentActivity) {
            this.get(Glide.with((FragmentActivity)object), resourceId, imageView, this.p, this.e, transformation);
        } else {
            this.get(Glide.with(AppApplication.AINSTANCE), resourceId, imageView, this.p, this.e, transformation);
        }
    }

    //加载本地图片
    @SuppressLint("CheckResult")
    private void get(RequestManager r, final int resourceId, ImageView i, int d, int e, Transformation t) {
        if(g.a(this)) {
            RequestBuilder<Drawable> requestBuilder = r.load(resourceId);

            if(t != null) {
                requestBuilder.transform(t);
            }

            requestBuilder.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .dontAnimate()
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .placeholder(d)
                    .error(e);

            requestBuilder.into(i);
        }
    }

    //加载默认图和失败图为本地图片的图片
    @SuppressLint("CheckResult")
    private void get(RequestManager r, final String u, ImageView i, int d, int e, Transformation t) {
        if(g.a(this)) {
            RequestBuilder<Drawable> requestBuilder = r.load(Http.getInstance().elog(this.getClass().toString(), u.trim()));
            if(u.contains(".gif")) {
                requestBuilder.diskCacheStrategy(DiskCacheStrategy.DATA)
                        .format(DecodeFormat.PREFER_RGB_565)
                        .placeholder(d)
                        .error(e);
            } else {
                if(t != null) {
                    requestBuilder.transform(t);
                }

                requestBuilder.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .dontAnimate()
//                        .dontTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .placeholder(d)
                        .error(e);
            }

            requestBuilder.into(i);

//            loadPathToImg(requestBuilder,i);
        }
    }

    //加载默认图和失败图为Drawable的图片
    @SuppressLint("CheckResult")
    private void get(RequestManager r, final String u, ImageView i, Drawable d, Drawable e, Transformation t) {
        if(g.a(this)) {
            RequestBuilder<Drawable> requestBuilder = r.load(Http.getInstance().elog(this.getClass().toString(), u.trim()));
            if(u.contains(".gif")) {
                requestBuilder.diskCacheStrategy(DiskCacheStrategy.DATA)
                        .format(DecodeFormat.PREFER_RGB_565)
                        .placeholder(d)
                        .error(e);
            } else {
                if(t != null) {
                    requestBuilder.transform(t);
                }

                requestBuilder.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .dontAnimate()
//                        .dontTransform()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .placeholder(d)
                        .error(e);
            }

            requestBuilder.into(i);

//            loadPathToImg(requestBuilder,i);
        }
    }

    public void intoBitmap(Context context, String imgUrl, ImageView imgView) {
        intoBitmap(context,imgUrl,imgView,0,0);
    }

    @SuppressLint("CheckResult")
    public void intoBitmap(Context context, String imgUrl, final ImageView imgView, int placeholderId, int errorId) {
        if(g.a(this)) {
            if (imgUrl.contains(".gif")){
                //https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01fab955b871a56ac725ca50a875f7.jpg&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1641026759&t=b74f2f369cb3f7ab2c53ae3e5aa80ef1
                Glide.with(context)
                        .load(Http.getInstance().elog(this.getClass().toString(), imgUrl.trim()))
                        .diskCacheStrategy(DiskCacheStrategy.DATA)
                        .format(DecodeFormat.PREFER_RGB_565)
                        .placeholder(placeholderId)
                        .error(errorId)
                        .into(new CustomTarget<Drawable>() {
                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                ScaleScreenHelperFactory.getInstance().loadViewWidthHeight(imgView,resource.getIntrinsicWidth(),resource.getIntrinsicHeight());
                                if (resource instanceof GifDrawable){
                                    ((GifDrawable) resource).setLoopCount(GifDrawable.LOOP_FOREVER);
                                    ((GifDrawable) resource).start();
                                }
                                imgView.setImageDrawable(resource);
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {

                            }
                        });
            }else {
                Glide.with(context)
                        .asBitmap()
                        .load(Http.getInstance().elog(this.getClass().toString(), imgUrl.trim()))
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .dontAnimate()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .placeholder(placeholderId)
                        .error(errorId)
                        .into(new CustomTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                ScaleScreenHelperFactory.getInstance().loadViewWidthHeight(imgView,resource.getWidth(),resource.getHeight());
                                imgView.setImageBitmap(resource);
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {
                            }
                        });
            }
        }
    }

    private void loadPathToImg(RequestBuilder<Drawable> drawableRequestBuilder, final ImageView imgView){
        drawableRequestBuilder.into(new CustomTarget<Drawable>() {
            @Override
            public void onLoadStarted(@Nullable Drawable placeholder) {
                if (placeholder != null){
                    UtilLog.e("--Glide--","onLoadStarted");
                    imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imgView.setImageDrawable(placeholder);
                }
            }

            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                UtilLog.e("--Glide--","onResourceReady");
                imgView.setScaleType(ImageView.ScaleType.FIT_XY);
                imgView.setImageDrawable(resource);
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                if (errorDrawable != null){
                    UtilLog.e("--Glide--","onLoadFailed");
                    imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imgView.setImageDrawable(errorDrawable);
                }
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
                if (placeholder != null){
                    UtilLog.e("--Glide--","onLoadCleared");
                    imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imgView.setImageDrawable(placeholder);
                }
            }
        });
    }

    public GlideLoader add(GlideLoader.OnDownloadCallBack onDownloadCallBack) {
        this.l.add(onDownloadCallBack);
        return this;
    }

    public void handler(final GlideLoader.OnEndCallBack e) {
        @SuppressLint("HandlerLeak")
        final Handler h = new Handler() {
            public void handleMessage(Message m) {
                switch(m.what) {
                    case 0:
                        GlideLoader.this.l.clear();
                        e.onEnd();
                        break;
                    case 1:
                        GlideLoader.OnDownloadCallBack d = (GlideLoader.OnDownloadCallBack)m.obj;
                        d.onDownload(d.l);
                }

            }
        };
        (new Thread() {
            public void run() {
                for(int i = 0; i < GlideLoader.this.l.size(); ++i) {
                    GlideLoader.OnDownloadCallBack d = (GlideLoader.OnDownloadCallBack)GlideLoader.this.l.get(i);

                    for(int k = 0; k < d.p.size(); ++k) {
                        try {
                            File f = new File(d.h != null?d.h: AppApplication.INSTANCE.getImageCacheFolder(), System.currentTimeMillis() + ".jpg");
                            UtilFile.copyFile(((File)Glide.with(AppApplication.INSTANCE.currentActivity()).load((String)d.p.get(k)).downloadOnly(-2147483648, -2147483648).get()).getPath(), f.getPath());
                            d.l.add(f);
                        } catch (Exception var6) {
                            ;
                        }
                    }

                    Message m = h.obtainMessage();
                    m.obj = d;
                    m.what = 1;
                    m.sendToTarget();
                }

                h.sendEmptyMessage(0);
            }
        }).start();
    }

    public static GlideLoader getInstance() {
        return GlideLoader.b.a;
    }

    private static class b {
        private static final GlideLoader a = new GlideLoader();

        private b() {
        }
    }

    public interface OnEndCallBack {
        void onEnd();
    }

    public abstract static class OnDownloadCallBack {
        List<String> p = new ArrayList();
        List<File> l = new ArrayList();
        String h;

        public OnDownloadCallBack(String... pahts) {
            this.p.addAll(Arrays.asList(pahts));
        }

        public OnDownloadCallBack(List<String> list) {
            this.p.addAll(list);
        }

        public void setFilePath(String path) {
            this.h = path;
        }

        public abstract void onDownload(List<File> var1);
    }
}

